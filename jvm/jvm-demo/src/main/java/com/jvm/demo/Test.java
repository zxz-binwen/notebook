package com.jvm.demo;

import java.util.*;
import java.util.stream.Collectors;

public class Test {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String next = scanner.next();

        final String[] split = next.split(",");

        int[] ints = new int[split.length];

        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }

        HashSet<Integer> set = new LinkedHashSet<>();
        for (int anInt : ints) {

            set.add(anInt);
        }

        final Integer[] objects = set.toArray(new Integer[set.size()]);

        // 下标- 次数
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < objects.length; i++) {

            int count = 0;

            for (int anInt : ints) {

                if (objects[i] == anInt) {
                    count++;
                }
            }
            map.put(i, count);
        }

        // 按照value 排序
        final List<Map.Entry<Integer, Integer>> entryList = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(Integer::intValue).reversed()))
                .collect(Collectors.toList());
        // 存下标
        // 存 value
        // 返回结果
        int[] newInts = new int[objects.length];

        for (int i = 0; i < entryList.size(); i++) {
            // 下标- 次数
            final Map.Entry<Integer, Integer> entry = entryList.get(i);
            // 下标
            Integer index = entry.getKey();

            newInts[i] = objects[index];
        }


        for (int i = 0; i < newInts.length; i++) {


            if (i == newInts.length - 1){
                System.out.print(newInts[i]);
            } else {
                System.out.print(newInts[i] + ",");
            }
        }

    }
}
