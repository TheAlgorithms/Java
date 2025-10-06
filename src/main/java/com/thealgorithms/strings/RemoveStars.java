package com.thealgorithms.strings;
/**
 * @author Swarit Srivastava (https://github.com/SwarritSrivastava)
 */
public final class RemoveStars {
    private RemoveStars() {
    }
    /**
     * Removes * characters from the given string. According to the follwing rules
     * You are given a string s, which contains stars *.
     * In one operation, you can:
     * Choose a star in s.
     * Remove the closest non-star character to its left, as well as remove the star itself.
     * Return the string after all stars have been removed.
     * @param input The input string from which duplicate characters need to be removed.
     * @return A string containing no stars as per the given constraints.
     */
    public static String removeStars(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        int n = input.length();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char t = input.charAt(i);
            if (t != '*') {
                ans.append(t);
            }
            else {
                ans.deleteCharAt(ans.length() - 1);
            }
        }
        return ans.toString();
    }
}
