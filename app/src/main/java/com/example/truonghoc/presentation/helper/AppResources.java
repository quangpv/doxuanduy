package com.example.truonghoc.presentation.helper;

import android.app.Application;

public class AppResources {
    private static AppResources sAppResources;
    private final Application application;

    public AppResources(Application applicationContext) {
        this.application = applicationContext;
    }

    public static AppResources getInstance() {
        return sAppResources;
    }

    public static void init(Application applicationContext) {
        sAppResources = new AppResources(applicationContext);
    }

    public String getString(int stringId) {
        return application.getString(stringId);
    }
}
