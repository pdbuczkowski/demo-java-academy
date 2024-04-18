package com.mobica.demojavaacademy.csv;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

public class StructureTests {
    @Test
    void offer() {
        var structure = new Structure<Integer>(3);

        Truth.assertThat(structure.offer(8)).isNull();
        Truth.assertThat(structure.offer(34)).isNull();
        Truth.assertThat(structure.offer(3)).isNull();
        Truth.assertThat(structure.offer(99)).isNull();
        Truth.assertThat(structure.offer(43)).isNull();
        Truth.assertThat(structure.offer(22)).isNull();
        Truth.assertThat(structure.offer(11)).isEqualTo(8);
        Truth.assertThat(structure.offer(55)).isEqualTo(34);
        Truth.assertThat(structure.offer(9)).isEqualTo(3);
        Truth.assertThat(structure.offer(17)).isEqualTo(99);
        Truth.assertThat(structure.offer(98)).isEqualTo(43);
        Truth.assertThat(structure.offer(7)).isEqualTo(22);
        Truth.assertThat(structure.offer(76)).isEqualTo(11);
        Truth.assertThat(structure.offer(6)).isEqualTo(55);
        Truth.assertThat(structure.offer(61)).isEqualTo(9);
    }
}
