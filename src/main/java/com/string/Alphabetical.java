package com.string;

public class Alphabetical {

    /**
     * Check if a string is alphabetical order or not
     *
     * @param s a string
     * @return {@code true} if given string is alphabetical order, otherwise {@code false}
     */
    public static boolean isAlphabetical(String s) {
        s = s.toLowerCase();
        for (int i = 0; i < s.length() - 1; ++i) {
            if (!Character.isLetter(s.charAt(i)) || !(s.charAt(i) <= s.charAt(i + 1))) {
                return false;
            }
        }
        return true;
    }
}
