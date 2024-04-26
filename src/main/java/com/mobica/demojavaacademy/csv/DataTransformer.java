package com.mobica.demojavaacademy.csv;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class DataTransformer {
    private final List<Bar> data;

    DataTransformer(List<Bar> data) {
        this.data = data;
    }

    void processAnnotations(Object object) {
        var clazz = object.getClass();
        for (Field field: clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(TransformData.class)) {
                field.setAccessible(true);

                List<Bar> outputList = new LinkedList<>();
                var transform = field.getAnnotation(TransformData.class);
                for (int beginIndex = 0; beginIndex < data.size(); beginIndex += transform.merge()) {
                    outputList.add(
                        toBar(
                            data.subList(beginIndex, Math.min(beginIndex + transform.merge(), data.size()))
                        )
                    );
                }

                try {
                    field.set(object, outputList);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Unable to set value for field " + field.getName());
                }
            }
        }
    }

    private Bar toBar(List<Bar> bars) {
        if (bars.isEmpty()) {
            return null;
        }
        return new Bar(
                bars.get(0).d(),
                bars.get(0).o(),
                bars.stream().mapToDouble(Bar::h).max().getAsDouble(),
                bars.stream().mapToDouble(Bar::l).min().getAsDouble(),
                bars.get(bars.size()-1).c(),
                bars.stream().map(Bar::v).reduce(0.0, Double::sum)
        );
    }
}
