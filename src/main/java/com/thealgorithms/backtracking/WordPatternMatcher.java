package com.thealgorithms.backtracking;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to determine if a pattern matches a string using backtracking.
 *
 * Example:
 * Pattern: "abab"
 * Input String: "JavaPythonJavaPython"
 * Output: true
 *
 * Pattern: "aaaa"
 * Input String: "JavaJavaJavaJava"
 * Output: true
 *
 * Pattern: "aabb"
 * Input String: "JavaPythonPythonJava"
 * Output: false
 */
public final class WordPatternMatcher {
    private WordPatternMatcher() {
    }

    /**
     * Determines if the given pattern matches the input string using backtracking.
     *
     * @param pattern The pattern to match.
     * @param inputString The string to match against the pattern.
     * @return True if the pattern matches the string, False otherwise.
     */
    public static boolean matchWordPattern(String pattern, String inputString) {
        Map<Character, String> patternMap = new HashMap<>();
        Map<String, Character> strMap = new HashMap<>();
        return backtrack(pattern, inputString, 0, 0, patternMap, strMap);
    }

    /**
     * Backtracking helper function to check if the pattern matches the string.
     *
     * @param pattern The pattern string.
     * @param inputString The string to match against the pattern.
     * @param patternIndex Current index in the pattern.
     * @param strIndex Current index in the input string.
     * @param patternMap Map to store pattern characters to string mappings.
     * @param strMap Map to store string to pattern character mappings.
     * @return True if the pattern matches, False otherwise.
     */
    private static boolean backtrack(String pattern, String inputString, int patternIndex, int strIndex, Map<Character, String> patternMap, Map<String, Character> strMap) {
        if (patternIndex == pattern.length() && strIndex == inputString.length()) {
            return true;
        }
        if (patternIndex == pattern.length() || strIndex == inputString.length()) {
            return false;
        }

        char currentChar = pattern.charAt(patternIndex);
        if (patternMap.containsKey(currentChar)) {
            String mappedStr = patternMap.get(currentChar);
            if (inputString.startsWith(mappedStr, strIndex)) {
                return backtrack(pattern, inputString, patternIndex + 1, strIndex + mappedStr.length(), patternMap, strMap);
            } else {
                return false;
            }
        }

        for (int end = strIndex + 1; end <= inputString.length(); end++) {
            String substring = inputString.substring(strIndex, end);
            if (strMap.containsKey(substring)) {
                continue;
            }

            patternMap.put(currentChar, substring);
            strMap.put(substring, currentChar);
            if (backtrack(pattern, inputString, patternIndex + 1, end, patternMap, strMap)) {
                return true;
            }

            patternMap.remove(currentChar);
            strMap.remove(substring);
        }

        return false;
    }
}
