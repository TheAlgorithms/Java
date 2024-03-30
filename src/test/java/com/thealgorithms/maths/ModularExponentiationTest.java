package com.thealgorihms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
public class ModularExponentiationTest {
  @Test
  public void testModExp() {
    assertEquals(3, ModularExponentiation.modExp(2, 3, 5));
    assertEquals(9, ModularExponentiation.modExp(3, 6, 20));
    assertEquals(1, ModularExponentiation.modExp(5, 0, 3));
    assertEquals(0, ModularExponentiation.modExp(0, 2, 5));
    assertEquals(1, ModularExponentiation.modExp(1, 100, 25));
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class,
                     () -> ModularExponentiation.modExp(4, 10, -15));
    assertEquals("Modulus must be positive integer", exception.getMessage());
  }
}
