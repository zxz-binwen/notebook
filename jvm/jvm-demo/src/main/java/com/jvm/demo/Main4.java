package com.jvm.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int a = 0; //统计整数
        int b = 0; // 统计负数
        int aSum = 0;


        String str;
        while ((str = bf.readLine()) != null) {

            final String[] split = str.split(" ");



            for (String s : split) {
                final int anInt = Integer.parseInt(s);

                if (anInt >= 0) {
                    a++;
                    aSum += anInt;
                } else {
                    b++;
                }
            }
        }

        double avg = 0d;
        if (b > 0) {
            avg = Math.round(aSum * 10.0 / a) / 10.0;
        }
        System.out.println(b);
        System.out.println(avg);
    }
}
