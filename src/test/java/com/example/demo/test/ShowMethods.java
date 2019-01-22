package com.example.demo.test;
// typeinfo/ShowMethods17.java
// TIJ4 Chapter Typeinfo, Exercise 17, page 593
// Modify the regular expression in ShowMethods.java to additionally
// strip off the keywords native and final (hint: us the OR operator '|').
// {Args: ShowMethods17}

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class ShowMethods {
    private static String usage =
            "usage:\n" +
                    "ShowMethods qualified.class.name\n" +
                    "To show all methods in class or:\n" +
                    "ShowMethods qualified.class.name word\n" +
                    "To search for methods involving 'word'";
    private static Pattern p = Pattern.compile("(\\w+\\.)|\\s*final|\\s*native");

    ShowMethods() {
    }

    ShowMethods(String usage) {
        this.usage = usage;
    }

    public static void main(String[] args) {
        // Class<ShowMethods> c = Class.forName("com.example.demo.test.ShowMethods");
        Class<ShowMethods> c = ShowMethods.class;
        try {
            ShowMethods instance = c.getDeclaredConstructor(String.class).newInstance("gdfgd");
            System.out.println(instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Method[] methods = c.getMethods();
        Constructor[] ctors = c.getConstructors();
        for (Method method : methods) {
            System.out.println(p.matcher(method.toString()).replaceAll(""));
        }
        for (Constructor ctor : ctors)
            System.out.println(p.matcher(ctor.toString()).replaceAll(""));
    }
}