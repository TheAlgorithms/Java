package com.thealgorithms.maths;

import org.junit.Test;
import static org.junit.Assert.*;

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