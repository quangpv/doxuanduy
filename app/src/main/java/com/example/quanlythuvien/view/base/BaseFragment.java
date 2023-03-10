package com.example.quanlythuvien.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.quanlythuvien.view.adapter.SachAdater;
import com.example.quanlythuvien.view.feature.acitonbar.ActionBarInputSearchState;
import com.example.quanlythuvien.view.feature.acitonbar.ActionBarTitleAndSearchButtonState;
import com.example.quanlythuvien.view.help.ActionBarStateContext;

import kotlin.jvm.functions.Function3;

public abstract class BaseFragment<T extends ViewBinding> extends Fragment {
    public SachAdater sachAdater;
    public ActionBarStateContext actionBar;
    public ActionBarTitleAndSearchButtonState stateTieuDe;
    public ActionBarInputSearchState stateSearch;
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

    public <S extends ViewModel> S getViewModel(Class<S> clazz) {
        return new ViewModelProvider(this).get(clazz);
    }

    public void toast(String it) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
