package com.example.demo.test;

public class DoubleSearchTest {
    /**
     * 本知识点我存在一点疑问：volatile到底是禁止了new过程内的指令重排序保证的线程安全
     * 还是因为java5新规定的volatile变量规则：变量的写操作发生在变量的读操作之前
     * 前者的解释可以为：volatile变量一定会在初始化完成之后再将地址的值赋给变量，保证外层判断不出现未初始化的情况
     * 后者的解释可以为：volatile变量使得再写操作完成之前，在写操作之后的读操作必须在写操作完成之前执行
     * 存疑点：java5之前，volatile并不能保证线程的安全性；有人解答为java5之前，jvm并没有完全实现禁止指令重排序
     */
    public static volatile DoubleSearchTest singleton = null;

    private DoubleSearchTest() {
    }

    public static DoubleSearchTest getInstance() {
        if (singleton == null) {
            synchronized (DoubleSearchTest.class) {
                if (singleton == null) {
                    singleton = new DoubleSearchTest();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        DoubleSearchTest instance1 = getInstance();
        DoubleSearchTest instance2 = getInstance();
        System.out.println(instance1 == instance2);
    }

}
