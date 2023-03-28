package com.example.truonghoc;

import org.junit.Test;

import java.util.Calendar;

public class SimpleUnitTest {
    @Test
    public void testCompareDate() {
        Calendar customCalendar = Calendar.getInstance();
        customCalendar.set(Calendar.DATE, 28);
        customCalendar.set(Calendar.MONTH, Calendar.MARCH);
        customCalendar.set(Calendar.YEAR, 2022);

        Calendar todayCalendar = Calendar.getInstance();


        boolean isCustomEqualsToday =
                customCalendar.get(Calendar.DATE) == todayCalendar.get(Calendar.DATE)
                        && customCalendar.get(Calendar.MONTH) == todayCalendar.get(Calendar.MONTH)
                        && customCalendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR);

        if (isCustomEqualsToday) System.out.println("Custom equals today");
        System.out.println("Custom not equals today");

    }
}
