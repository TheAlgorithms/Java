package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test case for Complement of Base 10 Number
 * @author Akshit Kumar Chandora (https://github.com/axitchandora)
 */

public class ComplementOfBaseTenNumberTest {

  @Test
  void testBitwiseComplement() {
    assertEquals(7, ComplementOfBaseTenNumber.bitwiseComplement(8));
    assertEquals(5, ComplementOfBaseTenNumber.bitwiseComplement(10));
    assertEquals(23, ComplementOfBaseTenNumber.bitwiseComplement(40));
  }
}
