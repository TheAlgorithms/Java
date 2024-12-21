package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EditDistanceTest {

    @ParameterizedTest
    @CsvSource({"'', '', 0", "'abc', '', 3", "'', 'abcd', 4", "'same', 'same', 0", "'a', 'b', 1", "'abc', 'abd', 1"})
    void testMinDistance(String str1, String str2, int expected) {
        assertEquals(expected, EditDistance.minDistance(str1, str2));
    }

    @Test
    public void testEditDistanceBothEmptyStrings() {
        assertEquals(0, EditDistance.editDistance("", ""));
    }

    @Test
    public void testEditDistanceOneEmptyString() {
        assertEquals(5, EditDistance.editDistance("", "hello"));
        assertEquals(7, EditDistance.editDistance("worldly", ""));
    }

    @Test
    public void testEditDistanceOneEmptyStringMemoization() {
        int[][] storage = new int[1][6];
        assertAll("String assertions",
            ()
                -> assertEquals(5, EditDistance.editDistance("", "hello", storage)),
            () -> assertEquals(0, storage[0][0]), () -> assertEquals(0, storage[0][1]), () -> assertEquals(0, storage[0][2]), () -> assertEquals(0, storage[0][3]), () -> assertEquals(0, storage[0][4]), () -> assertEquals(5, storage[0][5]));
    }

    @Test
    public void testEditDistanceEqualStrings() {
        assertEquals(0, EditDistance.editDistance("test", "test"));
        assertEquals(0, EditDistance.editDistance("abc", "abc"));
    }

    @Test
    public void testEditDistanceEqualStringsMemoization() {
        int[][] storage = new int[4][4];
        assertAll("String assertions",
            ()
                -> assertEquals(0, EditDistance.editDistance("abc", "abc", storage)),
            ()
                -> assertEquals(0, storage[0][0]),
            ()
                -> assertEquals(0, storage[0][1]),
            ()
                -> assertEquals(0, storage[0][2]),
            ()
                -> assertEquals(0, storage[0][3]),
            ()
                -> assertEquals(0, storage[1][0]),
            ()
                -> assertEquals(0, storage[1][1]),
            ()
                -> assertEquals(0, storage[1][2]),
            ()
                -> assertEquals(0, storage[1][3]),
            ()
                -> assertEquals(0, storage[2][0]),
            () -> assertEquals(0, storage[2][1]), () -> assertEquals(0, storage[2][2]), () -> assertEquals(0, storage[2][3]), () -> assertEquals(0, storage[3][0]), () -> assertEquals(0, storage[3][1]), () -> assertEquals(0, storage[3][2]), () -> assertEquals(0, storage[3][3]));
    }

    @Test
    public void testEditDistanceOneCharacterDifference() {
        assertEquals(1, EditDistance.editDistance("cat", "bat"));
        assertEquals(1, EditDistance.editDistance("cat", "cats"));
        assertEquals(1, EditDistance.editDistance("cats", "cat"));
    }

    @Test
    public void testEditDistanceOneCharacterDifferenceMemoization() {
        int[][] storage = new int[3][3];
        assertAll("All assertions",
            ()
                -> assertEquals(1, EditDistance.editDistance("at", "it", storage)),
            ()
                -> assertEquals(0, storage[0][0]),
            ()
                -> assertEquals(1, storage[0][1]),
            () -> assertEquals(2, storage[0][2]), () -> assertEquals(1, storage[1][0]), () -> assertEquals(0, storage[1][1]), () -> assertEquals(1, storage[1][2]), () -> assertEquals(2, storage[2][0]), () -> assertEquals(1, storage[2][1]), () -> assertEquals(1, storage[2][2]));
    }

    @Test
    public void testEditDistanceGeneralCases() {
        assertEquals(3, EditDistance.editDistance("kitten", "sitting"));
        assertEquals(2, EditDistance.editDistance("flaw", "lawn"));
        assertEquals(5, EditDistance.editDistance("intention", "execution"));
    }

    @Test
    public void testEditDistanceGeneralCasesMemoization() {
        int[][] storage = new int[7][8];
        assertEquals(3, EditDistance.editDistance("kitten", "sitting", storage));
        assertAll("All assertions", () -> assertEquals(0, storage[0][0]), () -> assertEquals(3, storage[6][7]));
    }
}
