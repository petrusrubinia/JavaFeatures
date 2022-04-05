package com.company.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AvoidablePractice {

    public AvoidablePractice() { }

    /**
     * The attempt to reuse the same reference after calling the terminal operation
     * will trigger the IllegalStateException.
     * This is a RuntimeException -> the combiler will not signalize this problem.
     */
    public void callTwoTerminalOperationOnStream() {
        try {
            Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
            Optional<String> anyElement = stream.findAny();
            Optional<String> firstElement = stream.findFirst();
        } catch ( IllegalStateException e) {
            System.out.println(e.getMessage());
            callTwoTerminalOperationsProperly();
        }
    }

    private void callTwoTerminalOperationsProperly() {
        List<String> elements = Stream.of("a", "b", "c").filter(el -> el.contains("b"))
                .collect(Collectors.toList());
        Optional<String> anyElement = elements.stream().findAny();
        Optional<String> firstElement = elements.stream().findFirst();
    }
}
