package com.example.truonghoc.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.databinding.ActivityThemHocSinhBinding;
import com.example.truonghoc.domain.HocSinh;
import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.domain.KhoiLop;

import java.util.Objects;

public class ThemHocSinh extends AppCompatActivity {
    ActivityThemHocSinhBinding themHocSinhBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themHocSinhBinding = ActivityThemHocSinhBinding.inflate(getLayoutInflater());
        setContentView(themHocSinhBinding.getRoot());
        themHocSinhBinding.thanhCongCuThem.setThemHocSinh(this);
    }

    public void xuLyAnLuu() {
        String maHs = Objects.requireNonNull(themHocSinhBinding.maHsNhapDemo.getText()).toString();
        String tenHs = Objects.requireNonNull(themHocSinhBinding.tenHsNhapDemo.getText()).toString();
        if (checkThongTinToiThieu(maHs, tenHs)) {
            Toast.makeText(this, "Tối Thiểu Cần Tên Và Mã Hs", Toast.LENGTH_SHORT).show();
        } else {
            if (kiemTraMaHocSinh(maHs)) {
                Toast.makeText(this, "Mã Học Sinh Đã Tồn Tại", Toast.LENGTH_SHORT).show();
            } else {
                themHocSinh(maHs, tenHs);
                finish();
            }
        }
    }

    private void themHocSinh(String maHs, String tenHs) {
        String gioiTinh = Objects.requireNonNull(themHocSinhBinding.gioiTinhHsDemo.getText()).toString();
        String sinhNgay = Objects.requireNonNull(themHocSinhBinding.sinhNgayDemo.getText()).toString();
        String khoiLop = Objects.requireNonNull(themHocSinhBinding.lopHsNhapDemo.getText()).toString();
        HocSinhDangHoc hocSinh = new HocSinhDangHoc(new HocSinh(maHs, tenHs, gioiTinh, sinhNgay), new KhoiLop(khoiLop));
        HocSinhDangHocDataBase.quanLyData(this).hocSinhDAO().themHocSinh(hocSinh);
    }

    private boolean kiemTraMaHocSinh(String maHs) {
        return HocSinhDangHocDataBase.quanLyData(this).hocSinhDAO().kiemTraHocSinh(maHs);
    }

    private boolean checkThongTinToiThieu(String maHs, String tenHs) {
        return maHs.isEmpty() || tenHs.isEmpty();
    }

    @Override
    public void onBackPressed() {
        xuLyKhiAnBack();
    }

    public void xuLyKhiAnBack() {
        AlertDialog.Builder dialogExit = new AlertDialog.Builder(this);
        dialogExit.setMessage("Hủy Thêm ?");
        dialogExit.setNegativeButton("Yes", (dialog, which) -> finish());
        dialogExit.setPositiveButton("No", (dialog, which) -> {
        });
        dialogExit.show();
    }
}