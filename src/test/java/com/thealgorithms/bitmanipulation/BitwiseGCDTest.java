package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class BitwiseGCDTest {

    @Test
    public void testGcdBasic() {
        assertEquals(6L, BitwiseGCD.gcd(48L, 18L));
    }

    @Test
    public void testGcdZeroAndNonZero() {
        assertEquals(5L, BitwiseGCD.gcd(0L, 5L));
        assertEquals(5L, BitwiseGCD.gcd(5L, 0L));
    }

    @Test
    public void testGcdBothZero() {
        assertEquals(0L, BitwiseGCD.gcd(0L, 0L));
    }

    @Test
    public void testGcdNegativeInputs() {
        assertEquals(6L, BitwiseGCD.gcd(-48L, 18L));
        assertEquals(6L, BitwiseGCD.gcd(48L, -18L));
        assertEquals(6L, BitwiseGCD.gcd(-48L, -18L));
    }

    @Test
    public void testGcdIntOverload() {
        assertEquals(6, BitwiseGCD.gcd(48, 18));
    }

    @Test
    public void testGcdArray() {
        long[] values = {48L, 18L, 6L};
        assertEquals(6L, BitwiseGCD.gcd(values));
    }

    @Test
    public void testGcdEmptyArray() {
        long[] empty = {};
        assertEquals(0L, BitwiseGCD.gcd(empty));
    }

    @Test
    public void testGcdCoprime() {
        assertEquals(1L, BitwiseGCD.gcd(17L, 13L));
    }

    @Test
    public void testGcdPowersOfTwo() {
        assertEquals(1024L, BitwiseGCD.gcd(1L << 20, 1L << 10));
    }

    @Test
    public void testGcdLargeNumbers() {
        assertEquals(6L, BitwiseGCD.gcd(270L, 192L));
    }

    @Test
    public void testGcdEarlyExitArray() {
        long[] manyCoprimes = {7L, 11L, 13L, 17L, 19L, 23L, 29L};
        assertEquals(1L, BitwiseGCD.gcd(manyCoprimes));
    }

    @Test
    public void testGcdSameNumbers() {
        assertEquals(42L, BitwiseGCD.gcd(42L, 42L));
    }

    @Test
    public void testGcdLongMinValueBigInteger() {
        // gcd(Long.MIN_VALUE, 0) = |Long.MIN_VALUE| = 2^63; must use BigInteger to represent it
        BigInteger expected = BigInteger.ONE.shiftLeft(63); // 2^63
        assertEquals(expected, BitwiseGCD.gcdBig(Long.MIN_VALUE, 0L));
    }

    @Test
    public void testGcdLongMinValueLongOverloadThrows() {
        // The long overload cannot return 2^63 as a positive signed long, so it must throw
        assertThrows(ArithmeticException.class, () -> BitwiseGCD.gcd(Long.MIN_VALUE, 0L));
    }

    @Test
    public void testGcdWithLongMinAndOther() {
        // gcd(Long.MIN_VALUE, 2^10) should be 2^10
        long p = 1L << 10;
        BigInteger expected = BigInteger.valueOf(p);
        assertEquals(expected, BitwiseGCD.gcdBig(Long.MIN_VALUE, p));
    }

    @Test
    public void testGcdWithBothLongMin() {
        // gcd(Long.MIN_VALUE, Long.MIN_VALUE) = 2^63
        BigInteger expected = BigInteger.ONE.shiftLeft(63);
        assertEquals(expected, BitwiseGCD.gcdBig(Long.MIN_VALUE, Long.MIN_VALUE));
    }

    @Test
    public void testGcdEdgeCasesMixed() {
        assertEquals(1L, BitwiseGCD.gcd(1L, Long.MAX_VALUE));
        assertEquals(1L, BitwiseGCD.gcd(Long.MAX_VALUE, 1L));
    }
}
