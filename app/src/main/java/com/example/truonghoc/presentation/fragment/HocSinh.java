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
import com.example.truonghoc.presentation.ManHinhChinh;
import com.example.truonghoc.presentation.ManHinhThongTinHocSinh;
import com.example.truonghoc.presentation.ThemHocSinh;
import com.example.truonghoc.presentation.apdapter.ClickListListener;
import com.example.truonghoc.presentation.apdapter.HocSinhDangHocAdapter;
import com.example.truonghoc.presentation.viewmodel.FragmentHocSinhViewModel;

import java.util.List;


public class HocSinh extends Fragment {
    FragmentHocSinhBinding hocSinhBinding;
    HocSinhDangHocAdapter adapter;
    FragmentHocSinhViewModel hocSinhViewModel;
    LiveData<List<HocSinhDangHoc>> danhSachRoom;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hocSinhBinding = FragmentHocSinhBinding.inflate(inflater, container, false);
        hocSinhViewModel = new ViewModelProvider(this).get(FragmentHocSinhViewModel.class);
        hocSinhBinding.setFragmentHocSinh(this);
        adapter = new HocSinhDangHocAdapter();
        danhSachRoom = HocSinhDangHocDataBase.quanLyData(getContext()).hocSinhDAO().layDanhSach();
        danhSachRoom.observe(getViewLifecycleOwner(), this::capNhapDanhSachView);
        danhSachRoom.observe(getViewLifecycleOwner(), this::timKiem);
        hocSinhBinding.recyclerviewHocsinh.setAdapter(adapter);
        moManHinhThongTin();
        return hocSinhBinding.getRoot();
    }

    private void moManHinhThongTin() {
        hocSinhBinding.recyclerviewHocsinh.addOnItemTouchListener(new ClickListListener(getContext(), (view, position) -> {
            Intent intent = new Intent(getContext(), ManHinhThongTinHocSinh.class);
            HocSinhDangHoc hocSinh = hocSinhViewModel.getDanhSach().getValue().get(position);
            intent.putExtra("HS",hocSinh);
            startActivity(intent);
        }));
    }

    private void capNhapDanhSachView(List<HocSinhDangHoc> danhSach) {
        hocSinhViewModel.getDanhSach().postValue(danhSach);
        hocSinhViewModel.getDanhSach().observe(getViewLifecycleOwner(),adapter::setDanhSach);
    }



    public void moThemHocSinh() {
        startActivity(new Intent(getContext(), ThemHocSinh.class));
    }

    public void timKiem(List<HocSinhDangHoc> danhSach) {
        ManHinhChinh manHinhChinh = (ManHinhChinh) requireActivity();
        manHinhChinh.binding.includedThanhCongCu.noidungTimKiem.addTextChangedListener((ITextWatcher) s -> hocSinhViewModel.timKiem(s.toString(), danhSach));
    }
}