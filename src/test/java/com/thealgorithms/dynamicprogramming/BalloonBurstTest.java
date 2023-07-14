package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BalloonBurstTest {
    BalloonBurst balloonBurst = new BalloonBurst();

    @Test
    public void testMaxCoinsCase1() {
        int[] nums = {3, 1, 5, 8};
        int expected = 167;
        assertEquals(expected, balloonBurst.maxCoins(nums));
    }

    @Test
    public void testMaxCoinsCase2() {
        int[] nums = {2, 4, 3, 5, 1};
        int expected = 120;
        assertEquals(expected, balloonBurst.maxCoins(nums));
    }

    @Test
    public void testMaxCoinsCase3() {
        int[] nums = {1, 2, 3};
        int expected = 12;
        assertEquals(expected, balloonBurst.maxCoins(nums));
    }

    @Test
    public void testMaxCoinsCase4() {
        int[] nums = {5};
        int expected = 5;
        assertEquals(expected, balloonBurst.maxCoins(nums));
    }

    @Test
    public void testMaxCoinsCase5() {
        int[] nums = {};
        int expected = 0;
        assertEquals(expected, balloonBurst.maxCoins(nums));
    }
}