package com.example.truonghoc.presentation.helper;

import android.text.Editable;
import android.text.TextWatcher;

public interface OnTextChangeListener extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    default void afterTextChanged(Editable s) {
        onTextChange(s.toString());
    }

    void onTextChange(String text);
}
