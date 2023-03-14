package com.example.quanlythuvien.view.feature.sach.addsach;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;
import androidx.lifecycle.Observer;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityAddSachBinding;
import com.example.quanlythuvien.domain.bo.SachMoi;
import com.example.quanlythuvien.domain.ui.IKiemTraGiaTriNhap;
import com.example.quanlythuvien.domain.ui.ISach;
import com.example.quanlythuvien.domain.ui.ISachGet;
import com.example.quanlythuvien.view.base.BaseActivity;
import com.example.quanlythuvien.view.help.DiaLogFactory;
import com.example.quanlythuvien.view.help.HienThiView;
import com.example.quanlythuvien.view.help.LangNgheSuKien;

public class AddSachActivity extends BaseActivity {
    ActivityAddSachBinding binding;
    AddSachViewModel viewModel;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DiaLogFactory.showDialog(this, "Hủy Thêm?", this::finish, () -> {
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
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
        viewModel.thongBao.observe(this, throwable -> toast(throwable.getMessage()));
        viewModel.sach.observe(this, iSach -> {
            ISachGet iSachGet = iSach instanceof SachMoi ? (ISachGet)iSach:null;
            if(iSachGet==null) return;
            LangNgheSuKien.theoDoiGiaTriNhap(this, iSachGet.getMaSach(),
                    iKiemTra -> HienThiView.hienThiTextLoi(binding.masach,iKiemTra));
            LangNgheSuKien.theoDoiGiaTriNhap(this, iSachGet.getTenSach(),
                    iKiemTra -> HienThiView.hienThiTextLoi(binding.tensach,iKiemTra));
        });
        viewModel.thongBaoThanhCong.observe(this, s -> {
            toast(s);
            finish();
        });
    }

}