package com.example.quanlythuvien.view.help;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class TabFragment {
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentFactory fragmentFactory;

    public TabFragment(FrameLayout frameLayout, FragmentManager fragmentManager) {
        this.frameLayout = frameLayout;
        this.fragmentManager = fragmentManager;
        this.fragmentFactory = new FragmentFactory();
    }

    public void thayTheFragment(Class<? extends Fragment> classz) {
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader != null) {
            Fragment f = fragmentFactory.instantiate(classLoader, classz.getName());
            fragmentManager.beginTransaction().replace(frameLayout.getId(), f).commitAllowingStateLoss();
        }}
    }
