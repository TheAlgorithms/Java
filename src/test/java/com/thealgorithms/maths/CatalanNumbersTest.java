package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for CatalanNumbers
 */
class CatalanNumbersTest {

    @Test
    void testCatalanNumbers() {
        assertEquals(1, CatalanNumbers.catalan(0)); // C(0) = 1
        assertEquals(1, CatalanNumbers.catalan(1)); // C(1) = 1
        assertEquals(2, CatalanNumbers.catalan(2)); // C(2) = 2
        assertEquals(5, CatalanNumbers.catalan(3)); // C(3) = 5
        assertEquals(14, CatalanNumbers.catalan(4)); // C(4) = 14
        assertEquals(42, CatalanNumbers.catalan(5)); // C(5) = 42
        assertEquals(132, CatalanNumbers.catalan(6)); // C(6) = 132
        assertEquals(429, CatalanNumbers.catalan(7)); // C(7) = 429
        assertEquals(1430, CatalanNumbers.catalan(8)); // C(8) = 1430
        assertEquals(4862, CatalanNumbers.catalan(9)); // C(9) = 4862
        assertEquals(16796, CatalanNumbers.catalan(10)); // C(10) = 16796
    }

    @Test
    void testIllegalInput() {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> CatalanNumbers.catalan(-1)), () -> assertThrows(IllegalArgumentException.class, () -> CatalanNumbers.catalan(-5)));
    }
}
