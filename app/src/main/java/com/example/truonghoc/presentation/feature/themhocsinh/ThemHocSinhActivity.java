package com.example.truonghoc.presentation.feature.themhocsinh;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

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
        themHocSinhBinding.chupAnh.setOnClickListener(v-> moCamera());
        viewModel.themThanhCong.observe(this, message -> {
            thongBaoThemHocSinh(message);
            finish();
        });
        viewModel.themThatBai.observe(this, this::thongBaoThemHocSinh);

    }

       private ActivityResultLauncher<Intent> layAnh = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
           @Override
           public void onActivityResult(ActivityResult result) {

           }
       });




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
                Toast.makeText(this, "Có Quyền Chưa Kích Hoạt", Toast.LENGTH_SHORT).show();
            }
        });

    private void moCameRa() {
        Toast.makeText(this, "Mở Máy Ảnh", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,CameraActivity.class);
        layAnh.launch(intent);
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