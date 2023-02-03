package com.example.truonghoc.presentation.camera;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.truonghoc.R;
import com.example.truonghoc.databinding.ActivityCameraBinding;
import com.example.truonghoc.presentation.camera.previewfragment.CameraProviewFragment;

public class CameraActivity extends AppCompatActivity {
    private ActivityCameraBinding activityCameraBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCameraBinding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(activityCameraBinding.getRoot());
        replaceFragment(new CameraProviewFragment());
        anHienThiHeThong();
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
        super.onBackPressed();
        finish();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_noidung_view, fragment);
        transaction.commit();
    }


}