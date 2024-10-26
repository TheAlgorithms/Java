package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IntegerToEnglishTest {

    @Test
    public void testIntegerToEnglish() {
        assertEquals("Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven", IntegerToEnglish.integerToEnglishWords(2147483647));
        assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", IntegerToEnglish.integerToEnglishWords(1234567));
        assertEquals("Twelve Thousand Three Hundred Forty Five", IntegerToEnglish.integerToEnglishWords(12345));
        assertEquals("One Hundred", IntegerToEnglish.integerToEnglishWords(100));
        assertEquals("Zero", IntegerToEnglish.integerToEnglishWords(0));
    }

    @Test
    public void testSmallNumbers() {
        assertEquals("Ten", IntegerToEnglish.integerToEnglishWords(10));
        assertEquals("Nineteen", IntegerToEnglish.integerToEnglishWords(19));
        assertEquals("Twenty One", IntegerToEnglish.integerToEnglishWords(21));
        assertEquals("Ninety Nine", IntegerToEnglish.integerToEnglishWords(99));
    }

    @Test
    public void testHundreds() {
        assertEquals("One Hundred One", IntegerToEnglish.integerToEnglishWords(101));
        assertEquals("Five Hundred Fifty", IntegerToEnglish.integerToEnglishWords(550));
        assertEquals("Nine Hundred Ninety Nine", IntegerToEnglish.integerToEnglishWords(999));
    }

    @Test
    public void testThousands() {
        assertEquals("One Thousand", IntegerToEnglish.integerToEnglishWords(1000));
        assertEquals("Ten Thousand One", IntegerToEnglish.integerToEnglishWords(10001));
        assertEquals("Seventy Six Thousand Five Hundred Forty Three", IntegerToEnglish.integerToEnglishWords(76543));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("One Million", IntegerToEnglish.integerToEnglishWords(1_000_000));
        assertEquals("One Billion", IntegerToEnglish.integerToEnglishWords(1_000_000_000));
        assertEquals("Two Thousand", IntegerToEnglish.integerToEnglishWords(2000));
    }
}
