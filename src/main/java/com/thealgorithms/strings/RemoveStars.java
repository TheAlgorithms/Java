package com.thealgorithms.strings;
/**
 * @author Swarit Srivastava (https://github.com/SwarritSrivastava)
 */
public final class RemoveStars {
    private RemoveStars() {
    }
    /**
     * Removes stars ('*') from the given string according to the following rules:
     * <ul>
     *   <li>For each star in the string, remove the closest non-star character to its left
     *       along with the star itself.</li>
     *   <li>Return the final string after performing all removals.</li>
     *   <li>Given that such operation is always possible for the input</li>
     * </ul>
     *
     * Example: {@code "leet**cod*e" -> "lecoe"}
     *
     * @param input The input string possibly containing '*' characters.
     * @return The resulting string after removing stars as per the rules.
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
