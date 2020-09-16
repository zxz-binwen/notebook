package com.jvm.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str;
        String s = "";
        while ((str = bf.readLine()) != null) {

            final char[] array = str.toCharArray();


            for (int i = array.length - 1; i >=0 ; i--) {
                s += array[i];
            }
            System.out.println(s);
        }
    }
}
