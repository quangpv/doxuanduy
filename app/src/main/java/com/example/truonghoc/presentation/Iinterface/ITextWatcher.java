package com.example.truonghoc.presentation.Iinterface;

import android.text.Editable;
import android.text.TextWatcher;

public interface ITextWatcher extends TextWatcher {
    @Override
    public default void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public default void onTextChanged(CharSequence s, int start, int before, int count) {

    }
    @Override
    void afterTextChanged(Editable s);
}
