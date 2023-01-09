package com.example.truonghoc.presentation.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.databinding.FragmentHocSinhBinding;
import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.presentation.Iinterface.ITextWatcher;
import com.example.truonghoc.presentation.MainActivity;
import com.example.truonghoc.presentation.ThongTinHocSinhActivity;
import com.example.truonghoc.presentation.ThemHocSinhActivity;
import com.example.truonghoc.presentation.apdapter.ClickListListener;
import com.example.truonghoc.presentation.apdapter.HocSinhDangHocAdapter;
import com.example.truonghoc.presentation.viewmodel.HocSinhViewModel;

import java.util.List;


public class HocSinhFragment extends Fragment {
    FragmentHocSinhBinding hocSinhBinding;
    HocSinhDangHocAdapter adapter;
    HocSinhViewModel hocSinhViewModel;
    LiveData<List<HocSinhDangHoc>> danhSachRoom;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hocSinhBinding = FragmentHocSinhBinding.inflate(inflater, container, false);
        hocSinhViewModel = new ViewModelProvider(this).get(HocSinhViewModel.class);
        adapter = new HocSinhDangHocAdapter();
        danhSachRoom = HocSinhDangHocDataBase.quanLyData(getContext()).hocSinhDAO().layDanhSach();
        danhSachRoom.observe(getViewLifecycleOwner(), this::capNhapDanhSachView);
        danhSachRoom.observe(getViewLifecycleOwner(), this::timKiem);
        hocSinhBinding.recyclerviewHocsinh.setAdapter(adapter);
        moManHinhThongTin();

        hocSinhBinding.themHs.setOnClickListener(v -> moThemHocSinh());
        return hocSinhBinding.getRoot();
    }

    private void moManHinhThongTin() {
        hocSinhBinding.recyclerviewHocsinh.addOnItemTouchListener(new ClickListListener(getContext(), (view, position) -> {
            Intent intent = new Intent(getContext(), ThongTinHocSinhActivity.class);
            HocSinhDangHoc hocSinh = hocSinhViewModel.getDanhSach().getValue().get(position);
            intent.putExtra("HS", hocSinh);
            startActivity(intent);
        }));
    }

    private void capNhapDanhSachView(List<HocSinhDangHoc> danhSach) {
        hocSinhViewModel.getDanhSach().postValue(danhSach);
        hocSinhViewModel.getDanhSach().observe(getViewLifecycleOwner(), adapter::setDanhSach);
    }


    public void moThemHocSinh() {
        startActivity(new Intent(getContext(), ThemHocSinhActivity.class));
    }

    public void timKiem(List<HocSinhDangHoc> danhSach) {
        MainActivity mainActivity = (MainActivity) requireActivity();
        mainActivity.binding.includedThanhCongCu.noidungTimKiem.addTextChangedListener((ITextWatcher) s -> hocSinhViewModel.timKiem(s.toString(), danhSach));
    }
}