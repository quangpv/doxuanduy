package com.example.truonghoc.presentation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import kotlin.jvm.functions.Function3;

public abstract class BindingFragment<T extends ViewBinding> extends BaseFragment {
    private Function3<LayoutInflater, ViewGroup, Boolean, ViewBinding> inflate;
    public T binding;

    public void setInflate(Function3<LayoutInflater, ViewGroup, Boolean, ViewBinding> inflate) {
        this.inflate = inflate;
    }

    @SuppressWarnings("all")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) throw new IllegalStateException("Not set inflate yet!");
        binding = (T) inflate.invoke(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
