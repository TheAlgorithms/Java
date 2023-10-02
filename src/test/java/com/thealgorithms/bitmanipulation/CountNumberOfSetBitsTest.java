package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CountNumberOfSetBitsTest {

  @Test
  public void testCountNumberOfSetBits() {
      assertEquals(4, CountNumberOfSetBits.countNumberOfSetBits(15));
      assertNotEquals(1, CountNumberOfSetBits.countNumberOfSetBits(3));
  }
}
