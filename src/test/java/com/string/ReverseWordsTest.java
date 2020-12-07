package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReverseWordsTest {

    @Test
    void testReverseWords() {
        Assertions.assertEquals("siht si ym rac", ReverseWords.returnReverseWords("this is my car"), "Words reversed correctly");
        Assertions.assertEquals("CBA 321", ReverseWords.returnReverseWords("ABC 123"), "Words reversed correctly");

    }
}