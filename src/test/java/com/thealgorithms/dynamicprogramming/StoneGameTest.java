package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoneGameTest {

    @Test
    void stoneGame_case1() {
        int[] stones = {5, 3, 4, 5};
        assertEquals(true, StoneGame.stoneGame(stones));
    }

    @Test
    void stoneGame_case2() {
        int[] stones = {5};
        assertEquals(true, StoneGame.stoneGame(stones));
    }

    @Test
    void stoneGame_case3() {
        int[] stones = {2, 4, 1};
        assertEquals(false, StoneGame.stoneGame(stones));
    }

    @Test
    void stoneGame_case4() {
        int[] stones = {3, 3, 3, 3};
        assertEquals(false, StoneGame.stoneGame(stones));
    }
}
