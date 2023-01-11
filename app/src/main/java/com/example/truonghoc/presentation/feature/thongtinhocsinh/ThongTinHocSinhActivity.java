package com.example.truonghoc.presentation.feature.thongtinhocsinh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.truonghoc.databinding.ActivityManHinhThongTinHocSinhBinding;
import com.example.truonghoc.domain.HocSinhDangHoc;

public class ThongTinHocSinhActivity extends AppCompatActivity {
    ActivityManHinhThongTinHocSinhBinding thongTinBinding;
    boolean checkEdit;
    HocSinhDangHoc hocSinh;
    ThongTinHocSinhViewModel thongTinHocSinhViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thongTinBinding = ActivityManHinhThongTinHocSinhBinding.inflate(getLayoutInflater());
        setContentView(thongTinBinding.getRoot());
        thongTinHocSinhViewModel = new ViewModelProvider(this).get(ThongTinHocSinhViewModel.class);

        thongTinBinding.thanhCongCuThemView.icBack.setOnClickListener(v -> xuLyAnBack());
        thongTinBinding.thanhCongCuThemView.icLuu.setOnClickListener(v -> setSuaThongTin(false));
        thongTinBinding.thanhCongCuThemView.icSua.setOnClickListener(v -> setSuaThongTin(true));

        hienThiThongTin();
    }

    private void hienThiThongTin() {
        Intent intent = getIntent();
        hocSinh = intent.getParcelableExtra("HS");
        thongTinBinding.thanhCongCuThemView.tieuDe.setText(hocSinh.getHocSinh().getMaHocSinh());
        thongTinBinding.tenHsView.setText(hocSinh.getHocSinh().getHoVaTen());
        thongTinBinding.gioiTinhView.setText(hocSinh.getHocSinh().getGioiTinh());
        thongTinBinding.sinhNgayView.setText(hocSinh.getHocSinh().getSinhNgay());
        thongTinBinding.lopHsView.setText(hocSinh.getKhoiLop().getKhoiLop());
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
            if (thongTinBinding.tenHsView.getText().toString().isEmpty()) {
                Toast.makeText(this, "Bạn Chưa Nhập Tên", Toast.LENGTH_SHORT).show();
                setSuaThongTin(true);
                return;
            }
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
        thongTinHocSinhViewModel.capNhapLaiThongTinHocSinh(hocSinh);
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