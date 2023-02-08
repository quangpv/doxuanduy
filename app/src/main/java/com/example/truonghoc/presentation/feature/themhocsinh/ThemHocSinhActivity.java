package com.example.truonghoc.presentation.feature.themhocsinh;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.truonghoc.R;
import com.example.truonghoc.databinding.ActivityThemHocSinhBinding;

import com.example.truonghoc.presentation.camera.CameraActivity;
import com.example.truonghoc.presentation.dialog.addavatar.AddAvatarBottomSheetFragment;
import com.example.truonghoc.presentation.helper.AppFileManager;

import java.util.Objects;

public class ThemHocSinhActivity extends AppCompatActivity {
    public ActivityThemHocSinhBinding themHocSinhBinding;
    private ThemHocSinhViewModel viewModel;
    private final AppFileManager appFileManager = AppFileManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themHocSinhBinding = ActivityThemHocSinhBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(ThemHocSinhViewModel.class);
        setContentView(themHocSinhBinding.getRoot());
        themHocSinhBinding.thanhCongCuThem.icBack.setOnClickListener(v -> xuLyKhiAnBack());
        themHocSinhBinding.thanhCongCuThem.icLuu.setOnClickListener(v -> themHocSinh());
        themHocSinhBinding.avatar.setOnClickListener(v -> moDiaLogCamera());
        viewModel.themThanhCong.observe(this, message -> {
            thongBaoToast(message);
            finish();
        });
        viewModel.themThatBai.observe(this, this::thongBaoToast);
        appFileManager.tenAnhTamThoi.observe(this, this::timVaHienThiAnhThuNho);

    }

    private void moDiaLogCamera() {
        AddAvatarBottomSheetFragment diaLog = new AddAvatarBottomSheetFragment();
        diaLog.show(getSupportFragmentManager(),diaLog.getTag());
    }

    private void timVaHienThiAnhThuNho(String s) {
      Uri uri = Uri.parse(Uri.fromFile(appFileManager.layThuMucAnhTamThoi())+"/"+s+".jpg");
        Glide.with(themHocSinhBinding.avatar)
                .load(uri)
                .override(100,100)
                .placeholder(R.drawable.avatardemo)
                .error(R.drawable.errorloadimg)
                .centerCrop()
                .into(themHocSinhBinding.avatar);
    }
    private void thongBaoToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void themHocSinh() {
        String maHs = Objects.requireNonNull(themHocSinhBinding.maHsNhapDemo.getText()).toString();
        String tenHs = Objects.requireNonNull(themHocSinhBinding.tenHsNhapDemo.getText()).toString();
        String gioiTinh = Objects.requireNonNull(themHocSinhBinding.gioiTinhHsDemo.getText()).toString();
        String sinhNgay = Objects.requireNonNull(themHocSinhBinding.sinhNgayDemo.getText()).toString();
        String khoiLop = Objects.requireNonNull(themHocSinhBinding.lopHsNhapDemo.getText()).toString();
        viewModel.themHocSinh(maHs, tenHs, gioiTinh, sinhNgay, khoiLop);
    }

    @Override
    public void onBackPressed() {
        xuLyKhiAnBack();
    }

    public void xuLyKhiAnBack() {
        new AlertDialog.Builder(this)
                .setMessage("Hủy Thêm ?")
                .setNegativeButton("Yes", (dialog, which) -> finish())
                .setPositiveButton("No", (dialog, which) -> {}).show();
    }
}