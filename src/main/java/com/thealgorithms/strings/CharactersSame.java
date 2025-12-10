package com.thealgorithms.strings;

public final class CharactersSame {
    private CharactersSame() {
    }

    /**
     * Checks if all characters in the string are the same.
     *
     * @param s the string to check
     * @return {@code true} if all characters in the string are the same or if the string is empty, otherwise {@code false}
     */
    public static boolean isAllCharactersSame(String s) {
        if (s.isEmpty()) {
            return true; // Empty strings can be considered as having "all the same characters"
        }

        char firstChar = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != firstChar) {
                return false;
            }
        }
        return true;
    }
}
