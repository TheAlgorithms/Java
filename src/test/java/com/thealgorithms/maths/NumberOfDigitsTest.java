package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfDigitsTest {
  @Test
  public void testNumberOfDigits() {
    assertEquals(9, NumberOfDigits.numberOfDigits(987654321));
  }

  @Test
  public void testNumberOfDigitsFast() {
    assertEquals(8, NumberOfDigits.numberOfDigitsFast(98765432));
  }

  @Test
  public void testNumberOfDigitsFaster() {
    assertEquals(7, NumberOfDigits.numberOfDigitsFaster(7765321));
  }

  @Test
  public void testNumberOfDigitsRecursion() {
    assertEquals(8, NumberOfDigits.numberOfDigitsRecursion(88654321));
  }
}