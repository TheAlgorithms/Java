package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test case for the CountSetBits class.
 * Author: Siddhaarth Raj Gangaraj (https://github.com/SiddhaarthG)
 */
class CountSetBitsTest {

  @Test
  void testCountSetBits() {
    // Test with zero
    long n = 0;
    int result = CountSetBits.countSetBits(n);
    assertEquals(0, result);

    // Test with a positive integer (15: Binary representation: 1111)
    n = 15;
    result = CountSetBits.countSetBits(n);
    assertEquals(4, result);

    // Test with a larger positive integer (255: Binary representation: 11111111)
    n = 255;
    result = CountSetBits.countSetBits(n);
    assertEquals(8, result);

    // Test with a negative integer (-1: Binary representation: 11111111 in two's complement)
    n = -1;
    try {
      CountSetBits.countSetBits(n);
      fail("Expected IllegalArgumentException for negative input");
    } catch (IllegalArgumentException e) {
      // Expected exception, check the exception message
      assertEquals("Input integer must be non-negative.", e.getMessage());
    }
  }
}
