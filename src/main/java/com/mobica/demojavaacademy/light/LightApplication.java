package com.mobica.demojavaacademy.light;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LightApplication {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("light2403.csv"));
        scanner.useDelimiter(";");

        var records = new ArrayList<Record>();

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var parts = line.split(";");
            var record = new Record(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            records.add(record);
        }

        System.out.println(records);

        scanner.close();
    }
}
