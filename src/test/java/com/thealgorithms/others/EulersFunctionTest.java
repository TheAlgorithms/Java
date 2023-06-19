package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class EulersFunctionTest {
    @Test
    public void testGetEuler() {
        HashMap<Integer, Integer> testCases = new HashMap<>();
        testCases.put(1, 1);
        testCases.put(2, 1);
        testCases.put(3, 2);
        testCases.put(4, 2);
        testCases.put(5, 4);
        testCases.put(6, 2);
        testCases.put(10, 4);
        testCases.put(21, 12);
        testCases.put(69, 44);
        testCases.put(47, 46);
        testCases.put(46, 22);
        testCases.put(55, 40);
        testCases.put(34, 16);
        testCases.put(20, 8);
        testCases.put(20, 8);
        testCases.put(1024, 512);

        for (final var tc : testCases.entrySet()) {
            assertEquals(tc.getValue(), EulersFunction.getEuler(tc.getKey()));
        }
    }

    @Test
    public void testGetEulerThrowsExceptionForNonPositiveInput() {
        assertThrows(IllegalArgumentException.class, () -> EulersFunction.getEuler(0));
    }
}
