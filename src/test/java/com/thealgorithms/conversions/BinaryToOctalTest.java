package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BinaryToOctalTest {

    @Test
    public void testBinaryToOctal() {
        assertEquals("226", BinaryToOctal.convertBinaryToOctal(10010110));
        assertEquals("135", BinaryToOctal.convertBinaryToOctal(1011101));
    }
}
