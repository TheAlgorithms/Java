package com.generation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

class SimplexNoiseTest {

    @Test
    void testGenerateHeightMap() {

        final int WIDTH = 256;
        final int HEIGHT = 256;
        final int X = 0;
        final int Y = 0;
        final String RESOURCE_NAME = "com/generation/expected-result.png";

        float[][] heightmap = new SimplexNoise(50, 0.3F, 1111111111111111L).generateHeightMap(X, Y, WIDTH, HEIGHT);
        BufferedImage image = null;

        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(RESOURCE_NAME)) {

            image = ImageIO.read(in);

            Assertions.assertEquals(WIDTH, image.getWidth());
            Assertions.assertEquals(HEIGHT, image.getHeight());

        } catch (IOException | IllegalArgumentException exception) {
            Assertions.fail(exception.toString());
        }

        for (int x = 0; x < WIDTH; x++) {

            for (int y = 0; y < HEIGHT; y++) {
                Assertions.assertEquals(new Color(image.getRGB(x, y)).getRed(), (int) (heightmap[x][y] * 255));
            }
        }
    }
}
