package com.example.truonghoc.presentation.helper;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppExecutors {
    private static AppExecutors sAppExecutors;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public static AppExecutors getInstance() {
        if (sAppExecutors == null) synchronized (AppExecutors.class) {
            sAppExecutors = new AppExecutors();
        }
        return sAppExecutors;
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    public Future<Bitmap> submit(Callable<Bitmap> callable) {
        return executor.submit(callable);
    }

    public ExecutorService executors() {
        return executor;
    }

    public void onMainThread(Runnable runnable) {
        handler.post(runnable);
    }

    public Future<?> submitTask(Runnable runnable) {
        return executor.submit(runnable);
    }
}
