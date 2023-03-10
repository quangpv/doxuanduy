package com.example.quanlythuvien.view.feature.sach.addsach;

import android.os.Bundle;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityAddSachBinding;
import com.example.quanlythuvien.view.base.BaseActivity;
import com.example.quanlythuvien.view.help.DiaLogFactory;

public class AddSachActivity extends BaseActivity {
    ActivityAddSachBinding binding;
    AddSachViewModel viewModel;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DiaLogFactory.showDialog(this, "Hủy Thêm?", this::finish, () -> {
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setBiding(ActivityAddSachBinding::inflate);
        viewModel = setViewModel(AddSachViewModel.class);
        binding.thanhCongCuTop.tieuDe.setText(R.string.tieude_themsach);
        binding.thanhCongCuTop.icSua.setSelected(true);
        binding.thanhCongCuTop.icSua.setOnClickListener(v-> viewModel.themSach());
        binding.thanhCongCuTop.icBack.setOnClickListener(v -> onBackPressed());
        viewModel.langNgheEditText(binding.masachNhap, (s, iSach) -> iSach.setMaSach(s));
        viewModel.langNgheEditText(binding.tensachNhap, (s, iSach) -> iSach.setTenSach(s));
        viewModel.langNgheEditText(binding.loaisachNhap, (s, iSach) -> iSach.setLoaiSach(s));
        viewModel.langNgheEditText(binding.tentacgiaNhap, (s, iSach) -> iSach.setTenTacGia(s));
        viewModel.langNgheEditText(binding.namxbNhap, (s, iSach) -> iSach.setNamXuatBan(s));
        viewModel.langNgheEditText(binding.nxbNhap, (s, iSach) -> iSach.setNhaXuatBan(s));
        viewModel.langNgheEditText(binding.tongNhap, (s, iSach) -> iSach.setTongSach(Integer.parseInt(s)));
        viewModel.thongBao.observe(this, this::toast);
        viewModel.thongBaoThanhCong.observe(this, s -> {
            toast(s);
            finish();
        });
    }

}