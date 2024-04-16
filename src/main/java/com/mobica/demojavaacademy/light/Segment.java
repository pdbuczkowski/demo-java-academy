package com.mobica.demojavaacademy.light;

import java.util.LinkedList;
import java.util.Queue;

class Segment<T> {
    final int maxSize;
    Queue<T> content = new LinkedList<>();

    Segment(int maxSize) {
        this.maxSize = maxSize;
    }

    T shift(T element) {
        content.add(element);
        if (content.size() > maxSize) {
            return content.remove();
        }
        return null;
    }
}

class RecordSegment extends Segment<Record> {

    RecordSegment(int maxSize) {
        super(maxSize);
    }


}