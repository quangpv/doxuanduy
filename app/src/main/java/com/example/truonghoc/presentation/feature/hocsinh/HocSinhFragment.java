package com.example.truonghoc.presentation.feature.hocsinh;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;
import androidx.viewbinding.ViewBinding;

import com.example.truonghoc.R;
import com.example.truonghoc.databinding.FragmentHocSinhBinding;
import com.example.truonghoc.presentation.base.BindingFragment;
import com.example.truonghoc.presentation.helper.ActionBarStateContext;
import com.example.truonghoc.presentation.helper.ViewUtils;
import com.example.truonghoc.presentation.helper.router.Router;
import com.example.truonghoc.presentation.helper.router.Routings;

import kotlin.jvm.functions.Function3;


public class HocSinhFragment extends BindingFragment<FragmentHocSinhBinding> {
    private HocSinhViewModel viewModel;
    private HocSinhAdapter hocSinhAdapter;
    private ActionBarStateContext actionBarContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInflate(FragmentHocSinhBinding::inflate);
        viewModel = getViewModel(HocSinhViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hocSinhAdapter = new HocSinhAdapter(binding.rvList);
        actionBarContext = new ActionBarStateContext(binding.actionBar);

        ActionBarTitleAndSearchButtonState titleState = new ActionBarTitleAndSearchButtonState(R.string.title_danh_sach_hoc_sinh);
        ActionBarInputSearchState searchState = new ActionBarInputSearchState(R.string.tim_kiem_hoc_sinh);
        titleState.onSearchClick = () -> actionBarContext.setState(searchState);
        searchState.onExitClick = () -> actionBarContext.setState(titleState);
        searchState.onSearching = (text) -> viewModel.search(text);
        hocSinhAdapter.onItemClick = item -> Router.open(this, new Routings.ThongTinHocSinh(item.getId()));
        binding.btnAdd.setOnClickListener(v -> Router.open(this, new Routings.TaoMoiHocSinh()));
        viewModel.hocSinhList.observe(getViewLifecycleOwner(), it -> {
            hocSinhAdapter.submit(it);
            ViewUtils.show(binding.txtEmpty, it.isEmpty());
        });

        actionBarContext.setState(titleState);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.tryFetch();
    }
}