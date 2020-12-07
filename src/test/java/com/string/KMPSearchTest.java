package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class KMPSearchTest {

    @Test
    void isSubstring() {
        Assertions.assertEquals(1,KMPSearch.isSubstring("mississippi","is")); // pattern found at index 1
        Assertions.assertEquals(-1,KMPSearch.isSubstring("mississippi","piss")); // pattern not found in text
        Assertions.assertEquals(-1,KMPSearch.isSubstring("is","miss")); // length of pattern greater than length of text
    }
}