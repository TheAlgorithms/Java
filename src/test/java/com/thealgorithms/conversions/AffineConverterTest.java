package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AffineConverterTest {

    private AffineConverter converter;

    @BeforeEach
    void setUp() {
        converter = new AffineConverter(2.0, 3.0);
    }

    @Test
    void testConstructor() {
        assertEquals(3.0, converter.convert(0.0), "Expected value when input is 0.0");
        assertEquals(5.0, converter.convert(1.0), "Expected value when input is 1.0");
        assertEquals(7.0, converter.convert(2.0), "Expected value when input is 2.0");
    }

    @Test
    void testConvert() {
        assertEquals(3.0, converter.convert(0.0), "Conversion at 0.0 should equal the intercept");
        assertEquals(7.0, converter.convert(2.0), "2.0 should convert to 7.0");
        assertEquals(11.0, converter.convert(4.0), "4.0 should convert to 11.0");
    }

    @Test
    void testInvert() {
        AffineConverter inverted = converter.invert();
        assertEquals(0.0, inverted.convert(3.0), "Inverted converter should return 0.0 for input 3.0");
        assertEquals(1.0, inverted.convert(5.0), "Inverted converter should return 1.0 for input 5.0");
        assertEquals(2.0, inverted.convert(7.0), "Inverted converter should return 2.0 for input 7.0");
    }

    @Test
    void testInvertWithZeroSlope() {
        AffineConverter zeroSlopeConverter = new AffineConverter(0.0, 3.0);
        assertThrows(AssertionError.class, zeroSlopeConverter::invert, "Invert should throw assertion error when slope is zero");
    }

    @Test
    void testCompose() {
        AffineConverter otherConverter = new AffineConverter(1.0, 2.0);
        AffineConverter composed = converter.compose(otherConverter);

        assertEquals(7.0, composed.convert(0.0), "Expected composed conversion at 0.0");
        assertEquals(9.0, composed.convert(1.0), "Expected composed conversion at 1.0");
        assertEquals(11.0, composed.convert(2.0), "Expected composed conversion at 2.0");
    }
}
