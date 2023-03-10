package com.example.quanlythuvien.view.help;


import android.os.Handler;
import android.os.Looper;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    public ExecutorService executors() {
        return executor;
    }

    public void onMainThread(Runnable runnable) {
        handler.post(runnable);
    }
}
