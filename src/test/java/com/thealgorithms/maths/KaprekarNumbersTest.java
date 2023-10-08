package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class KaprekarNumbersTest {

    @Test
    void testFor1() {
        assertTrue(KaprekarNumbers.isKaprekarNumber(1));
    }

    @Test
    void testFor45() {
        assertTrue(KaprekarNumbers.isKaprekarNumber(45));
    }

    @Test
    void testFor297() {
        assertTrue(KaprekarNumbers.isKaprekarNumber(297));
    }

    @Test
    void testFor2223() {
        assertTrue(KaprekarNumbers.isKaprekarNumber(2223));
    }

    @Test
    void testFor857143() {
        assertTrue(KaprekarNumbers.isKaprekarNumber(857143));
    }

    @Test
    void testFor3() {
        assertFalse(KaprekarNumbers.isKaprekarNumber(3));
    }

    @Test
    void testFor26() {
        assertFalse(KaprekarNumbers.isKaprekarNumber(26));
    }

    @Test
    void testFor98() {
        assertFalse(KaprekarNumbers.isKaprekarNumber(98));
    }

    @Test
    void testForRangeOfNumber() {
        try {
            List<Long> rangedNumbers = KaprekarNumbers.kaprekarNumberInRange(1, 100000);
            long[] allTheNumbers = {
                1,
                9,
                45,
                55,
                99,
                297,
                703,
                999,
                2223,
                2728,
                4950,
                5050,
                7272,
                7777,
                9999,
                17344,
                22222,
                77778,
                82656,
                95121,
                99999,
            };
            for (long i : allTheNumbers) {
                assert rangedNumbers.contains(i);
            }
        } catch (Exception e) {
            assert false;
        }
    }
}
