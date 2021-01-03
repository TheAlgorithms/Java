package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckAnagramsTest extends CheckAnagrams {

    @Test
    void testCheckAnagrams() {
        Assertions.assertEquals(true, isAnagrams("DELL", "LLED"), "The strings are equals");
        Assertions.assertNotEquals(true, isAnagrams("RACE", "ARCER"), "The strings are not equals");
    }
}