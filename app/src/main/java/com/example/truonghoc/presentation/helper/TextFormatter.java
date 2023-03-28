package com.example.truonghoc.presentation.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TextFormatter {
    private static final TextFormatter sTextFormatter = new TextFormatter();

    public static TextFormatter getInstance() {
        return sTextFormatter;
    }

    public String format(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date);
    }
}
