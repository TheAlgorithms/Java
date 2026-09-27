package com.thealgorithms.streaming;

import java.util.Arrays;

/**
 * <b>Dynamic time warping</b>: how far apart two series are once one of them is allowed to be
 * stretched and squeezed in time.
 *
 * <p>The Euclidean distance compares sample {@code i} with sample {@code i} and nothing else, so two
 * recordings of the same gesture, one performed slightly faster, come out as far apart as two
 * unrelated ones. Dynamic time warping instead looks for the cheapest way to line the two series up:
 * every point of the first has to be matched to at least one point of the second and the other way
 * round, the matching may never go backwards, and the cost is the sum over the matched pairs. That
 * alignment is a shortest path through a grid, and the dynamic program is the obvious one:
 *
 * <pre>
 * D[i][j] = |a[i] - b[j]| + min( D[i-1][j], D[i][j-1], D[i-1][j-1] )
 * </pre>
 *
 * <p>The three predecessors are exactly the three legal moves: consume a point of the first series,
 * of the second, or of both. The answer is the bottom right corner.
 *
 * <p>Left alone, the alignment may match one point of a series against an arbitrarily long stretch of
 * the other, which is rarely meaningful and costs {@code O(n * m)} regardless. The Sakoe-Chiba band
 * forbids matches further apart in time than a given width, which both rules out those degenerate
 * alignments and narrows the grid that has to be filled. The band has to be at least the difference
 * in length, or no alignment exists at all.
 *
 * <p>Note that the result is not a metric: it does not satisfy the triangle inequality, so it can be
 * used to rank candidates but not to index them without further care.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * double distance = DynamicTimeWarping.distance(query, candidate);
 * double banded = DynamicTimeWarping.distance(query, candidate, 10);
 * int[][] alignment = DynamicTimeWarping.path(query, candidate);
 * }</pre>
 *
 * <p>The distance costs O(n * m) time and O(min(n, m)) memory; the path costs O(n * m) of both,
 * because it has to remember the grid.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Dynamic_time_warping">Dynamic time warping</a>
 */
public final class DynamicTimeWarping {

    private DynamicTimeWarping() {
    }

    /**
     * Returns the warping distance between two series.
     *
     * @param first the first series, left untouched
     * @param second the second series, left untouched
     * @return the cost of the cheapest alignment
     * @throws IllegalArgumentException if a series is empty or holds a non-finite value
     * @throws NullPointerException if a series is {@code null}
     */
    public static double distance(double[] first, double[] second) {
        return distance(first, second, Math.max(first.length, second.length));
    }

    /**
     * Returns the warping distance between two series, with the alignment confined to a Sakoe-Chiba
     * band.
     *
     * @param first the first series, left untouched
     * @param second the second series, left untouched
     * @param band how far apart in time two matched points may be, at least the difference in length
     * @return the cost of the cheapest alignment inside the band
     * @throws IllegalArgumentException if a series is empty or holds a non-finite value, or if the
     *     band is too narrow for any alignment to exist
     * @throws NullPointerException if a series is {@code null}
     */
    public static double distance(double[] first, double[] second, int band) {
        requireSeries(first, "first");
        requireSeries(second, "second");
        requireBand(band, first.length, second.length);

        double[] previous = new double[second.length + 1];
        double[] current = new double[second.length + 1];
        Arrays.fill(previous, Double.POSITIVE_INFINITY);
        previous[0] = 0.0;

        for (int i = 1; i <= first.length; i++) {
            Arrays.fill(current, Double.POSITIVE_INFINITY);
            int from = Math.max(1, i - band);
            int to = Math.min(second.length, i + band);
            for (int j = from; j <= to; j++) {
                double cost = Math.abs(first[i - 1] - second[j - 1]);
                double best = Math.min(previous[j], Math.min(current[j - 1], previous[j - 1]));
                current[j] = cost + best;
            }
            double[] swap = previous;
            previous = current;
            current = swap;
        }
        return previous[second.length];
    }

    /**
     * Returns the cheapest alignment itself.
     *
     * @param first the first series, left untouched
     * @param second the second series, left untouched
     * @return the matched pairs of indices, from {@code (0, 0)} to the two last indices
     * @throws IllegalArgumentException if a series is empty or holds a non-finite value
     * @throws NullPointerException if a series is {@code null}
     */
    public static int[][] path(double[] first, double[] second) {
        return path(first, second, Math.max(first.length, second.length));
    }

    /**
     * Returns the cheapest alignment inside a Sakoe-Chiba band.
     *
     * @param first the first series, left untouched
     * @param second the second series, left untouched
     * @param band how far apart in time two matched points may be, at least the difference in length
     * @return the matched pairs of indices, from {@code (0, 0)} to the two last indices
     * @throws IllegalArgumentException if a series is empty or holds a non-finite value, or if the
     *     band is too narrow for any alignment to exist
     * @throws NullPointerException if a series is {@code null}
     */
    public static int[][] path(double[] first, double[] second, int band) {
        requireSeries(first, "first");
        requireSeries(second, "second");
        requireBand(band, first.length, second.length);

        double[][] grid = new double[first.length + 1][second.length + 1];
        for (double[] row : grid) {
            Arrays.fill(row, Double.POSITIVE_INFINITY);
        }
        grid[0][0] = 0.0;

        for (int i = 1; i <= first.length; i++) {
            int from = Math.max(1, i - band);
            int to = Math.min(second.length, i + band);
            for (int j = from; j <= to; j++) {
                double cost = Math.abs(first[i - 1] - second[j - 1]);
                grid[i][j] = cost + Math.min(grid[i - 1][j], Math.min(grid[i][j - 1], grid[i - 1][j - 1]));
            }
        }

        int steps = 0;
        int row = first.length;
        int column = second.length;
        int[][] reversed = new int[first.length + second.length][2];
        while (row > 0 && column > 0) {
            reversed[steps][0] = row - 1;
            reversed[steps][1] = column - 1;
            steps++;
            double diagonal = grid[row - 1][column - 1];
            double above = grid[row - 1][column];
            double left = grid[row][column - 1];
            if (diagonal <= above && diagonal <= left) {
                row--;
                column--;
            } else if (above <= left) {
                row--;
            } else {
                column--;
            }
        }

        int[][] alignment = new int[steps][2];
        for (int i = 0; i < steps; i++) {
            alignment[i] = reversed[steps - 1 - i];
        }
        return alignment;
    }

    private static void requireSeries(double[] series, String name) {
        if (series.length == 0) {
            throw new IllegalArgumentException("The " + name + " series must not be empty");
        }
        for (double value : series) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Samples must be finite, but the " + name + " series held " + value);
            }
        }
    }

    private static void requireBand(int band, int firstLength, int secondLength) {
        int minimum = Math.abs(firstLength - secondLength);
        if (band < minimum) {
            throw new IllegalArgumentException("The band must be at least the difference in length, " + minimum + ", but was " + band);
        }
    }
}
