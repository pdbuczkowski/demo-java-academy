package com.mobica.demojavaacademy.csv;

public class Structure<T> {
    final Segment<T> first, second;

    Structure(int maxSize) {
        first = new Segment<>(maxSize);
        second = new Segment<>(maxSize);
    }

    T offer(T element) {
        var shifted = first.shift(element);
        if (shifted != null) {
            return second.shift(shifted);
        }
        return null;
    }
}
