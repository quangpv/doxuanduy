package com.example.truonghoc.presentation.helper.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import java.io.Serializable;

public class Router {
    public static void open(LifecycleOwner owner, Routing routing) {
        Context context;
        if (owner instanceof Fragment) {
            context = ((Fragment) owner).requireContext();
        } else if (owner instanceof Activity) {
            context = (Activity) owner;
        } else {
            return;
        }
        Intent intent = new Intent(context, routing.getDestinationClass());
        Arguments.put(intent, routing);
        context.startActivity(intent);
    }

    public static void navigateTo(LifecycleOwner owner, Routing routing) {

    }

    public interface Routing extends Serializable {

        Class<?> getDestinationClass();
    }

}
