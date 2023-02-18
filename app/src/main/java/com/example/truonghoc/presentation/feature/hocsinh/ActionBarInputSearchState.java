package com.example.truonghoc.presentation.feature.hocsinh;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.util.Consumer;
import androidx.viewbinding.ViewBinding;

import com.example.truonghoc.databinding.StateActionbarInputSearchBinding;
import com.example.truonghoc.presentation.helper.ActionBarStateContext;
import com.example.truonghoc.presentation.helper.ViewUtils;


public class ActionBarInputSearchState implements ActionBarStateContext.State, TextWatcher, AutoCloseable {
    private final int hintRes;
    public Runnable onExitClick;
    public Consumer<String> onSearching;
    private StateActionbarInputSearchBinding binding;

    public ActionBarInputSearchState(int hintRes) {
        this.hintRes = hintRes;
    }

    @Override
    public ViewBinding onCreate(LayoutInflater inflater, ViewGroup parent) {
        if (binding == null) {
            binding = StateActionbarInputSearchBinding.inflate(inflater, parent, false);
        }
        return binding;
    }

    @Override
    public void close() {
        binding.edtSearch.removeTextChangedListener(this);
    }

    @Override
    public void onApply() {
        binding.edtSearch.addTextChangedListener(this);
        binding.btnClose.setOnClickListener(v -> {
            binding.edtSearch.setText("");
        });
        binding.btnExit.setOnClickListener(v -> {
            onExitClick.run();
        });
        ViewUtils.show(binding.btnClose, binding.edtSearch.length() > 0);
        binding.edtSearch.setHint(binding.getRoot().getResources().getString(hintRes));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        onSearching.accept(s.toString());
        ViewUtils.show(binding.btnClose, s.length() > 0);
    }
}
