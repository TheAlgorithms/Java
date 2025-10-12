package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringRotationTest {

    @Test
    void testValidRotation() {
        assertTrue(StringRotation.isRotation("waterbottle", "erbottlewat"));
    }

    @Test
    void testInvalidRotation() {
        assertFalse(StringRotation.isRotation("hello", "world"));
    }

    @Test
    void testDifferentLengths() {
        assertFalse(StringRotation.isRotation("abc", "abcd"));
    }

    @Test
    void testNullInput() {
        assertFalse(StringRotation.isRotation(null, "abc"));
        assertFalse(StringRotation.isRotation("abc", null));
    }
}
