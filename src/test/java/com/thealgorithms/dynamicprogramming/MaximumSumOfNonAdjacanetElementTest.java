package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MaxSumNonAdjacentTest {
  @Test
  public void testMaxSumNonAdjacentWithMultipleElements() {
    int[] array = { 3, 2, 5, 10, 7 };
    assertEquals(15, MaximumSumOfNonAdjacanetElement.maxSumNonAdjacent(array));
  }

  @Test
  public void testMaxSumNonAdjacentWithAllPositiveNumbers() {
    int[] array = { 5, 5, 10, 100, 10, 5 };
    assertEquals(110, MaximumSumOfNonAdjacanetElement.maxSumNonAdjacent(array));
  }

  @Test
  public void testMaxSumNonAdjacentWithSingleElement() {
    int[] array = { 7 };
    assertEquals(7, MaximumSumOfNonAdjacanetElement.maxSumNonAdjacent(array));
  }

  @Test
  public void testMaxSumNonAdjacentWithEmptyArray() {
    int[] array = {};
    assertEquals(0, MaximumSumOfNonAdjacanetElement.maxSumNonAdjacent(array));
  }

  @Test
  public void testMaxSumNonAdjacentWithNegativeNumbers() {
    int[] array = { 5, -2, 10, -4, 6 };
    assertEquals(16, MaximumSumOfNonAdjacanetElement.maxSumNonAdjacent(array));
  }

  @Test
  public void testMaxSumNonAdjacentWithAllNegativeNumbers() {
    int[] array = { -1, -2, -3, -4 };
    assertEquals(0, MaximumSumOfNonAdjacanetElement.maxSumNonAdjacent(array));
  }

  @Test
  public void testMaxSumNonAdjacentWithLargeNumbers() {
    int[] array = { 1000, 2000, 3000, 4000, 5000 };
    assertEquals(9000, MaximumSumOfNonAdjacanetElement.maxSumNonAdjacent(array));
  }
}
