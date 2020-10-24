package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AlphabeticalTest extends Alphabetical {

    @Test
    void testAlphabetical() {
        Assertions.assertEquals(true, isAlphabetical("abc"), "The string is in order");
        Assertions.assertEquals(false, isAlphabetical("testing"), "The string is not in order");
    }
}