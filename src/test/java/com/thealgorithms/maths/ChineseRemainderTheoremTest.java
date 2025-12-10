package com.thealgorithms.maths;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ChineseRemainderTheoremTest {
    @Test
    public void testCRTSimpleCase() {
        List<Integer> remainders = Arrays.asList(2, 3, 2);
        List<Integer> moduli = Arrays.asList(3, 5, 7);
        int expected = 23;
        int result = ChineseRemainderTheorem.solveCRT(remainders, moduli);
        assertEquals(expected, result);
    }

    @Test
    public void testCRTLargeModuli() {
        List<Integer> remainders = Arrays.asList(1, 2, 3);
        List<Integer> moduli = Arrays.asList(5, 7, 9);
        int expected = 156;
        int result = ChineseRemainderTheorem.solveCRT(remainders, moduli);
        assertEquals(expected, result);
    }

    @Test
    public void testCRTWithSingleCongruence() {
        List<Integer> remainders = singletonList(4);
        List<Integer> moduli = singletonList(7);
        int expected = 4;
        int result = ChineseRemainderTheorem.solveCRT(remainders, moduli);
        assertEquals(expected, result);
    }

    @Test
    public void testCRTWithMultipleSolutions() {
        List<Integer> remainders = Arrays.asList(0, 3);
        List<Integer> moduli = Arrays.asList(4, 5);
        int expected = 8;
        int result = ChineseRemainderTheorem.solveCRT(remainders, moduli);
        assertEquals(expected, result);
    }

    @Test
    public void testCRTLargeNumbers() {
        List<Integer> remainders = Arrays.asList(0, 4, 6);
        List<Integer> moduli = Arrays.asList(11, 13, 17);
        int expected = 550;
        int result = ChineseRemainderTheorem.solveCRT(remainders, moduli);
        assertEquals(expected, result);
    }
}
