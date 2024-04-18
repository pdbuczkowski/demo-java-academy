package com.mobica.demojavaacademy.csv.sod;

import com.mobica.demojavaacademy.csv.model.Closed;
import com.mobica.demojavaacademy.csv.model.Open;

import java.util.LinkedList;
import java.util.List;

public class StrategyResult {
    public List<Closed> closed = new LinkedList<>();
    public List<Open> open = new LinkedList<>();
}
