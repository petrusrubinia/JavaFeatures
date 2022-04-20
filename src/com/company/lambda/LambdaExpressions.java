package com.company.lambda;

/**
 * Lambda expression express instances of functional interface
 *
 * Lambda expression provides:
 *  1. Enable to treat functionality as a method argument, or code as data.
 *  2. A function that can be created without belonging to any class.
 *  3. A lambda expression can be passed around as if it was an object and executed an demand
 */

interface FuncInterface {
    void abstractFun(int x);

    default void normalFun(){
        System.out.println("HELLO");
    }
}
public class LambdaExpressions {
    public LambdaExpressions() {
        FuncInterface funcInterface = (int x) -> System.out.println(2*x);
        funcInterface.abstractFun(5);
    }
}
