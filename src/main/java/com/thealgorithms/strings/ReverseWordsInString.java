package com.thealgorithms.strings;

/**
 * Given an input string s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters.
 * The words in s will be separated by at least one space.
 * Return a string of the words in reverse order concatenated by a single space.
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words.Do not include any extra spaces
 */

public final class ReverseWordsInString {

    private ReverseWordsInString() {
    }

    public static String reverseWordsInString(String s) {

        s = s.replaceAll("\\s+", " ").trim();
        StringBuilder res = new StringBuilder();
        int start = 0;
        int end = 0;

        while (start < s.length()) {

            Integer[] arr = findNextWord(s, start, end);

            end = arr[1];

            String word = s.substring(start, end);

            start = end;

            if (res.length() > 0) {
                res.insert(0, " ");
                res.insert(0, word);
            } else {
                res.append(word);
            }
        }
        return res.toString();
    }

    private static Integer[] findNextWord(String s, int start, int end) {
        while (s.charAt(start) == ' ') {
            start++;
        }
        end = start + 1;

        while (end < s.length() && (s.charAt(end) != ' ')) {
            end++;
        }

        return new Integer[] {start, end};
    }
}
