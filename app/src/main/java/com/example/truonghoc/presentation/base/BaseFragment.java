package com.example.truonghoc.presentation.base;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseFragment extends Fragment {
    public <T extends ViewModel> T getViewModel(Class<T> clazz) {
        return new ViewModelProvider(this).get(clazz);
    }
}
