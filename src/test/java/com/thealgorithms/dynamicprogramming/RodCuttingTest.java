package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RodCuttingTest {

    @Test
    public void testCutRodWithSinglePiece() {
        // Test case 1: Rod length is 1 with a single piece
        int[] price1 = {2};
        assertEquals(2, RodCutting.cutRod(price1, 1));
    }

    @Test
    public void testCutRodWithTwoPieces() {
        // Test case 2: Rod length is 2 with two pieces
        int[] price2 = {2, 5};
        assertEquals(5, RodCutting.cutRod(price2, 2));
    }

    @Test
    public void testCutRodWithThreePieces() {
        // Test case 3: Rod length is 3 with three pieces
        int[] price3 = {2, 5, 13};
        assertEquals(13, RodCutting.cutRod(price3, 3));
    }

    @Test
    public void testCutRodWithFourPieces() {
        // Test case 4: Rod length is 4 with four pieces
        int[] price4 = {2, 5, 13, 19};
        assertEquals(19, RodCutting.cutRod(price4, 4));
    }

    @Test
    public void testCutRodWithFivePieces() {
        // Test case 5: Rod length is 5 with five pieces
        int[] price5 = {2, 5, 13, 19, 20};
        assertEquals(21, RodCutting.cutRod(price5, 5));
    }

    @Test
    public void testCutRodWithNegativeInput() {
        // Test case 6: Negative rod length (should throw an IllegalArgumentException)
        int[] negativePrice = {2, 5, 13};
        assertThrows(NegativeArraySizeException.class, () -> RodCutting.cutRod(negativePrice, -3));
        
        // Test case 7: Negative prices in the price array (should return 13)
        int[] negativePrices = {2, -5, 13};
        assertEquals(13, RodCutting.cutRod(negativePrices, 3));
    }

    @Test
    public void testCutRodWithZeroLength() {
        // Test case 8: Rod length is 0 (should return 0 as the rod cannot be cut)
        int[] emptyPrice = {};
        assertEquals(0, RodCutting.cutRod(emptyPrice, 0));
        
        // Test case 9: Rod length is greater than the length of the price array (invalid input)
        int[] price6 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> RodCutting.cutRod(price6, 20));
    }

    @Test
    public void testCutRodWithLargeInput() {
        // Test case 10: Large rod length
        int[] price7 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(10, RodCutting.cutRod(price7, 10));
    }
}
