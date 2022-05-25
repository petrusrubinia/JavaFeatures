package com.company.consumerSupplier;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {
    private static Consumer<Integer> consumer = (t) -> {
        System.out.println("Printing: " + t);
    };
    public static void main(String[] args) {
        useConsumerWithStreams();
        consumer.accept(10);
    }

    private static void useConsumerWithStreams() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("--------------START------------");
        list.stream().forEach(consumer);
        System.out.println("--------------END---------------");
    }

}
