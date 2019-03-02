package com.example.demo.test;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicTest {
    private static AtomicReference<AtomicTest> INSTANCE = new AtomicReference<>();

    public static AtomicTest getInstance() {
        for (; ; ) {
            AtomicTest instance = INSTANCE.get();
            if (instance != null) {
                return instance;
            }
            instance = new AtomicTest();
            if (INSTANCE.compareAndSet(null, instance)) {
                return instance;
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
