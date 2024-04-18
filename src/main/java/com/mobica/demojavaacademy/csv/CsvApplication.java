package com.mobica.demojavaacademy.csv;

import com.mobica.demojavaacademy.csv.sod.SoD;
import com.mobica.demojavaacademy.csv.sod.StrategyResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvApplication {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        var scanner = new Scanner(new File("light2403.csv"));
        scanner.useDelimiter(";");

        var records = new ArrayList<Record>();

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var parts = line.split(";");
            var record = new Record(
                    sdf.parse(parts[0]),
                    Double.parseDouble(parts[1]),
                    Double.parseDouble(parts[2]),
                    Double.parseDouble(parts[3]),
                    Double.parseDouble(parts[4]),
                    Double.parseDouble(parts[5])
            );
            records.add(record);
        }

        var sod = new SoD();
        var map = sod.groupDays(records);

        var sodResults = new HashMap<Date, StrategyResult>();
        map.keySet().stream().forEach(date -> {
            var dateRecords = map.get(date);
            var result = sod.strategyBuy(dateRecords, 0.5, 1.0);
        });
        scanner.close();
    }
}
