package com.example.quanlythuvien.view.model;

import android.text.Editable;
import android.text.TextWatcher;

public interface ITextWatch extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after){

    }

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count){

    }

    @Override
    default void afterTextChanged(Editable s){
        layTextThayDoi(s.toString());
    }

    void layTextThayDoi(String text);
}
