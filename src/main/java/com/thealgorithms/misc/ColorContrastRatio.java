package com.thealgorithms.misc;

import java.awt.Color;

/**
 * @brief A Java implementation of the offcial W3 documented procedure to
 * calculate contrast ratio between colors on the web. This is used to calculate
 * the readability of a foreground color on top of a background color.
 * @since 2020-10-15
 * @see [Color Contrast
 * Ratio](https://www.w3.org/TR/WCAG20-TECHS/G17.html#G17-procedure)
 * @author [Seth Falco](https://github.com/SethFalco)
 */
public class ColorContrastRatio {

    /**
     * @brief Calculates the contrast ratio between two given colors.
     * @param a Any color, used to get the red, green, and blue values.
     * @param b Another color, which will be compared against the first color.
     * @return The contrast ratio between the two colors.
     */
    public double getContrastRatio(Color a, Color b) {
        final double aColorLuminance = getRelativeLuminance(a);
        final double bColorLuminance = getRelativeLuminance(b);

        if (aColorLuminance > bColorLuminance) {
            return (aColorLuminance + 0.05) / (bColorLuminance + 0.05);
        }

        return (bColorLuminance + 0.05) / (aColorLuminance + 0.05);
    }

    /**
     * @brief Calculates the relative luminance of a given color.
     * @param color Any color, used to get the red, green, and blue values.
     * @return The relative luminance of the color.
     * @see [More info on relative
     * luminance.](https://www.w3.org/TR/2008/REC-WCAG20-20081211/#relativeluminancedef)
     */
    public double getRelativeLuminance(Color color) {
        final double red = getColor(color.getRed());
        final double green = getColor(color.getGreen());
        final double blue = getColor(color.getBlue());

        return 0.2126 * red + 0.7152 * green + 0.0722 * blue;
    }

    /**
     * @brief Calculates the final value for a color to be used in the relative
     * luminance formula as described in step 1.
     * @param color8Bit 8-bit representation of a color component value.
     * @return Value for the provided color component to be used in the relative
     * luminance formula.
     */
    public double getColor(int color8Bit) {
        final double sRgb = getColorSRgb(color8Bit);
        return (sRgb <= 0.03928) ? sRgb / 12.92 : Math.pow((sRgb + 0.055) / 1.055, 2.4);
    }

    /**
     * @brief Calculates the Color sRGB value as denoted in step 1 of the
     * procedure document.
     * @param color8Bit 8-bit representation of a color component value.
     * @return A percentile value of the color component.
     */
    private double getColorSRgb(double color8Bit) {
        return color8Bit / 255.0;
    }

    /**
     * You can check this example against another open-source implementation
     * available on GitHub.
     *
     * @see [Online Contrast
     * Ratio](https://contrast-ratio.com/#rgb%28226%2C%20229%2C%20248-on-rgb%2823%2C%20103%2C%20154%29)
     * @see [GitHub Repository for Online Contrast
     * Ratio](https://github.com/LeaVerou/contrast-ratio)
     */
    private static void test() {
        final ColorContrastRatio algImpl = new ColorContrastRatio();

        final Color black = Color.BLACK;
        final double blackLuminance = algImpl.getRelativeLuminance(black);
        assert blackLuminance == 0 : "Test 1 Failed - Incorrect relative luminance.";

        final Color white = Color.WHITE;
        final double whiteLuminance = algImpl.getRelativeLuminance(white);
        assert whiteLuminance == 1 : "Test 2 Failed - Incorrect relative luminance.";

        final double highestColorRatio = algImpl.getContrastRatio(black, white);
        assert highestColorRatio == 21 : "Test 3 Failed - Incorrect contrast ratio.";

        final Color foreground = new Color(23, 103, 154);
        final double foregroundLuminance = algImpl.getRelativeLuminance(foreground);
        assert foregroundLuminance == 0.12215748057375966 : "Test 4 Failed - Incorrect relative luminance.";

        final Color background = new Color(226, 229, 248);
        final double backgroundLuminance = algImpl.getRelativeLuminance(background);
        assert backgroundLuminance == 0.7898468477881603 : "Test 5 Failed - Incorrect relative luminance.";

        final double contrastRatio = algImpl.getContrastRatio(foreground, background);
        assert contrastRatio == 4.878363954846178 : "Test 6 Failed - Incorrect contrast ratio.";
    }

    public static void main(String args[]) {
        test();
    }
}
