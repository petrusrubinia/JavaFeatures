package com.company.reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class ExerciseReflation {

    @Test
    public void givenObject_whenGetsFieldsNamesAtRuntime_thenCorrect() {
        Object person = new Person();
        Field[] fields = person.getClass().getDeclaredFields();
        List<String> actualFieldNames = getFieldNames(fields);
        assertTrue(Arrays.asList("name", "age").containsAll(actualFieldNames));
    }

    private List<String> getFieldNames(Field[] fields) {
        return Arrays.stream(fields)
                .map(field -> field.getName())
                .collect(Collectors.toList());
    }

}
