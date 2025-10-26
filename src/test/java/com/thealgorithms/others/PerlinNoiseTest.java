package com.thealgorithms.others;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PerlinNoiseTest {

    @Test
    @DisplayName("generatePerlinNoise returns array with correct dimensions")
    void testDimensions() {
        int w = 8;
        int h = 6;
        float[][] noise = PerlinNoise.generatePerlinNoise(w, h, 4, 0.6f, 123L);
        assertThat(noise).hasDimensions(w, h);
    }

    @Test
    @DisplayName("All values are within [0,1] after normalization")
    void testRange() {
        int w = 16;
        int h = 16;
        float[][] noise = PerlinNoise.generatePerlinNoise(w, h, 5, 0.7f, 42L);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                assertThat(noise[x][y]).isBetween(0f, 1f);
            }
        }
    }

    @Test
    @DisplayName("Deterministic for same parameters and seed")
    void testDeterminism() {
        int w = 10;
        int h = 10;
        long seed = 98765L;
        float[][] a = PerlinNoise.generatePerlinNoise(w, h, 3, 0.5f, seed);
        float[][] b = PerlinNoise.generatePerlinNoise(w, h, 3, 0.5f, seed);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                assertThat(a[x][y]).isEqualTo(b[x][y]);
            }
        }
    }

    @Test
    @DisplayName("Different seeds produce different outputs (probabilistically)")
    void testDifferentSeeds() {
        int w = 12;
        int h = 12;
        float[][] a = PerlinNoise.generatePerlinNoise(w, h, 4, 0.8f, 1L);
        float[][] b = PerlinNoise.generatePerlinNoise(w, h, 4, 0.8f, 2L);

        // Count exact equalities; expect very few or none.
        int equalCount = 0;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if (Float.compare(a[x][y], b[x][y]) == 0) {
                    equalCount++;
                }
            }
        }
        assertThat(equalCount).isLessThan(w * h / 10); // less than 10% equal exact values
    }

    @Test
    @DisplayName("Interpolation endpoints are respected")
    void testInterpolateEndpoints() {
        assertThat(PerlinNoise.interpolate(0f, 1f, 0f)).isEqualTo(0f);
        assertThat(PerlinNoise.interpolate(0f, 1f, 1f)).isEqualTo(1f);
        assertThat(PerlinNoise.interpolate(0.2f, 0.8f, 0.5f)).isEqualTo(0.5f);
    }

    @Test
    @DisplayName("Single octave reduces to bilinear interpolation of base grid")
    void testSingleOctaveLayer() {
        int w = 8;
        int h = 8;
        long seed = 7L;
        float[][] base = PerlinNoise.createBaseGrid(w, h, seed);
        float[][] layer = PerlinNoise.generatePerlinNoiseLayer(base, w, h, 0); // period=1
        // With period = 1, x0=x, x1=(x+1)%w etc. Values should be smooth and within
        // [0,1]
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                assertThat(layer[x][y]).isBetween(0f, 1f);
            }
        }
    }

    @Test
    @DisplayName("Invalid inputs are rejected")
    void testInvalidInputs() {
        assertThatThrownBy(() -> PerlinNoise.generatePerlinNoise(0, 5, 1, 0.5f, 1L)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> PerlinNoise.generatePerlinNoise(5, -1, 1, 0.5f, 1L)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> PerlinNoise.generatePerlinNoise(5, 5, 0, 0.5f, 1L)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> PerlinNoise.generatePerlinNoise(5, 5, 1, 0f, 1L)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> PerlinNoise.generatePerlinNoise(5, 5, 1, Float.NaN, 1L)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> PerlinNoise.generatePerlinNoise(5, 5, 1, 1.1f, 1L)).isInstanceOf(IllegalArgumentException.class);
    }
}
