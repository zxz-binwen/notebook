package com.jvm.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while ((str = bufferedReader.readLine()) != null) {

            final String[] s = str.split(" ");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            int j = m * n;
            if (n > m) {
                int temp = n;
                n = m;
                m = temp;
            }

            while (n != 0) {
                int r = m % n;
                m = n;
                n = r;
            }

            int max = j / m;

            System.out.println(max);

        }

    }
}
