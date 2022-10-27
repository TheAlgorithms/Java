package com.thealgorithms.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexaDecimalToDecimalTest {

    @Test
    public void testhexaDecimalToDecimal(){
        assertEquals(161, HexaDecimalToDecimal.getHexaToDec("A1"));
        assertEquals(428, HexaDecimalToDecimal.getHexaToDec("1ac"));
    }
}
