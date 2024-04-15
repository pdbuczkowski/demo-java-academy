package com.mobica.demojavaacademy;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Stream.builder;

public class StreamsTests {

    //    List<Integer> list = new ArrayList.of(6,7,8,9,4,3,2,5,1);
    private final Integer[] array = {5, 6, 7, 2, 1, 3532, 234, 4, 5};
    private final Stream<Integer> intStream = Arrays.stream(array);

    @Test
    void countElements() {
        Truth.assertThat(intStream.count()).isEqualTo(array.length);
    }

    @Test
    void mapElements() {
        Stream<String> stringStream = intStream.map(element -> "element_" + element);

        stringStream.forEach(System.out::println);
    }

    @Test
    void toList() {
        List<Integer> list = intStream.toList();
        System.out.println(list);
    }

    @Test
    void toSet() {
        Set<Integer> list = intStream.collect(Collectors.toSet());
        System.out.println(list);
    }

    @Test
    void sort() {
        Stream<Integer> sorted = intStream.sorted();
        sorted.forEach(System.out::println);
    }

    @Test
    void flatMap() {
        intStream.flatMap(e ->
                e % 2 == 0
                        ? Stream.of("a", "b", "c")
                        : Stream.of("d", "e", "f")
        ).forEach(System.out::println);
    }

    @Test
    void distinct() {
        var afterDistinct = Stream.of(2,2,2,3,22,2)
                .distinct()
                .toList();
        Truth.assertThat(afterDistinct.size()).isEqualTo(3);
    }

    @Test
    void allMatch() {
        var result = Stream.of(1,2,3,4,5,6,7)
                .allMatch(e -> e < 10);
        Truth.assertThat(result).isTrue();
    }

    @Test
    void dropWhile() {
        var result = Stream.of(1,2,3,4,5,6,7,8,9)
                .dropWhile(e -> e <= 3)
                .toArray();
        Truth.assertThat(result.length).isEqualTo(6);
        Truth.assertThat(result).isEqualTo(new Integer[]{4,5,6,7,8,9});
    }

    @Test
    void generateAndLimit() {
        var infiniteStream = Stream.generate(() -> new Random().nextInt());

        var limitedStream = infiniteStream.limit(9);
        var list = limitedStream.toList();

        System.out.println(list);

        Truth.assertThat(list.size()).isEqualTo(9);
    }
}
