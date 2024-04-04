package com.thealgorihms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
public class ModularExponentiationTest {
    @Test
    public void testModPow() {
        assertEquals(3, ModularExponentiation.modPow(2, 3, 5));
        assertEquals(9, ModularExponentiation.modPow(3, 6, 20));
        assertEquals(1, ModularExponentiation.modPow(5, 0, 3));
        assertEquals(0, ModularExponentiation.modPow(0, 2, 5));
        assertEquals(1, ModularExponentiation.modPow(1, 100, 25));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ModularExponentiation.modPow(4, 10, -15));
        assertEquals("Modulus must be positive integer", exception.getMessage());
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> ModularExponentiation.modPow(5, -3, 12));
    }
}
