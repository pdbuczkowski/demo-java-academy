package com.mobica.demojavaacademy.light;

public class DataLine {
//    final String d, o, h, l, c, v;
    final String[] parts;

    DataLine(String line) {
        parts = line.split(";");
    }

    String d() { return parts[0]; }
    String o() { return parts[1]; }
    String h() { return parts[2]; }
    String l() { return parts[3]; }
    String c() { return parts[4]; }
    String v() { return parts[5]; }
}
