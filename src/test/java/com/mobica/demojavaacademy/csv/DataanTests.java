package com.mobica.demojavaacademy.csv;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class DataanTests {
    @Test
    void test() {
        final var list = getList();

        list.add(0);
        list.clear();

        assert !list.isEmpty();
    }

    List<Integer> getList() {
        final var list = new LinkedList<Integer>();
        return list;
    }
}
