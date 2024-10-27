package com.thealgorithms.strings;

import java.util.Arrays;
import java.util.Collections;

public final class ReverseWordsInString {

    private ReverseWordsInString() {
    }

    /**
     * @param s the input string
     * @return A string created by reversing the order of the words in {@code s}
     * @brief Reverses words in the input string
     */
    public static String reverseWordsInString(final String s) {
        var words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
