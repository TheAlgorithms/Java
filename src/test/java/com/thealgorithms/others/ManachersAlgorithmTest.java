package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ManachersAlgorithmTest {
    @Test
    void manacherAlgorithmFirstTestCase() {
        String s = " ";
        assertEquals(" ", ManachersAlgorithm.longestPalindromeSubstring(s));
    }

    @Test
    void manacherAlgorithmSecondTestCase() {
        String s = "asddf";
        assertEquals("dd", ManachersAlgorithm.longestPalindromeSubstring(s));
    }

    @Test
    void manacherAlgorithmThirdTestCase() {
        String s = "nitinmukesh";
        assertEquals("nitin", ManachersAlgorithm.longestPalindromeSubstring(s));
    }

    @Test
    void manacherAlgorithmFourthTestCase() {
        String s = "aaaaaaaaaaaaaaaaaaa";
        assertEquals("aaaaaaaaaaaaaaaaaaa", ManachersAlgorithm.longestPalindromeSubstring(s));
    }
    
    @Test
    void manacherAlgorithmFifthTestCase() {
        String s = "wdfghjklsracecarswekfjdsnitins";
        assertEquals("sracecars", ManachersAlgorithm.longestPalindromeSubstring(s));
    }
    
}
