package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PronicNumberTest {

    @Test
    void testForPronicNumber() {
        //given
        int number = 30;

        //when
        boolean result = PronicNumber.isPronic(number);

        //then
        assertTrue(result);
    }

    @Test
    void testForNonPronicNumber() {
        //given
        int number = 21;

        //when
        boolean result = PronicNumber.isPronic(number);

        //then
        assertFalse(result);
    }
}
