package com.company.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class LazyInvocationPractice {

    private static final Logger LOGGER = Logger.getLogger(LazyInvocationPractice.class.getName());
    private long counter;

    /**
     * The intermediate operations are lazy. This means that they will be invoked only if
     * it is necessary for the terminal operation execution.
     * <p>
     * After calling this method, the counter value is 0 => the fielter() method wasn't even called once.
     * The reason is that the terminal operation is missing.
     */
    public void applyLazyInvocationWithOUTTerminalOperation() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0;
        Stream<String> stream = list.stream().filter(element -> {
            wasCalled();
            return element.contains("2");
        });
    }


    /**
     * This methods calls the filter() method twice and the map() method once.
     * <p>
     * This is becasue the pipeline executes vertically. In our example,
     * the first element of the stream didn't satisfy the filter's predicate.
     * Then we invoked the filter() method for the second element, which passed the filter.
     * Without calling the filter() for the third element, we went down through the pipeline
     * to the map() method.
     * <p>
     * The findFirst() operation satisfies by just one element. So in this particular example,
     * the lazy invocation allowed us to avoid two method calls, one for the filter() and one for the map().
     */
    public void applyLazyInvocationWithTerminalOperation() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0;
        Optional<String> stream = list.stream().filter(element -> {
            LOGGER.info("filter() was called");
            return element.contains("2");
        }).map(element -> {
            LOGGER.info("map() was called");
            return element.toUpperCase();
        }).findFirst();
    }

    /**
     * This method calls the filter method until it finds a matching value,
     * after that, calls the map method.
     * After the map method is called the process is taken from the start,
     * This is becasue the pipeline executes vertically.
     */
    public void applyLazyInvocationWithTerminalOperation2() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0;
        long nr = list.stream().filter(element -> {
            LOGGER.info("filter() was called");
            return element.contains("2");
        }).map(element -> {
            LOGGER.info("map() was called");
            return element.toUpperCase();
        }).count();
    }

    private void wasCalled() {
        counter++;
    }

    /**
     * Execution of this code will increase the value of the counter by three.
     * This means that we called the map() method of the stream three times, but the
     * value of the size is one. So the resulting stream has just one element, and we
     * executed the expensive map() operations for no reason two out of the three times.
     */
    public void applyWrongOrderOfExecution() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().map(el -> {
            wasCalled();
            return el.substring(0, 3);
        }).skip(2).count();
    }

    /**
     * RULE: intermediate operations which reduce the size of the stream should be placed before
     * operations which are applying to each element.
     * So, we need to keep methods are skip(), filter() and distinct() at the top of the stream pipeline.
     */
    public void applyBestPracticeOderOfExecution() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().skip(2).map(el -> {
            wasCalled();
            return el.substring(0, 3);
        }).count();
    }
}
