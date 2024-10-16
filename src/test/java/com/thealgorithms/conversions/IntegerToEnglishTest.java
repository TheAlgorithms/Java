package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IntegerToEnglishTest {

    @Test
    public void testIntegerToEnglish() {
        assertEquals("Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven", IntegerToEnglish.integerToEnglishWords(2147483647));
        assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", IntegerToEnglish.integerToEnglishWords(1234567));
        assertEquals("Twelve Thousand Three Hundred Forty Five", IntegerToEnglish.integerToEnglishWords(12345));
    }
}
