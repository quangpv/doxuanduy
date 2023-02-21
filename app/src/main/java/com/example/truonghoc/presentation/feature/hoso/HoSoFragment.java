package com.example.truonghoc.presentation.feature.hoso;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.truonghoc.databinding.FragmentHoSoBinding;
import com.example.truonghoc.domain.ui.IHoSo;
import com.example.truonghoc.domain.ui.IHoSoEditable;
import com.example.truonghoc.presentation.base.BindingFragment;
import com.example.truonghoc.presentation.helper.ViewUtils;
import com.example.truonghoc.presentation.model.BiConsumer;
import com.example.truonghoc.presentation.model.ITextWatcher;

import java.util.Arrays;

public class HoSoFragment extends BindingFragment<FragmentHoSoBinding> {
    private HoSoViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInflate(FragmentHoSoBinding::inflate);
        viewModel = getViewModel(HoSoViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(v -> viewModel.save());

        registerTextChange(binding.edtName, (it, model) -> model.setName(it));
        registerTextChange(binding.edtSDT, (it, model) -> model.setPhoneNumber(it));
        registerTextChange(binding.edtAddress, (it, model) -> model.setAddress(it));

        viewModel.hoso.observe(getViewLifecycleOwner(), it -> {
            binding.edtSDT.setText(it.getPhoneNumber());
            binding.edtAddress.setText(it.getAddress());
            binding.edtName.setText(it.getName());

            for (EditText editText : Arrays.asList(binding.edtSDT, binding.edtAddress, binding.edtName)) {
                editText.setEnabled(it instanceof IHoSoEditable);
            }
            ViewUtils.show(binding.btnSave, it instanceof IHoSoEditable);
        });
    }

    private void registerTextChange(EditText editText, BiConsumer<String, IHoSoEditable> callback) {
        editText.addTextChangedListener((ITextWatcher) s -> {
            IHoSo hoSo = viewModel.hoso.getValue();
            if (!(hoSo instanceof IHoSoEditable)) {
                return;
            }
            callback.accept(s.toString(), (IHoSoEditable) hoSo);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.tryFetch();
    }
}
