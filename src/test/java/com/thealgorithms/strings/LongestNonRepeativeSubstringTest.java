package com.thealgorithms.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestNonRepeativeSubstringTest {

    @Test
    public void palindrome() {
        String input1 = "HelloWorld";
        String input2 = "javaIsAProgrammingLanguage";
        Assertions.assertEquals(LongestNonRepeativeSubstring.lengthOfLongestSubstring(input1), 5);
        Assertions.assertEquals(LongestNonRepeativeSubstring.lengthOfLongestSubstring(input2), 9);
    }
}
