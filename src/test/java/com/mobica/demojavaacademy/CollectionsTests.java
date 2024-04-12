package com.mobica.demojavaacademy;

import com.google.common.truth.Truth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
public class CollectionsTests {

    @Test
    public void arrayList() {
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
    public void linkedList() {
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
    public void hashSet() {
        HashSet<Integer> list = new HashSet<>();

        list.add(2);
        list.add(3);
        list.add(5);
        list.add(8);

        Truth.assertThat(list.size()).isEqualTo(4);

        list.add(5);
        list.add(8);

        Truth.assertThat(list.size()).isEqualTo(4);
    }
}