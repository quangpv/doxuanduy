package com.example.quanlythuvien.view.feature.sach;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;
import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.FragmentSachBinding;
import com.example.quanlythuvien.view.adapter.SachAdater;
import com.example.quanlythuvien.view.base.BaseFragment;
import com.example.quanlythuvien.view.feature.acitonbar.ActionBarInputSearchState;
import com.example.quanlythuvien.view.feature.acitonbar.ActionBarTitleAndSearchButtonState;
import com.example.quanlythuvien.view.feature.sach.addsach.AddSachActivity;
import com.example.quanlythuvien.view.feature.sach.infosach.InfoSachActivity;
import com.example.quanlythuvien.view.help.ActionBarStateContext;
import com.example.quanlythuvien.view.help.HienThiView;
import com.example.quanlythuvien.view.help.OpenActivity;

public class SachFragment extends BaseFragment<FragmentSachBinding> {

    SachViewModel viewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInflate(FragmentSachBinding::inflate);
        viewModel = getViewModel(SachViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sachAdater = new SachAdater(binding.rycsach);
        actionBar = new ActionBarStateContext(binding.thanhCongCuTop);
        stateTieuDe = new ActionBarTitleAndSearchButtonState(R.string.tieude_ds_sach);
        stateSearch = new ActionBarInputSearchState(R.string.hint_search_sach);
        stateTieuDe.btnSearchClick = () -> actionBar.setTrangThai(stateSearch);
        stateTieuDe.btnBack = () -> requireActivity().onBackPressed();
        stateSearch.exitClick = () -> actionBar.setTrangThai(stateTieuDe);
        stateSearch.onSearching = (s) -> viewModel.search(s);
        binding.btnAdd.setOnClickListener(v -> OpenActivity.open(this,AddSachActivity.class,null));
        sachAdater.onItemClick = itemList -> OpenActivity.open(this, InfoSachActivity.class,itemList.getMaSach());
        sachAdater.onClickXoa = itemList -> viewModel.xoaSach(itemList);
        viewModel.danhSach.observe(getViewLifecycleOwner(), it -> {
            sachAdater.setDanhSach(it);
            HienThiView.show(binding.nullvalue, it.isEmpty());
        });
        actionBar.setTrangThai(stateTieuDe);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.khoiDong();
    }
}