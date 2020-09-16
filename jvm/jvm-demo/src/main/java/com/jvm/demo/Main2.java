package com.jvm.demo;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double a = scanner.nextDouble();
        final double cubeRoot = getCubeRoot(a);

        System.out.println(cubeRoot);
    }

    private static double getCubeRoot(double a) {

        double diff = 0.00001;

        double start = 0d;
        double end = a;
        double mid = 0d;
        while (end - start > diff) {
            mid = start + (end - start) / 2;

            if (mid * mid * mid > a) {
                end = mid;
            } else if (mid * mid * mid < a) {
                start = mid;
            } else {
                return mid;
            }
        }
        return Math.round(end * 10) / 10.0;
    }
}
