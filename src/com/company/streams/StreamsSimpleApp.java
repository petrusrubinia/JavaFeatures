package com.company.streams;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//process sequences of elements
// the central API class is the Stream<T>
// streams can be created from different element source -> collection; arrays by using stream() and of() methods
public class StreamsSimpleApp {

    private WaysOFCreatingStreams waysOFCreatingStreams;

    public StreamsSimpleApp(){
        waysOFCreatingStreams = new WaysOFCreatingStreams();
    }



    /**
     * The stream operations are divided into:
     *  1. Intermediate Operations => return Stream<T> && allow chaining
     *  2. Terminal Operations => return a result of definite type
     */
    private void applyStreamOperations(){
        Stream<String> stream = waysOFCreatingStreams.createStreamUsingList();
        long count = stream.distinct().count();
    }

    /**
     * Stream API helps to substitute : for, for-each and while loops.
     * It allows concentrating on operation's logic, but not on the iteration over the sequence of elements
     */
    private void iterateThroughStream(){
        Stream<String> stream = waysOFCreatingStreams.createStreamUsingList();
        boolean isExist = stream.anyMatch(el -> el.contains("a"));
    }

    /**
     * The filter method allows us to pick a stream of elements that satisfy a predicate
     */
    private Stream<String> filter() {
        // finds all elements of this stream which contain char "d" and creates
        // a new stream containing only the filtered elements.
        return waysOFCreatingStreams.createList().stream()
                .filter(el -> el.contains("d"));
    }


    /**
     * The operations map is used to convert elements of a Stream by applying a special function to them.
     * @return
     */
    private Stream<Path> mapOperation(){
        List<String> uris = new ArrayList<>(){{
           add("C:\\My.txt");
        }};
        return uris.stream().map(uri -> Paths.get(uri));
    }

    /**
     * In the "matching" category the Stream API provides the next methods: anyMatch(); allMatch(); noneMatch()
     * These operations are of type terminal.
     */
    private void matchingOperation() {
        List<String> list = waysOFCreatingStreams.createList();
        boolean exists = list.stream().anyMatch(el -> el.contains("h"));
        boolean everyStringHas = list.stream().allMatch(el -> el.contains("h"));
        boolean noMatch = list.stream().noneMatch(el -> el.contains("h"));

    }

    /**
     * The reduce(start value, accumulator function) method is used to reduce a
     * sequence of elements to some value acording to a specific function
     */
    private void reductionOperation() {
        List<Integer> integers = Arrays.asList(1, 1, 1);
        Integer reduced = integers.stream().reduce(23, (a, b) -> a + b); // 23 + 1 + 1 + 1
    }

    /**
     * Converts a stream to a Coleection or a Map;
     * @return
     */
    private List<String> collectingOperation(){
        return waysOFCreatingStreams.createList().stream().map(el -> el.toUpperCase()).collect(Collectors.toList());
    }



}
