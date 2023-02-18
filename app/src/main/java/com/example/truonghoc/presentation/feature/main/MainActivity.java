package com.example.truonghoc.presentation.feature.main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.truonghoc.R;
import com.example.truonghoc.databinding.ActivityMainBinding;
import com.example.truonghoc.presentation.base.BaseActivity;
import com.example.truonghoc.presentation.feature.giaovien.GiaoVienFragment;
import com.example.truonghoc.presentation.feature.hocsinh.HocSinhFragment;
import com.example.truonghoc.presentation.feature.hoso.HoSoFragment;
import com.example.truonghoc.presentation.helper.navigator.FragmentNavigator;
import com.example.truonghoc.presentation.helper.navigator.TabNavigator;


public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private FragmentNavigator fragmentNavigator;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setBinding(ActivityMainBinding::inflate);
        viewModel = getViewModel(MainViewModel.class);
        fragmentNavigator = new TabNavigator(binding.containerMain, getSupportFragmentManager());

        binding.navMain.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tabHocSinh:
                    fragmentNavigator.navigateTo(HocSinhFragment.class);
                    break;
                case R.id.tabGiaoVien:
                    fragmentNavigator.navigateTo(GiaoVienFragment.class);
                    break;
                case R.id.tabHoSo:
                    fragmentNavigator.navigateTo(HoSoFragment.class);
                    break;
                default:
                    return false;
            }
            return true;
        });
        if (savedInstanceState == null) binding.navMain.setSelectedItemId(R.id.tabHocSinh);
    }
}