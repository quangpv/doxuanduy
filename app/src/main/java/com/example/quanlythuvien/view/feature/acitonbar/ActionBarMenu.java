package com.example.quanlythuvien.view.feature.acitonbar;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.example.quanlythuvien.databinding.ThanhCongCuTopBinding;
import com.example.quanlythuvien.databinding.TimkiemBinding;
import com.example.quanlythuvien.view.help.ActionBarStateContext;

public class ActionBarMenu implements ActionBarStateContext.TrangThai {
    ThanhCongCuTopBinding binding;
    int tieuDe;
    public Runnable iconBack, iConSua, iConLuu;
    Boolean itemIconPhai;

    public ActionBarMenu(int tieuDe, Boolean aBoolean) {
        this.tieuDe = tieuDe;
        this.itemIconPhai = aBoolean;
    }

    @Override
    public ViewBinding onCreate(LayoutInflater inflater, ViewGroup parent) {
        if (binding == null) {
            binding = ThanhCongCuTopBinding.inflate(inflater, parent, false);
        }
        return binding;
    }

    @Override
    public void Apply() {
        binding.icBack.setOnClickListener(v -> iconBack.run());
        binding.tieuDe.setText(binding.getRoot().getResources().getString(tieuDe));
        binding.icSua.setSelected(itemIconPhai);
    }
}
