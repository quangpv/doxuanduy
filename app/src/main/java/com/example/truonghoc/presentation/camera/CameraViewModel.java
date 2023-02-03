package com.example.truonghoc.presentation.camera;

import android.content.Intent;
import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.presentation.helper.AppExecutors;

public class CameraViewModel extends ViewModel {
    AppExecutors appExecutors = AppExecutors.getInstance();

    public MutableLiveData<Bitmap> anhDaChup = new MutableLiveData<>();

    public void guiAnh(Bitmap bitmap) {
        appExecutors.execute(() -> anhDaChup.postValue(bitmap));
    }
}
