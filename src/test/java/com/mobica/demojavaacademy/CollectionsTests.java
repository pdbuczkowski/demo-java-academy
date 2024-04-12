package com.mobica.demojavaacademy;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class CollectionsTests {

    @Test
    void testArrayList() {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(2);
        list.add(3);
        list.add(5);
        list.add(8);

        Truth.assertThat(list.size()).isEqualTo(4);

        list.add(5);
        list.add(8);

        Truth.assertThat(list.size()).isEqualTo(6);
    }

    @Test
    void testLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(2);
        list.add(3);
        list.add(5);
        list.add(8);

        Truth.assertThat(list.size()).isEqualTo(4);

        list.add(5);
        list.add(8);

        Truth.assertThat(list.size()).isEqualTo(6);
    }

    @Test
    void testHashSet() {
        HashSet<Integer> set = new HashSet<>();

        set.add(2);
        set.add(3);
        set.add(5);
        set.add(8);

        Truth.assertThat(set.size()).isEqualTo(4);

        set.add(5);
        set.add(8);

        Truth.assertThat(set.size()).isEqualTo(4);

        Truth.assertThat(set.contains(5)).isTrue();
        Truth.assertThat(set.remove(5)).isTrue();
        Truth.assertThat(set.size()).isEqualTo(3);
        Truth.assertThat(set.contains(5)).isFalse();
        Truth.assertThat(set.remove(5)).isFalse();
    }

    @Test
    void testHashMap() {
        HashMap<String, String> map = new HashMap<>();

        map.put("Asia", "A");
        map.put("Basia", "B");
        map.put("Czesia", "C");

        Truth.assertThat(map.size()).isEqualTo(3);

        Truth.assertThat(map.put("Czesia", "D")).isEqualTo("C");

        System.out.println("map: " + map);

        Truth.assertThat(map.putIfAbsent("Emila", "E")).isNull();
        Truth.assertThat(map.keySet().size()).isEqualTo(4);

        Truth.assertThat(map.putIfAbsent("Czesia", "F")).isEqualTo("D");
        Truth.assertThat(map.get("Czesia")).isEqualTo("D");

        Truth.assertThat(map.put("Emila", "A")).isEqualTo("E");

        Truth.assertThat(map.keySet().size()).isEqualTo(4); // "Asia", "Basia", "Czesia", "Emila"
        Truth.assertThat(map.values().size()).isEqualTo(3); // "A", "B", "D", "A"
    }

    @Test
    void testPriorityQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(7);
        queue.add(5);
        queue.add(8);
        queue.add(5);

        System.out.println("queue: " + queue);

        Truth.assertThat(queue.size()).isEqualTo(4);

        Truth.assertThat(queue.remove(3)).isFalse();
        Truth.assertThat(queue.size()).isEqualTo(4);

        Truth.assertThat(queue.remove(8)).isTrue();
        Truth.assertThat(queue.size()).isEqualTo(3);

        Truth.assertThat(queue.peek()).isEqualTo(5);
        Truth.assertThat(queue.size()).isEqualTo(3);    // the "peek" operation doesn't remove element from queue

        Truth.assertThat(queue.poll()).isEqualTo(5);
        Truth.assertThat(queue.size()).isEqualTo(2);    // the first element has been removed from queue by the "poll" operation
    }
}
