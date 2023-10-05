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
        // Parse the hexadecimal values for red, green, and blue
        int red = Integer.parseInt(hex.substring(1, 3), 16);
        int green = Integer.parseInt(hex.substring(3, 5), 16);
        int blue = Integer.parseInt(hex.substring(5), 16);

        return new int[] {red, green, blue};
    }

    public static void main(String[] args) {
        String hexColor = "#FF5733"; // Hexadecimal color code
        int[] rgb = hexStringToRGB(hexColor);
        System.out.println("Red: " + rgb[0]);
        System.out.println("Green: " + rgb[1]);
        System.out.println("Blue: " + rgb[2]);
    }
}
