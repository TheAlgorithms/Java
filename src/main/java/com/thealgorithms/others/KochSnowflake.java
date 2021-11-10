package com.thealgorithms.others;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * The Koch snowflake is a fractal curve and one of the earliest fractals to
 * have been described. The Koch snowflake can be built up iteratively, in a
 * sequence of stages. The first stage is an equilateral triangle, and each
 * successive stage is formed by adding outward bends to each side of the
 * previous stage, making smaller equilateral triangles. This can be achieved
 * through the following steps for each line: 1. divide the line segment into
 * three segments of equal length. 2. draw an equilateral triangle that has the
 * middle segment from step 1 as its base and points outward. 3. remove the line
 * segment that is the base of the triangle from step 2. (description adapted
 * from https://en.wikipedia.org/wiki/Koch_snowflake ) (for a more detailed
 * explanation and an implementation in the Processing language, see
 * https://natureofcode.com/book/chapter-8-fractals/
 * #84-the-koch-curve-and-the-arraylist-technique ).
 */
public class KochSnowflake {

    public static void main(String[] args) {
        // Test Iterate-method
        ArrayList<Vector2> vectors = new ArrayList<Vector2>();
        vectors.add(new Vector2(0, 0));
        vectors.add(new Vector2(1, 0));
        ArrayList<Vector2> result = Iterate(vectors, 1);

        assert result.get(0).x == 0;
        assert result.get(0).y == 0;

        assert result.get(1).x == 1. / 3;
        assert result.get(1).y == 0;

        assert result.get(2).x == 1. / 2;
        assert result.get(2).y == Math.sin(Math.PI / 3) / 3;

        assert result.get(3).x == 2. / 3;
        assert result.get(3).y == 0;

        assert result.get(4).x == 1;
        assert result.get(4).y == 0;

        // Test GetKochSnowflake-method
        int imageWidth = 600;
        double offsetX = imageWidth / 10.;
        double offsetY = imageWidth / 3.7;
        BufferedImage image = GetKochSnowflake(imageWidth, 5);

        // The background should be white
        assert image.getRGB(0, 0) == new Color(255, 255, 255).getRGB();

        // The snowflake is drawn in black and this is the position of the first vector
        assert image.getRGB((int) offsetX, (int) offsetY) == new Color(0, 0, 0).getRGB();

        // Save image
        try {
            ImageIO.write(image, "png", new File("KochSnowflake.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Go through the number of iterations determined by the argument "steps".
     * Be careful with high values (above 5) since the time to calculate
     * increases exponentially.
     *
     * @param initialVectors The vectors composing the shape to which the
     * algorithm is applied.
     * @param steps The number of iterations.
     * @return The transformed vectors after the iteration-steps.
     */
    public static ArrayList<Vector2> Iterate(ArrayList<Vector2> initialVectors, int steps) {
        ArrayList<Vector2> vectors = initialVectors;
        for (int i = 0; i < steps; i++) {
            vectors = IterationStep(vectors);
        }

        return vectors;
    }

    /**
     * Method to render the Koch snowflake to a image.
     *
     * @param imageWidth The width of the rendered image.
     * @param steps The number of iterations.
     * @return The image of the rendered Koch snowflake.
     */
    public static BufferedImage GetKochSnowflake(int imageWidth, int steps) {
        if (imageWidth <= 0) {
            throw new IllegalArgumentException("imageWidth should be greater than zero");
        }

        double offsetX = imageWidth / 10.;
        double offsetY = imageWidth / 3.7;
        Vector2 vector1 = new Vector2(offsetX, offsetY);
        Vector2 vector2
                = new Vector2(imageWidth / 2, Math.sin(Math.PI / 3) * imageWidth * 0.8 + offsetY);
        Vector2 vector3 = new Vector2(imageWidth - offsetX, offsetY);
        ArrayList<Vector2> initialVectors = new ArrayList<Vector2>();
        initialVectors.add(vector1);
        initialVectors.add(vector2);
        initialVectors.add(vector3);
        initialVectors.add(vector1);
        ArrayList<Vector2> vectors = Iterate(initialVectors, steps);
        return GetImage(vectors, imageWidth, imageWidth);
    }

    /**
     * Loops through each pair of adjacent vectors. Each line between two
     * adjacent vectors is divided into 4 segments by adding 3 additional
     * vectors in-between the original two vectors. The vector in the middle is
     * constructed through a 60 degree rotation so it is bent outwards.
     *
     * @param vectors The vectors composing the shape to which the algorithm is
     * applied.
     * @return The transformed vectors after the iteration-step.
     */
    private static ArrayList<Vector2> IterationStep(ArrayList<Vector2> vectors) {
        ArrayList<Vector2> newVectors = new ArrayList<Vector2>();
        for (int i = 0; i < vectors.size() - 1; i++) {
            Vector2 startVector = vectors.get(i);
            Vector2 endVector = vectors.get(i + 1);
            newVectors.add(startVector);
            Vector2 differenceVector = endVector.subtract(startVector).multiply(1. / 3);
            newVectors.add(startVector.add(differenceVector));
            newVectors.add(startVector.add(differenceVector).add(differenceVector.rotate(60)));
            newVectors.add(startVector.add(differenceVector.multiply(2)));
        }

        newVectors.add(vectors.get(vectors.size() - 1));
        return newVectors;
    }

    /**
     * Utility-method to render the Koch snowflake to an image.
     *
     * @param vectors The vectors defining the edges to be rendered.
     * @param imageWidth The width of the rendered image.
     * @param imageHeight The height of the rendered image.
     * @return The image of the rendered edges.
     */
    private static BufferedImage GetImage(
            ArrayList<Vector2> vectors, int imageWidth, int imageHeight) {
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Set the background white
        g2d.setBackground(Color.WHITE);
        g2d.fillRect(0, 0, imageWidth, imageHeight);

        // Draw the edges
        g2d.setColor(Color.BLACK);
        BasicStroke bs = new BasicStroke(1);
        g2d.setStroke(bs);
        for (int i = 0; i < vectors.size() - 1; i++) {
            int x1 = (int) vectors.get(i).x;
            int y1 = (int) vectors.get(i).y;
            int x2 = (int) vectors.get(i + 1).x;
            int y2 = (int) vectors.get(i + 1).y;

            g2d.drawLine(x1, y1, x2, y2);
        }

        return image;
    }

    /**
     * Inner class to handle the vector calculations.
     */
    private static class Vector2 {

        double x, y;

        public Vector2(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("[%f, %f]", this.x, this.y);
        }

        /**
         * Vector addition
         *
         * @param vector The vector to be added.
         * @return The sum-vector.
         */
        public Vector2 add(Vector2 vector) {
            double x = this.x + vector.x;
            double y = this.y + vector.y;
            return new Vector2(x, y);
        }

        /**
         * Vector subtraction
         *
         * @param vector The vector to be subtracted.
         * @return The difference-vector.
         */
        public Vector2 subtract(Vector2 vector) {
            double x = this.x - vector.x;
            double y = this.y - vector.y;
            return new Vector2(x, y);
        }

        /**
         * Vector scalar multiplication
         *
         * @param scalar The factor by which to multiply the vector.
         * @return The scaled vector.
         */
        public Vector2 multiply(double scalar) {
            double x = this.x * scalar;
            double y = this.y * scalar;
            return new Vector2(x, y);
        }

        /**
         * Vector rotation (see https://en.wikipedia.org/wiki/Rotation_matrix)
         *
         * @param angleInDegrees The angle by which to rotate the vector.
         * @return The rotated vector.
         */
        public Vector2 rotate(double angleInDegrees) {
            double radians = angleInDegrees * Math.PI / 180;
            double ca = Math.cos(radians);
            double sa = Math.sin(radians);
            double x = ca * this.x - sa * this.y;
            double y = sa * this.x + ca * this.y;
            return new Vector2(x, y);
        }
    }
}
