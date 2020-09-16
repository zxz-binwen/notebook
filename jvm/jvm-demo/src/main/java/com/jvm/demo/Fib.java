package com.jvm.demo;

import java.util.Scanner;

public class Fib {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            final int nextInt = in.nextInt();
            System.out.println(fib(nextInt));
        }

    }


    public static int fib(int x) {

        if (x <= 0) {
            return 0;
        } else if (x == 1) {
            return 1;
        }else if (x == 3){
            return 2;
        } else {
            return fib(x - 1) + fib(x - 3);
        }
    }
}
