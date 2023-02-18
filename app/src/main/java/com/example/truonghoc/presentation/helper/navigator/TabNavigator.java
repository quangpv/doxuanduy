package com.example.truonghoc.presentation.helper.navigator;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;

public class TabNavigator extends FragmentNavigator {
    private final FragmentManager supportFragmentManager;
    private final FrameLayout container;
    private final FragmentFactory fragmentFactory;

    public TabNavigator(FrameLayout container, FragmentManager supportFragmentManager) {
        super();
        this.supportFragmentManager = supportFragmentManager;
        this.container = container;
        this.fragmentFactory = new FragmentFactory();
    }

    @Override
    public void navigateTo(Class<? extends Fragment> fragmentClass) {
        navigateTo(fragmentClass, null);
    }

    @Override
    public void navigateTo(Class<? extends Fragment> fragmentClass, Bundle bundle) {
        Fragment f = fragmentFactory.instantiate(this.getClass().getClassLoader(), fragmentClass.getName());
        supportFragmentManager.beginTransaction().replace(container.getId(), f).commitAllowingStateLoss();
    }
}
