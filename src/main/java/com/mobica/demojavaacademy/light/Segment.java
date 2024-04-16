package com.mobica.demojavaacademy.light;

import java.util.LinkedList;
import java.util.NoSuchElementException;

class Segment<T> {
    final int maxSize;
    LinkedList<T> content = new LinkedList<>();

    Segment(int maxSize) {
        this.maxSize = maxSize;
    }

    T shift(T element) {
        content.addFirst(element);
        if (content.size() > maxSize) {
            return content.removeLast();
        }
        return null;
    }
}

class RecordSegment extends Segment<Record> {

    private static final String ERR_MSG_EMPTY_CONTENT = "Segment content is empty.";

    RecordSegment(int maxSize) {
        super(maxSize);
    }

    double h() throws NoSuchElementException {
        var opt = content.stream().max((r1, r2) -> {
            var diff = r1.h() - r2.h();
            if (diff == 0.0) {
                return 0;
            }
            return diff > 0 ? 1 : -1;
        });

        if (opt.isPresent()) {
            return opt.get().h();
        } else {
            throw new NoSuchElementException(ERR_MSG_EMPTY_CONTENT);
        }
    }

    double l() throws NoSuchElementException {
        var opt = content.stream().min((r1, r2) -> {
            var diff = r1.l() - r2.l();
            if (diff == 0.0) {
                return 0;
            }
            return diff > 0 ? 1 : -1;
        });

        if (opt.isPresent()) {
            return opt.get().l();
        } else {
            throw new NoSuchElementException(ERR_MSG_EMPTY_CONTENT);
        }
    }

    double o() throws NoSuchElementException {
        if (content.isEmpty()) {
            throw new NoSuchElementException(ERR_MSG_EMPTY_CONTENT);
        }
        return content.peekFirst().o();
    }

    double c() throws NoSuchElementException {
        if (content.isEmpty()) {
            throw new NoSuchElementException(ERR_MSG_EMPTY_CONTENT);
        }
        return content.peekLast().c();
    }
}
