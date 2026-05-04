package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class PermutationsTest {

    @Test
    void testIntegerPermutations() {
        int[] nums = {1, 2};
        List<List<Integer>> result = Permutations.permutations(nums);
        assertEquals(2, result.size());
    }

    @Test
    void testStringPermutations() {
        List<String> result = Permutations.permutations("ab");
        assertEquals(2, result.size());
    }
}