package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the MinimumConsecutiveCards class.
 *
 * @author (https://github.com/Chiefpatwal)
 */
public class MinimumConsecutiveCardsTest {

    @Test
    public void testMinimumCardPickup() {
        // Test cases for the minimumCardPickup method
        assertEquals(2, MinimumConsecutiveCards.minimumCardPickup(new int[] {1, 2, 3, 2, 4, 1}));
        assertEquals(-1, MinimumConsecutiveCards.minimumCardPickup(new int[] {1, 2, 3, 4, 5}));
        assertEquals(2, MinimumConsecutiveCards.minimumCardPickup(new int[] {1, 1, 1, 1}));
        assertEquals(2, MinimumConsecutiveCards.minimumCardPickup(new int[] {2, 2, 3, 4, 5}));
    }
}
