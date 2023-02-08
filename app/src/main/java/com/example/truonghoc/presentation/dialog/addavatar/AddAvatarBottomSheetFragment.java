package com.example.truonghoc.presentation.dialog.addavatar;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.truonghoc.databinding.FragmentAddAvatarBottomSheetBinding;
import com.example.truonghoc.presentation.camera.CameraActivity;
import com.example.truonghoc.presentation.helper.AppPermission;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class AddAvatarBottomSheetFragment extends BottomSheetDialogFragment {

    AppPermission appPermission = AppPermission.getInstance();
    FragmentAddAvatarBottomSheetBinding binding;
    AddAvatarBottomSheetViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddAvatarBottomSheetViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddAvatarBottomSheetBinding.inflate(inflater, container, false);
        binding.openCamera.setOnClickListener(v -> openCamera());
        return binding.getRoot();
    }

    private void openCamera() {
        if (appPermission.checkQuyenCamera()) {
            moCameRa();
        } else {
            phanHoiCapQuyen.launch(appPermission.dsQuyenCamera().toArray(new String[0]));
        }
    }

    private final ActivityResultLauncher<String[]> phanHoiCapQuyen =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                if (appPermission.checkQuyenCamera()) {
                    moCameRa();
                } else {
                    thongBaoToast("Có Quyền Chưa Kích Hoạt");
                }
            });

    private void moCameRa() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Intent intent = new Intent(requireActivity(),CameraActivity.class);
            intent.putExtra("hs", bundle);
            requireActivity().startActivity(intent);
        } else {
            requireActivity().startActivity(new Intent(requireActivity(), CameraActivity.class));
        }
    }


    private void thongBaoToast(String s) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show();
    }
}