package com.example.truonghoc.presentation.camera.viewimgfragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.truonghoc.databinding.FragmentViewImageBinding;
import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.presentation.camera.CameraViewModel;


public class ViewImageFragment extends Fragment {
    private FragmentViewImageBinding fragmentViewImageBinding;
    private CameraViewModel cameraViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraViewModel = new ViewModelProvider(requireActivity()).get(CameraViewModel.class);
        cameraViewModel.truyenMaHocSinh(layMaHocSinh());
    }

    private String layMaHocSinh() {
        Intent intent = requireActivity().getIntent();
        return intent.getStringExtra("maHs");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentViewImageBinding = FragmentViewImageBinding.inflate(inflater, container, false);
        return fragmentViewImageBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cameraViewModel.anhDaChup.observe(getViewLifecycleOwner(), this::hienThiAnh);
        cameraViewModel.luuAnhLoi.observe(getViewLifecycleOwner(), this::thongBaoToast);
        fragmentViewImageBinding.huyAnh.setOnClickListener(v -> backFragment());
        fragmentViewImageBinding.layAnh.setOnClickListener(v -> anLuuAnh());
    }

    private void thongBaoToast(String error) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
    }

    private void hienThiAnh(Bitmap bitmap) {
        Glide.with(requireContext()).load(bitmap).into(fragmentViewImageBinding.viewimage);
    }


    private void anLuuAnh() {
        new AlertDialog.Builder(requireContext())
                .setMessage("Thêm Ảnh Này?")
                .setNegativeButton("Yes", (dialog, which) -> {
                    cameraViewModel.luuAnhTamThoi();
                    requireActivity().finish();
                })
                .setPositiveButton( "No", (dialog, which) -> {
                }).show();
    }



    private void backFragment() {
        if (requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        }
    }

}