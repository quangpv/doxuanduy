package com.example.quanlythuvien.view.feature.menu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityGiaoDienChinhBinding;
import com.example.quanlythuvien.view.base.BaseActivity;
import com.example.quanlythuvien.view.feature.sach.SachFragment;
import com.example.quanlythuvien.view.help.DiaLogFactory;
import com.example.quanlythuvien.view.help.TabFragment;

public class GiaoDienChinh extends BaseActivity {
    ActivityGiaoDienChinhBinding binding;
    GiaoDienChinhViewModel viewModel;

    @Override
    public void onBackPressed() {
        DiaLogFactory.showDialog(this, "Đăng Xuất ?", this::finish, () -> {
        }).show();
    }

    @SuppressLint("NonConstantResourceId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setBiding(ActivityGiaoDienChinhBinding::inflate);
        viewModel = setViewModel(GiaoDienChinhViewModel.class);
        tabFragment = new TabFragment(binding.fragmentNoidungView, getSupportFragmentManager());
        binding.navview.setOnItemSelectedListener(this::xuLyView);
        if (savedInstanceState == null) binding.navview.setSelectedItemId(R.id.muontra);

    }


    @SuppressLint("NonConstantResourceId")
    private boolean xuLyView(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.muontra:

                break;
            case R.id.docgia:

                break;
            case R.id.sach:
                tabFragment.thayTheFragment(SachFragment.class);
                break;
            default:
                return false;
        }
        return true;
    }

}