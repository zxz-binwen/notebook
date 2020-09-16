package com.jvm.demo;

import java.util.Scanner;

public class Main5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        final int count = sc.nextInt(); // 字符串个数

        String[] strs = new String[count];

        for (int i =0; i < count; i ++) {
            strs[i] = sc.next();
        }

        for (String str : strs) {
            final int length = str.length();
            if (length < 8) {
                System.out.println(str + get0(8 - length));
            } else {
                final int s = length % 8;
                for (int i = 0; i < s; i++) {
                    System.out.println(str.substring(i, (i + 1) * 8));
                }
                final int i = length - s * 8;
                System.out.println(str.substring(s * 8) + get0(length - i));
            }
        }
    }

    private static String get0(int i) {
        StringBuilder s = new StringBuilder();

        for (int j = 0; j <i; j++) {
            s.append("0");
        }
        return s.toString();
    }
}
