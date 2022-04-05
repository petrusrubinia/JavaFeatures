package com.company.streams.reduction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamReductionWithCollectMethod {

    /**
     * The reduction of a stream can be executed by using terminal operation : collect().
     * This method accepts an argument of the type Collector which specifies the mechanism of reduction.
     * There are already created, predefined collectors for most common operations,
     * but this collector can be also customised.
     *
     * e.g of Collectors: averagingInt, summingInt, summarizingInt, Collectos.groupingBy,
     *                  Collectors.partitioningBy, Collectors.collectingAndThen
     */
    public void applyCollectMethod() {
        List<String> list = new ArrayList<>(){{
           add("abc");
           add("bla bla");
           add("bubu");
        }};

        List<Integer> integers = new ArrayList<>() {{
           add(1);
           add(20);
           add(24);
        }};
        //reducing to list.
        List<String> listTOList = list.stream().map(x-> x.toUpperCase()).collect(Collectors.toList());
        //reducing to String; => [abc, bla bla, bubu]
        String listToString = list.stream().map(x -> x.toUpperCase())
                .collect(Collectors.joining(", ", "[", "]"));
        // processing the average value of all numeric elements of the stream
        double average = integers.stream().collect(Collectors.averagingInt(x -> x));
    }

    public Collector<String, ?, LinkedList<String>> toLinkedList(){
        return Collector.of(LinkedList::new, LinkedList::add,
                (first, second) -> {
                    first.addAll(second);
                    return first;
                });
    }

    public LinkedList<String> applyCollectMethodOnCustomCollector(){
        List<String> list = new ArrayList<>(){{
            add("abc");
            add("bla bla");
            add("bubu");
        }};
        return (LinkedList<String>) list.stream().collect(toLinkedList());
    }
}
