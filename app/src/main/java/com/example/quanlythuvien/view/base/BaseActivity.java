package com.example.quanlythuvien.view.base;

import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import com.example.quanlythuvien.view.help.TabFragment;

public abstract class BaseActivity extends AppCompatActivity {
    public TabFragment tabFragment;

    public <T extends ViewBinding> T setBiding (Function<LayoutInflater,T> value){
            T binding = value.apply(getLayoutInflater());
            setContentView(binding.getRoot());
            return binding;
    }
    public <T extends ViewModel> T setViewModel(Class<T> classz){
        return new ViewModelProvider(this).get(classz);

    }
    public void toast(String it) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show();
    }

}
