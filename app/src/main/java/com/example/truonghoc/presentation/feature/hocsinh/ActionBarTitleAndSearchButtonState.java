package com.example.truonghoc.presentation.feature.hocsinh;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.example.truonghoc.databinding.StateActionbarTitleAndSearchButtonBinding;
import com.example.truonghoc.presentation.helper.ActionBarStateContext;

public class ActionBarTitleAndSearchButtonState implements ActionBarStateContext.State {
    private final int titleRes;
    public Runnable onSearchClick;
    private StateActionbarTitleAndSearchButtonBinding binding;

    public ActionBarTitleAndSearchButtonState(int stringRes) {
        this.titleRes = stringRes;
    }

    @Override
    public ViewBinding onCreate(LayoutInflater inflater, ViewGroup parent) {
        if (binding == null)
            binding = StateActionbarTitleAndSearchButtonBinding.inflate(inflater, parent, false);
        return binding;
    }

    @Override
    public void onApply() {
        binding.txtTitle.setText(binding.getRoot().getResources().getString(this.titleRes));
        binding.btnSearch.setOnClickListener(v -> onSearchClick.run());
    }
}
