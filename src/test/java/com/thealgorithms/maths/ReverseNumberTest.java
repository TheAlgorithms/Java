package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class ReverseNumberTest {

    @Test
    public void testReverseNumber() {
        HashMap<Integer, Integer> testCases = new HashMap<>();
        testCases.put(0, 0);
        testCases.put(1, 1);
        testCases.put(10, 1);
        testCases.put(123, 321);
        testCases.put(7890, 987);

        for (final var tc : testCases.entrySet()) {
            assertEquals(ReverseNumber.reverseNumber(tc.getKey()), tc.getValue());
        }
    }

    @Test
    public void testReverseNumberThrowsExceptionForNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> ReverseNumber.reverseNumber(-1));
    }
}
