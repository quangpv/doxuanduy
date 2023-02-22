package com.example.truonghoc.presentation.feature.hoso;

import android.view.View;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.util.Consumer;

import com.example.truonghoc.domain.ui.IImage;
import com.example.truonghoc.domain.bo.UriImage;

public class OpenToSelectImageAction implements View.OnClickListener {

    private final ActivityResultLauncher<String> mLauncher;

    public OpenToSelectImageAction(
            ActivityResultCaller owner,
            Consumer<IImage> onResult
    ) {
        mLauncher = owner.registerForActivityResult(new ActivityResultContracts.GetContent(), (result) -> {
            onResult.accept(new UriImage(result));
        });
    }

    @Override
    public void onClick(View v) {
        mLauncher.launch("image/*");
    }
}
