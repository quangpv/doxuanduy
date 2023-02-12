package com.example.truonghoc.presentation.camera;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.truonghoc.R;
import com.example.truonghoc.databinding.ActivityCameraBinding;
import com.example.truonghoc.presentation.camera.previewfragment.CameraPreviewFragment;

public class CameraActivity extends AppCompatActivity {
    private ActivityCameraBinding activityCameraBinding;
    private CameraViewModel cameraViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCameraBinding = ActivityCameraBinding.inflate(getLayoutInflater());
        cameraViewModel = new ViewModelProvider(this).get(CameraViewModel.class);
        setContentView(activityCameraBinding.getRoot());
        cameraViewModel.truyenMaHocSinh(layMaHocSinh());
        replaceFragment(new CameraPreviewFragment());
        anHienThiHeThong();
    }
    private String layMaHocSinh() {
        Intent intent = getIntent();
        return intent.getStringExtra("maHs");
    }

    @Override
    protected void onResume() {
        super.onResume();
        anHienThiHeThong();
    }

    private void anHienThiHeThong() {
        activityCameraBinding.fragment.fragmentNoidungView.postDelayed(
                this::heThongUi, 500L
        );
    }

    private void heThongUi() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(getWindow(), activityCameraBinding.fragment.fragmentNoidungView);
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsControllerCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            finish();
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_noidung_view, fragment);
        transaction.commit();
    }


}