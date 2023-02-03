package com.example.truonghoc.presentation.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.presentation.helper.AppExecutors;

import java.nio.ByteBuffer;

public class CameraViewModel extends ViewModel {
    AppExecutors appExecutors = AppExecutors.getInstance();

    public MutableLiveData<Bitmap> anhDaChup = new MutableLiveData<>();

    public void guiAnh(ImageProxy imageProxy) {
        appExecutors.execute(() -> anhDaChup.postValue(getBitMap(imageProxy)));
    }
    private Bitmap getBitMap(@NonNull ImageProxy image) {
        ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
        byteBuffer.rewind();
        byte[] bytes = new byte[byteBuffer.capacity()];
        byteBuffer.get(bytes);
        byte[] bytes1 = bytes.clone();
        return BitmapFactory.decodeByteArray(bytes1, 0, bytes1.length);
    }
}
