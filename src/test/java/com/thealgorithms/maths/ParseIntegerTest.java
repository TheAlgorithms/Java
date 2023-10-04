package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 01/07/2023
 */
public class ParseIntegerTest {
    private static final String NULL_PARAMETER_MESSAGE = "Input parameter must not be null!";
    private static final String EMPTY_PARAMETER_MESSAGE = "Input parameter must not be empty!";
    private static final String INCORRECT_FORMAT_MESSAGE = "Input parameter of incorrect format";

    @Test
    public void testNullInput() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> ParseInteger.parseInt(null));
        Assertions.assertEquals(exception.getMessage(), NULL_PARAMETER_MESSAGE);
    }

    @Test
    public void testEmptyInput() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> ParseInteger.parseInt(""));
        Assertions.assertEquals(exception.getMessage(), EMPTY_PARAMETER_MESSAGE);
    }

    @Test
    public void testInputOfIncorrectFormat() {
        IllegalArgumentException exception = Assertions.assertThrows(NumberFormatException.class, () -> ParseInteger.parseInt("+0a123"));
        Assertions.assertTrue(exception.getMessage().contains(INCORRECT_FORMAT_MESSAGE));

        exception = Assertions.assertThrows(NumberFormatException.class, () -> ParseInteger.parseInt("b"));
        Assertions.assertTrue(exception.getMessage().contains(INCORRECT_FORMAT_MESSAGE));
    }

    @Test
    public void testPositiveValueIsSuccessfullyConverted() {
        Assertions.assertEquals(ParseInteger.parseInt("0"), Integer.parseInt("0"));
        Assertions.assertEquals(ParseInteger.parseInt("123"), Integer.parseInt("123"));
        Assertions.assertEquals(ParseInteger.parseInt("0123"), Integer.parseInt("0123"));
        Assertions.assertEquals(ParseInteger.parseInt("+0123"), Integer.parseInt("+0123"));
        Assertions.assertEquals(ParseInteger.parseInt("+123"), Integer.parseInt("+123"));
    }

    @Test
    public void testNegativeValueIsSuccessfullyConverted() {
        Assertions.assertEquals(ParseInteger.parseInt("-1"), Integer.parseInt("-1"));
        Assertions.assertEquals(ParseInteger.parseInt("-123"), Integer.parseInt("-123"));
        Assertions.assertEquals(ParseInteger.parseInt("-0123"), Integer.parseInt("-0123"));
        Assertions.assertEquals(ParseInteger.parseInt("-00123"), Integer.parseInt("-00123"));
    }
}
