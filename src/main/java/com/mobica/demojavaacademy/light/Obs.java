package com.mobica.demojavaacademy.light;

import java.util.LinkedList;

interface Obs {
    LinkedList<Record> records = new LinkedList<>();

    default void next(Record record) {
        records.add(record);
    }
}

class ObsL implements Obs {

    @Override
    public void next(Record record) {

    }
}

class ObsS {

}
