package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class KasaiAlgorithmTest {

    @Test
    public void testKasaiBanana() {
        String text = "banana";
        // Suffixes:
        // 0: banana
        // 1: anana
        // 2: nana
        // 3: ana
        // 4: na
        // 5: a
        //
        // Sorted Suffixes:
        // 5: a
        // 3: ana
        // 1: anana
        // 0: banana
        // 4: na
        // 2: nana
        int[] suffixArr = {5, 3, 1, 0, 4, 2};

        int[] expectedLcp = {1, 3, 0, 0, 2, 0};

        assertArrayEquals(expectedLcp, KasaiAlgorithm.kasai(text, suffixArr));
    }

    @Test
    public void testKasaiAaaa() {
        String text = "aaaa";
        // Sorted Suffixes:
        // 3: a
        // 2: aa
        // 1: aaa
        // 0: aaaa
        int[] suffixArr = {3, 2, 1, 0};
        int[] expectedLcp = {1, 2, 3, 0};

        assertArrayEquals(expectedLcp, KasaiAlgorithm.kasai(text, suffixArr));
    }

    @Test
    public void testKasaiEmptyString() {
        assertArrayEquals(new int[0], KasaiAlgorithm.kasai("", new int[0]));
    }

    @Test
    public void testKasaiSingleChar() {
        assertArrayEquals(new int[] {0}, KasaiAlgorithm.kasai("A", new int[] {0}));
    }

    @Test
    public void testKasaiNullTextOrSuffixArray() {
        assertThrows(IllegalArgumentException.class, () -> KasaiAlgorithm.kasai(null, new int[] {0}));
        assertThrows(IllegalArgumentException.class, () -> KasaiAlgorithm.kasai("A", null));
    }

    @Test
    public void testKasaiInvalidSuffixArrayLength() {
        assertThrows(IllegalArgumentException.class, () -> KasaiAlgorithm.kasai("A", new int[] {0, 1}));
    }

    @Test
    public void testKasaiInvalidSuffixArrayIndex() {
        assertThrows(IllegalArgumentException.class, () -> KasaiAlgorithm.kasai("A", new int[] {1})); // Out of bounds
        assertThrows(IllegalArgumentException.class, () -> KasaiAlgorithm.kasai("A", new int[] {-1})); // Out of bounds
    }
}
