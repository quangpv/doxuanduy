package com.example.truonghoc.presentation.camera.viewimgfragment;


import android.content.Context;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.example.truonghoc.databinding.FragmentViewImageBinding;
import com.example.truonghoc.presentation.camera.CameraViewModel;

public class ViewImageFragment extends Fragment {
    private FragmentViewImageBinding fragmentViewImageBinding;
    private CameraViewModel cameraViewModel;
    private Context sContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraViewModel = new ViewModelProvider(requireActivity()).get(CameraViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentViewImageBinding = FragmentViewImageBinding.inflate(inflater, container, false);
        cameraViewModel.anhDaChup.observe(getViewLifecycleOwner(), imageProxy -> {
            Glide.with(sContext).load(cameraViewModel.conventerBitmap(imageProxy)).into(fragmentViewImageBinding.viewimage);
        });
        fragmentViewImageBinding.huyAnh.setOnClickListener(v -> backFragment());
        fragmentViewImageBinding.layAnh.setOnClickListener(v -> anLuuAnh());
        return fragmentViewImageBinding.getRoot();
    }

    private void anLuuAnh() {
        new AlertDialog.Builder(sContext)
                .setMessage("Thêm Ảnh Này?")
                .setNegativeButton("Yes", (dialog, which) -> layAnh())
                .setPositiveButton("No", (dialog, which) -> {
                }).show();
    }

    private void layAnh() {
    }


    private void backFragment() {

        if(requireActivity().getSupportFragmentManager().getBackStackEntryCount()>0){
            requireActivity().getSupportFragmentManager().popBackStackImmediate();}
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sContext = context;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}