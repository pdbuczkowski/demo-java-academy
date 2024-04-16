package com.mobica.demojavaacademy.light;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

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
}
