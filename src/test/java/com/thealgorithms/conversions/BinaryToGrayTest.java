package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinaryToGrayTest {
    BinaryToGray btg = new BinaryToGray();

    @Test
    public void binaryToDecimalFirstTestCase(){
        assertEquals("1", btg.binaryToGray("1"));
    }

    @Test
    public void binaryToDecimalSecondTestCase() {
        assertEquals("0", btg.binaryToGray("0"));
    }

    @Test
    public void binaryToDecimalThirdTestCase() {
        assertEquals("11", btg.binaryToGray("10"));
    }

    @Test
    public void binaryToDecimalFourthTestCase() {
        assertEquals("1101101", btg.binaryToGray("1001001"));
    }

    @Test
    public void binaryToDecimalFifthTestCase() {
        assertEquals("100000", btg.binaryToGray("111111"));
    }
}
