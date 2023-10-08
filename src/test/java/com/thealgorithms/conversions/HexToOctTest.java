package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HexToOctTest {

    @Test
    public void testHexToOct() {
        assertEquals(110, HexToOct.decimal2octal(HexToOct.hex2decimal("48")));
        assertEquals(255, HexToOct.decimal2octal(HexToOct.hex2decimal("AD")));
    }
}
