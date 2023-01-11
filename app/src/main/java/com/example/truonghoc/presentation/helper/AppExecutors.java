package com.example.truonghoc.presentation.helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static AppExecutors sAppExecutors;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public static AppExecutors getInstance() {
        if (sAppExecutors == null) synchronized (AppExecutors.class) {
            sAppExecutors = new AppExecutors();
        }
        return sAppExecutors;
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}
