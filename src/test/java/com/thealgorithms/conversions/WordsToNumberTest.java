package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class WordsToNumberTest {

    @Test
    void testNullInput() {
        WordsToNumberException exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert(null));
        assertEquals(WordsToNumberException.ErrorType.NULL_INPUT, exception.getErrorType(), "Exception should be of type NULL_INPUT");
    }

    @Test
    void testStandardCases() {
        assertEquals("0", WordsToNumber.convert("zero"), "'zero' should convert to '0'");
        assertEquals("5", WordsToNumber.convert("five"), "'five' should convert to '5'");
        assertEquals("21", WordsToNumber.convert("twenty one"), "'twenty one' should convert to '21'");
        assertEquals("101", WordsToNumber.convert("one hundred one"), "'one hundred' should convert to '101'");
        assertEquals("342", WordsToNumber.convert("three hundred and forty two"), "'three hundred and forty two' should convert to '342'");
    }

    @Test
    void testLargeNumbers() {
        assertEquals("1000000", WordsToNumber.convert("one million"), "'one million' should convert to '1000000'");
        assertEquals("1000000000", WordsToNumber.convert("one billion"), "'one billion' should convert to '1000000000'");
        assertEquals("1000000000000", WordsToNumber.convert("one trillion"), "'one trillion' should convert to '1000000000000'");
        assertEquals("999000000900999", WordsToNumber.convert("nine hundred ninety nine trillion nine hundred thousand nine hundred and ninety nine"), "'nine hundred ninety nine trillion nine hundred thousand nine hundred and ninety nine' should convert to '999000000900999'");
    }

    @Test
    void testNegativeNumbers() {
        assertEquals("-5", WordsToNumber.convert("negative five"), "'negative five' should convert to '-5'");
        assertEquals("-120", WordsToNumber.convert("negative one hundred and twenty"), "'negative one hundred and twenty' should convert correctly");
    }

    @Test
    void testNegativeLargeNumbers() {
        assertEquals("-1000000000000", WordsToNumber.convert("negative one trillion"), "'negative one trillion' should convert to '-1000000000000'");
        assertEquals("-9876543210987", WordsToNumber.convert("Negative Nine Trillion Eight Hundred Seventy Six Billion Five Hundred Forty Three Million Two Hundred Ten Thousand Nine Hundred Eighty Seven"), "");
    }

    @Test
    void testDecimalNumbers() {
        assertEquals("3.1415", WordsToNumber.convert("three point one four one five"), "'three point one four one five' should convert to '3.1415'");
        assertEquals("-2.718", WordsToNumber.convert("negative two point seven one eight"), "'negative two point seven one eight' should convert to '-2.718'");
        assertEquals("-1E-7", WordsToNumber.convert("negative zero point zero zero zero zero zero zero one"), "'negative zero point zero zero zero zero zero zero one' should convert to '-1E-7'");
    }

    @Test
    void testLargeDecimalNumbers() {
        assertEquals("1000000000.0000000001", WordsToNumber.convert("one billion point zero zero zero zero zero zero zero zero zero one"), "Tests a large whole number with a tiny fractional part");
        assertEquals("999999999999999.9999999999999",
            WordsToNumber.convert("nine hundred ninety nine trillion nine hundred ninety nine billion nine hundred ninety nine million nine hundred ninety nine thousand nine hundred ninety nine point nine nine nine nine nine nine nine nine nine nine nine nine nine"),
            "Tests maximum scale handling for large decimal numbers");
        assertEquals("0.505", WordsToNumber.convert("zero point five zero five"), "Tests a decimal with an internal zero, ensuring correct parsing");
        assertEquals("42.00000000000001", WordsToNumber.convert("forty two point zero zero zero zero zero zero zero zero zero zero zero zero zero one"), "Tests a decimal with leading zeros before a significant figure");
        assertEquals("7.89E-7", WordsToNumber.convert("zero point zero zero zero zero zero zero seven eight nine"), "Tests scientific notation for a small decimal with multiple digits");
        assertEquals("0.999999", WordsToNumber.convert("zero point nine nine nine nine nine nine"), "Tests a decimal close to one with multiple repeated digits");
    }

    @Test
    void testCaseInsensitivity() {
        assertEquals("21", WordsToNumber.convert("TWENTY-ONE"), "Uppercase should still convert correctly");
        assertEquals("-100.0001", WordsToNumber.convert("negAtiVe OnE HuNdReD, point ZeRO Zero zERo ONE"), "Mixed case should still convert correctly");
        assertEquals("-225647.00019", WordsToNumber.convert("nEgative twO HundRed, and twenty-Five thOusaNd, six huNdred Forty-Seven, Point zero zero zero One nInE"));
    }

    @Test
    void testInvalidInputs() {
        WordsToNumberException exception;

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("negative one hundred AlPha"));
        assertEquals(WordsToNumberException.ErrorType.UNKNOWN_WORD, exception.getErrorType());

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("twenty thirteen"));
        assertEquals(WordsToNumberException.ErrorType.UNEXPECTED_WORD, exception.getErrorType());

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("negative negative ten"));
        assertEquals(WordsToNumberException.ErrorType.MULTIPLE_NEGATIVES, exception.getErrorType());

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("one hundred hundred"));
        assertEquals(WordsToNumberException.ErrorType.UNEXPECTED_WORD, exception.getErrorType());

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("one thousand and hundred"));
        assertEquals(WordsToNumberException.ErrorType.INVALID_CONJUNCTION, exception.getErrorType());

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("one thousand hundred"));
        assertEquals(WordsToNumberException.ErrorType.UNEXPECTED_WORD, exception.getErrorType());

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("nine hundred and nine hundred"));
        assertEquals(WordsToNumberException.ErrorType.INVALID_CONJUNCTION, exception.getErrorType());

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("forty two point"));
        assertEquals(WordsToNumberException.ErrorType.MISSING_DECIMAL_NUMBERS, exception.getErrorType());

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("sixty seven point hello"));
        assertEquals(WordsToNumberException.ErrorType.UNEXPECTED_WORD_AFTER_POINT, exception.getErrorType());

        exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convert("one negative"));
        assertEquals(WordsToNumberException.ErrorType.INVALID_NEGATIVE, exception.getErrorType());
    }

    @Test
    void testConvertToBigDecimal() {
        assertEquals(new BigDecimal("-100000000000000.056"), WordsToNumber.convertToBigDecimal("negative one hundred trillion point zero five six"), "should convert to appropriate BigDecimal value");

        WordsToNumberException exception = assertThrows(WordsToNumberException.class, () -> WordsToNumber.convertToBigDecimal(null));
        assertEquals(WordsToNumberException.ErrorType.NULL_INPUT, exception.getErrorType(), "Exception should be of type NULL_INPUT");
    }
}
