package com.example.truonghoc.presentation.feature.themhocsinh;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.truonghoc.databinding.ActivityThemHocSinhBinding;
import com.example.truonghoc.presentation.camera.CameraActivity;
import com.example.truonghoc.presentation.helper.AppPermission;

import java.util.Objects;

public class ThemHocSinhActivity extends AppCompatActivity {
    public ActivityThemHocSinhBinding themHocSinhBinding;
    private ThemHocSinhViewModel viewModel;
    private final AppPermission appPermission = AppPermission.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themHocSinhBinding = ActivityThemHocSinhBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(ThemHocSinhViewModel.class);
        setContentView(themHocSinhBinding.getRoot());
        themHocSinhBinding.thanhCongCuThem.icBack.setOnClickListener(v -> xuLyKhiAnBack());
        themHocSinhBinding.thanhCongCuThem.icLuu.setOnClickListener(v -> themHocSinh());
        themHocSinhBinding.chupAnh.setOnClickListener(v -> moCamera());
        viewModel.themThanhCong.observe(this, message -> {
            thongBaoToast(message);
            finish();
        });
        viewModel.themThatBai.observe(this, this::thongBaoToast);

    }

    private final ActivityResultLauncher<Intent> layAnh =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == 20) {
                    if (result.getData() != null) {
                        Intent intent = result.getData();
                        String uri = intent.getStringExtra("image");
                        hienThiAnhThuNho(uri);
                    }
                }
            });

    private void hienThiAnhThuNho(String uri) {
        Glide.with(themHocSinhBinding.avatar)
                .load(uri)
                .override(100,100)
                .centerCrop()
                .into(themHocSinhBinding.avatar);
    }


    private void thongBaoToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void moCamera() {
        if (appPermission.checkQuyenCamera()) {
            moCameRa();
        } else {
            phanHoiCapQuyen.launch(appPermission.dsQuyenCamera().toArray(new String[0]));
        }
    }

    private final ActivityResultLauncher<String[]> phanHoiCapQuyen =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                if (appPermission.checkQuyenCamera()) {
                    moCameRa();
                } else {
                    thongBaoToast("Có Quyền Chưa Kích Hoạt");
                }
            });

    private void moCameRa() {
        thongBaoToast("Open Camera");
        Intent intent = new Intent(this, CameraActivity.class);
        layAnh.launch(intent);
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