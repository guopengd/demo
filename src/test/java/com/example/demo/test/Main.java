package com.example.demo.test;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static LinkedList<Integer> stack = new LinkedList<>();
    public static ArrayList<Integer> result = new ArrayList<>();

    public static Integer count(int value, int start, int end) {
        int count = 0;
        int[] arr = new int[end - start + 1];
        int maxIndex = arr.length;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = start + i;
        }

        for (int i = 0; i < arr.length; i++) {
            if (value < arr[i]) {
                maxIndex = i;
                break;
            }
        }
        for (int i = 0; i < maxIndex; i++) {
            for (int j = i + 1; i < maxIndex; i++) {
                if (arr[i] + arr[j] == value) {
                    count++;
                }
            }
        }
        return count;
    }

    public static String sub(String string) {
        char[] chars = string.toLowerCase().toCharArray();

        int maxIndex = 0;
        int MaxMun = 1;
        int tempIndex = 0;
        int tempMun = 1;
        boolean b = true;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] + 1 == chars[i + 1]) {
                if (b) {
                    tempIndex = i + 1;
                    tempMun++;
                } else {
                    maxIndex = tempMun > MaxMun ? tempIndex : maxIndex;
                    MaxMun = tempMun > MaxMun ? tempMun : MaxMun;
                    tempIndex = i + 1;
                    tempMun = 2;
                    b = true;
                }
            } else {
                b = false;
            }
        }
        maxIndex = tempMun > MaxMun ? tempIndex : maxIndex;
        MaxMun = tempMun > MaxMun ? tempMun : MaxMun;
        return string.substring(maxIndex - MaxMun + 1, maxIndex + 1);
    }

    public static void main(String[] args) {
        System.out.println(sub("ababababcdabbbc"));
    }


    public static void action(String action) {
        if (action.startsWith("push")) {
            stack.push(Integer.valueOf(action.split(" ")[1]));
        }
        if (action.startsWith("pop")) {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        if (action.startsWith("delete")) {
            int index = Integer.valueOf(action.split(" ")[1]);
            if (index < stack.size()) {
                stack.remove(index);
            }
        }
        if (action.startsWith("query")) {
            if (stack.isEmpty()) {
                result.add(0);
            } else {
                result.add(stack.peek());
            }
        }
    }
}
