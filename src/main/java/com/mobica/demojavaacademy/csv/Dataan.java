package com.mobica.demojavaacademy.csv;

import com.mobica.demojavaacademy.csv.model.Cl;
import com.mobica.demojavaacademy.csv.model.Op;

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

    public double buy(LinkedList<Bar> data, double tp, double sl) {
        assert tp > 0 : "TP should be > 0.0";
        assert sl > 0 : "SL should be > 0.0";

        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data set should not  be empty.");
        }

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

        return
                cPos.stream().map(p -> p.closed() - p.opened()).reduce(0.0, Double::sum) +
                oPos.stream().map(p -> data.peekLast().close() - p.open()).reduce(0.0, Double::sum);
    }

    double sell(LinkedList<Bar> data, double tp, double sl) {
        assert tp > 0 : "TP should be > 0.0";
        assert sl > 0 : "SL should be > 0.0";

        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data set should not be empty.");
        }

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

        return
                cPos.stream().map(p -> -(p.closed() - p.opened())).reduce(0.0, Double::sum) +
                oPos.stream().map(p -> -(data.peekLast().close() - p.open())).reduce(0.0, Double::sum);
    }
}
