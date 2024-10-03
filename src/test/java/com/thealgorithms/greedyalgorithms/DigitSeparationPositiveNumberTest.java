package com.thealgorithms.greedyalgorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class DigitSeparationPositiveNumberTest {

    @Test
    public void testDigitSeparationReverseOrder_SingleDigit() {
        DigitSeparationPositiveNumber digitSeparation = new DigitSeparationPositiveNumber();
        List<Long> result = digitSeparation.digitSeparationReverseOrder(5);
        assertEquals(List.of(5L), result);
    }

    @Test
    public void testDigitSeparationReverseOrderMultipleDigits() {
        DigitSeparationPositiveNumber digitSeparation = new DigitSeparationPositiveNumber();
        List<Long> result = digitSeparation.digitSeparationReverseOrder(123);
        assertEquals(List.of(3L, 2L, 1L), result);
    }

    @Test
    public void testDigitSeparationReverseOrderLargeNumber() {
        DigitSeparationPositiveNumber digitSeparation = new DigitSeparationPositiveNumber();
        List<Long> result = digitSeparation.digitSeparationReverseOrder(123456789);
        assertEquals(List.of(9L, 8L, 7L, 6L, 5L, 4L, 3L, 2L, 1L), result);
    }

    @Test
    public void testDigitSeparationReverseOrderZero() {
        DigitSeparationPositiveNumber digitSeparation = new DigitSeparationPositiveNumber();
        List<Long> result = digitSeparation.digitSeparationReverseOrder(0);
        assertEquals(List.of(0L), result);
    }

    @Test
    public void testDigitSeparationForwardOrderSingleDigit() {
        DigitSeparationPositiveNumber digitSeparation = new DigitSeparationPositiveNumber();
        List<Long> result = digitSeparation.digitSeparationForwardOrder(5);
        assertEquals(List.of(5L), result);
    }

    @Test
    public void testDigitSeparationForwardOrderMultipleDigits() {
        DigitSeparationPositiveNumber digitSeparation = new DigitSeparationPositiveNumber();
        List<Long> result = digitSeparation.digitSeparationForwardOrder(123);
        assertEquals(List.of(1L, 2L, 3L), result);
    }

    @Test
    public void testDigitSeparationForwardOrderLargeNumber() {
        DigitSeparationPositiveNumber digitSeparation = new DigitSeparationPositiveNumber();
        List<Long> result = digitSeparation.digitSeparationForwardOrder(123456789);
        assertEquals(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L), result);
    }

    @Test
    public void testDigitSeparationForwardOrderZero() {
        DigitSeparationPositiveNumber digitSeparation = new DigitSeparationPositiveNumber();
        List<Long> result = digitSeparation.digitSeparationForwardOrder(0);
        assertEquals(List.of(0L), result);
    }
}
