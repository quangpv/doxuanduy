package com.example.truonghoc.presentation.feature.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import com.example.truonghoc.R;

import com.example.truonghoc.databinding.ActivityMainBinding;
import com.example.truonghoc.databinding.MenuTraiTrencungBinding;
import com.example.truonghoc.domain.ThongTinTruongHoc;
import com.example.truonghoc.presentation.feature.giaovien.GiaoVienFragment;
import com.example.truonghoc.presentation.feature.hocsinh.HocSinhFragment;


public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;
    private MainViewModel viewModel;
    private MenuTraiTrencungBinding bindingHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.includedThanhCongCu.icMenuTraiOpen.setOnClickListener(v -> openMenuTrai());
        binding.includedThanhCongCu.timKiem.setOnClickListener(v -> anTimKiem());
        binding.includedThanhCongCu.xoaTimKiem.setOnClickListener(v -> anXoaTimKiem());

        binding.navView.setNavigationItemSelectedListener(this::xuLyAnMenuTrai);

        bindingHeader = MenuTraiTrencungBinding.bind(binding.navView.getHeaderView(0));

        bindingHeader.suaInfoDaumenutrai.setOnClickListener(v -> {
            setCauHinhHeader(true);
            luuThongTinTruong();
        });

        bindingHeader.luuInfoDaumenutrai.setOnClickListener(v -> setCauHinhHeader(false));

        viewModel.tieuDeTCC.observe(this, s -> {
            binding.includedThanhCongCu.tieuDe.setText(s);
        });

        viewModel.thongTinTruongHoc.observe(this, info -> {
            bindingHeader.tenTruongView.setText(info.getTenTruong());
            bindingHeader.diachiTruongView.setText(info.getDiaChiTruong());
            bindingHeader.sdtTruongView.setText(info.getSdtTruong());
        });

        viewModel.layThongTinTruong();

        caiDatFragmentMacDich();
    }

    private void caiDatFragmentMacDich() {
        showFragmentByMenuId(R.id.nav_hoc_sinh);
    }

    public void setCauHinhHeader(boolean value) {
        bindingHeader.tenTruongView.setEnabled(value);
        bindingHeader.diachiTruongView.setEnabled(value);
        bindingHeader.sdtTruongView.setEnabled(value);
        if (value) {
            bindingHeader.suaInfoDaumenutrai.setVisibility(View.INVISIBLE);
            bindingHeader.luuInfoDaumenutrai.setVisibility(View.VISIBLE);
        } else {
            bindingHeader.suaInfoDaumenutrai.setVisibility(View.VISIBLE);
            bindingHeader.luuInfoDaumenutrai.setVisibility(View.INVISIBLE);
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
        String tenTruong = bindingHeader.tenTruongView.getText().toString();
        String diaChi = bindingHeader.diachiTruongView.getText().toString();
        String sdtTruong = bindingHeader.sdtTruongView.getText().toString();
        viewModel.luuThongTinTruongHoc(new ThongTinTruongHoc(tenTruong, diaChi, sdtTruong));
    }

    private boolean xuLyAnMenuTrai(MenuItem item) {
        showFragmentByMenuId(item.getItemId());
        binding.drawerLayoutChinh.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragmentByMenuId(int itemId) {
        Fragment fragment = getFragmentByNavId(itemId);
        viewModel.setLoai(itemId);
        replaceFragment(fragment);
    }

    private Fragment getFragmentByNavId(int itemId) {
        if (itemId == R.id.nav_hoc_sinh) {
            return new HocSinhFragment();
        }
        if (itemId == R.id.nav_giao_vien) {
            return new GiaoVienFragment();
        }
        return null;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_noidung_view, fragment);
        transaction.commit();
    }


}