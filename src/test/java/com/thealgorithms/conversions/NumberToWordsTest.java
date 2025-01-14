package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class NumberToWordsTest {

    @Test
    void testNullInput() {
        assertEquals("Invalid Input", NumberToWords.convert(null), "Null input should return 'Invalid Input'");
    }

    @Test
    void testZeroInput() {
        assertEquals("Zero", NumberToWords.convert(BigDecimal.ZERO), "Zero input should return 'Zero'");
    }

    @Test
    void testPositiveWholeNumbers() {
        assertEquals("One", NumberToWords.convert(BigDecimal.ONE), "1 should convert to 'One'");
        assertEquals("One Thousand", NumberToWords.convert(new BigDecimal("1000")), "1000 should convert to 'One Thousand'");
        assertEquals("One Million", NumberToWords.convert(new BigDecimal("1000000")), "1000000 should convert to 'One Million'");
    }

    @Test
    void testNegativeWholeNumbers() {
        assertEquals("Negative One", NumberToWords.convert(new BigDecimal("-1")), "-1 should convert to 'Negative One'");
        assertEquals("Negative One Thousand", NumberToWords.convert(new BigDecimal("-1000")), "-1000 should convert to 'Negative One Thousand'");
    }

    @Test
    void testFractionalNumbers() {
        assertEquals("Zero Point One Two Three", NumberToWords.convert(new BigDecimal("0.123")), "0.123 should convert to 'Zero Point One Two Three'");
        assertEquals("Negative Zero Point Four Five Six", NumberToWords.convert(new BigDecimal("-0.456")), "-0.456 should convert to 'Negative Zero Point Four Five Six'");
    }

    @Test
    void testLargeNumbers() {
        assertEquals("Nine Hundred Ninety Nine Million Nine Hundred Ninety Nine Thousand Nine Hundred Ninety Nine", NumberToWords.convert(new BigDecimal("999999999")), "999999999 should convert correctly");
        assertEquals("One Trillion", NumberToWords.convert(new BigDecimal("1000000000000")), "1000000000000 should convert to 'One Trillion'");
    }

    @Test
    void testNegativeLargeNumbers() {
        assertEquals("Negative Nine Trillion Eight Hundred Seventy Six Billion Five Hundred Forty Three Million Two Hundred Ten Thousand Nine Hundred Eighty Seven", NumberToWords.convert(new BigDecimal("-9876543210987")), "-9876543210987 should convert correctly");
    }

    @Test
    void testFloatingPointPrecision() {
        assertEquals("One Million Point Zero Zero One", NumberToWords.convert(new BigDecimal("1000000.001")), "1000000.001 should convert to 'One Million Point Zero Zero One'");
    }

    @Test
    void testEdgeCases() {
        assertEquals("Zero", NumberToWords.convert(new BigDecimal("-0.0")), "-0.0 should convert to 'Zero'");
        assertEquals("Zero Point Zero Zero Zero Zero Zero Zero One", NumberToWords.convert(new BigDecimal("1E-7")), "1E-7 should convert to 'Zero Point Zero Zero Zero Zero Zero Zero One'");
    }
}
