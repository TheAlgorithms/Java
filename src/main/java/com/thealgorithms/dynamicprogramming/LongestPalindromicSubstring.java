package com.thealgorithms.dynamicprogramming;

/*
 * Algorithm explanation https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String a = "babad";
        String b = "cbbd";

        String aLPS = LPS(a);
        String bLPS = LPS(b);

        System.out.println(a + " => " + aLPS);
        System.out.println(b + " => " + bLPS);
    }

    private static String LPS(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        boolean[][] arr = new boolean[input.length()][input.length()];
        int start = 0, end = 0;
        for (int g = 0; g < input.length(); g++) {
            for (int i = 0, j = g; j < input.length(); i++, j++) {
                if (g == 0) {
                    arr[i][j] = true;
                } else if (g == 1) {
                    arr[i][j] = input.charAt(i) == input.charAt(j);
                } else {
                    arr[i][j] = input.charAt(i) == input.charAt(j) && arr[i + 1][j - 1];
                }

                if (arr[i][j]) {
                    start = i;
                    end = j;
                }
            }
        }
        return input.substring(start, end + 1);
    }
}
