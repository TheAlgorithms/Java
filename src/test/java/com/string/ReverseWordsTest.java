package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReveresWordsTest {

    @Test
    void testReverseWords() {
        ReverseWords reverseWords = new ReverseWords();
        Assertions.assertEquals(true, reverseWords.returnReverseWords("this is my car"), "siht si ym rac");
        Assertions.assertEquals(true, reverseWords.returnReverseWords("ABC 123"), "CBA 321");

    }
}