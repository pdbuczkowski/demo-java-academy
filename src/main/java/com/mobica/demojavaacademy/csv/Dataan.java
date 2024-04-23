package com.mobica.demojavaacademy.csv;

import com.mobica.demojavaacademy.csv.model.Cl;
import com.mobica.demojavaacademy.csv.model.Op;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.mobica.demojavaacademy.csv.sod.Util.justDate;

public class Dataan {

    public Map<Date, LinkedList<Bar>> aggregateByDays(Collection<Bar> data) {
        var map = new HashMap<Date, LinkedList<Bar>>();

        data.forEach(record -> {
            var date = justDate(record.date());
            if (map.containsKey(date)) {
                map.get(date).add(record);
            } else {
                var list = new LinkedList<Bar>();
                list.add(record);
                map.put(date, list);
            }
        });
        return map;
    }

    public double b(LinkedList<Bar> data, double tp, double sl) {
        final var oPos = new LinkedList<Op>();
        final var cPos = new LinkedList<Cl>();

        data.forEach(bar -> {
            oPos.add(new Op(bar.open(), bar.open() + tp, bar.open() - sl));

            var closedTp = oPos.stream().filter(open -> bar.high() >= open.tp()).toList();
            oPos.removeAll(closedTp);
            closedTp.stream()
                    .map(p -> new Cl(p.open(), p.tp()))
                    .forEach(cPos::add);

            var closedSl = oPos.stream().filter(open -> bar.low() <= open.sl()).toList();
            oPos.removeAll(closedSl);
            closedSl.stream()
                    .map(p -> new Cl(p.open(), p.sl()))
                    .forEach(cPos::add);
        });

        var result = 0.0;
        result += cPos.stream().map(p -> p.closed() - p.opened()).reduce(0.0, Double::sum);
        result += oPos.stream().map(p -> data.peekLast().close() - p.open()).reduce(0.0, Double::sum);
        return result;
    }

    double s(LinkedList<Bar> data, double tp, double sl) {
        // TODO data.isNotEmpty, tp && sl > 0.0

        final var oPos = new LinkedList<Op>();
        final var cPos = new LinkedList<Cl>();

        data.forEach(bar -> {
            oPos.add(new Op(bar.open(), bar.open() - tp, bar.open() + sl));

            var closedTp = oPos.stream().filter(p -> bar.low() <= p.tp()).toList();
            oPos.removeAll(closedTp);
            closedTp.stream()
                    .map(p -> new Cl(p.open(), p.tp()))
                    .forEach(cPos::add);

            var closedSl = oPos.stream().filter(p -> bar.high() >= p.sl()).toList();
            oPos.removeAll(closedSl);
            closedSl.stream()
                    .map(p -> new Cl(p.open(), p.sl()))
                    .forEach(cPos::add);
        });

        var result = 0.0;
        result += cPos.stream().map(p -> -(p.closed() - p.opened())).reduce(0.0, Double::sum);
        result += oPos.stream().map(p -> -(data.peekLast().close() - p.open())).reduce(0.0, Double::sum);
        return result;
    }
}