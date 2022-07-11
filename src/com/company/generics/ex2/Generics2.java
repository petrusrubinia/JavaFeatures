package com.company.generics.ex2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class Generics2 {

    /**
     * Write a generic method to exchange the positions of two different elements in an array.
     */
    @Test
    public void exchangePositions() {
        Integer[] arrayList = new Integer[]{1,2,4,5,6};
        exchange(arrayList, 2,4);
        assert arrayList[2] == 6;
        assert arrayList[4] == 4;
    }

    public <T> void exchange(T[] array, int index1, int index2) {
        T aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
    }

    /**
     * Write a generic method to find the maximal element in the range [begin, end) of a list.
     */
    @Test
    public void findMaxValue_whenTryGenerics_thenCorrect() {
        List<String> list = Arrays.asList("mama", "tata", "papa");
        String maxValue = findMaxValue(list,0,3);
        System.out.println(maxValue);
    }

    public <T extends Comparable> T findMaxValue(List<? extends T> elements, int begin, int end){
        T max = elements.get(begin);
        for (int i = begin; i < end; i++) {
            if (elements.get(i).compareTo(max) > 0) {
                max = elements.get(i);
            }
        }
        return max;
    }

}
