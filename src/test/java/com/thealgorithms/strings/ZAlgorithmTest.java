/*
 * https://en.wikipedia.org/wiki/Z-algorithm
 */
package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ZAlgorithmTest {

    @Test
    void testZFunction() {
        int[] z = ZAlgorithm.zFunction("aaaaa");
        assertArrayEquals(new int[] { 0, 4, 3, 2, 1 }, z);
    }

    @Test
    void testSearchFound() {
        assertEquals(2, ZAlgorithm.search("abcabca", "cab"));
    }

    @Test
    void testSearchNotFound() {
        assertEquals(-1, ZAlgorithm.search("abcdef", "gh"));
    }
}
