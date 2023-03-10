package com.example.quanlythuvien.view.feature.acitonbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.util.Consumer;
import androidx.viewbinding.ViewBinding;

import com.example.quanlythuvien.databinding.TimkiemBinding;
import com.example.quanlythuvien.view.help.ActionBarStateContext;
import com.example.quanlythuvien.view.help.HienThiView;

public class ActionBarInputSearchState implements ActionBarStateContext.TrangThai, TextWatcher {
    int hint;
    TimkiemBinding binding;
    public Runnable exitClick;
    public Consumer<String> onSearching;

    public ActionBarInputSearchState(int hint) {
        this.hint = hint;
    }

    @Override
    public ViewBinding onCreate(LayoutInflater inflater, ViewGroup parent) {
        if (binding == null) {
            binding = TimkiemBinding.inflate(inflater, parent, false);
        }
        return binding;
    }

    @Override
    public void Apply() {
        binding.editseach.addTextChangedListener(this);
        binding.offSearch.setOnClickListener(v -> exitClick.run());
        binding.clearSearch.setOnClickListener(v -> binding.editseach.setText(""));
        HienThiView.show(binding.clearSearch, binding.editseach.length() > 0);
        binding.editseach.setHint(binding.getRoot().getResources().getString(this.hint));
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
        HienThiView.show(binding.clearSearch, s.length() > 0);
    }
}

