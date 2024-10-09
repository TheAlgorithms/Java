package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoundaryFillTest {

    private int[][] image;

    @BeforeEach
    void setUp() {
        image = new int[][] {{0, 0, 0, 0, 0, 0, 0}, {0, 3, 3, 3, 3, 0, 0}, {0, 3, 0, 0, 3, 0, 0}, {0, 3, 0, 0, 3, 3, 3}, {0, 3, 3, 3, 0, 0, 3}, {0, 0, 0, 3, 0, 0, 3}, {0, 0, 0, 3, 3, 3, 3}};
    }

    @Test
    void testGetPixel() {
        assertEquals(3, BoundaryFill.getPixel(image, 1, 1));
        assertEquals(0, BoundaryFill.getPixel(image, 2, 2));
        assertEquals(3, BoundaryFill.getPixel(image, 4, 3));
    }

    @Test
    void testPutPixel() {
        BoundaryFill.putPixel(image, 2, 2, 5);
        assertEquals(5, BoundaryFill.getPixel(image, 2, 2));

        BoundaryFill.putPixel(image, 0, 0, 7);
        assertEquals(7, BoundaryFill.getPixel(image, 0, 0));
    }

    @Test
    void testBoundaryFill() {
        BoundaryFill.boundaryFill(image, 2, 2, 5, 3);

        int[][] expectedImage = {{0, 0, 0, 0, 0, 0, 0}, {0, 3, 3, 3, 3, 0, 0}, {0, 3, 5, 5, 3, 0, 0}, {0, 3, 5, 5, 3, 3, 3}, {0, 3, 3, 3, 5, 5, 3}, {0, 0, 0, 3, 5, 5, 3}, {0, 0, 0, 3, 3, 3, 3}};

        for (int i = 0; i < image.length; i++) {
            assertArrayEquals(expectedImage[i], image[i]);
        }
    }

    @Test
    void testBoundaryFillEdgeCase() {
        BoundaryFill.boundaryFill(image, 1, 1, 3, 3);

        int[][] expectedImage = {{0, 0, 0, 0, 0, 0, 0}, {0, 3, 3, 3, 3, 0, 0}, {0, 3, 0, 0, 3, 0, 0}, {0, 3, 0, 0, 3, 3, 3}, {0, 3, 3, 3, 0, 0, 3}, {0, 0, 0, 3, 0, 0, 3}, {0, 0, 0, 3, 3, 3, 3}};

        for (int i = 0; i < image.length; i++) {
            assertArrayEquals(expectedImage[i], image[i]);
        }
    }

    @Test
    void testBoundaryFillInvalidCoordinates() {
        BoundaryFill.boundaryFill(image, -1, -1, 5, 3);

        int[][] expectedImage = {{0, 0, 0, 0, 0, 0, 0}, {0, 3, 3, 3, 3, 0, 0}, {0, 3, 0, 0, 3, 0, 0}, {0, 3, 0, 0, 3, 3, 3}, {0, 3, 3, 3, 0, 0, 3}, {0, 0, 0, 3, 0, 0, 3}, {0, 0, 0, 3, 3, 3, 3}};

        for (int i = 0; i < image.length; i++) {
            assertArrayEquals(expectedImage[i], image[i]);
        }
    }
}
