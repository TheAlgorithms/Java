package com.thealgorithms.strings;

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
