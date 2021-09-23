package com.cuidar.app_cer.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCalendar {

    SimpleDateFormat sdf;

    public MyCalendar() {
        this.sdf = new SimpleDateFormat("yyyy/MM/dd");
    }


    public String getFirstDayOfMonth(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String formattedDate = this.sdf.format(calendar.getTime());

        return formattedDate;
    }

    public String getLastDayOfMonth(){
        Calendar calendar = Calendar.getInstance();

        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        String formattedDate = this.sdf.format(calendar.getTime());

        return formattedDate;
    }
}
