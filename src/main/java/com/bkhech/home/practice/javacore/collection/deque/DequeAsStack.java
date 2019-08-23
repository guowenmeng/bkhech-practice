package com.bkhech.home.practice.javacore.collection.deque;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author guowm
 * @date 2019/8/23
 *
 * 官方建议使用Deque替代Stack: Line 134
 * Deques can also be used as LIFO (Last-In-First-Out) stacks.  This
 * interface should be used in preference to the legacy {@link Stack} class.
 */
public class DequeAsStack {

    public static void stack() {
        Stack<Integer> stack = new Stack<>();
        IntStream.range(0,10).forEach(item -> stack.add(item));
        Integer stackElement = stack.pop();
        System.out.println("stackElement = " + stackElement);
    }

    public static void dequeAsStack() {
        Queue asLifoQueue = Collections.asLifoQueue(new ArrayDeque());
        IntStream.range(0,10).forEach(item -> asLifoQueue.add(item));
        Object poll = asLifoQueue.poll();
        System.out.println("poll = " + poll);
    }

    public static void main(String[] args) {
        dequeAsStack();
    }

}
