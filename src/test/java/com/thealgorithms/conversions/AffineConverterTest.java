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
    void testConstructorWithValidValues() {
        assertEquals(3.0, converter.convert(0.0), "Expected value when input is 0.0");
        assertEquals(5.0, converter.convert(1.0), "Expected value when input is 1.0");
    }

    @Test
    void testConstructorWithInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> new AffineConverter(Double.NaN, 3.0), "Constructor should throw IllegalArgumentException for NaN slope");
    }

    @Test
    void testConvertWithNegativeValues() {
        assertEquals(-1.0, converter.convert(-2.0), "Negative input should convert correctly");
        assertEquals(-3.0, new AffineConverter(-1.0, -1.0).convert(2.0), "Slope and intercept can be negative");
    }

    @Test
    void testConvertWithFloatingPointPrecision() {
        double result = new AffineConverter(1.3333, 0.6667).convert(3.0);
        assertEquals(4.6666, result, 1e-4, "Conversion should maintain floating-point precision");
    }

    @Test
    void testInvert() {
        AffineConverter inverted = converter.invert();
        assertEquals(0.0, inverted.convert(3.0), "Inverted should return 0.0 for input 3.0");
        assertEquals(1.0, inverted.convert(5.0), "Inverted should return 1.0 for input 5.0");
    }

    @Test
    void testInvertWithZeroSlope() {
        AffineConverter zeroSlopeConverter = new AffineConverter(0.0, 3.0);
        assertThrows(AssertionError.class, zeroSlopeConverter::invert, "Invert should throw AssertionError when slope is zero");
    }

    @Test
    void testCompose() {
        AffineConverter otherConverter = new AffineConverter(1.0, 2.0);
        AffineConverter composed = converter.compose(otherConverter);

        assertEquals(7.0, composed.convert(0.0), "Expected composed conversion at 0.0");
        assertEquals(9.0, composed.convert(1.0), "Expected composed conversion at 1.0");
    }

    @Test
    void testMultipleCompositions() {
        AffineConverter c1 = new AffineConverter(2.0, 1.0);
        AffineConverter c2 = new AffineConverter(3.0, -2.0);
        AffineConverter c3 = c1.compose(c2); // (2x + 1) ∘ (3x - 2) => 6x - 1

        assertEquals(-3.0, c3.convert(0.0), "Composed transformation should return -3.0 at 0.0");
        assertEquals(3.0, c3.convert(1.0), "Composed transformation should return 3.0 at 1.0");
    }

    @Test
    void testIdentityComposition() {
        AffineConverter identity = new AffineConverter(1.0, 0.0);
        AffineConverter composed = converter.compose(identity);

        assertEquals(3.0, composed.convert(0.0), "Identity composition should not change the transformation");
        assertEquals(7.0, composed.convert(2.0), "Identity composition should behave like the original");
    }

    @Test
    void testLargeInputs() {
        double largeValue = 1e6;
        assertEquals(2.0 * largeValue + 3.0, converter.convert(largeValue), "Should handle large input values without overflow");
    }
}
