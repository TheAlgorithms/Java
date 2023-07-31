package com.thealgorithms.maths;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class GenericRootTest {
    @Test
    public void testGenericRoot() {
        final Map<Integer, Integer> testCases = Map.ofEntries(entry(0, 0), entry(1, 1), entry(12345, 6), entry(123, 6), entry(15937, 7), entry(222222, 3), entry(99999, 9));
        for (final var tc : testCases.entrySet()) {
            assertEquals(tc.getValue(), GenericRoot.genericRoot(tc.getKey()));
        }
    }
}