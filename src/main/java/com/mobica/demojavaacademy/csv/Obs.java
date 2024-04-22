package com.mobica.demojavaacademy.csv;

import java.util.LinkedList;

interface Obs {
    LinkedList<Bar> BARS = new LinkedList<>();

    default void next(Bar bar) {
        BARS.add(bar);
    }
}

class ObsL implements Obs {

    @Override
    public void next(Bar bar) {

    }
}

class ObsS {

}
