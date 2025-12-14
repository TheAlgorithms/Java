package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

public class MoveToFrontTest {

    @Test
    public void testTransformAndInverseBananaExample() {
        String original = "annb$aa";
        String alphabet = "$abn";
        List<Integer> expectedTransform = List.of(1, 3, 0, 3, 3, 3, 0);

        // Test forward transform
        List<Integer> actualTransform = MoveToFront.transform(original, alphabet);
        assertEquals(expectedTransform, actualTransform);

        // Test inverse transform
        String reconstructed = MoveToFront.inverseTransform(actualTransform, alphabet);
        assertEquals(original, reconstructed);
    }

    @Test
    public void testTransformAndInverseCabaaExample() {
        String original = "cabaa";
        String alphabet = "abcdef";
        List<Integer> expectedTransform = List.of(2, 1, 2, 1, 0);

        // Test forward transform
        List<Integer> actualTransform = MoveToFront.transform(original, alphabet);
        assertEquals(expectedTransform, actualTransform);

        // Test inverse transform
        String reconstructed = MoveToFront.inverseTransform(actualTransform, alphabet);
        assertEquals(original, reconstructed);
    }

    @Test
    public void testEmptyInput() {
        String original = "";
        String alphabet = "abc";
        List<Integer> expectedTransform = List.of();

        List<Integer> actualTransform = MoveToFront.transform(original, alphabet);
        assertEquals(expectedTransform, actualTransform);

        String reconstructed = MoveToFront.inverseTransform(actualTransform, alphabet);
        assertEquals(original, reconstructed);
    }

    @Test
    public void testEmptyAlphabet() {
        assertThrows(IllegalArgumentException.class, () -> MoveToFront.transform("abc", ""));

        assertEquals("", MoveToFront.inverseTransform(List.of(1, 2), ""));
    }

    @Test
    public void testSymbolNotInAlphabet() {
        // 'd' is not in "abc"
        assertThrows(IllegalArgumentException.class, () -> MoveToFront.transform("abd", "abc"));
    }

    @Test
    public void testIndexOutOfBounds() {
        // Index 5 is out of bounds for alphabet "abc"
        // 1. test index >= alphabet.size()
        assertThrows(IllegalArgumentException.class, () -> MoveToFront.inverseTransform(List.of(1, 2, 5), "abc"));

        // 2. test index < 0
        assertThrows(IllegalArgumentException.class, () -> MoveToFront.inverseTransform(List.of(1, -1, 2), "abc"));
    }

    @Test
    public void testTransformNull() {
        List<Integer> expected = List.of();
        assertEquals(expected, MoveToFront.transform(null, "abc"));
        assertThrows(IllegalArgumentException.class, () -> MoveToFront.transform("abc", null));
    }

    @Test
    public void testInverseTransformNulls() {
        // 1. test indices == null
        assertEquals("", MoveToFront.inverseTransform(null, "abc"));

        // 2. test initialAlphabet == null
        assertEquals("", MoveToFront.inverseTransform(List.of(1, 2), null));
    }
}
