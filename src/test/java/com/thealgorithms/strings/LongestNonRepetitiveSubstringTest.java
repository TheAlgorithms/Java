package com.thealgorithms.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestNonRepetitiveSubstringTest {

    @Test
    public void palindrome() {
        String input1 = "HelloWorld";
        String input2 = "javaIsAProgrammingLanguage";
        Assertions.assertEquals(LongestNonRepetitiveSubstring.lengthOfLongestSubstring(input1), 5);
        Assertions.assertEquals(LongestNonRepetitiveSubstring.lengthOfLongestSubstring(input2), 9);
    }
}
