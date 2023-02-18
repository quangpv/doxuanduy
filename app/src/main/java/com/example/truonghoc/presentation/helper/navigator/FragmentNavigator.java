package com.example.truonghoc.presentation.helper.navigator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public abstract class FragmentNavigator {
    public abstract void navigateTo(Class<? extends Fragment> fragmentClass);

    public abstract void navigateTo(Class<? extends Fragment> fragmentClass, Bundle bundle);
}
