package com.mobica.demojavaacademy.csv;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class CsvApplication {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        final var source = new DataSource("light2403.csv");
        final var dataan = new Dataan();

        var barsByDay = dataan.aggregateByDays(source.bars);

        System.out.println(
            barsByDay.keySet().stream()
                .map(date -> {
                    var cal = Calendar.getInstance();
                    cal.setTime(date);
                    return cal.get(Calendar.DAY_OF_MONTH);
                })
                .toList()
        );

        var bars25 = barsByDay.get(new GregorianCalendar(2024, Calendar.MARCH, 27).getTime());

        var l = dataan.openLong(bars25, 1.0, 0.5);
        System.out.println("l=" + l);
        var avgL = l / bars25.size();
        System.out.println("avgL=" + avgL);

        var s = dataan.openShort(bars25, 0.5, 1.0);
        System.out.println("s=" + s);
        var avgS = s / bars25.size();
        System.out.println("avgS=" + avgS);

        // the following field has been initialized by use of annotation
        source.m5.forEach(System.out::println);
    }
}
