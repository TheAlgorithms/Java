package com.thealgorithms.strings;

// Longest Palindromic Substring
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class LongestPalindromicSubstring {

    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        str = sc.nextLine();
        System.out.println("Longest Palindromic substring is : " + s.longestPalindrome(str));
        sc.close();
    }
}

class Solution {
    private boolean Palindrome(String str) {
        str = str.toLowerCase();
        String rev = "";
        for (int i = str.length() - 1; i > -1; i--) rev += str.charAt(i);
        if (str.equals(rev)) return true;
        return false;
    }

    public String longestPalindrome(String str) {
        // brute-force approach using Java Collections
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length() - 1; j++) {
                String sub = str.substring(i, j);
                boolean flag = Palindrome(sub);
                if (flag == true) al.add(sub);
            }
        }
        Collections.sort(al);
        String res = al.get(al.size() - 1);
        if (res.length() < 2) res = "none";
        return res;
    }
}
