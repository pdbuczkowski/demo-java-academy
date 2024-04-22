package com.mobica.demojavaacademy.csv;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CsvApplication {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        var dataan = new Dataan("light2403.csv");

        var barsByDay = dataan.aggregateByDays(dataan.bars);

        System.out.println(
                barsByDay.keySet().stream()
                        .map(date -> {
                            var cal = Calendar.getInstance();
                            cal.setTime(date);
                            return cal.get(Calendar.DAY_OF_MONTH);
                        })
                        .toList()
        );

        var bars25 = barsByDay.get(new GregorianCalendar(2024, Calendar.MARCH,25).getTime());

        var b = dataan.b(bars25, 1.0, 0.5);
        System.out.println("b=" + b);
        var avgB = b / bars25.stream().count();
        System.out.println("avgB=" + avgB);
    }
}
