package com.mobica.demojavaacademy.csv;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class DataTransformer {
    private final List<Bar> data;

    DataTransformer(List<Bar> data) {
        this.data = data;
        System.out.println("data size: " + data.size());
    }

    void processAnnotations(Object object) {
        var clazz = object.getClass();
        for (Field field: clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(TransformData.class)) {
                field.setAccessible(true);

                List<Bar> outputList = new LinkedList<>();
                var transform = field.getAnnotation(TransformData.class);
                for (int beginIndex = 0; beginIndex < data.size(); beginIndex += transform.group()) {
                    outputList.add(
                            toBar(
                                    data.subList(beginIndex, beginIndex + transform.group())
                            )
                    );
                }

                try {
                    field.set(object, outputList);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Bar toBar(List<Bar> bars) {
        if (bars.isEmpty()) {
            return null;
        }
        return new Bar(
                bars.get(0).date(),
                bars.get(0).open(),
                bars.stream().mapToDouble(Bar::high).max().getAsDouble(),
                bars.stream().mapToDouble(Bar::low).min().getAsDouble(),
                bars.get(bars.size()-1).close(),
                bars.stream().map(Bar::volume).reduce(0.0, Double::sum)
        );
    }
}
