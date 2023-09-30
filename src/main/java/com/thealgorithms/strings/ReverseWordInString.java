package com.thealgorithms.strings;

/**
 Given an input string s, reverse the order of the words.
 A word is defined as a sequence of non-space characters.
 The words in s will be separated by at least one space.
 Return a string of the words in reverse order concatenated by a single space.
 Note that s may contain leading or trailing spaces or multiple spaces between two words.
 The returned string should only have a single space separating the words.Do not include any extra spaces
 */

public class ReverseWordInString {

    public static void main(String[] args) {

        System.out.println(reverseWords("the sky is blue"));
    }

    public static String reverseWords(String s) {

        s = s.trim();

        String res = "";
        int start = 0;
        int end;

        while (start < s.length()) {
            while (s.charAt(start) == ' ') {
                start++;
            }
            end = start + 1;

            while (end < s.length() && (s.charAt(end) != ' ')) {
                end++;
            }

            String sub = s.substring(start, end);
            start = end;
            if (res.isEmpty()) {
                res = sub;
            } else {
                res = sub + " " + res;
            }
        }
        return res;
    }
}
