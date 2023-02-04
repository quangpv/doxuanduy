package com.example.truonghoc.presentation.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageInfo;
import androidx.camera.core.ImageProxy;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.presentation.helper.AppExecutors;

import java.nio.ByteBuffer;

public class CameraViewModel extends ViewModel {
    AppExecutors appExecutors = AppExecutors.getInstance();

    public MutableLiveData<Bitmap> anhDaChup = new MutableLiveData<>();

    public void guiAnh(ImageProxy imageProxy) {
        Bitmap bitmap = xoayAnh(getBitMap(imageProxy),imageProxy.getImageInfo().getRotationDegrees());
        appExecutors.execute(() -> anhDaChup.postValue(bitmap));
    }

    private Bitmap xoayAnh(Bitmap bitMap, int rotationDegrees) {
        switch (rotationDegrees){
            case 90: return xuLyXoayAnh(bitMap,90);
            case 180: return xuLyXoayAnh(bitMap,180);
            default: return bitMap;
        }
    }

    private Bitmap xuLyXoayAnh(Bitmap bitMap,int rotate) {
        Matrix matrix = new Matrix();
        matrix.preRotate(rotate);
        Bitmap a = Bitmap.createBitmap(bitMap,0,0,bitMap.getWidth(),bitMap.getHeight(),matrix,true);
        bitMap.recycle();
        return a;
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
