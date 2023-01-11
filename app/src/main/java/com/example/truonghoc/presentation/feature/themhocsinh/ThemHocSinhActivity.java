package com.example.truonghoc.presentation.feature.themhocsinh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.truonghoc.databinding.ActivityThemHocSinhBinding;

import java.util.Objects;

public class ThemHocSinhActivity extends AppCompatActivity {
    ActivityThemHocSinhBinding themHocSinhBinding;
    private ThemHocSinhViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themHocSinhBinding = ActivityThemHocSinhBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(ThemHocSinhViewModel.class);
        setContentView(themHocSinhBinding.getRoot());
        themHocSinhBinding.thanhCongCuThem.icBack.setOnClickListener(v -> xuLyKhiAnBack());
        themHocSinhBinding.thanhCongCuThem.icLuu.setOnClickListener(v -> themHocSinh());
        viewModel.themThanhCong.observe(this, message -> {
            thongBaoThemHocSinh(message);
            finish();
        });

        viewModel.themThatBai.observe(this, this::thongBaoThemHocSinh);
    }

    private void thongBaoThemHocSinh(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
                .setPositiveButton("No", (dialog, which) -> {

                }).show();
    }
}