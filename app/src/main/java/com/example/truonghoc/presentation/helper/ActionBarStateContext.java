package com.example.truonghoc.presentation.helper;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.viewbinding.ViewBinding;

public class ActionBarStateContext {
    private final FrameLayout container;
    private State mPrevious;

    public ActionBarStateContext(FrameLayout actionBar) {
        this.container = actionBar;
    }

    public void setState(State state) {
        if (mPrevious instanceof AutoCloseable) {
            try {
                ((AutoCloseable) mPrevious).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        container.removeAllViews();
        ViewBinding binding = state.onCreate(LayoutInflater.from(container.getContext()), container);
        state.onApply();
        container.addView(binding.getRoot());
        mPrevious = state;
    }

    public interface State {
        ViewBinding onCreate(LayoutInflater inflater, ViewGroup parent);

        void onApply();
    }
}
