package com.example.truonghoc.domain.bo;

import androidx.annotation.NonNull;

import com.example.truonghoc.domain.ui.HasRawDate;
import com.example.truonghoc.domain.ui.IDate;
import com.example.truonghoc.domain.ui.RawDateSettable;
import com.example.truonghoc.presentation.helper.DateTimeParser;
import com.example.truonghoc.presentation.helper.TextFormatter;

import java.util.Date;

public class MutableDate implements IDate, HasRawDate, RawDateSettable {
    private String value;
    private Date dateObj;

    public MutableDate(String dateStr) {
        this.value = dateStr;
        this.dateObj = DateTimeParser.getInstance().parse(dateStr);
    }

    @Override
    public Date getRawDate() {
        return dateObj;
    }

    @NonNull
    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public void setRawDate(Date date) {
        this.dateObj = date;
        this.value = TextFormatter.getInstance().format(date);
    }
}
