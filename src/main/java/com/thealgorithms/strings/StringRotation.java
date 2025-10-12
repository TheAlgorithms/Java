package com.thealgorithms.strings;

public class StringRotation {

    private StringRotation() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Checks if str2 is a rotation of str1
     * @param str1 Original string
     * @param str2 String to check for rotation
     * @return true if str2 is a rotation of str1, false otherwise
     */
    public static boolean isRotation(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }

        if (str1.length() != str2.length()) {
            return false;
        }

        String concatenated = str1 + str1;
        return concatenated.contains(str2);
    }
}
