package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UniqueSubsequencesCountTest {

    @Test
    void subseqCountTestOne() {
        String s = "abc";

        assertEquals(7, UniqueSubsequencesCount.subseqCount(s));
    }

    @Test
    void subseqCountTestTwo() {
        String s = "abcdashgdhas";

        assertEquals(3592, UniqueSubsequencesCount.subseqCount(s));
    }

    @Test
    void subseqCountTestThree() {
        String s = "aaaaa";

        assertEquals(5, UniqueSubsequencesCount.subseqCount(s));
    }
}
