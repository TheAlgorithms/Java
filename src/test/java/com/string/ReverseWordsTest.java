package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReveresWordsTest {

    @Test
    void testAlphabetical() {
        ReverseWords reverseWords = new ReverseWords();
        Assertions.assertEquals(true, returnReverseWords("this is my car"), "siht si ym rac");
        Assertions.assertEquals(true, returnReverseWords("ABC 123"), "CBA 321");
    }
}