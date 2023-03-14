package com.example.quanlythuvien.domain.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public interface IHuyObseverOnDestroy extends DefaultLifecycleObserver {
    @Override
    default void onDestroy(@NonNull LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
        try {
            onDestroyed();
        } catch (Exception ignored) {
        }

    }

    void onDestroyed() throws Exception;
}
