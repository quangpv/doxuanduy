package com.example.truonghoc.domain.bo;

import android.graphics.Bitmap;

import com.example.truonghoc.domain.ui.HasBitmap;
import com.example.truonghoc.domain.ui.IImage;

public class BitmapImage implements IImage, HasBitmap {
    private final Bitmap bitmap;

    public BitmapImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public Bitmap getBitmap() {
        return this.bitmap;
    }
}
