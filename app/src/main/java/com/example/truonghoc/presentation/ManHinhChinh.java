package com.example.truonghoc.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import android.view.View;

import com.example.truonghoc.R;
import com.example.truonghoc.data.QuanLyData;
import com.example.truonghoc.databinding.ActivityManHinhChinhBinding;
import com.example.truonghoc.databinding.MenuTraiTrencungBinding;
import com.example.truonghoc.domain.ThongTinTruongHoc;
import com.example.truonghoc.presentation.fragment.GiaoVien;
import com.example.truonghoc.presentation.fragment.HocSinh;
import com.example.truonghoc.presentation.viewmodel.ManHinhChinhViewModel;

import java.util.List;


public class ManHinhChinh extends AppCompatActivity {

    public ActivityManHinhChinhBinding binding;
    private ManHinhChinhViewModel viewModel;
    private MenuTraiTrencungBinding bindingheader;

    final int fragmentHocSinh = 0;
    final int fragmentGiaoVien = 1;
    int checkFragent = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManHinhChinhBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
        bindingheader = MenuTraiTrencungBinding.bind(binding.navView.getHeaderView(0));
        bindingheader.setManHinhChinh(this);
//
        viewModel = new ViewModelProvider(this).get(ManHinhChinhViewModel.class);
        binding.includedThanhCongCu.setManHinhChinhViewModel(viewModel);
        binding.includedThanhCongCu.setManHinhChinh(this);
        fragmentMacDich();
        xuLyAnMenuTrai();

    }


    private void fragmentMacDich() {
        setFragmentView(1, fragmentHocSinh, R.string.tieude_hoc_sinh);
    }


    public void setCauHinhHeader(boolean value) {
        bindingheader.tenTruongView.setEnabled(value);
        bindingheader.diachiTruongView.setEnabled(value);
        bindingheader.sdtTruongView.setEnabled(value);
        if (value) {
            bindingheader.suaInfoDaumenutrai.setVisibility(View.INVISIBLE);
            bindingheader.luuInfoDaumenutrai.setVisibility(View.VISIBLE);
        } else {
            bindingheader.suaInfoDaumenutrai.setVisibility(View.VISIBLE);
            bindingheader.luuInfoDaumenutrai.setVisibility(View.INVISIBLE);
            luuThongTinTruong();
        }
    }

    public void anTimKiem() {
        binding.includedThanhCongCu.timKiem.setVisibility(View.INVISIBLE);
        binding.includedThanhCongCu.xoaTimKiem.setVisibility(View.VISIBLE);
        binding.includedThanhCongCu.tieuDe.setVisibility(View.INVISIBLE);
        binding.includedThanhCongCu.noidungTimKiem.setVisibility(View.VISIBLE);
    }
    public void anXoaTimKiem() {
        binding.includedThanhCongCu.timKiem.setVisibility(View.VISIBLE);
        binding.includedThanhCongCu.xoaTimKiem.setVisibility(View.INVISIBLE);
        binding.includedThanhCongCu.tieuDe.setVisibility(View.VISIBLE);
        binding.includedThanhCongCu.noidungTimKiem.setVisibility(View.INVISIBLE);
        binding.includedThanhCongCu.noidungTimKiem.setText(null);
    }

    public void openMenuTrai() {
        binding.drawerLayoutChinh.openDrawer(GravityCompat.START);
    }

    private void luuThongTinTruong() {
        String tenTruong = bindingheader.tenTruongView.getText().toString();
        String diaChi = bindingheader.diachiTruongView.getText().toString();
        String sdtTruong = bindingheader.sdtTruongView.getText().toString();
        ThongTinTruongHoc thongTin = new ThongTinTruongHoc(tenTruong, diaChi, sdtTruong);
        QuanLyData.setThongTinTruong(thongTin);
    }


    private void xuLyAnMenuTrai() {
        binding.navView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_hoc_sinh) {
                if (checkFragent != fragmentHocSinh) {
                    setFragmentView(1, fragmentHocSinh, R.string.tieude_hoc_sinh);
                }
            }
            if (item.getItemId() == R.id.nav_giao_vien) {
                if (checkFragent != fragmentGiaoVien) {
                    setFragmentView(2, fragmentGiaoVien, R.string.tieude_giao_vien);
                }
            }
            binding.drawerLayoutChinh.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void setFragmentView(int loaiFragment, int tenFragment, int tieuDe2) {
        viewModel.tieuDeTCC().postValue(getResources().getString(tieuDe2));
        viewModel.tieuDeTCC().observe(this, s -> binding.includedThanhCongCu.tieuDe.setText(s));
        checkFragent = tenFragment;
        if (loaiFragment == 1) {
            replaceFragment(new HocSinh());
        }
        if (loaiFragment == 2) {
            replaceFragment(new GiaoVien());
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_noidung_view, fragment);
        transaction.commit();
    }


}