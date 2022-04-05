package com.company.streams.reduction;

import java.util.Arrays;
import java.util.List;

/**
 * The method reduce has the next params.
 *  1. Identity - an element that is the initial value of the reduction operation and the
 *                  default result if the stream is empty
 *  2. Accumulator - A function that takes two parameters
 *                 - a partial result of the reduction operation and the next element of the stream.
 *  3. Combiner - a function used to combine the partial result of the reduction operation when the reduction
 *                  is parallelized or when there;s mismatch between the types of the accumulator arguments
 *                  and the types of the accumulator implementation
 */
public class StreamReductionWIthReduceMethod {

    public void applyReduceSimpleExample(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers.stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        assert(result == 21);
    }

    public void applyReduceSWithMethodReferance(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers.stream()
                .reduce(0, Integer::sum);
        assert(result == 21);
    }

    public void joinChars() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String result = letters.stream()
                .reduce("",
                        (partialString, element) -> partialString.toUpperCase() + element.toUpperCase());
        assert letters.equals("ABCDE");
    }

    /**
     * When a stream executed in parallel, the Java runtime splits the stream into multiple substreams.
     * In such cases, we need to use a function to combine the results of the substreams into a single one.
     * This is the role of the combiner.
     */
    public void useTheThirdParamOfReduceMethod1() {
        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);
        int computedAges = ages.parallelStream().reduce(
                0,
                (a,b) -> a + b,
                Integer::sum);

        assert computedAges == 160;
    }

    /**
     * For the second param of the method reduce, its used a lambda which adds an integer
     * with an object of type User.
     * The third param, the combiner, its needed in order to compile this code.
     *
     *
     * To put it simply, if we use sequential streams and the types of the accumulator
     * arguments and the types of its implementation match, we don't need to use a combiner.
     */
    public void useTheThirdParamOfReduceMethod2() {
        List<User> users = Arrays.asList(
                new User("John", 30),
                new User("Julie", 35)
        );
        int computedAges = users.stream()
                .reduce(0,
                        (partialAgeResult, user) -> partialAgeResult + user.getAge(),
                        Integer::sum);
        assert computedAges == 65;
    }

    public void catchExceptionWhenUsingReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int divider = 0;
        int result = numbers.stream().reduce(
                    0,
                    (a, b) -> {
                        try {
                            return a / divider + b + divider;
                        } catch (ArithmeticException e) {
                            System.out.println("ARITHMETIC EXCEPTION: DIVISION BY ZERO");
                        }
                        return 0;
                    });
        System.out.println("RESULT : " + result);
    }
}
