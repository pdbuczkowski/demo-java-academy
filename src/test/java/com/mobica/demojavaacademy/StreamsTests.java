package com.mobica.demojavaacademy;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsTests {

    private final Integer[] array = {5, 6, 7, 2, 1, 3532, 234, 4, 5};
    private final Stream<Integer> intStream = Arrays.stream(array);

    @Test
    void countElements() {
        Truth
                .assertThat(intStream.count())
                .isEqualTo(array.length);
    }

    @Test
    void mapElements() {
        Stream<String> stringStream = intStream.map(element -> "element_" + element);

        var stringArray = stringStream.toArray();
        for (var index = 0; index < stringArray.length; index++) {
            Truth
                    .assertThat(stringArray[index])
                    .isEqualTo("element_" + array[index++]);
        }
    }

    @Test
    void toList() {
        List<Integer> list = intStream.toList();

        Truth
                .assertThat(list.toArray())
                .isEqualTo(array);
    }

    @Test
    void toSet() {
        Set<Integer> set = intStream.collect(Collectors.toSet());

        Truth
                .assertThat(set.toArray())
                .isEqualTo(new Integer[]{1, 2, 4, 5, 6, 7, 234, 3532});
    }

    @Test
    void sort() {
        Stream<Integer> sorted = intStream.sorted();
        var sorterArray = sorted.toArray();

        Truth
                .assertThat(sorterArray)
                .isEqualTo(new Integer[]{1, 2, 4, 5, 5, 6, 7, 234, 3532});
    }

    @Test
    void flatMap() {
        var sb = new StringBuilder();

        intStream.flatMap(e ->
                e % 2 == 0
                        ? Stream.of("a", "b", "c")
                        : Stream.of("d", "e", "f")
        ).forEach(sb::append);

        Truth
                .assertThat(sb.toString())
                .isEqualTo("defabcdefabcdefabcabcabcdef");
    }

    @Test
    void distinct() {
        var afterDistinct = Stream.of(2, 2, 2, 3, 22, 2)
                .distinct()
                .toList();
        Truth.assertThat(afterDistinct.size()).isEqualTo(3);
    }

    @Test
    void allMatch() {
        var result = Stream.of(1, 2, 3, 4, 5, 6, 7)
                .allMatch(e -> e < 10);
        Truth.assertThat(result).isTrue();
    }

    @Test
    void dropWhile() {
        var result = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .dropWhile(e -> e <= 3)
                .toArray();
        Truth.assertThat(result.length).isEqualTo(6);
        Truth.assertThat(result).isEqualTo(new Integer[]{4, 5, 6, 7, 8, 9});
    }

    @Test
    void generateAndLimit() {
        var infiniteStream = Stream.generate(() -> new Random().nextInt());

        var limitedStream = infiniteStream.limit(9);
        var list = limitedStream.toList();

        Truth.assertWithMessage("List size should be the same as the 'limit' operator argument.")
                .that(list.size())
                .isEqualTo(9);
    }
}
