package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TDigestTest {

    /**
     * Fraction of the sample that is strictly smaller than the given value, i.e. the rank the digest
     * actually hit. Comparing ranks rather than values is the meaningful way to score a quantile
     * sketch: it is insensitive to how steep the distribution happens to be.
     */
    private static double trueRank(double[] sortedValues, double value) {
        int index = Arrays.binarySearch(sortedValues, value);
        if (index < 0) {
            index = -(index + 1);
        }
        return (double) index / sortedValues.length;
    }

    private static double[] gaussianSample(int count, long seed) {
        Random random = new Random(seed);
        double[] values = new double[count];
        for (int i = 0; i < count; i++) {
            values[i] = random.nextGaussian();
        }
        return values;
    }

    private static double[] uniformSample(int count, long seed) {
        Random random = new Random(seed);
        double[] values = new double[count];
        for (int i = 0; i < count; i++) {
            values[i] = random.nextDouble();
        }
        return values;
    }

    @ParameterizedTest
    @ValueSource(doubles = {9.0, 0.0, -5.0, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsInvalidCompression(double compression) {
        assertThrows(IllegalArgumentException.class, () -> new TDigest(compression));
    }

    @Test
    void queriesBeforeTheFirstSampleFail() {
        TDigest digest = new TDigest();
        assertTrue(digest.isEmpty());
        assertEquals(0.0, digest.totalWeight());
        assertEquals(100.0, digest.compression());
        assertThrows(IllegalStateException.class, () -> digest.quantile(0.5));
        assertThrows(IllegalStateException.class, () -> digest.cdf(0.5));
        assertThrows(IllegalStateException.class, digest::min);
        assertThrows(IllegalStateException.class, digest::max);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        TDigest digest = new TDigest();
        assertThrows(IllegalArgumentException.class, () -> digest.add(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsInvalidWeights(double weight) {
        TDigest digest = new TDigest();
        assertThrows(IllegalArgumentException.class, () -> digest.add(1.0, weight));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1, 1.1, Double.NaN})
    void rejectsInvalidQuantiles(double q) {
        TDigest digest = new TDigest();
        digest.add(1.0);
        assertThrows(IllegalArgumentException.class, () -> digest.quantile(q));
    }

    @Test
    void rejectsNaNInCdf() {
        TDigest digest = new TDigest();
        digest.add(1.0);
        assertThrows(IllegalArgumentException.class, () -> digest.cdf(Double.NaN));
    }

    @Test
    void aSingleSampleAnswersEveryQuantile() {
        TDigest digest = new TDigest();
        digest.add(4.0);
        assertEquals(4.0, digest.quantile(0.0));
        assertEquals(4.0, digest.quantile(0.5));
        assertEquals(4.0, digest.quantile(1.0));
        assertEquals(4.0, digest.min());
        assertEquals(4.0, digest.max());
        assertEquals(1.0, digest.totalWeight());
        assertEquals(1, digest.centroidCount());
    }

    @Test
    void aConstantStreamCollapsesIntoOneValue() {
        TDigest digest = new TDigest();
        for (int i = 0; i < 10_000; i++) {
            digest.add(3.5);
        }
        assertEquals(3.5, digest.quantile(0.1), 1e-12);
        assertEquals(3.5, digest.quantile(0.9), 1e-12);
        assertEquals(0.5, digest.cdf(3.5), 1e-12);
    }

    @Test
    void extremesAreExact() {
        double[] values = gaussianSample(20_000, 11L);
        TDigest digest = new TDigest();
        digest.addAll(values);

        double[] sorted = values.clone();
        Arrays.sort(sorted);
        assertEquals(sorted[0], digest.min(), 0.0);
        assertEquals(sorted[sorted.length - 1], digest.max(), 0.0);
        assertEquals(sorted[0], digest.quantile(0.0), 0.0);
        assertEquals(sorted[sorted.length - 1], digest.quantile(1.0), 0.0);
        assertEquals(0.0, digest.cdf(sorted[0] - 1.0));
        assertEquals(1.0, digest.cdf(sorted[sorted.length - 1] + 1.0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.01, 0.1, 0.25, 0.5, 0.75, 0.9, 0.99, 0.999})
    @DisplayName("hits the requested rank of a uniform stream")
    void hitsTheRequestedRankOnUniformData(double q) {
        double[] values = uniformSample(100_000, 20240517L);
        TDigest digest = new TDigest(100.0);
        digest.addAll(values);

        double[] sorted = values.clone();
        Arrays.sort(sorted);
        assertEquals(q, trueRank(sorted, digest.quantile(q)), 0.01, "requested q=" + q);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.01, 0.1, 0.5, 0.9, 0.99, 0.999})
    @DisplayName("is far more accurate in the tails than in the middle, by design")
    void isMostAccurateInTheTails(double q) {
        double[] values = gaussianSample(200_000, 4242L);
        TDigest digest = new TDigest(200.0);
        digest.addAll(values);

        double[] sorted = values.clone();
        Arrays.sort(sorted);
        double rankError = Math.abs(trueRank(sorted, digest.quantile(q)) - q);

        // The tolerance follows the shape of the scale function: it shrinks as q approaches 0 or 1.
        double allowedRankError = 0.01 * Math.max(0.05, 4.0 * q * (1.0 - q));
        assertTrue(rankError <= allowedRankError, "q=" + q + " rank error " + rankError + " exceeded " + allowedRankError);
    }

    @Test
    void quantilesAreMonotonic() {
        TDigest digest = new TDigest();
        digest.addAll(gaussianSample(50_000, 777L));

        double previous = digest.quantile(0.0);
        for (int i = 1; i <= 1_000; i++) {
            double current = digest.quantile(i / 1_000.0);
            assertTrue(current >= previous, "quantile decreased at q=" + i / 1_000.0);
            previous = current;
        }
    }

    @Test
    @DisplayName("the cdf inverts the quantile function")
    void cdfAgreesWithQuantile() {
        TDigest digest = new TDigest(200.0);
        digest.addAll(uniformSample(100_000, 314159L));

        for (int step = 1; step <= 19; step++) {
            double q = step * 0.05;
            assertEquals(q, digest.cdf(digest.quantile(q)), 0.02, "at q=" + q);
        }
    }

    @Test
    void cdfMatchesTheEmpiricalDistribution() {
        double[] values = uniformSample(50_000, 2718L);
        TDigest digest = new TDigest();
        digest.addAll(values);

        double[] sorted = values.clone();
        Arrays.sort(sorted);
        for (int step = 1; step <= 19; step++) {
            double x = step * 0.05;
            assertEquals(trueRank(sorted, x), digest.cdf(x), 0.02, "at x=" + x);
        }
    }

    @Test
    @DisplayName("a weighted sample stands for that many observations")
    void supportsWeightedSamples() {
        TDigest weighted = new TDigest();
        TDigest repeated = new TDigest();
        for (int i = 0; i < 100; i++) {
            weighted.add(i, 10.0);
            for (int repeat = 0; repeat < 10; repeat++) {
                repeated.add(i);
            }
        }

        assertEquals(1_000.0, weighted.totalWeight(), 1e-9);
        assertEquals(repeated.totalWeight(), weighted.totalWeight(), 1e-9);
        assertEquals(repeated.quantile(0.5), weighted.quantile(0.5), 2.0);
    }

    @Test
    @DisplayName("merging shard digests approximates a digest of the whole stream")
    void mergesLikeAPartialAggregate() {
        double[] values = gaussianSample(120_000, 5150L);
        TDigest whole = new TDigest(200.0);
        TDigest shardA = new TDigest(200.0);
        TDigest shardB = new TDigest(200.0);
        TDigest shardC = new TDigest(200.0);
        for (int i = 0; i < values.length; i++) {
            whole.add(values[i]);
            if (i % 3 == 0) {
                shardA.add(values[i]);
            } else if (i % 3 == 1) {
                shardB.add(values[i]);
            } else {
                shardC.add(values[i]);
            }
        }

        shardA.merge(shardB);
        shardA.merge(shardC);

        assertEquals(whole.totalWeight(), shardA.totalWeight(), 1e-9);
        assertEquals(whole.min(), shardA.min(), 0.0);
        assertEquals(whole.max(), shardA.max(), 0.0);

        double[] sorted = values.clone();
        Arrays.sort(sorted);
        for (double q : new double[] {0.01, 0.1, 0.5, 0.9, 0.99}) {
            assertEquals(q, trueRank(sorted, shardA.quantile(q)), 0.01, "merged digest at q=" + q);
        }
    }

    @Test
    void mergingAnEmptyDigestChangesNothing() {
        TDigest digest = new TDigest();
        digest.addAll(1.0, 2.0, 3.0, 4.0, 5.0);
        double median = digest.quantile(0.5);

        digest.merge(new TDigest());
        assertEquals(median, digest.quantile(0.5), 0.0);
        assertEquals(5.0, digest.totalWeight());
    }

    @ParameterizedTest
    @ValueSource(doubles = {20.0, 100.0, 500.0})
    @DisplayName("the sketch stays small no matter how long the stream is")
    void keepsTheCentroidCountBounded(double compression) {
        TDigest digest = new TDigest(compression);
        digest.addAll(gaussianSample(200_000, 8L));

        assertTrue(digest.centroidCount() <= compression, "kept " + digest.centroidCount() + " centroids for compression " + compression);
        assertEquals(digest.centroidCount(), digest.centroidMeansSnapshot().length);
        assertEquals(digest.centroidCount(), digest.centroidWeightsSnapshot().length);

        double[] means = digest.centroidMeansSnapshot();
        for (int i = 1; i < means.length; i++) {
            assertTrue(means[i - 1] <= means[i], "centroids are not sorted at index " + i);
        }
        assertEquals(digest.totalWeight(), Arrays.stream(digest.centroidWeightsSnapshot()).sum(), 1e-6);
    }

    @Test
    @DisplayName("a higher compression buys accuracy")
    void higherCompressionIsMoreAccurate() {
        double[] values = gaussianSample(200_000, 6060L);
        double[] sorted = values.clone();
        Arrays.sort(sorted);

        TDigest coarse = new TDigest(20.0);
        TDigest fine = new TDigest(500.0);
        coarse.addAll(values);
        fine.addAll(values);

        double coarseError = Math.abs(trueRank(sorted, coarse.quantile(0.5)) - 0.5);
        double fineError = Math.abs(trueRank(sorted, fine.quantile(0.5)) - 0.5);
        assertTrue(fineError <= coarseError, "coarse=" + coarseError + " fine=" + fineError);
    }

    @Test
    void handlesSortedInput() {
        TDigest digest = new TDigest();
        for (int i = 1; i <= 100_000; i++) {
            digest.add(i);
        }
        assertEquals(50_000.0, digest.quantile(0.5), 1_000.0);
        assertEquals(1.0, digest.min());
        assertEquals(100_000.0, digest.max());
    }

    @Test
    void resetForgetsEverything() {
        TDigest digest = new TDigest();
        digest.addAll(gaussianSample(1_000, 3L));
        digest.reset();

        assertTrue(digest.isEmpty());
        assertEquals(0.0, digest.totalWeight());
        assertThrows(IllegalStateException.class, () -> digest.quantile(0.5));

        digest.addAll(1.0, 2.0, 3.0);
        assertEquals(3.0, digest.totalWeight());
        assertEquals(2.0, digest.quantile(0.5), 0.5);
    }

    @Test
    void toStringMentionsTheState() {
        TDigest digest = new TDigest();
        assertTrue(digest.toString().contains("centroids=0"), digest.toString());
        digest.addAll(1.0, 2.0, 3.0);
        assertFalse(digest.isEmpty());
        assertTrue(digest.toString().contains("weight=3.0"), digest.toString());
    }

    @Test
    @DisplayName("the very edges of the quantile function interpolate towards the tracked extremes")
    void interpolatesTowardsTheExtremes() {
        double[] values = uniformSample(100_000, 161803L);
        TDigest digest = new TDigest();
        digest.addAll(values);

        double[] sorted = values.clone();
        Arrays.sort(sorted);
        double deepTail = digest.quantile(0.999999);
        assertTrue(deepTail <= digest.max(), "the estimate must not exceed the maximum");
        assertTrue(deepTail >= sorted[sorted.length - 10], "the deep tail should sit among the largest samples");

        assertEquals(1.0, digest.cdf(digest.max()), 1e-9);
        assertEquals(0.0, digest.cdf(digest.min()), 0.01);
    }

    @Test
    @DisplayName("a stream made of two repeated values keeps its quantiles sane")
    void handlesHeavilyDuplicatedValues() {
        TDigest digest = new TDigest();
        for (int i = 0; i < 20_000; i++) {
            digest.add(i % 2 == 0 ? 1.0 : 2.0);
        }

        assertEquals(1.0, digest.min());
        assertEquals(2.0, digest.max());
        assertTrue(digest.quantile(0.25) <= 1.5, "the lower quarter is made of ones");
        assertTrue(digest.quantile(0.75) >= 1.5, "the upper quarter is made of twos");
        assertTrue(digest.cdf(1.5) >= 0.0 && digest.cdf(1.5) <= 1.0);
    }

    @Test
    @DisplayName("weights spanning eighteen orders of magnitude still give sane, monotone quantiles")
    void survivesWildlyDisparateWeights() {
        TDigest digest = new TDigest(50.0);
        digest.add(0.0, 1e12);
        for (int i = 1; i <= 10_000; i++) {
            digest.add(i, 1e-6);
        }

        // A point mass this dominant is the regime where the packing rule degenerates into one centroid
        // per sample, so it is also the regime that decides whether the centroid array is large enough.
        assertEquals(1e12 + 10_000 * 1e-6, digest.totalWeight(), 1.0);
        assertEquals(0.0, digest.min());
        assertEquals(10_000.0, digest.max());
        assertEquals(0.0, digest.quantile(0.5), 1e-6, "essentially all the weight sits at zero");
        assertTrue(digest.quantile(0.999999) > 0.0, "the tail must not collapse onto the bulk");

        double previous = digest.quantile(0.0);
        for (int i = 1; i <= 1_000; i++) {
            double current = digest.quantile(i / 1_000.0);
            assertTrue(current >= previous, "quantiles stopped being monotonic at q=" + i / 1_000.0);
            previous = current;
        }
    }
}
