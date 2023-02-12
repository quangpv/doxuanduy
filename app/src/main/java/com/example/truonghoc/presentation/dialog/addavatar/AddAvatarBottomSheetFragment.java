package com.example.truonghoc.presentation.dialog.addavatar;

import static androidx.activity.result.contract.ActivityResultContracts.*;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.truonghoc.databinding.FragmentAddAvatarBottomSheetBinding;
import com.example.truonghoc.presentation.camera.CameraActivity;
import com.example.truonghoc.presentation.helper.AppFileManager;
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
        viewModel.truyenMaHocSinh(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddAvatarBottomSheetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        appPermission.checkThatBai.observe(getViewLifecycleOwner(), this::yeuCauLaiQuyen);
        binding.openCamera.setOnClickListener(v -> openCamera());
        binding.openThuVien.setOnClickListener(v -> moThuVien());
        super.onViewCreated(view, savedInstanceState);
    }

    private final ActivityResultLauncher<PickVisualMediaRequest> hinhAnh =
            registerForActivityResult(new PickVisualMedia(), result ->
                    viewModel.luuAnhTuThuVien(result));

    private void moThuVien() {
        hinhAnh.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }

    private void yeuCauLaiQuyen(String s) {
        thongBaoToast(s);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().requestPermissions(appPermission.dsQuyenCamera().toArray(new String[0]), 20);
        }
    }


    private void openCamera() {
        if (appPermission.checkQuyenCamera()) {
            moCameRa();
        } else {
            appPermission.checkThatBai.setValue("Không Đủ Quyền");
        }

    }


    private void moCameRa() {
        Intent intent = new Intent(requireActivity(), CameraActivity.class);
        if (viewModel.layMaHocSinh() != null) {
            intent.putExtra("maHs", viewModel.layMaHocSinh());
            requireActivity().startActivity(intent);
        } else {
            requireActivity().startActivity(intent);
        }
    }


    private void thongBaoToast(String s) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show();
    }
}