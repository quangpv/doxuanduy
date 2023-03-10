package com.example.quanlythuvien.view.feature.main;


import android.os.Bundle;


import com.example.quanlythuvien.databinding.ActivityMainBinding;

import com.example.quanlythuvien.view.base.BaseActivity;
import com.example.quanlythuvien.view.feature.menu.GiaoDienChinh;
import com.example.quanlythuvien.view.help.OpenActivity;


public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setBiding(ActivityMainBinding::inflate);
        viewModel = setViewModel(MainViewModel.class);
        viewModel.langNgheEditText(binding.nhapTK, (s, iThuThuSet) -> iThuThuSet.setTk(s));
        viewModel.langNgheEditText(binding.nhapMk, (s, iThuThuSet) -> iThuThuSet.setMK(s));
        viewModel.thongBao.observe(this, this::toast);
        viewModel.thongBaoThanhCong.observe(this, s -> {
            toast(s);
            OpenActivity.open(this, GiaoDienChinh.class,null);
        });
        binding.dangki.setOnClickListener(v -> viewModel.dangNhapVaDangKi(true));
        binding.dangNhap.setOnClickListener(v -> viewModel.dangNhapVaDangKi(false));
    }


}