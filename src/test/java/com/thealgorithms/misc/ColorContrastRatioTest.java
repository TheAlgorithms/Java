package com.thealgorithms.misc;

import com.thealgorithms.others.ArrayLeftRotation;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorContrastRatioTest {
    Color black = Color.BLACK;
    Color white = Color.WHITE;
    Color foreground = new Color(23, 103, 154);

    Color background = new Color(226, 229, 248);
    ColorContrastRatio colorContrastRatio = new ColorContrastRatio();

    // Test tinh do troi
    @Test
    void getContrastRatio(){
        double contrastRatio = colorContrastRatio.getContrastRatio(foreground, background);
        double highestColorRatio = colorContrastRatio.getContrastRatio(black, white);
        //   if aColorLuminance  bColorLuminance
        double highestColorRatioWhiteBlack = colorContrastRatio.getContrastRatio(white, black);


        assertEquals(contrastRatio,4.878363954846178);
        assertEquals(highestColorRatio,21);
        assertEquals(highestColorRatioWhiteBlack,21);
    }

    // Test do choi
    @Test
    void getRelativeLuminance(){
        double relativeLuminanceBlack = colorContrastRatio.getRelativeLuminance(black);
        double relativeLuminanceWhite = colorContrastRatio.getRelativeLuminance(white);


        assertEquals(relativeLuminanceBlack,0);
        assertEquals(relativeLuminanceWhite,1);
    }


    @Test
    void getColor(){
        double relativeLuminanceBlack = colorContrastRatio.getColor(0);
        double relativeLuminanceWhite = colorContrastRatio.getColor(255);


        assertEquals(relativeLuminanceBlack,0);
        assertEquals(relativeLuminanceWhite,1);
    }
}
