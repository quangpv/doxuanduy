package com.example.truonghoc.presentation.helper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

public class PermissionSupporter {
    private static final String[] CAMERA = new String[]{
            Manifest.permission.CAMERA
    };
    private final LifecycleOwner owner;

    public PermissionSupporter(LifecycleOwner owner) {
        this.owner = owner;
    }

    public View.OnClickListener accessCamera(View.OnClickListener action) {
        PermissionRequest request = new PermissionRequest(CAMERA, (isAccessible) -> {
            if (isAccessible) action.onClick(null);
        });
        return v -> request.run();
    }

    public View.OnClickListener accessFile(View.OnClickListener action) {
        return action;
    }

    class PermissionRequest {
        private final String[] permissions;
        private final Consumer<Boolean> consumer;
        private ActivityResultLauncher<String[]> mLauncher = null;

        public PermissionRequest(String[] permissions, Consumer<Boolean> consumer) {
            this.permissions = permissions;
            this.consumer = consumer;
            if (owner instanceof ActivityResultCaller) {
                mLauncher = ((ActivityResultCaller) owner).registerForActivityResult(
                        new ActivityResultContracts.RequestMultiplePermissions(),
                        (result) -> {

                            boolean isAllAccessible = true;
                            for (Boolean value : result.values()) {
                                isAllAccessible = isAllAccessible && value;
                            }
                            consumer.accept(isAllAccessible);
                        }
                );
            }
        }

        public void run() {
            Context context = null;
            if (owner instanceof Fragment) {
                context = ((Fragment) owner).requireContext();
            } else {
                if (owner instanceof Context) context = (Context) owner;
            }
            if (context == null) {
                return;
            }

            if (isAllAccessible(context)) {
                consumer.accept(true);
                return;
            }

            if (mLauncher != null) mLauncher.launch(this.permissions);
        }

        private boolean isAllAccessible(Context context) {
            for (String permission : this.permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
    }
}
