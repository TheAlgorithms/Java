package com.thealgorithms.strings;

import java.util.Scanner;

final class LongestPalindromicSubstring {
    private LongestPalindromicSubstring() {
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String str = sc.nextLine();
        System.out.println("Longest palindromic substring is: " + s.longestPalindrome(str));
        sc.close();
    }
}

class Solution {
    int left = 0, right = -1, maxSize = 0;

    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            f(s, i, i); // Odd length palindromes
            f(s, i, i + 1); // Even length palindromes
        }
        return s.substring(left, right + 1);
    }

    public void f(String s, int l, int r) {
        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            } else {
                break;
            }
        }
        int size = r - l - 1;
        if (maxSize < size) {
            left = l + 1;
            right = r - 1;
            maxSize = size;
        }
    }
}
