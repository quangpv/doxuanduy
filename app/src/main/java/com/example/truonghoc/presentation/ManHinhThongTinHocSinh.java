package com.example.truonghoc.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.databinding.ActivityManHinhThongTinHocSinhBinding;
import com.example.truonghoc.domain.HocSinh;
import com.example.truonghoc.domain.HocSinhDangHoc;

public class ManHinhThongTinHocSinh extends AppCompatActivity {
    ActivityManHinhThongTinHocSinhBinding thongTinBinding;
    boolean checkEdit;
    HocSinhDangHoc hocSinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thongTinBinding = ActivityManHinhThongTinHocSinhBinding.inflate(getLayoutInflater());
        setContentView(thongTinBinding.getRoot());
        //
        Intent intent = getIntent();
        hocSinh = intent.getParcelableExtra("HS");
        thongTinBinding.setHocSinhDangHoc(hocSinh);
        //
        thongTinBinding.thanhCongCuThemView.setHocSinhDangHoc(hocSinh);
        thongTinBinding.thanhCongCuThemView.setManHinhThongTin(this);
    }



    public void setSuaThongTin(boolean value) {
        checkEdit = value;
        thongTinBinding.gioiTinhView.setEnabled(value);
        thongTinBinding.tenHsView.setEnabled(value);
        thongTinBinding.sinhNgayView.setEnabled(value);
        thongTinBinding.lopHsView.setEnabled(value);
        if (value) {
            thongTinBinding.thanhCongCuThemView.icSua.setVisibility(View.INVISIBLE);
            thongTinBinding.thanhCongCuThemView.icLuu.setVisibility(View.VISIBLE);
        } else {
            thongTinBinding.thanhCongCuThemView.icSua.setVisibility(View.VISIBLE);
            thongTinBinding.thanhCongCuThemView.icLuu.setVisibility(View.INVISIBLE);
            luuThongTinHocSinh();
        }
    }

    private void luuThongTinHocSinh() {
        hocSinh.getHocSinh().setHoVaTen(thongTinBinding.tenHsView.getText().toString());
        hocSinh.getHocSinh().setGioiTinh(thongTinBinding.gioiTinhView.getText().toString());
        hocSinh.getHocSinh().setSinhNgay(thongTinBinding.sinhNgayView.getText().toString());
        hocSinh.getKhoiLop().setKhoiLop(thongTinBinding.lopHsView.getText().toString());
        HocSinhDangHocDataBase.quanLyData(this).hocSinhDAO().suaHocSinh(hocSinh);
    }

    public void xuLyAnBack() {
        if (checkEdit) {
            hienThiDiaLog();
        } else {
            finish();
        }
    }

    private void hienThiDiaLog() {
        AlertDialog.Builder dialogExit = new AlertDialog.Builder(this);
        dialogExit.setMessage("Hủy Sửa Thông Tin ?");
        dialogExit.setNegativeButton("Yes", (dialog, which) -> finish());
        dialogExit.setPositiveButton("No", (dialog, which) -> {
        });
        dialogExit.show();
    }

    @Override
    public void onBackPressed() {
        xuLyAnBack();
    }
}