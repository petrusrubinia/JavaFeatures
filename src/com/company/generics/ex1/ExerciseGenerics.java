package com.company.generics.ex1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * Write a generic method to count the number of elements in a collection that have
 * a specific property (for example, odd integers, prime numbers, palindromes).
 */
public class ExerciseGenerics {

    @Test
    public void countOddIntegers() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4);
        int count = countIf(collection, new OddPredicate());
        System.out.println("Number of odd integers = " + count);
    }

    private <T> int countIf(Collection<T> collection, UnaryPredicate<T> predicate) {
        return (int) collection.stream().filter(el -> predicate.test(el)).count();
    }
}
