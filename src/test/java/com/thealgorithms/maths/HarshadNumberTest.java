package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HarshadNumberTest {

    @Test
    public void harshadNumber() {
        
        assertTrue(HarshadNumber.isHarshad(18));
        assertFalse(HarshadNumber.isHarshad(-18));
        assertFalse(HarshadNumber.isHarshad(19));
        assertTrue(HarshadNumber.isHarshad(999999999));
        assertFalse(HarshadNumber.isHarshad(0));

        assertTrue(HarshadNumber.isHarshad("18"));
        assertFalse(HarshadNumber.isHarshad("-18"));
        assertFalse(HarshadNumber.isHarshad("19"));
        assertTrue(HarshadNumber.isHarshad("999999999"));
        assertTrue(HarshadNumber.isHarshad("99999999999100"));
    }
}
