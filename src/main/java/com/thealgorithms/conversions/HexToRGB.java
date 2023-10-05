package com.thealgorithms.conversions;

/**
 * Converts any Hexadecimal To RGB
 *
 * @author Thiago Bomfim
 * @see <a href="https://en.wikipedia.org/wiki/RGB_color_model">RGB color model</a>
 */
public class HexToRGB {

    /**
     * This method converts a Hexadecimal number to an RGB number
     * <p>
     * Example: #FFFFFF - [255,255,255]
     *
     * @param hex The Hexadecimal
     * @return The RGB number
     */
    public static int[] hexStringToRGB(String hex) {
        var red = hex.substring(1, 3);
        var green = hex.substring(3, 5);
        var blue = hex.substring(5);
        return new int[]{
                Integer.parseInt(red, 16),
                Integer.parseInt(green, 16),
                Integer.parseInt(blue, 16)
        };
    }

    public static void main(String[] args) {
        String hexColor = "#FF5733"; // Hexadecimal color code
        int[] rgb = hexStringToRGB(hexColor);
        System.out.println("Red: " + rgb[0]);
        System.out.println("Green: " + rgb[1]);
        System.out.println("Blue: " + rgb[2]);
    }
}
