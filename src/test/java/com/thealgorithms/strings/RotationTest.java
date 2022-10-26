package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RotationTest {

    @Test
    public void isLongestPalindromicSubstring() {
        assertEquals("eksge", Rotation.rotate("geeks", 2));
        assertEquals("anasban", Rotation.rotate("bananas", 3));
        assertEquals("abracadabra", Rotation.rotate("abracadabra", 0));
     }
}
