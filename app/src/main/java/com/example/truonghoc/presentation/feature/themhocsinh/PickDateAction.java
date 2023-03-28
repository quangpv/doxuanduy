package com.example.truonghoc.presentation.feature.themhocsinh;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.TextView;

import androidx.core.util.Supplier;

import com.example.truonghoc.domain.ui.HasRawDate;
import com.example.truonghoc.domain.ui.IDate;
import com.example.truonghoc.domain.ui.RawDateSettable;

import java.util.Calendar;
import java.util.Date;

public class PickDateAction implements View.OnClickListener {
    private final Supplier<IDate> dateSupplier;

    public PickDateAction(Supplier<IDate> dateSupplier) {
        this.dateSupplier = dateSupplier;
    }

    @Override
    public void onClick(View v) {
        IDate selectedDate = dateSupplier.get();
        if (!(selectedDate instanceof RawDateSettable)) return;
        RawDateSettable settable = (RawDateSettable) selectedDate;

        Date currentDate = null;
        Calendar maxCalendar = Calendar.getInstance();

        maxCalendar.set(Calendar.YEAR, maxCalendar.get(Calendar.YEAR) - 5);
        Date maxDateOfBirth = maxCalendar.getTime();
        if (selectedDate instanceof HasRawDate) {
            currentDate = ((HasRawDate) selectedDate).getRawDate();
        }
        if (currentDate == null) {
            currentDate = maxDateOfBirth;
        }
        Calendar calendarReturn = Calendar.getInstance();

        calendarReturn.setTime(currentDate);
        DatePickerDialog dialog = new DatePickerDialog(v.getContext(), 0, (view, year, month, dayOfMonth) -> {
            calendarReturn.set(Calendar.YEAR, year);
            calendarReturn.set(Calendar.MONTH, month);
            calendarReturn.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            settable.setRawDate(calendarReturn.getTime());
            if (v instanceof TextView) {
                ((TextView) v).setText(selectedDate.toString());
            }
        }, calendarReturn.get(Calendar.YEAR), calendarReturn.get(Calendar.MONTH), calendarReturn.get(Calendar.DAY_OF_MONTH));

        maxCalendar.setTime(maxDateOfBirth);
        dialog.getDatePicker().setMaxDate(maxCalendar.getTime().getTime());
        dialog.show();
    }
}