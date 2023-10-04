package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ManachersAlgorithmTest {
    @Test
    public void manacherAlgorithmFirstTestCase() {
        String s = " ";
        assertEquals(" ", ManachersAlgorithm.longestPalindromeSubstring(s));
    }

    @Test
    public void manacherAlgorithmSecondTestCase() {
        String s = "asddf";
        assertEquals("dd", ManachersAlgorithm.longestPalindromeSubstring(s));
    }

    // @Test
    // public void manacherAlgorithmThirdTestCase() {
    //     String s = "nitinmukesh";
    //     assertEquals("nitin", ManachersAlgorithm.longestPalindromeSubstring(s));
    // }

    @Test
    public void manacherAlgorithmFourthTestCase() {
        String s = "aaaaaaaaaaaaaaaaaaa";
        assertEquals("aaaaaaaaaaaaaaaaaaa", ManachersAlgorithm.longestPalindromeSubstring(s));
    }
    
    @Test
    public void manacherAlgorithmFifthTestCase() {
        String s = "wdfghjklsracecarswekfjdsnitins";
        assertEquals("sracecars", ManachersAlgorithm.longestPalindromeSubstring(s));
    }
}
