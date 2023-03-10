package com.example.quanlythuvien.view.feature.sach.infosach;


import android.os.Bundle;
import android.view.View;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityInfoSachBinding;
import com.example.quanlythuvien.domain.bo.SachMoi;
import com.example.quanlythuvien.domain.ui.ISach;
import com.example.quanlythuvien.domain.ui.ISachGet;
import com.example.quanlythuvien.view.base.BaseActivity;
import com.example.quanlythuvien.view.help.DiaLogFactory;


public class InfoSachActivity extends BaseActivity {
    ActivityInfoSachBinding binding;
    InfoSachViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setBiding(ActivityInfoSachBinding::inflate);
        viewModel = setViewModel(InfoSachViewModel.class);
        viewModel.setId(getIntent());
        viewModel.sach.observe(this, this::setView);
        binding.thanhCongCuTop.icBack.setOnClickListener(v -> onBackPressed());
        binding.thanhCongCuTop.tieuDe.setText(R.string.tieude_thongtin_sach);
        binding.thanhCongCuTop.icSua.setOnClickListener(this::xuLySuaSach);

        viewModel.langNgheEditText(binding.tensachNhap, (s, iSach) -> iSach.setTenSach(s));
        viewModel.langNgheEditText(binding.loaisachNhap, (s, iSach) -> iSach.setLoaiSach(s));
        viewModel.langNgheEditText(binding.tentacgiaNhap, (s, iSach) -> iSach.setTenTacGia(s));
        viewModel.langNgheEditText(binding.namxbNhap, (s, iSach) -> iSach.setNamXuatBan(s));
        viewModel.langNgheEditText(binding.nxbNhap, (s, iSach) -> iSach.setNhaXuatBan(s));
        viewModel.langNgheEditText(binding.tong, (s, iSach) -> iSach.setTongSach(Integer.parseInt(s)));
    }

    private void xuLySuaSach(View v) {
        v.setSelected(!v.isSelected());
        if (v.isSelected()) {
            viewModel.batSuaSach();
            return;
        }
        if (!viewModel.daCoSua()) {
            viewModel.tatSuaSach();
            return;
        }
        DiaLogFactory.showDialog(this, "Sửa Học Sinh Này ?"
                , () -> viewModel.luuHocSinh()
                , () -> viewModel.tatSuaSach()).show();
    }

    @Override
    public void onBackPressed() {
        if (viewModel.daCoSua())
            DiaLogFactory.showDialog(this, "Hủy Sửa Thông Tin ?", this::finish, () -> {}).show();
        else finish();
    }

    private void setView(ISach iSach) {
        if (iSach instanceof ISachGet) {
            ISachGet iSachGet = (ISachGet) iSach;
            binding.tensachNhap.setText(iSachGet.getTenSach());
            binding.loaisachNhap.setText(iSachGet.getLoaiSach());
            binding.tentacgiaNhap.setText(iSachGet.getTenTacGia());
            binding.nxbNhap.setText(iSachGet.getNhaXuatBan());
            binding.namxbNhap.setText(iSachGet.getNamXuatBan());
            binding.tong.setText(String.valueOf(iSachGet.getTongSach()));
            binding.chothue.setText(String.valueOf(iSachGet.getChoThue()));
            binding.tonSach.setText(String.valueOf((iSachGet.getTongSach() - iSachGet.getChoThue())));
        }
        setSuaView(iSach instanceof SachMoi);

    }

    private void setSuaView(Boolean value) {
        binding.tensachNhap.setEnabled(value);
        binding.loaisachNhap.setEnabled(value);
        binding.tentacgiaNhap.setEnabled(value);
        binding.namxbNhap.setEnabled(value);
        binding.nxbNhap.setEnabled(value);
        binding.tentacgiaNhap.setEnabled(value);
        binding.tong.setEnabled(value);
        binding.chothue.setEnabled(value);
    }
}
