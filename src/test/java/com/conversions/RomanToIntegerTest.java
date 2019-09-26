package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RomanToIntegerTest {
    @BeforeEach
    public  void setUp() throws Exception {

    }
    @Test
    public void shouldReturnZero() {
        Assertions.assertEquals(0,  RomanToInteger.romanToInt(""));
    }
    @Test
    public void singleDigit() {
        Assertions.assertEquals(1,  RomanToInteger.romanToInt("I"));
        Assertions.assertEquals(5,  RomanToInteger.romanToInt("V"));
        Assertions.assertEquals(10,  RomanToInteger.romanToInt("X"));
        Assertions.assertEquals(50,  RomanToInteger.romanToInt("L"));
        Assertions.assertEquals(100,  RomanToInteger.romanToInt("C"));
        Assertions.assertEquals(500,  RomanToInteger.romanToInt("D"));
        Assertions.assertEquals(1000,  RomanToInteger.romanToInt("M"));
    }
    @Test
    public void repetition() {
        Assertions.assertEquals(2, RomanToInteger.romanToInt("II"));
        Assertions.assertEquals(20, RomanToInteger.romanToInt("XX"));
    }
    @Test
    public void manyLettersInOrder() {
        Assertions.assertEquals(4, RomanToInteger.romanToInt("IV"));
        Assertions.assertEquals(14, RomanToInteger.romanToInt("XIV"));
        Assertions.assertEquals(39, RomanToInteger.romanToInt("XXXIX"));
        Assertions.assertEquals(344, RomanToInteger.romanToInt("CCCXLIV"));
        Assertions.assertEquals(399, RomanToInteger.romanToInt("CCCXCIX"));
        Assertions.assertEquals(400, RomanToInteger.romanToInt("CD"));
        Assertions.assertEquals(900, RomanToInteger.romanToInt("CM"));
        Assertions.assertEquals(3992, RomanToInteger.romanToInt("MMMCMXCII"));
        Assertions.assertEquals(3999, RomanToInteger.romanToInt("MMMCMXCIX"));
    }
}
