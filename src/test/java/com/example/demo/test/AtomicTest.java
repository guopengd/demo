package com.example.demo.test;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicTest {

    private static AtomicReference<AtomicTest> INSTANCE = new AtomicReference<>();

    private AtomicTest() {
    }

    public static AtomicTest getInstance() {
        for (; ; ) {
            AtomicTest singleton = INSTANCE.get();
            if (singleton != null) {
                return singleton;
            }
            singleton = new AtomicTest();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }

    public static void main(String[] args) {
        AtomicTest test1 = getInstance();
        AtomicTest test2 = getInstance();
        System.out.println(test1);
        System.out.println(test1 == test2);
    }
}
