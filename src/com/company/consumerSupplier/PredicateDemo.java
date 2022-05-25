package com.company.consumerSupplier;

import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(String[] args) {
        Predicate<Integer> predicate = integer -> {
            if (integer % 2 == 0) {
                return true;
            }
            return false;
        };
        System.out.println(predicate.test(7));
    }
}
