package com.mobica.demojavaacademy.csv;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class SegmentTests {

    @Test
    void shift() {
        var segment = new Segment<Integer>(4);

        segment.shift(5);
        segment.shift(9);
        segment.shift(3);
        var shifted = segment.shift(2);
        Truth.assertThat(shifted).isNull();

        shifted = segment.shift(1);
        Truth.assertThat(shifted).isEqualTo(5);
    }

    @Test
    void traits() {
        var segment = new RecordSegment(3);

        segment.shift(new Bar(new Date(), 1.0, 2.0, 0.5, 1.5, 1));
        segment.shift(new Bar(new Date(), 2.0, 4.0, -10, -5, 1));
        segment.shift(new Bar(new Date(), -7, -6, -10, -6.5, 1));

        Truth.assertThat(segment.h()).isEqualTo(4);
        Truth.assertThat(segment.l()).isEqualTo(-10);
        Truth.assertThat(segment.o()).isEqualTo(-7);
        Truth.assertThat(segment.c()).isEqualTo(1.5);
    }
}
