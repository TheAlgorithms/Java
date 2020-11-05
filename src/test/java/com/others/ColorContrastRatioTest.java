package com.others;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * You can check the examples against another open-source implementation available on GitHub.
 *
 * @see <a href="https://contrast-ratio.com/#rgb%28226%2C%20229%2C%20248-on-rgb%2823%2C%20103%2C%20154%29">Online Contrast Ratio</a>
 * @see <a href="https://github.com/LeaVerou/contrast-ratio">GitHub Repository for Online Contrast Ratio</a>
 */
public class ColorContrastRatioTest {

    private static ColorContrastRatio ccr;

    @BeforeAll
    public static void before() {
        ccr = new ColorContrastRatio();
    }

    @Test
    public void testRelativeLuminance() {
        final double expected = 0.12215748057375966;
        final double actual = ccr.getRelativeLuminance(new Color(23, 103, 154));

        assertEquals(expected, actual);
    }

    @Test
    public void testRelativeLuminance2() {
        final double expected = 0.7898468477881603;
        final double actual = ccr.getRelativeLuminance(new Color(226, 229, 248));

        assertEquals(expected, actual);
    }

    @Test
    public void testColorContrastRatio() {
        final double expected = 4.878363954846178;
        final double actual = ccr.getContrastRatio(new Color(23, 103, 154), new Color(226, 229, 248));

        assertEquals(expected, actual);
    }

    @Test
    public void testColorContrastRatioInverse() {
        final double expected = 4.878363954846178;
        final double actual = ccr.getContrastRatio(new Color(226, 229, 248), new Color(23, 103, 154));

        assertEquals(expected, actual);
    }
}
