package com.example.truonghoc.presentation.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.presentation.helper.AppExecutors;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;

public class CameraViewModel extends ViewModel {
    AppExecutors appExecutors = AppExecutors.getInstance();
    FileManager fileManager = FileManager.getInstance();

    public MutableLiveData<Bitmap> anhDaChup = new MutableLiveData<>();
    public MutableLiveData<Uri> anhDaLuu = new MutableLiveData<>();
    public MutableLiveData<String> luuAnhLoi = new MutableLiveData<>();

    public void guiAnh(ImageProxy imageProxy) {
        appExecutors.execute(() -> {
            anhDaChup.postValue(xoayAnh(getBitMap(imageProxy), imageProxy.getImageInfo().getRotationDegrees()));
        });
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

    public void luuAnh() {
        Bitmap bitmap = anhDaChup.getValue();
        if (bitmap == null) return;
        appExecutors.execute(() -> {
            String uri = null;
            try {
                uri = fileManager.save(bitmap, System.currentTimeMillis() + "");
                anhDaLuu.postValue(Uri.fromFile(new File(uri)));
            } catch (IOException e) {
                e.printStackTrace();
                luuAnhLoi.postValue(e.getMessage());
            }
        });
    }
}
