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
    public void shouldReturnNullSpace() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RomanToInteger.romanToInt(" ");
        });
    }
    @Test
    public void shouldReturnNullISpace() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RomanToInteger.romanToInt("I ");
        });
    }
    @Test
    public void shouldReturnNullSpaceI() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RomanToInteger.romanToInt(" I");
        });
    }
    @Test
    public void shouldReturnNullSpaceISpaceI() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RomanToInteger.romanToInt(" I I");
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            RomanToInteger.romanToInt(" I ");
        });
    }
    @Test
    public void shouldReturnNullSpaceISpace() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RomanToInteger.romanToInt(" I ");
        });
    }
    @Test
    public void singleDigitI() {
        Assertions.assertEquals(1,  RomanToInteger.romanToInt("I"));
    }
    @Test
    public void singleDigitV() {
        Assertions.assertEquals(5,  RomanToInteger.romanToInt("V"));
    }
    @Test
    public void singleDigitX() {
        Assertions.assertEquals(10,  RomanToInteger.romanToInt("X"));
    }
    @Test
    public void singleDigitL() {
        Assertions.assertEquals(50,  RomanToInteger.romanToInt("L"));
    }
    @Test
    public void singleDigitC() {
        Assertions.assertEquals(100,  RomanToInteger.romanToInt("C"));
    }
    @Test
    public void singleDigitD() {
        Assertions.assertEquals(500,  RomanToInteger.romanToInt("D"));
    }
    @Test
    public void singleDigitM() {
        Assertions.assertEquals(1000,  RomanToInteger.romanToInt("M"));
    }
    @Test
    public void repetitionII() {
        Assertions.assertEquals(2, RomanToInteger.romanToInt("II"));
    }
    @Test
    public void repetition() {
        Assertions.assertEquals(20, RomanToInteger.romanToInt("XX"));
    }
    @Test
    public void manyLettersInOrderIV() {
        Assertions.assertEquals(4, RomanToInteger.romanToInt("IV"));
    }
    @Test
    public void manyLettersInOrderIX() {
        Assertions.assertEquals(9, RomanToInteger.romanToInt("IX"));
    }
    @Test
    public void manyLettersInOrderXI() {
        Assertions.assertEquals(11, RomanToInteger.romanToInt("XI"));
    }
    @Test
    public void manyLettersInOrderXIV() {
        Assertions.assertEquals(14, RomanToInteger.romanToInt("XIV"));
    }
    @Test
    public void manyLettersInOrderXXXIX() {
        Assertions.assertEquals(39, RomanToInteger.romanToInt("XXXIX"));
    }
    @Test
    public void manyLettersInOrderCCCXLIV() {
        Assertions.assertEquals(344, RomanToInteger.romanToInt("CCCXLIV"));
    }
    @Test
    public void manyLettersInOrderCCCXCIX() {
        Assertions.assertEquals(399, RomanToInteger.romanToInt("CCCXCIX"));
    }
    @Test
    public void manyLettersInOrderCD() {
        Assertions.assertEquals(400, RomanToInteger.romanToInt("CD"));
    }
    @Test
    public void manyLettersInOrderCM() {
        Assertions.assertEquals(900, RomanToInteger.romanToInt("CM"));
    }
    @Test
    public void manyLettersInOrderMMMCMXCII() {
        Assertions.assertEquals(3992, RomanToInteger.romanToInt("MMMCMXCII"));
    }
    @Test
    public void manyLettersInOrderMMMCMXCIX() {
        Assertions.assertEquals(3999, RomanToInteger.romanToInt("MMMCMXCIX"));
    }
}