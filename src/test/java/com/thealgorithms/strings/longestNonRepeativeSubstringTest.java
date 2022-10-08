package com.thealgorithms.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class longestNonRepeativeSubstringTest {

    @Test
    public void palindrome() {
        String input1 = "HelloWorld";
        String input2 = "javaIsAProgrammingLanguage";
        Assertions.assertEquals(
            longestNonRepeativeSubstring.lengthOfLongestSubstring(input1),
            5
        );
        Assertions.assertEquals(
            longestNonRepeativeSubstring.lengthOfLongestSubstring(input2),
            9
        );
    }
}
