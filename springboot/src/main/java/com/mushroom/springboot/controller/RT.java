package com.mushroom.springboot.controller;

import java.util.Objects;

public class RT {
    public static void main(String[] args){
        String yes = Objects.requireNonNull("OK");
        System.out.println(yes);
        Comparable<Integer> comparable = new Comparable<Integer>() {
            @Override
            public int compareTo(Integer num) {
                return 6 - num;
            }
        };
        Comparable<Integer> com2 = num -> 6 - num;
        System.out.println(comparable.compareTo(5));
        System.out.println(com2.compareTo(5));

        Double d = new Double(-2);
        System.out.println(Double.toHexString(d));
    }
}
