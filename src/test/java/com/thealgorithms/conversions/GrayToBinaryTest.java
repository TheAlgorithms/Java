package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GrayToBinaryTest {
    GrayToBinary gtb = new GrayToBinary();

    @Test
    public void binaryToDecimalFirstTestCase() {
        assertEquals("1", gtb.grayToBinary("1"));
    }

    @Test
    public void binaryToDecimalSecondTestCase() {
        assertEquals("0", gtb.grayToBinary("0"));
    }

    @Test
    public void binaryToDecimalThirdTestCase() {
        assertEquals("10", gtb.grayToBinary("11"));
    }

    @Test
    public void binaryToDecimalFourthTestCase() {
        assertEquals("1001001", gtb.grayToBinary("1101101"));
    }

    @Test
    public void binaryToDecimalFifthTestCase() {
        assertEquals("111111", gtb.grayToBinary("100000"));
    }
}
