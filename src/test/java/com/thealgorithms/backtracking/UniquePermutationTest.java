package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class UniquePermutationTest {

    @Test
    void testUniquePermutations_AAB() {
        List<String> expected = Arrays.asList("AAB", "ABA", "BAA");
        List<String> result = UniquePermutation.generateUniquePermutations("AAB");
        assertEquals(expected, result);
    }

    @Test
    void testUniquePermutations_ABC() {
        List<String> expected = Arrays.asList("ABC", "ACB", "BAC", "BCA", "CAB", "CBA");
        List<String> result = UniquePermutation.generateUniquePermutations("ABC");
        assertEquals(expected, result);
    }

    @Test
    void testEmptyString() {
        List<String> expected = Arrays.asList("");
        List<String> result = UniquePermutation.generateUniquePermutations("");
        assertEquals(expected, result);
    }
}
