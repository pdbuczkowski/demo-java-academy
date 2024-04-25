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

        var bars25 = barsByDay.get(new GregorianCalendar(2024, Calendar.MARCH,25).getTime());

        var b = dataan.buy(new LinkedList<>(), 0, 0);
//        var b = dataan.buy(new LinkedList<>(), 1.0, 0.5);
//        var b = dataan.b(bars25, 1.0, 0.5);
        System.out.println("b=" + b);
        var avgB = b / bars25.stream().count();
        System.out.println("avgB=" + avgB);
    }
}
