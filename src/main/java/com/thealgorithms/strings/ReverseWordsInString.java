package com.thealgorithms.strings;

import java.util.Arrays;
import java.util.Collections;


public final class ReverseWordsInString {

    private ReverseWordsInString() {
    }

    /**
     * Reverse word in the string
     *
     * @param s contains sentence
       @return Reverse word of {@code s}
     */

    public static String reverseWordsInString(final String s) {
        var words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
