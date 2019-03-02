package com.example.demo.test;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ConcurrentHashMap;

public class BufferTest implements Runnable {

    int i = 100;

    @Test
    public void Nio() {
        try (FileInputStream fin = new FileInputStream("F:\\data\\logs\\info.log");
             FileOutputStream fout = new FileOutputStream("F:\\data\\logs\\info.txt")) {
            FileChannel fcRead = fin.getChannel();
            FileChannel fcWrite = fout.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (fcRead.read(buffer) != -1) {
                buffer.flip();
                fcWrite.write(buffer);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serverSocket() {
    }

    public static int add(int a, int b) {
        try {
            return a + b;
        } catch (Exception e) {
            System.out.println("catch语句块");
        } finally {
            System.out.println("finally语句块");
        }
        return 0;
    }

    public static void main(String[] args) {
        BufferTest tt = new BufferTest();
        Thread thread = new Thread(tt);
        thread.start();
        tt.m2();
        System.out.println("main i=" + tt.i);
    }

    synchronized void m1() {
        try {
            i = 1000;
            Thread.sleep(500);
            System.out.println("i=" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void m2() {
        try {
            Thread.sleep(250);
            i = 2000;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        m1();
    }
}