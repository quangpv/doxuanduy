package com.example.truonghoc.presentation.camera;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;

import com.example.truonghoc.presentation.ChuongTrinh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileManager {
    private static FileManager sFileManager;
    private final Application application;

    public FileManager(Application application) {
        this.application = application;
    }

    public static FileManager getInstance() {
        return sFileManager;
    }

    public static void init(ChuongTrinh chuongTrinh) {
        sFileManager = new FileManager(chuongTrinh);
    }

    public String save(Bitmap bitmap, String name) throws IOException {
        // Assume block needs to be inside a Try/Catch block.
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        int counter = 0;
        File file = new File(path, name + ".jpg");
        fOut = new FileOutputStream(file);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
        fOut.flush();
        fOut.close();
        return path;
    }
}
