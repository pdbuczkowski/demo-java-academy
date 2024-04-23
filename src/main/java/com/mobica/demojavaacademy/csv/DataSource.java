package com.mobica.demojavaacademy.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DataSource {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    final List<Bar> bars;

    DataSource(String file) throws FileNotFoundException, ParseException {
        bars = new LinkedList<>();

        var scanner = new Scanner(new File(file));
        while (scanner.hasNextLine()) {
            bars.add(toBar(scanner.nextLine()));
        }
        scanner.close();
    }

    DataSource(String[] lines) throws ParseException {
        bars = new LinkedList<>();

        for (String line: lines) {
            bars.add(toBar(line));
        }
    }

    private Bar toBar(String line) throws ParseException {
        var parts = line.split(";");
        return new Bar(
                sdf.parse(parts[0]),
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]),
                Double.parseDouble(parts[4]),
                Double.parseDouble(parts[5])
        );
    }
}
