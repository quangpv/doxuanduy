package com.example.truonghoc.presentation.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.presentation.helper.AppExecutors;

import java.util.concurrent.Future;

public class BaseViewModel extends ViewModel {

    private final AppExecutors executors = AppExecutors.getInstance();

    @SuppressWarnings("all")
    protected void launch(LiveData<? extends Throwable> error, LaunchRunnable block) {
        Future future = executors.submitTask(() -> {
            try {
                block.run();
            } catch (Throwable e) {
                ((MutableLiveData) error).postValue(e);
                e.printStackTrace();
            }
        });
        addCloseable(() -> future.cancel(true));
    }

    protected void launch(LaunchRunnable block) {
        launch(null, block);
    }

    public interface LaunchRunnable {
        void run() throws Throwable;
    }
}
