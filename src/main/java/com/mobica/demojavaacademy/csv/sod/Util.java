package com.mobica.demojavaacademy.csv.sod;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {
    public static Date cutOffTime(Date date) {
        var cal = new GregorianCalendar();
        cal.setTime(date);

        return new GregorianCalendar(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        )
                .getTime();
    }
}
