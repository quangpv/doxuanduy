package com.example.truonghoc.presentation.base;

import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.truonghoc.R;


public class BaseActivity extends AppCompatActivity {

    public <T extends ViewModel> T getViewModel(Class<T> clazz) {
        return new ViewModelProvider(this).get(clazz);
    }


    public <T extends ViewBinding> T setBinding(Function<LayoutInflater, T> inflate) {
        T binding = inflate.apply(getLayoutInflater());
        setContentView(binding.getRoot());
        return binding;
    }

    public void toast(String it) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show();
    }
}
