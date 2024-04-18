package com.mobica.demojavaacademy.csv.sod;

import com.mobica.demojavaacademy.csv.Record;
import com.mobica.demojavaacademy.csv.model.Closed;
import com.mobica.demojavaacademy.csv.model.Open;

import java.util.*;

import static com.mobica.demojavaacademy.csv.sod.Util.cutOffTime;

public class SoD {
    public Map<Date, List<Record>> groupDays(Collection<Record> data) {
        var map = new HashMap<Date, List<Record>>();
        data.forEach(record -> {
            var date = cutOffTime(record.date());
            if (map.containsKey(date)) {
                map.get(date).add(record);
            } else {
                var list = new LinkedList<Record>();
                list.add(record);
                map.put(date, list);
            }
        });
        return map;
    }

    public StrategyResult strategyBuy(Collection<Record> strategyData, double tp, double sl) {
        var result = new StrategyResult();

        strategyData.forEach(record -> {
            result.open.add(new Open(record.open(), record.open() + tp, record.open() - sl));

            var closedTp = result.open.stream().filter(open -> record.high() >= open.tp()).toList();
            var closedSl = result.open.stream().filter(open -> record.low() <= open.sl()).toList();

            result.open.removeAll(closedTp);
            result.open.removeAll(closedSl);

            closedTp.stream()
                    .map(open -> new Closed(open.open(), open.tp()))
                    .forEach(closed -> result.closed.add(closed));
            closedSl.stream()
                    .map(open -> new Closed(open.open(), open.sl()))
                    .forEach(closed -> result.closed.add(closed));
        });
        return result;
    }
}
