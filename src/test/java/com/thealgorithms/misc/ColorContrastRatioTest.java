package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ColorContrastRatioTest {
    private final ColorContrastRatio colorContrastRationCalculator = new ColorContrastRatio();

    static Stream<Arguments> relativeLuminanceProvider() {
        return Stream.of(Arguments.of(Color.BLACK, 0.0), Arguments.of(Color.WHITE, 1.0), Arguments.of(new Color(23, 103, 154), 0.12215748057375966), Arguments.of(new Color(226, 229, 248), 0.7898468477881603));
    }

    static Stream<Arguments> contrastRatioProvider() {
        return Stream.of(Arguments.of(Color.BLACK, Color.WHITE, 21.0), Arguments.of(new Color(23, 103, 154), new Color(226, 229, 248), 4.878363954846178));
    }

    @ParameterizedTest
    @MethodSource("relativeLuminanceProvider")
    void testGetRelativeLuminance(Color color, double expectedLuminance) {
        assertEquals(expectedLuminance, colorContrastRationCalculator.getRelativeLuminance(color), 1e-10);
    }

    @ParameterizedTest
    @MethodSource("contrastRatioProvider")
    void testGetContrastRatio(Color a, Color b, double expectedRatio) {
        assertEquals(expectedRatio, colorContrastRationCalculator.getContrastRatio(a, b), 1e-10);
    }
}
