package com.thealgorithms.conversions;

import java.util.Arrays;

/**
 * The RGB color model is an additive color model in which red, green, and blue
 * light are added together in various ways to reproduce a broad array of
 * colors. The name of the model comes from the initials of the three additive
 * primary colors, red, green, and blue. Meanwhile, the HSV representation
 * models how colors appear under light. In it, colors are represented using
 * three components: hue, saturation and (brightness-)value. This class provides
 * methods for converting colors from one representation to the other.
 * (description adapted from https://en.wikipedia.org/wiki/RGB_color_model and
 * https://en.wikipedia.org/wiki/HSL_and_HSV).
 */
public class RgbHsvConversion {

    public static void main(String[] args) {
        // Expected RGB-values taken from https://www.rapidtables.com/convert/color/hsv-to-rgb.html

        // Test hsvToRgb-method
        assert Arrays.equals(hsvToRgb(0, 0, 0), new int[] { 0, 0, 0 });
        assert Arrays.equals(hsvToRgb(0, 0, 1), new int[] { 255, 255, 255 });
        assert Arrays.equals(hsvToRgb(0, 1, 1), new int[] { 255, 0, 0 });
        assert Arrays.equals(hsvToRgb(60, 1, 1), new int[] { 255, 255, 0 });
        assert Arrays.equals(hsvToRgb(120, 1, 1), new int[] { 0, 255, 0 });
        assert Arrays.equals(hsvToRgb(240, 1, 1), new int[] { 0, 0, 255 });
        assert Arrays.equals(hsvToRgb(300, 1, 1), new int[] { 255, 0, 255 });
        assert Arrays.equals(
            hsvToRgb(180, 0.5, 0.5),
            new int[] { 64, 128, 128 }
        );
        assert Arrays.equals(
            hsvToRgb(234, 0.14, 0.88),
            new int[] { 193, 196, 224 }
        );
        assert Arrays.equals(
            hsvToRgb(330, 0.75, 0.5),
            new int[] { 128, 32, 80 }
        );

        // Test rgbToHsv-method
        // approximate-assertions needed because of small deviations due to converting between
        // int-values and double-values.
        assert approximatelyEqualHsv(
            rgbToHsv(0, 0, 0),
            new double[] { 0, 0, 0 }
        );
        assert approximatelyEqualHsv(
            rgbToHsv(255, 255, 255),
            new double[] { 0, 0, 1 }
        );
        assert approximatelyEqualHsv(
            rgbToHsv(255, 0, 0),
            new double[] { 0, 1, 1 }
        );
        assert approximatelyEqualHsv(
            rgbToHsv(255, 255, 0),
            new double[] { 60, 1, 1 }
        );
        assert approximatelyEqualHsv(
            rgbToHsv(0, 255, 0),
            new double[] { 120, 1, 1 }
        );
        assert approximatelyEqualHsv(
            rgbToHsv(0, 0, 255),
            new double[] { 240, 1, 1 }
        );
        assert approximatelyEqualHsv(
            rgbToHsv(255, 0, 255),
            new double[] { 300, 1, 1 }
        );
        assert approximatelyEqualHsv(
            rgbToHsv(64, 128, 128),
            new double[] { 180, 0.5, 0.5 }
        );
        assert approximatelyEqualHsv(
            rgbToHsv(193, 196, 224),
            new double[] { 234, 0.14, 0.88 }
        );
        assert approximatelyEqualHsv(
            rgbToHsv(128, 32, 80),
            new double[] { 330, 0.75, 0.5 }
        );
    }

