package com.generation;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SimplexNoiseTest {

    @Test
    public void testGenerateHeightMap() {

        final int WIDTH = 256;
        final int HEIGHT = 256;
        final int X = 0;
        final int Y = 0;

        float[][] heightmap = new SimplexNoise(50, 0.3F, 1111111111111111L).generateHeightMap(X, Y, WIDTH, HEIGHT);
        BufferedImage image = null;

        try (InputStream in = this.getClass().getResourceAsStream("expected-result.png")) {

            image = ImageIO.read(in);

            assertEquals(WIDTH, image.getWidth(), "width differs");
            assertEquals(HEIGHT, image.getHeight(), "height differs");

        } catch (IOException | IllegalArgumentException exception) {
            exception.printStackTrace();
            fail(exception.toString());
        }

        for (int x = 0; x < WIDTH; x++) {

            for (int y = 0; y < HEIGHT; y++) {

                assertEquals(new Color(image.getRGB(x, y)).getRed(), (int) (heightmap[x][y] * 255), "image data differs");
            }
        }
    }
}
