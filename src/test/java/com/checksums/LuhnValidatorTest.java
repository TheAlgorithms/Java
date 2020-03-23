package com.checksums;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LuhnValidatorTest {

	private LuhnValidator luhnValidator;

    @BeforeEach
    public void setUp() {
        luhnValidator = new LuhnValidator();
    }

    @Test
    public void testSingleDigitStringInvalid() {
        assertFalse(luhnValidator.isValid("1"));
    }

    @Test
    public void testSingleZeroIsInvalid() {
        assertFalse(luhnValidator.isValid("0"));
    }

    @Test
    public void testSimpleValidSINReversedRemainsValid() {
        assertTrue(luhnValidator.isValid("059"));
    }


    @Test
    public void testSimpleValidSINReversedBecomesInvalid() {
        assertTrue(luhnValidator.isValid("59"));
    }

 
    @Test
    public void testValidCanadianSINValid() {
        assertTrue(luhnValidator.isValid("055 444 285"));
    }


    @Test
    public void testInvalidCanadianSINInvalid() {
        assertFalse(luhnValidator.isValid("055 444 286"));
    }

    @Test
    public void testInvalidCreditCardInvalid() {
        assertFalse(luhnValidator.isValid("8273 1232 7352 0569"));
    }

    @Test
    public void testValidNumberWithAnEvenNumberOfDigits() {
        assertTrue(luhnValidator.isValid("095 245 88"));
    }

    @Test
    public void testValidNumberWithAnOddNumberOfSpaces() {
        assertTrue(luhnValidator.isValid("234 567 891 234"));
    }

    @Test
    public void testValidStringsWithANonDigitAtEndInvalid() {
        assertFalse(luhnValidator.isValid("059a"));
    }

    @Test
    public void testStringContainingPunctuationInvalid() {
        assertFalse(luhnValidator.isValid("055-444-285"));
    }

    @Test
    public void testStringContainingSymbolsInvalid() {
        assertFalse(luhnValidator.isValid("055# 444$ 285"));
    }

    @Test
    public void testSingleSpaceWithZeroInvalid() {
        assertFalse(luhnValidator.isValid(" 0"));
    }

    @Test
    public void testMoreThanSingleZeroValid() {
        assertTrue(luhnValidator.isValid("0000 0"));
    }

    @Test
    public void testDigitNineConvertedToOutputNine() {
        assertTrue(luhnValidator.isValid("091"));
    }
}
