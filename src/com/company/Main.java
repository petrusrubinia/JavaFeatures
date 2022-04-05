package com.company;

import com.company.streams.AvoidablePractice;
import com.company.streams.LazyInvocationPractice;
import com.company.streams.reduction.StreamReductionWIthReduceMethod;
import com.company.streams.reduction.StreamReductionWithCollectMethod;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Display random message!");
        System.out.println("---------------AVOIDABLE PRACTICE-------------------");
        testAvoidablePractice();
        System.out.println("--------------LAZY INVOCATION--------------------");
        testLazyInvocationPractice();
        System.out.println("----------------STREAM REDUCTION------------------");
        testStreamReduction();
        System.out.println("--------------STREAM REDUCTION WITH REDUCE METHOD--------------------");
        testStreamReductionWithReduceMethod();
    }

    private static void testStreamReductionWithReduceMethod() {
        StreamReductionWIthReduceMethod reduction = new StreamReductionWIthReduceMethod();
        reduction.applyReduceSimpleExample();
        reduction.joinChars();
        reduction.useTheThirdParamOfReduceMethod1();
        reduction.useTheThirdParamOfReduceMethod2();
        reduction.catchExceptionWhenUsingReduce();
    }

    private static void testAvoidablePractice() {
        AvoidablePractice avoidablePractice = new AvoidablePractice();
        avoidablePractice.callTwoTerminalOperationOnStream();
    }

    private static void testLazyInvocationPractice() {
        LazyInvocationPractice lazyInvocationPractice = new LazyInvocationPractice();
        lazyInvocationPractice.applyLazyInvocationWithTerminalOperation();
    }

    private static void testStreamReduction(){
        StreamReductionWithCollectMethod streamReductionWithCollectMethod = new StreamReductionWithCollectMethod();

        LinkedList<String> linkedList = streamReductionWithCollectMethod.applyCollectMethodOnCustomCollector();
        linkedList.stream().forEach(System.out::println);
    }
}
