package com.example.truonghoc.presentation.camera.viewimgfragment;

import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.truonghoc.databinding.FragmentViewImageBinding;
import com.example.truonghoc.presentation.camera.CameraViewModel;

public class ViewImageFragment extends Fragment {
    FragmentViewImageBinding fragmentViewImageBinding;
    CameraViewModel cameraViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraViewModel= new ViewModelProvider(this).get(CameraViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cameraViewModel.anhDaChup.observe(getViewLifecycleOwner(),bitmap ->
                Glide.with(this)
                        .load(bitmap)
                        .into(fragmentViewImageBinding.viewimage));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentViewImageBinding = FragmentViewImageBinding.inflate(inflater,container,false);

        return fragmentViewImageBinding.getRoot();
    }

}