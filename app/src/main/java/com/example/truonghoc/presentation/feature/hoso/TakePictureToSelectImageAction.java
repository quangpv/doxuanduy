package com.example.truonghoc.presentation.feature.hoso;

import android.view.View;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.util.Consumer;

import com.example.truonghoc.domain.bo.BitmapImage;
import com.example.truonghoc.domain.ui.IImage;

public class TakePictureToSelectImageAction implements View.OnClickListener {


    private final ActivityResultLauncher<Void> mLauncher;

    public TakePictureToSelectImageAction(
            ActivityResultCaller owner,
            Consumer<IImage> onResult
    ) {
        mLauncher = owner.registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), (result) -> {
            onResult.accept(new BitmapImage(result));
        });
    }

    @Override
    public void onClick(View v) {
        mLauncher.launch(null);
    }
}
