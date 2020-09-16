package com.jvm.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;

public class Main6 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            int sum = Integer.valueOf(str);
            for (int i = 2; i <= Math.sqrt(sum) ; i++) {
                if (sum % i ==0) {
                    sb.append(i+" ");
                    sum/=i;
                    i--;
                }
            }
            sb.append(sum+" ");
            System.out.println(sb.toString());
        }
    }

}
