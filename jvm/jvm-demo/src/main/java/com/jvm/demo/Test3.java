package com.jvm.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test3 {

    public static void main(String[] args) {

        // 此题应该为动态规划问题
        Scanner sc = new Scanner(System.in);

        final String mapLength = sc.next();
        final String[] s = mapLength.split(" ");

        int m = Integer.parseInt(s[0]); // 地图的长度
        int n = Integer.parseInt(s[1]); // 地图的宽度

        int[][] ints = new int[m][n];

        int index = 0;
        while (sc.hasNext()) {
            final String next = sc.next();
            final String[] s2 = next.split(" ");
            for (int i = 0; i < s2.length; i++) {
                ints[index][i] = Integer.parseInt(s2[i]);
            }
            index++;
        }


        // 处理数据
        /**
         * 0 畅通
         * 1、障碍物
         * 2、人物
         * 3、聚餐地点
         *
         *
         * 4 4
         * 2 1 2 3
         * 0 1 0 0
         * 0 1 0 0
         * 0 1 0 0
         *
         * 4 4
         * 2 1 0 3
         * 0 1 2 1
         * 0 3 0 0
         * 0 0 0 0
         */

    }
}
