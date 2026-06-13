package com.thealgorithms.strings;

/**
 * Title Case converts a string so that the first letter of each word
 * is capitalized and the rest are lowercase.
 * Example: "the quick brown fox" -> "The Quick Brown Fox"
 *
 * @see <a href="https://en.wikipedia.org/wiki/Title_case">
 *     Wikipedia: Title Case</a>
 */
public final class TitleCase {

    private TitleCase() {
        // Utility class
    }

    /**
     * Converts a string to title case.
     *
     * @param input The string to convert
     * @return The title-cased string, or empty string if input is null/empty.
     *     If input contains only whitespace, it is returned as is.
     */
    public static String toTitleCase(final String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
                result.append(c);
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
