package com.example.truonghoc.presentation.feature.hocsinh;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.truonghoc.R;
import com.example.truonghoc.databinding.FragmentHocSinhBinding;
import com.example.truonghoc.presentation.base.BaseFragment;
import com.example.truonghoc.presentation.helper.ViewUtils;
import com.example.truonghoc.presentation.helper.router.Router;
import com.example.truonghoc.presentation.helper.router.Routings;


public class HocSinhFragment extends BaseFragment {
    private FragmentHocSinhBinding binding;
    private HocSinhViewModel viewModel;
    private HocSinhAdapter hocSinhAdapter;
    private ActionBarStateContext actionBarContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(HocSinhViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHocSinhBinding.inflate(inflater, container, false);
        return binding.getRoot();
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