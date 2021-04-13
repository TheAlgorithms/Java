package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UpperTest extends Upper {

    @Test
    void testUpper() {
        Assertions.assertEquals(toUpperCase("abc"), ("abc").toUpperCase(), "The strings are equals");

        Assertions.assertNotEquals(toUpperCase("abc"), "abc", "The strings are not equals");
    }

}