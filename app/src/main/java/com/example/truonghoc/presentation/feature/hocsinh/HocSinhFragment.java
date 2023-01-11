package com.example.truonghoc.presentation.feature.hocsinh;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.truonghoc.databinding.FragmentHocSinhBinding;
import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.presentation.apdapter.HocSinhDangHocAdapter;
import com.example.truonghoc.presentation.feature.main.MainActivity;
import com.example.truonghoc.presentation.feature.themhocsinh.ThemHocSinhActivity;
import com.example.truonghoc.presentation.feature.thongtinhocsinh.ThongTinHocSinhActivity;
import com.example.truonghoc.presentation.model.ClickListListener;
import com.example.truonghoc.presentation.model.ITextWatcher;

import java.util.List;


public class HocSinhFragment extends Fragment {
    FragmentHocSinhBinding hocSinhBinding;
    HocSinhDangHocAdapter adapter;
    HocSinhViewModel hocSinhViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hocSinhViewModel = new ViewModelProvider(this).get(HocSinhViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hocSinhBinding = FragmentHocSinhBinding.inflate(inflater, container, false);
        return hocSinhBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new HocSinhDangHocAdapter();

        adapter.onItemClick = this::moManHinhThongTin;
        hocSinhBinding.themHs.setOnClickListener(v -> moThemHocSinh());

        hocSinhViewModel.danhSachHocSinh.observe(getViewLifecycleOwner(), adapter::setDanhSach);
        getNoiDungTimKiemView().addTextChangedListener((ITextWatcher) s -> hocSinhViewModel.timKiem(s.toString()));
        hocSinhBinding.recyclerviewHocsinh.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        hocSinhViewModel.timKiem(getNoiDungTimKiemView().getText().toString());
    }

    private EditText getNoiDungTimKiemView() {
        MainActivity mainActivity = (MainActivity) requireActivity();
        return mainActivity.binding.includedThanhCongCu.noidungTimKiem;
    }

    private void moManHinhThongTin(HocSinhDangHoc hocSinhDangHoc) {
        Intent intent = new Intent(getContext(), ThongTinHocSinhActivity.class);
        intent.putExtra("HS", hocSinhDangHoc);
        startActivity(intent);
    }

    public void moThemHocSinh() {
        startActivity(new Intent(getContext(), ThemHocSinhActivity.class));
    }
}