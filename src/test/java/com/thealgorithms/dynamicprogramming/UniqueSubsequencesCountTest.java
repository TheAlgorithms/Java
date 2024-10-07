package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UniqueSubsequencesCountTest {

    @Test
    void subseqCountTestOne() {
        String s = "abc";

        assertEquals(7, UniqueSubsequencesCount.countSubsequences(s));
    }

    @Test
    void subseqCountTestTwo() {
        String s = "abcdashgdhas";

        assertEquals(3592, UniqueSubsequencesCount.countSubsequences(s));
    }

    @Test
    void subseqCountTestThree() {
        String s = "a";

        assertEquals(1, UniqueSubsequencesCount.countSubsequences(s));
    }

    @Test
    void subseqCountTestFour() {
        String s = "a b";

        assertEquals(7, UniqueSubsequencesCount.countSubsequences(s));
    }

    @Test
    void subseqCountTestFive() {
        String s = "a1b2";

        assertEquals(15, UniqueSubsequencesCount.countSubsequences(s));
    }

    @Test
    void subseqCountTesSix() {
        String s = "AaBb";
        assertEquals(15, UniqueSubsequencesCount.countSubsequences(s));
    }

    @Test
    void subseqCountTesSeven() {
        String s = "abab";
        assertEquals(11, UniqueSubsequencesCount.countSubsequences(s));
    }
}
