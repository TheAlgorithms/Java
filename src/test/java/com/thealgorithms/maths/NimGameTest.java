package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NimGameTest {

    @Test
    public void testFirstPlayerWins() {
        assertTrue(NimGame.canWinNim(1));
        assertTrue(NimGame.canWinNim(2));
        assertTrue(NimGame.canWinNim(3));
        assertTrue(NimGame.canWinNim(5));
    }

    @Test
    public void testSecondPlayerWins() {
        assertFalse(NimGame.canWinNim(4));
        assertFalse(NimGame.canWinNim(8));
        assertFalse(NimGame.canWinNim(12));
    }
}
