package com.example.truonghoc.presentation.feature.themanh;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.lifecycle.ProcessCameraProvider;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.truonghoc.databinding.FragmentThemAnhBinding;
import com.example.truonghoc.presentation.helper.AppPermission;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.common.util.concurrent.ListenableFuture;


public class ThemAnhFragment extends BottomSheetDialogFragment {
    private FragmentThemAnhBinding themAnhBinding;
    private final AppPermission appPermission = AppPermission.getInstance();
    private ActivityResultLauncher<String[]> requestPermissionLauncher;
    private final Context context = getContext();
//    private final static int REQUEST_CODE_PERMISSIONS = 10;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        themAnhBinding = FragmentThemAnhBinding.inflate(inflater, container, false);
        return themAnhBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        themAnhBinding.cameraAdd.setOnClickListener(v -> khoiDongCamera());
        phanHoiCapQuyen();
        super.onViewCreated(view, savedInstanceState);
    }


    private void khoiDongCamera() {
        if (appPermission.checkQuyenCameRa()) {
            moCameRa();
        } else {
            requestPermissionLauncher.launch(appPermission.dsQuyenCamera().toArray(new String[0]));
        }
    }


    private void phanHoiCapQuyen() {
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            if (result.containsValue(true)) {
                moCameRa();
            } else {
                Toast.makeText(getActivity(), "Có Quyền Chưa Kích Hoạt", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void moCameRa() {
        ListenableFuture<ProcessCameraProvider> cameraProview = ProcessCameraProvider.getInstance(getContext());

    }
}