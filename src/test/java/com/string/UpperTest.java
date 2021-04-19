package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UpperTest extends Upper {

    @Test
    void testUpper() {
        Assertions.assertEquals(toUpperCase("abc"), "ABC", "The strings are equal");

        Assertions.assertNotEquals(toUpperCase("abc"), "abc", "The strings are not equal");
    }

}