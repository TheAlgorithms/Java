package com.thealgorithms.others;

import java.util.Random;
import java.util.Scanner;

/**
 * Utility for generating 2D value-noise blended across octaves (commonly known
 * as Perlin-like noise).
 *
 * <p>
 * The implementation follows the classic approach of:
 * <ol>
 * <li>Generate a base grid of random values in [0, 1).</li>
 * <li>For each octave k, compute a layer by bilinear interpolation of the base
 * grid
 * at period 2^k.</li>
 * <li>Blend all layers from coarse to fine using a geometric series of
 * amplitudes
 * controlled by {@code persistence}, then normalize to [0, 1].</li>
 * </ol>
 *
 * <p>
 * For background see:
 * <a href="http://devmag.org.za/2009/04/25/perlin-noise/">Perlin Noise</a>.
 *
 * <p>
 * Constraints and notes:
 * <ul>
 * <li>{@code width} and {@code height} should be positive.</li>
 * <li>{@code octaveCount} must be at least 1 (0 would lead to a division by
 * zero).</li>
 * <li>{@code persistence} should be in (0, 1], typical values around
 * 0.5–0.8.</li>
 * <li>Given the same seed and parameters, results are deterministic.</li>
 * </ul>
 */

public final class PerlinNoise {
    private PerlinNoise() {
    }

    /**
     * Generate a 2D array of blended noise values normalized to [0, 1].
     *
     * @param width       width of the noise array (columns)
     * @param height      height of the noise array (rows)
     * @param octaveCount number of octaves (layers) to blend; must be >= 1
     * @param persistence per-octave amplitude multiplier in (0, 1]
     * @param seed        seed for the random base grid
     * @return a {@code width x height} array containing blended noise values in [0,
     *         1]
     */
    static float[][] generatePerlinNoise(int width, int height, int octaveCount, float persistence, long seed) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("width and height must be > 0");
        }

        if (octaveCount < 1) {
            throw new IllegalArgumentException("octaveCount must be >= 1");
        }
        if (!(persistence > 0f && persistence <= 1f)) { // using > to exclude 0 and NaN
            throw new IllegalArgumentException("persistence must be in (0, 1]");
        }
        final float[][] base = createBaseGrid(width, height, seed);
        final float[][][] layers = createLayers(base, width, height, octaveCount);
        return blendAndNormalize(layers, width, height, persistence);
    }

    /** Create the base random lattice values in [0,1). */
    static float[][] createBaseGrid(int width, int height, long seed) {
        final float[][] base = new float[width][height];
        Random random = new Random(seed);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                base[x][y] = random.nextFloat();
            }
        }
        return base;
    }

    /** Pre-compute each octave layer at increasing frequency. */
    static float[][][] createLayers(float[][] base, int width, int height, int octaveCount) {
        final float[][][] noiseLayers = new float[octaveCount][][];
        for (int octave = 0; octave < octaveCount; octave++) {
            noiseLayers[octave] = generatePerlinNoiseLayer(base, width, height, octave);
        }
        return noiseLayers;
    }

    /** Blend layers using geometric amplitudes and normalize to [0,1]. */
    static float[][] blendAndNormalize(float[][][] layers, int width, int height, float persistence) {
        final int octaveCount = layers.length;
        final float[][] out = new float[width][height];
        float amplitude = 1f;
        float totalAmplitude = 0f;

        for (int octave = octaveCount - 1; octave >= 0; octave--) {
            amplitude *= persistence;
            totalAmplitude += amplitude;
            final float[][] layer = layers[octave];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    out[x][y] += layer[x][y] * amplitude;
                }
            }
        }

        if (totalAmplitude <= 0f || Float.isInfinite(totalAmplitude) || Float.isNaN(totalAmplitude)) {
            throw new IllegalStateException("Invalid totalAmplitude computed during normalization");
        }

        final float invTotal = 1f / totalAmplitude;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out[x][y] *= invTotal;
            }
        }
        return out;
    }

    /**
     * Generate a single octave layer by bilinear interpolation of a base grid at a
     * given octave (period = 2^octave).
     *
     * @param base   base random float array of size {@code width x height}
     * @param width  width of noise array
     * @param height height of noise array
     * @param octave current octave (0 for period 1, 1 for period 2, ...)
     * @return float array containing the octave's interpolated values
     */
    static float[][] generatePerlinNoiseLayer(float[][] base, int width, int height, int octave) {
        float[][] perlinNoiseLayer = new float[width][height];

        // Calculate period (wavelength) for different shapes.
        int period = 1 << octave; // 2^k
        float frequency = 1f / period; // 1/2^k

        for (int x = 0; x < width; x++) {
            // Calculate the horizontal sampling indices.
            int x0 = (x / period) * period;
            int x1 = (x0 + period) % width;
            float horizontalBlend = (x - x0) * frequency;

            for (int y = 0; y < height; y++) {
                // Calculate the vertical sampling indices.
                int y0 = (y / period) * period;
                int y1 = (y0 + period) % height;
                float verticalBlend = (y - y0) * frequency;

                // Blend top corners.
                float top = interpolate(base[x0][y0], base[x1][y0], horizontalBlend);

                // Blend bottom corners.
                float bottom = interpolate(base[x0][y1], base[x1][y1], horizontalBlend);

                // Blend top and bottom interpolation to get the final value for this cell.
                perlinNoiseLayer[x][y] = interpolate(top, bottom, verticalBlend);
            }
        }

        return perlinNoiseLayer;
    }

    /**
     * Linear interpolation between two values.
     *
     * @param a     value at alpha = 0
     * @param b     value at alpha = 1
     * @param alpha interpolation factor in [0, 1]
     * @return interpolated value {@code (1 - alpha) * a + alpha * b}
     */
    static float interpolate(float a, float b, float alpha) {
        return a * (1 - alpha) + alpha * b;
    }

    /**
     * Small demo that prints a text representation of the noise using a provided
     * character set.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        final int width;
        final int height;
        final int octaveCount;
        final float persistence;
        final long seed;
        final String charset;
        final float[][] perlinNoise;

        System.out.println("Width (int): ");
        width = in.nextInt();

        System.out.println("Height (int): ");
        height = in.nextInt();

        System.out.println("Octave count (int): ");
        octaveCount = in.nextInt();

        System.out.println("Persistence (float): ");
        persistence = in.nextFloat();

        System.out.println("Seed (long): ");
        seed = in.nextLong();

        System.out.println("Charset (String): ");
        charset = in.next();

        perlinNoise = generatePerlinNoise(width, height, octaveCount, persistence, seed);
        final char[] chars = charset.toCharArray();
        final int length = chars.length;
        final float step = 1f / length;
        // Output based on charset thresholds.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float value = step;
                float noiseValue = perlinNoise[x][y];

                for (char c : chars) {
                    if (noiseValue <= value) {
                        System.out.print(c);
                        break;
                    }

                    value += step;
                }
            }

            System.out.println();
        }
        in.close();
    }
}
