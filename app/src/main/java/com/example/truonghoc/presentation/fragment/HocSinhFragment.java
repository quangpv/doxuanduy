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
import com.example.truonghoc.presentation.Iinterface.ClickListListener;
import com.example.truonghoc.presentation.apdapter.HocSinhDangHocAdapter;
import com.example.truonghoc.presentation.viewmodel.FragmentHocSinhViewModel;

import java.util.List;


public class HocSinhFragment extends Fragment {
    FragmentHocSinhBinding hocSinhBinding;
    HocSinhDangHocAdapter adapter;
    FragmentHocSinhViewModel fragmentHocSinhViewModel;
    LiveData<List<HocSinhDangHoc>> danhSachRoom;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hocSinhBinding = FragmentHocSinhBinding.inflate(inflater, container, false);
        fragmentHocSinhViewModel = new ViewModelProvider(this).get(FragmentHocSinhViewModel.class);
        hocSinhBinding.recyclerviewHocsinh.addOnItemTouchListener(new ClickListListener(getContext(),(view, position) -> moManHinhThongTin(position)));
        hocSinhBinding.themHs.setOnClickListener(v -> moThemHocSinh());
        adapter = new HocSinhDangHocAdapter(getContext());
        danhSachRoom = HocSinhDangHocDataBase.getInstance().hocSinhDAO().layDanhSach();
        danhSachRoom.observe(getViewLifecycleOwner(), this::capNhapDanhSachView);
        danhSachRoom.observe(getViewLifecycleOwner(), this::timKiem);
        hocSinhBinding.recyclerviewHocsinh.setAdapter(adapter);
        return hocSinhBinding.getRoot();
    }

    private void moManHinhThongTin(int position) {
        Intent intent = new Intent(getContext(), ThongTinHocSinhActivity.class);
        HocSinhDangHoc hocSinh = fragmentHocSinhViewModel.getDanhSach().getValue().get(position);
        intent.putExtra("HS", hocSinh);
        startActivity(intent);
    }

    private void capNhapDanhSachView(List<HocSinhDangHoc> danhSach) {
        fragmentHocSinhViewModel.getDanhSach().postValue(danhSach);
        fragmentHocSinhViewModel.getDanhSach().observe(getViewLifecycleOwner(), adapter::setDanhSach);
    }


    public void moThemHocSinh() {
        startActivity(new Intent(getContext(), ThemHocSinhActivity.class));
    }

    public void timKiem(List<HocSinhDangHoc> danhSach) {
        MainActivity mainActivity = (MainActivity) requireActivity();
        mainActivity.binding.includedThanhCongCu.noidungTimKiem.addTextChangedListener((ITextWatcher) s -> fragmentHocSinhViewModel.timKiem(s.toString(), danhSach));
    }
}