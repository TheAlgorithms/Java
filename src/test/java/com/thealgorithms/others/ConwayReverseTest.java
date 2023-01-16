package com.thealgorithms.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConwayReverseTest {
    @Test
    public void testGenerateWith11(){
        assertEquals("444444444444", ConwayReverse.generateList("1234").get(2));
    }

    @Test
    public void testGenerateWith123456(){
        assertEquals("444444444444666666666666666666666666666666666666888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888", ConwayReverse.generateList("12345678").get(2));
    }

    @Test
    public void testGenerateWithErrors(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> ConwayReverse.generateList("1231231231233"));
    }

    @Test
    public void testGenerateLastElementWth11(){
        assertEquals("1", ConwayReverse.generatelastElement("11"));
    }

    @Test
    public void testGeneratelastElementWithErrors(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> ConwayReverse.generatelastElement("1326548"));    }

    @Test
    public void testGenerateNextElementWith1234567890(){
        assertEquals("2444666668888888000000000", ConwayReverse.generatelastElement("1234567890"));
    }

    @Test
    public void testGenerateNextElementWith1V3456789X(){
        assertEquals("V444666668888888XXXXXXXXX", ConwayReverse.generatelastElement("1V3456789X"));
    }

}
