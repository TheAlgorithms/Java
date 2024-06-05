package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CountCharTest {

    @Test
    void testCountCharacters() {
        String input = "12345";
        int expectedValue = 5;

        assertEquals(expectedValue, CountChar.countCharacters(input));
    }
}
