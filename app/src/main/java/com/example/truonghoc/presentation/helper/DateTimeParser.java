package com.example.truonghoc.presentation.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeParser {
    private static final DateTimeParser sDateTimeParser = new DateTimeParser();

    public static DateTimeParser getInstance() {
        return sDateTimeParser;
    }

    public Date parse(String dateStr) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}
