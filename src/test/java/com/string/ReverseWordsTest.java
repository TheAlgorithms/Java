package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReveresWordsTest {

    @Test
    void testReverseWords() {
        ReverseWords reverseWords = new ReverseWords();
        Assertions.assertEquals("siht si ym rac", reverseWords.returnReverseWords("this is my car"), "Words reversed correctly");
        Assertions.assertEquals("CBA 321", reverseWords.returnReverseWords("ABC 123"), "Words reversed correctly");

    }
}