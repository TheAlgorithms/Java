package com.thealgorithms.strings;

// Longest Palindromic Substring
import java.util.Scanner;

class LongestPalindromicSubstring {

    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        str = sc.nextLine();
        System.out.println(
            "Longest substring is : " + s.longestPalindrome(str)
        );
    }
}

class Solution {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        String maxStr = "";
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (isValid(s, i, j) == true) {
                    if (j - i + 1 > maxStr.length()) { // update maxStr
                        maxStr = s.substring(i, j + 1);
                    }
                }
            }
        }
        return maxStr;
    }

    private boolean isValid(String s, int lo, int hi) {
        int n = hi - lo + 1;
        for (int i = 0; i < n / 2; ++i) {
            if (s.charAt(lo + i) != s.charAt(hi - i)) {
                return false;
            }
        }
        return true;
    }
}
