package com.mitrais.thymeleaf_layout_demo.utils;

public class ArrayUtil {

    public static final String APPLICATION_NAME = "ThymeLeaf Demo";

    public static String[] array(String... args) {
        return args;
    }

    public static String getStr() {
        return APPLICATION_NAME;
    }
}