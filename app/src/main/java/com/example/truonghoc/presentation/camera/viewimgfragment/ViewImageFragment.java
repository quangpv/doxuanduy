package com.example.truonghoc.presentation.camera.viewimgfragment;


import android.content.Context;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
    Context sContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraViewModel = new ViewModelProvider(requireActivity()).get(CameraViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentViewImageBinding = FragmentViewImageBinding.inflate(inflater, container, false);
        cameraViewModel.anhDaChup.observe(getViewLifecycleOwner(), bitmap ->
                Glide.with(sContext).load(bitmap).into(fragmentViewImageBinding.viewimage));
        fragmentViewImageBinding.huyAnh.setOnClickListener(v -> backFragment());
        return fragmentViewImageBinding.getRoot();
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