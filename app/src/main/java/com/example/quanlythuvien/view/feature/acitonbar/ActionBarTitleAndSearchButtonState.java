package com.example.quanlythuvien.view.feature.acitonbar;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;
import com.example.quanlythuvien.databinding.TieudeTimkiemBinding;
import com.example.quanlythuvien.view.help.ActionBarStateContext;

public class ActionBarTitleAndSearchButtonState implements ActionBarStateContext.TrangThai {
    private final int titleRes;
    TieudeTimkiemBinding binding;
    public Runnable btnSearchClick, btnBack;
    public ActionBarTitleAndSearchButtonState(int stringRes) {
        this.titleRes = stringRes;

    }

    @Override
    public ViewBinding onCreate(LayoutInflater inflater, ViewGroup parent) {
        if (binding == null) {
            binding = TieudeTimkiemBinding.inflate(inflater, parent, false);
        }
        return binding;
    }

    @Override
    public void Apply() {
        binding.tenFragment.setText(binding.getRoot().getResources().getString(this.titleRes));
        binding.btnSearch.setOnClickListener(v -> btnSearchClick.run());
        binding.icback.setOnClickListener(v -> btnBack.run());
    }
}
