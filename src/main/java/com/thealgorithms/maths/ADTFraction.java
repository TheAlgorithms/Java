package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ADTFractionTest {

    @Test
    void testAddition() {
        ADTFraction a = new ADTFraction(1, 2);
        ADTFraction b = new ADTFraction(1, 3);
        assertEquals(new ADTFraction(5, 6), a.plus(b));
    }

    @Test
    void testMultiplication() {
        ADTFraction a = new ADTFraction(2, 3);
        ADTFraction b = new ADTFraction(3, 4);
        assertEquals(new ADTFraction(1, 2), a.times(b)); // simplified to 1/2
    }

    @Test
    void testReciprocal() {
        ADTFraction a = new ADTFraction(3, 4);
        assertEquals(new ADTFraction(4, 3), a.reciprocal());
    }

    @Test
    void testSimplification() {
        ADTFraction a = new ADTFraction(2, 4);
        assertEquals(new ADTFraction(1, 2), a);
    }
}