    /**
     * Conversion from the HSV-representation to the RGB-representation.
     *
     * @param hue Hue of the color.
     * @param saturation Saturation of the color.
     * @param value Brightness-value of the color.
     * @return The tuple of RGB-components.
     */
    public static int[] hsvToRgb(double hue, double saturation, double value) {
        if (hue < 0 || hue > 360) {
            throw new IllegalArgumentException(
                "hue should be between 0 and 360"
            );
        }

        if (saturation < 0 || saturation > 1) {
            throw new IllegalArgumentException(
                "saturation should be between 0 and 1"
            );
        }

        if (value < 0 || value > 1) {
            throw new IllegalArgumentException(
                "value should be between 0 and 1"
            );
        }

        double chroma = value * saturation;
        double hueSection = hue / 60;
        double secondLargestComponent =
            chroma * (1 - Math.abs(hueSection % 2 - 1));
        double matchValue = value - chroma;

        return getRgbBySection(
            hueSection,
            chroma,
            matchValue,
            secondLargestComponent
        );
    }

    /**
     * Conversion from the RGB-representation to the HSV-representation.
     *
     * @param red Red-component of the color.
     * @param green Green-component of the color.
     * @param blue Blue-component of the color.
     * @return The tuple of HSV-components.
     */
    public static double[] rgbToHsv(int red, int green, int blue) {
        if (red < 0 || red > 255) {
            throw new IllegalArgumentException(
                "red should be between 0 and 255"
            );
        }

        if (green < 0 || green > 255) {
            throw new IllegalArgumentException(
                "green should be between 0 and 255"
            );
        }

        if (blue < 0 || blue > 255) {
            throw new IllegalArgumentException(
                "blue should be between 0 and 255"
            );
        }

        double dRed = (double) red / 255;
        double dGreen = (double) green / 255;
        double dBlue = (double) blue / 255;
        double value = Math.max(Math.max(dRed, dGreen), dBlue);
        double chroma = value - Math.min(Math.min(dRed, dGreen), dBlue);
        double saturation = value == 0 ? 0 : chroma / value;
        double hue;

        if (chroma == 0) {
            hue = 0;
        } else if (value == dRed) {
            hue = 60 * (0 + (dGreen - dBlue) / chroma);
        } else if (value == dGreen) {
            hue = 60 * (2 + (dBlue - dRed) / chroma);
        } else {
            hue = 60 * (4 + (dRed - dGreen) / chroma);
        }

        hue = (hue + 360) % 360;

        return new double[] { hue, saturation, value };
    }

    private static boolean approximatelyEqualHsv(double[] hsv1, double[] hsv2) {
        boolean bHue = Math.abs(hsv1[0] - hsv2[0]) < 0.2;
        boolean bSaturation = Math.abs(hsv1[1] - hsv2[1]) < 0.002;
        boolean bValue = Math.abs(hsv1[2] - hsv2[2]) < 0.002;

        return bHue && bSaturation && bValue;
    }

    private static int[] getRgbBySection(
        double hueSection,
        double chroma,
        double matchValue,
        double secondLargestComponent
    ) {
        int red;
        int green;
        int blue;

        if (hueSection >= 0 && hueSection <= 1) {
            red = convertToInt(chroma + matchValue);
            green = convertToInt(secondLargestComponent + matchValue);
            blue = convertToInt(matchValue);
        } else if (hueSection > 1 && hueSection <= 2) {
            red = convertToInt(secondLargestComponent + matchValue);
            green = convertToInt(chroma + matchValue);
            blue = convertToInt(matchValue);
        } else if (hueSection > 2 && hueSection <= 3) {
            red = convertToInt(matchValue);
            green = convertToInt(chroma + matchValue);
            blue = convertToInt(secondLargestComponent + matchValue);
        } else if (hueSection > 3 && hueSection <= 4) {
            red = convertToInt(matchValue);
            green = convertToInt(secondLargestComponent + matchValue);
            blue = convertToInt(chroma + matchValue);
        } else if (hueSection > 4 && hueSection <= 5) {
            red = convertToInt(secondLargestComponent + matchValue);
            green = convertToInt(matchValue);
            blue = convertToInt(chroma + matchValue);
        } else {
            red = convertToInt(chroma + matchValue);
            green = convertToInt(matchValue);
            blue = convertToInt(secondLargestComponent + matchValue);
        }

        return new int[] { red, green, blue };
    }

    private static int convertToInt(double input) {
        return (int) Math.round(255 * input);
    }
}
