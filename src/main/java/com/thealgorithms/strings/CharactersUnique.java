package com.thealgorithms.strings;

import java.util.HashSet;
import java.util.Locale;

public class CharactersUnique {

    /**
     * check if all the characters of a string are unique
     *
     * @param s the string to check
     * @return {@code true} if all characters of a string are unique, otherwise
     * {@code false}
     */
    public static boolean allCharactersUnique(String s) {
        if (s.length() <= 1) {
            return true;
        }
        String lowerCaseString = s.toLowerCase();

        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < lowerCaseString.length(); i++) {
            if (set.contains(lowerCaseString.charAt(i))) {
                return false;
            }
            set.add(lowerCaseString.charAt(i));
        }
        return true;
    }
}
