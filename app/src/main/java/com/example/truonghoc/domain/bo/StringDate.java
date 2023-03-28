package com.example.truonghoc.domain.bo;

import androidx.annotation.NonNull;

import com.example.truonghoc.domain.ui.IDate;

public class StringDate implements IDate {
    private final String value;

    public StringDate(String value) {
        this.value = value;
    }

    public StringDate() {
        this.value = "";
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}
