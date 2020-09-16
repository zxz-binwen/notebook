package com.jvm.demo;

public class Demo {

    static int a = 10;
//    public static final int b = 2;

    public static void main(String[] args) throws Exception {

        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {

        char[] strs = s.toCharArray();

        int maxLength = 0;
        for (int i = 0; i < strs.length; i++) {

            String s1 = "";

            for (int j = i; j < strs.length; j++) {
                if (s1.contains(strs[j] + "")) {
                    break;
                }
                s1 += strs[j];
            }

            maxLength = Math.max(maxLength, s1.length());
        }

        return maxLength;
    }
}
