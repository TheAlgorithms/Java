package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UpperTest extends Upper {

    @Test
    void testUpper() {
        Assertions.assertEquals( "ABC",toUpperCase("abc"), "The strings are equal");

        Assertions.assertNotEquals( "abc", toUpperCase("abc"),"The strings are not equal");
    }

}