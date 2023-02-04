package com.example.truonghoc.presentation.camera.previewfragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.truonghoc.R;
import com.example.truonghoc.databinding.FragmentCameraProviewBinding;
import com.example.truonghoc.presentation.camera.CameraViewModel;
import com.example.truonghoc.presentation.camera.viewimgfragment.ViewImageFragment;
import com.example.truonghoc.presentation.helper.AppExecutors;
import com.google.common.util.concurrent.ListenableFuture;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CameraProviewFragment extends Fragment {
    private FragmentCameraProviewBinding fragmentCameraProviewBinding;
    private CameraViewModel cameraViewModel;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private Context sContext;
    private ImageCapture imageCapture;
    private final AppExecutors appExecutors = AppExecutors.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraViewModel = new ViewModelProvider(requireActivity()).get(CameraViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCameraProviewBinding = FragmentCameraProviewBinding.inflate(inflater, container, false);
        cameraProviderFuture = ProcessCameraProvider.getInstance(sContext);
        cameraProviderFuture.addListener(this::hienThiXemTruoc, ContextCompat.getMainExecutor(sContext));
        fragmentCameraProviewBinding.chupAnh2.setOnClickListener(v -> layAnh2());
        fragmentCameraProviewBinding.icBackCamera.setOnClickListener(v -> requireActivity().finish());
        return fragmentCameraProviewBinding.getRoot();

    }

    private void hienThiXemTruoc() {
        ProcessCameraProvider cameraProvider;
        try {
            cameraProvider = cameraProviderFuture.get();
            bindView(cameraProvider);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void bindView(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(fragmentCameraProviewBinding.previewView.getSurfaceProvider());
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        imageCapture = new ImageCapture.Builder().build();
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sContext = context;
    }

    private void layAnh2() {
        imageCapture.takePicture(ContextCompat.getMainExecutor(sContext), new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy image) {
                super.onCaptureSuccess(image);
                Toast.makeText(sContext, "ok" + image.getImageInfo().getRotationDegrees(), Toast.LENGTH_SHORT).show();
                viewImg();
                cameraViewModel.guiAnh(image);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                super.onError(exception);
                Toast.makeText(sContext, "sida", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void viewImg() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_noidung_view, new ViewImageFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}