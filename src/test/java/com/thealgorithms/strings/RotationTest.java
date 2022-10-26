package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RotationTest {

    @Test
    public void testRotation() {
        assertEquals("eksge", Rotation.rotation("geeks", 2));
        assertEquals("anasban", Rotation.rotation("bananas", 3));
        assertEquals("abracadabra", Rotation.rotation("abracadabra", 0));
     }
}
