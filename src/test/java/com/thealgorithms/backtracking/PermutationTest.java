package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PermutationTest {

    @Test
    void testNoElement() {
        List<Integer[]> result = Permutation.permutation(new Integer[] {});
        assertEquals(result.get(0).length, 0);
    }

    @Test
    void testSingleElement() {
        List<Integer[]> result = Permutation.permutation(new Integer[] { 1 });
        assertEquals(result.get(0)[0], 1);
    }

    @Test
    void testMultipleElements() {
        List<Integer[]> result = Permutation.permutation(
            new Integer[] { 1, 2 }
        );
        assertTrue(Arrays.equals(result.get(0), new Integer[] { 1, 2 }));
        assertTrue(Arrays.equals(result.get(1), new Integer[] { 2, 1 }));
    }
}
