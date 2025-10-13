package com.thealgorithms.strings;

/**
 * Removes stars from the input string by deleting the closest non-star character
 * to the left of each star along with the star itself.
 *
 * @param s1 The input string containing stars (*)
 * @return The string after all stars and their closest left characters are removed
 */
public class RemoveStars {

    public static String removeStars(String s1) {
        StringBuilder sc = new StringBuilder();

        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);

            if (ch == '*') {
                if (sc.length() > 0) {
                    sc.deleteCharAt(sc.length() - 1);
                }
            } else {
                sc.append(ch);
            }
        }

        return sc.toString();
    }
}
