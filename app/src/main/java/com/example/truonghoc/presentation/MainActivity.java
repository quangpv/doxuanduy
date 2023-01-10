package com.example.truonghoc.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import com.example.truonghoc.R;
import com.example.truonghoc.data.QuanLyData;
import com.example.truonghoc.databinding.ActivityMainBinding;
import com.example.truonghoc.databinding.MenuTraiTrencungBinding;
import com.example.truonghoc.domain.ThongTinTruongHoc;
import com.example.truonghoc.presentation.fragment.GiaoVienFragment;
import com.example.truonghoc.presentation.fragment.HocSinhFragment;
import com.example.truonghoc.presentation.viewmodel.MainViewModel;


public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;
    private MainViewModel viewModel;
    private MenuTraiTrencungBinding bindingheader;

    final int fragmentHocSinh = 0;
    final int fragmentGiaoVien = 1;
    int checkFragent = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.includedThanhCongCu.icMenuTraiOpen.setOnClickListener(v->openMenuTrai());
        binding.includedThanhCongCu.timKiem.setOnClickListener(v->anTimKiem());
        binding.includedThanhCongCu.xoaTimKiem.setOnClickListener(v->anXoaTimKiem());
        binding.navView.setNavigationItemSelectedListener(this::xuLyAnMenuTrai);

        bindingheader = MenuTraiTrencungBinding.bind(binding.navView.getHeaderView(0));
        bindingheader.suaInfoDaumenutrai.setOnClickListener(v->setCauHinhHeader(true));
        bindingheader.luuInfoDaumenutrai.setOnClickListener(v->setCauHinhHeader(false));
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        fragmentMacDich();

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
        layThongTinTruong();
    }

    private void layThongTinTruong() {
        viewModel.thongTinTruong().postValue(QuanLyData.layThongTinTruong());
        viewModel.thongTinTruong().observe(this, info -> {
            bindingheader.tenTruongView.setText(info.getTenTruong());
            bindingheader.diachiTruongView.setText(info.getDiaChiTruong());
            bindingheader.sdtTruongView.setText(info.getSdtTruong());
        });

    }

    private void luuThongTinTruong() {
        String tenTruong = bindingheader.tenTruongView.getText().toString();
        String diaChi = bindingheader.diachiTruongView.getText().toString();
        String sdtTruong = bindingheader.sdtTruongView.getText().toString();
        viewModel.luuThongTinTruong(tenTruong,diaChi,sdtTruong);
    }


    private boolean xuLyAnMenuTrai(MenuItem item) {
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
    }

    private void setFragmentView(int loaiFragment, int tenFragment, int tieuDe2) {
        viewModel.tieuDeTCC().postValue(getResources().getString(tieuDe2));
        viewModel.tieuDeTCC().observe(this, s -> binding.includedThanhCongCu.tieuDe.setText(s));
        checkFragent = tenFragment;
        if (loaiFragment == 1) {
            replaceFragment(new HocSinhFragment());
        }
        if (loaiFragment == 2) {
            replaceFragment(new GiaoVienFragment());
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_noidung_view, fragment);
        transaction.commit();
    }


}