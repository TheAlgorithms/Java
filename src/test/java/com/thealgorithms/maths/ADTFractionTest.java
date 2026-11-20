package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ADTFractionTest {

    private final ADTFraction fraction1 = new ADTFraction(3, 5);
    private final ADTFraction fraction2 = new ADTFraction(7, 8);

    @Test
    void testConstructorWithDenominatorEqualToZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ADTFraction(1, 0));
        assertEquals("Denominator cannot be 0", exception.getMessage());
    }

    @Test
    public void testPlus() {
        assertEquals(new ADTFraction(59, 40), fraction1.plus(fraction2));
    }

    @Test
    public void testTimes() {
        assertEquals(new ADTFraction(12, 5), fraction1.times(4));
        assertEquals(new ADTFraction(21, 40), fraction1.times(fraction2));
    }

    @Test
    public void testReciprocal() {
        assertEquals(new ADTFraction(5, 3), fraction1.reciprocal());
    }

    @Test
    public void testValue() {
        assertEquals(0.6F, fraction1.value());
    }

    @Test
    public void testEqualsAndHashCode() {
        ADTFraction fraction3 = new ADTFraction(3, 5);
        assertTrue(fraction1.equals(fraction3) && fraction3.equals(fraction1));
        assertEquals(fraction1.hashCode(), fraction3.hashCode());

        ADTFraction equivalent = new ADTFraction(6, 10);
        assertEquals(fraction1, equivalent);
        assertEquals(fraction1.hashCode(), equivalent.hashCode());

        ADTFraction negativeDenom = new ADTFraction(3, -5);
        ADTFraction negativeNum = new ADTFraction(-3, 5);
        assertEquals(negativeNum, negativeDenom);
        assertEquals(negativeNum.hashCode(), negativeDenom.hashCode());
    }

    @Test
    public void testReductionAndNormalization() {
        ADTFraction f = new ADTFraction(4, 8);
        assertEquals(1, f.numerator());
        assertEquals(2, f.denominator());

        ADTFraction neg = new ADTFraction(2, -4);
        assertEquals(-1, neg.numerator());
        assertEquals(2, neg.denominator());

        ADTFraction zero = new ADTFraction(0, 5);
        assertEquals(0, zero.numerator());
        assertEquals(1, zero.denominator());
    }

    @Test
    public void testToString() {
        assertEquals("3/5", fraction1.toString());
        assertEquals("-3/5", new ADTFraction(3, -5).toString());
    }
}
