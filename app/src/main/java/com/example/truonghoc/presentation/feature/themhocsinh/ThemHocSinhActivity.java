package com.example.truonghoc.presentation.feature.themhocsinh;


import android.os.Bundle;
import android.widget.EditText;

import com.example.truonghoc.databinding.ActivityThemHocSinhBinding;
import com.example.truonghoc.domain.ui.HasSetId;
import com.example.truonghoc.domain.ui.IChiTietHocSinhEditable;
import com.example.truonghoc.presentation.base.BaseActivity;
import com.example.truonghoc.presentation.helper.OnTextChangeListener;
import com.example.truonghoc.presentation.model.BiConsumer;

public class ThemHocSinhActivity extends BaseActivity {
    private ActivityThemHocSinhBinding binding;
    private ThemHocSinhViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setBinding(ActivityThemHocSinhBinding::inflate);
        viewModel = getViewModel(ThemHocSinhViewModel.class);

        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnConfirm.setOnClickListener(v -> viewModel.add());

        registerTextChange(binding.edtDob, (it, editable) -> editable.setDob(it));
        registerTextChange(binding.edtGioiTinh, (it, editable) -> editable.setGioiTinh(it));
        registerTextChange(binding.edtTen, (it, editable) -> editable.setName(it));
        registerTextChange(binding.edtLop, (it, editable) -> editable.setLop(it));
        registerTextChange(binding.edtId, (it, editable) -> ((HasSetId) editable).setId(it));

        viewModel.themThanhCong.observe(this, it -> {
            toast("Thêm học sinh thành công");
            finish();
        });
        viewModel.themThatBai.observe(this, this::toast);
    }

    void registerTextChange(EditText editText, BiConsumer<String, IChiTietHocSinhEditable> callback) {
        editText.addTextChangedListener((OnTextChangeListener) it -> {
            if (viewModel.hocSinh.getValue() == null) {
                return;
            }
            callback.accept(it, viewModel.hocSinh.getValue());
        });
    }


}