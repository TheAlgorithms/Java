package strings;

import java.util.Arrays;

/**
 * This program checks if one string is the substring of the other
 * (ignoring the case).
 */
public class CheckSubstrings {
    public static void main(String[] args) {
        assert stringContainsSubstring("Pineapple", "Apple");//true
        assert stringContainsSubstring("Pankaj", "aj");//true
        assert stringContainsSubstring("Earth", "Mars");//false
    }

    /**
     * Check if one string is a substring of the other
     *
     * @param string the first string
     * @param substring the substring
     * @return {@code true} if "substring" variable is a substring of "string" variable, otherwise {@code false}
     */
    public static boolean stringContainsSubstring(String string, String substring) {
        string = string.toLowerCase();
        substring=substring.toLowerCase();
        boolean result = false;
		result = string.contains(substring);
		return result;
    }
}
