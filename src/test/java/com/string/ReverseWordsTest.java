package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReveresWordsTest extends ReverseWords {

    @Test
    void testAlphabetical() {
        Assertions.assertEquals(true, isReverseWords("this is my car"), "siht si ym rac");
        Assertions.assertEquals(true, isReverseWords("ABC 123"), "CBA 321");
    }
}