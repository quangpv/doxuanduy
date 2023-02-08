package com.example.truonghoc.presentation.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;


import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.presentation.helper.AppExecutors;
import com.example.truonghoc.presentation.helper.AppFileManager;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CameraViewModel extends ViewModel {
    AppExecutors appExecutors = AppExecutors.getInstance();
    AppFileManager fileManager = AppFileManager.getInstance();
    public MutableLiveData<Bitmap> anhDaChup = new MutableLiveData<>();
    public MutableLiveData<String> luuAnhLoi = new MutableLiveData<>();

    public void guiAnh(ImageProxy imageProxy) {
        appExecutors.execute(() ->
                anhDaChup.postValue(xoayAnh(getBitMap(imageProxy), imageProxy.getImageInfo().getRotationDegrees())));
    }

    private Bitmap xoayAnh(Bitmap bitMap, int rotationDegrees) {
        switch (rotationDegrees) {
            case 90:
                return xuLyXoayAnh(bitMap, 90);
            case 180:
                return xuLyXoayAnh(bitMap, 180);
            default:
                return bitMap;
        }
    }

    private Bitmap xuLyXoayAnh(Bitmap bitMap, int rotate) {
        Matrix matrix = new Matrix();
        matrix.preRotate(rotate);
        Bitmap a = Bitmap.createBitmap(bitMap, 0, 0, bitMap.getWidth(), bitMap.getHeight(), matrix, true);
        bitMap.recycle();
        return a;
    }

    // áp dụng cho ảnh trả về 256
    private Bitmap getBitMap(@NonNull ImageProxy image) {
        ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
        byteBuffer.rewind();
        byte[] bytes = new byte[byteBuffer.capacity()];
        byteBuffer.get(bytes);
        byte[] bytes1 = bytes.clone();
        return BitmapFactory.decodeByteArray(bytes1, 0, bytes1.length);
    }

    public void luuAnhTamThoi() {
        Bitmap bitmap = anhDaChup.getValue();
        if (bitmap == null) return;
        appExecutors.execute(() -> {
            String tenAnhTamThoi2 = System.currentTimeMillis()+ "";
            try {
                fileManager.saveAnhTamThoi(bitmap, tenAnhTamThoi2 );
            } catch (IOException e) {
                e.printStackTrace();
                luuAnhLoi.postValue(e.getMessage());
            }
        });
    }

    public void luuAnhDaCoThongTin(HocSinhDangHoc hs) {
        Bitmap bitmap = anhDaChup.getValue();
        if(bitmap==null)return;
        appExecutors.execute(() -> {
            try {
                fileManager.saveDaCoMaHs(bitmap,hs.getHocSinh().getMaHocSinh());
            } catch (IOException e) {
                e.printStackTrace();
                luuAnhLoi.postValue(e.getMessage());
            }

        });

    }
}
