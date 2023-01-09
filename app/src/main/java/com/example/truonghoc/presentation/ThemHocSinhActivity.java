package com.example.truonghoc.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.databinding.ActivityThemHocSinhBinding;
import com.example.truonghoc.domain.HocSinh;
import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.domain.KhoiLop;
import com.example.truonghoc.presentation.viewmodel.ThemHocSinhViewModel;

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
        themHocSinhBinding.thanhCongCuThem.setThemHocSinh(this);

        themHocSinhBinding.thanhCongCuThem.icLuu.setOnClickListener(v -> {
            String maHs = Objects.requireNonNull(themHocSinhBinding.maHsNhapDemo.getText()).toString();
            String tenHs = Objects.requireNonNull(themHocSinhBinding.tenHsNhapDemo.getText()).toString();
            String gioiTinh = Objects.requireNonNull(themHocSinhBinding.gioiTinhHsDemo.getText()).toString();
            String sinhNgay = Objects.requireNonNull(themHocSinhBinding.sinhNgayDemo.getText()).toString();
            String khoiLop = Objects.requireNonNull(themHocSinhBinding.lopHsNhapDemo.getText()).toString();
            viewModel.themHocSinh(maHs, tenHs, gioiTinh, sinhNgay, khoiLop);
        });

        viewModel.themThanhCong.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        });

        viewModel.themThatBai.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
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