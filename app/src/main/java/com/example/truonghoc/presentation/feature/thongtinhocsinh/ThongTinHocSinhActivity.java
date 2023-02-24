package com.example.truonghoc.presentation.feature.thongtinhocsinh;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.truonghoc.databinding.ActivityThongTinHocSinhBinding;
import com.example.truonghoc.domain.ui.IChiTietHocSinhEditable;
import com.example.truonghoc.presentation.base.BaseActivity;
import com.example.truonghoc.presentation.helper.DialogFactory;
import com.example.truonghoc.presentation.helper.OnTextChangeListener;
import com.example.truonghoc.presentation.helper.router.Arguments;
import com.example.truonghoc.presentation.helper.router.Routings;
import com.example.truonghoc.presentation.model.BiConsumer;


public class ThongTinHocSinhActivity extends BaseActivity {
    private ActivityThongTinHocSinhBinding binding;
    private ThongTinHocSinhViewModel viewModel;
    private Routings.ThongTinHocSinh args;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setBinding(ActivityThongTinHocSinhBinding::inflate);
        viewModel = getViewModel(ThongTinHocSinhViewModel.class);
        args = Arguments.of(getIntent());

        viewModel.chiTiet.observe(this, it -> {
            binding.edtTen.setText(it.getName());
            binding.edtDob.setText(it.getDob());
            binding.edtGioiTinh.setText(it.getGender());
            binding.edtLop.setText(it.getLop());

            boolean isEditable = it instanceof IChiTietHocSinhEditable;
            binding.edtTen.setEnabled(isEditable);
            binding.edtGioiTinh.setEnabled(isEditable);
            binding.edtLop.setEnabled(isEditable);
            binding.edtDob.setEnabled(isEditable);
        });

        registerTextChange(binding.edtTen, (it, editable) -> editable.setName(it));
        registerTextChange(binding.edtGioiTinh, (it, editable) -> editable.setGioiTinh(it));
        registerTextChange(binding.edtLop, (it, editable) -> editable.setLop(it));
        registerTextChange(binding.edtDob, (it, editable) -> editable.setDob(it));

        binding.btnEdit.setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            if (v.isSelected()) {
                viewModel.enableEdit();
                return;
            }
            if (!viewModel.isEdit()) {
                viewModel.disableEdit();
                return;
            }
            DialogFactory.createXacNhanSuaThongTinHocSinh(this, () -> viewModel.save(),
                    () -> viewModel.disableEdit()).show();
        });

        binding.btnDelete.setOnClickListener(v -> viewModel.delete(args.id));
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        viewModel.xoaThanhCong.observe(this, v -> finish());
        viewModel.setId(args.id);
    }

    void registerTextChange(EditText editText, BiConsumer<String, IChiTietHocSinhEditable> callback) {
        editText.addTextChangedListener((OnTextChangeListener) it -> {
            if (!(viewModel.chiTiet.getValue() instanceof IChiTietHocSinhEditable)) {
                return;
            }
            IChiTietHocSinhEditable editable = (IChiTietHocSinhEditable) viewModel.chiTiet.getValue();
            callback.accept(it, editable);
        });
    }

    public void xuLyAnBack() {
        if (viewModel.isEdit()) {
            DialogFactory.createHuySuaThongTinHocSinh(this).show();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        xuLyAnBack();
    }
}