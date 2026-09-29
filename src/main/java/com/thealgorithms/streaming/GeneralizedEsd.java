package com.thealgorithms.streaming;

import java.util.Arrays;

/**
 * The <b>generalized extreme Studentized deviate test</b> of Rosner: finds up to {@code r} outliers
 * in a sample, without being told how many are there.
 *
 * <p>Grubbs' test asks whether the single most extreme point is an outlier, and it breaks as soon as
 * there are two of them: each one inflates the standard deviation the other is measured against, so
 * a pair of outliers can hide one another completely. This is the masking problem, and the
 * generalized test is the standard answer to it. It removes the most extreme point, recomputes the
 * statistics on what is left, and repeats {@code r} times:
 *
 * <pre>
 * R_i      = max |x - mean| / sd            over the points that are still in
 * lambda_i = (n - i) * t / sqrt( (n - i - 1 + t^2)(n - i + 1) )
 * where t  = the 1 - alpha / (2(n - i + 1)) quantile of a t distribution with n - i - 1 degrees of freedom
 * </pre>
 *
 * <p>The number of outliers is the <i>largest</i> {@code i} whose statistic exceeds its critical
 * value, not the first one. That is what defeats masking: in a sample with three outliers the first
 * two tests can easily fall short while the third one succeeds, and the test then reports all three.
 *
 * <p>This is a batch test and it needs the whole sample, which is exactly the trade it makes against
 * the streaming detectors in this package: it has a stated significance level and it decides how many
 * points are outliers, where {@link HampelFilter} answers one sample at a time against a threshold the
 * caller has to choose. Rosner's own recommendation is to use it on at least 25 points, and {@code r}
 * is an upper bound that may be set generously, because overstating it costs accuracy only in extreme
 * cases.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * GeneralizedEsd test = new GeneralizedEsd(0.05);
 * int[] outliers = test.findOutliers(sample, 10);
 * }</pre>
 *
 * <p>The test costs O(r * n) time and allocates O(n). The t quantile behind the critical value is
 * computed from the regularized incomplete beta function, so no table is needed.
 *
 * @see HampelFilter
 * @see <a href="https://en.wikipedia.org/wiki/Grubbs%27s_test">Grubbs's test and its generalization</a>
 */
public final class GeneralizedEsd {

    /** Significance level used when none is given. */
    public static final double DEFAULT_SIGNIFICANCE_LEVEL = 0.05;

    private static final int MINIMUM_SAMPLE_SIZE = 3;
    private static final double[] LANCZOS = {
        0.99999999999980993,
        676.5203681218851,
        -1259.1392167224028,
        771.32342877765313,
        -176.61502916214059,
        12.507343278686905,
        -0.13857109526572012,
        9.9843695780195716e-6,
        1.5056327351493116e-7,
    };

    private final double significanceLevel;

    /**
     * Creates a test at the customary significance level of {@code 0.05}.
     */
    public GeneralizedEsd() {
        this(DEFAULT_SIGNIFICANCE_LEVEL);
    }

    /**
     * Creates a test.
     *
     * @param significanceLevel the probability of declaring an outlier in clean data, in {@code (0, 1)}
     * @throws IllegalArgumentException if {@code significanceLevel} is not inside {@code (0, 1)}
     */
    public GeneralizedEsd(double significanceLevel) {
        if (!(significanceLevel > 0.0) || !(significanceLevel < 1.0)) {
            throw new IllegalArgumentException("The significance level must lie in (0, 1), but was " + significanceLevel);
        }
        this.significanceLevel = significanceLevel;
    }

    /**
     * Finds the outliers of a sample.
     *
     * @param sample the data to inspect, left untouched
     * @param maxOutliers upper bound on the number of outliers, at least one and at most
     *     {@code sample.length - 2}
     * @return the indices of the outliers in ascending order, empty if the sample looks clean
     * @throws IllegalArgumentException if the sample holds fewer than three points or a non-finite
     *     value, or if {@code maxOutliers} is out of range
     * @throws NullPointerException if {@code sample} is {@code null}
     */
    public int[] findOutliers(double[] sample, int maxOutliers) {
        if (sample.length < MINIMUM_SAMPLE_SIZE) {
            throw new IllegalArgumentException("The sample must hold at least " + MINIMUM_SAMPLE_SIZE + " points, but held " + sample.length);
        }
        if (maxOutliers < 1 || maxOutliers > sample.length - 2) {
            throw new IllegalArgumentException("The number of outliers to look for must lie in [1, " + (sample.length - 2) + "], but was " + maxOutliers);
        }
        for (double value : sample) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Samples must be finite, but was " + value);
            }
        }

        int size = sample.length;
        double[] values = Arrays.copyOf(sample, size);
        int[] indices = new int[size];
        for (int i = 0; i < size; i++) {
            indices[i] = i;
        }

        int[] removed = new int[maxOutliers];
        int outliers = 0;
        int remaining = size;

        for (int step = 1; step <= maxOutliers; step++) {
            double mean = mean(values, remaining);
            double deviation = standardDeviation(values, remaining, mean);
            if (deviation == 0.0) {
                break;
            }

            int worst = indexOfLargestDeviation(values, remaining, mean);
            double statistic = Math.abs(values[worst] - mean) / deviation;
            removed[step - 1] = indices[worst];
            if (statistic > criticalValue(size, step)) {
                outliers = step;
            }

            remaining--;
            values[worst] = values[remaining];
            indices[worst] = indices[remaining];
        }

        int[] result = Arrays.copyOf(removed, outliers);
        Arrays.sort(result);
        return result;
    }

    /**
     * Returns the critical value a statistic has to exceed at one step of the test.
     *
     * @param sampleSize the size of the whole sample
     * @param step which removal this is, counting from one
     * @return the critical value, that is {@code lambda_i}
     * @throws IllegalArgumentException if {@code sampleSize} is smaller than three, or if {@code step}
     *     is outside {@code [1, sampleSize - 2]}
     */
    public double criticalValue(int sampleSize, int step) {
        if (sampleSize < MINIMUM_SAMPLE_SIZE) {
            throw new IllegalArgumentException("The sample size must be at least " + MINIMUM_SAMPLE_SIZE + ", but was " + sampleSize);
        }
        if (step < 1 || step > sampleSize - 2) {
            throw new IllegalArgumentException("The step must lie in [1, " + (sampleSize - 2) + "], but was " + step);
        }
        int size = sampleSize - step + 1;
        int degreesOfFreedom = size - 2;
        double probability = 1.0 - significanceLevel / (2.0 * size);
        double t = studentTQuantile(probability, degreesOfFreedom);
        return (size - 1) * t / Math.sqrt((degreesOfFreedom + t * t) * size);
    }

    /**
     * Returns the significance level of the test.
     *
     * @return the level given at construction time
     */
    public double significanceLevel() {
        return significanceLevel;
    }

    @Override
    public String toString() {
        return "GeneralizedEsd{significanceLevel=" + significanceLevel + "}";
    }

    private static double mean(double[] values, int size) {
        double sum = 0.0;
        for (int i = 0; i < size; i++) {
            sum += values[i];
        }
        return sum / size;
    }

    private static double standardDeviation(double[] values, int size, double mean) {
        double sum = 0.0;
        for (int i = 0; i < size; i++) {
            double deviation = values[i] - mean;
            sum += deviation * deviation;
        }
        return Math.sqrt(sum / (size - 1));
    }

    private static int indexOfLargestDeviation(double[] values, int size, double mean) {
        int worst = 0;
        double largest = -1.0;
        for (int i = 0; i < size; i++) {
            double deviation = Math.abs(values[i] - mean);
            if (deviation > largest) {
                largest = deviation;
                worst = i;
            }
        }
        return worst;
    }

    /**
     * Returns the {@code probability} quantile of a t distribution, found by bisecting its cumulative
     * distribution function.
     *
     * @param probability the probability to invert, in {@code (0, 1)}
     * @param degreesOfFreedom the degrees of freedom, at least one
     * @return the quantile
     */
    private static double studentTQuantile(double probability, int degreesOfFreedom) {
        if (probability < 0.5) {
            return -studentTQuantile(1.0 - probability, degreesOfFreedom);
        }
        double low = 0.0;
        double high = 1.0;
        while (studentTCumulative(high, degreesOfFreedom) < probability && high < 1e12) {
            high *= 2.0;
        }
        for (int i = 0; i < 200; i++) {
            double middle = 0.5 * (low + high);
            if (studentTCumulative(middle, degreesOfFreedom) < probability) {
                low = middle;
            } else {
                high = middle;
            }
        }
        return 0.5 * (low + high);
    }

    /**
     * Returns the probability that a t distributed variable stays below {@code t}.
     *
     * @param t the point to evaluate at
     * @param degreesOfFreedom the degrees of freedom, at least one
     * @return the cumulative probability
     */
    private static double studentTCumulative(double t, int degreesOfFreedom) {
        double x = degreesOfFreedom / (degreesOfFreedom + t * t);
        double tail = 0.5 * regularizedIncompleteBeta(0.5 * degreesOfFreedom, 0.5, x);
        return t >= 0.0 ? 1.0 - tail : tail;
    }

    /**
     * Returns the regularized incomplete beta function, evaluated with the continued fraction of
     * Lentz.
     *
     * @param a first shape parameter, strictly positive
     * @param b second shape parameter, strictly positive
     * @param x the point to evaluate at, in {@code [0, 1]}
     * @return {@code I_x(a, b)}
     */
    private static double regularizedIncompleteBeta(double a, double b, double x) {
        if (x <= 0.0) {
            return 0.0;
        }
        if (x >= 1.0) {
            return 1.0;
        }
        double logBeta = logGamma(a) + logGamma(b) - logGamma(a + b);
        if (x < (a + 1.0) / (a + b + 2.0)) {
            return Math.exp(a * Math.log(x) + b * Math.log1p(-x) - logBeta) * betaContinuedFraction(a, b, x) / a;
        }
        return 1.0 - Math.exp(b * Math.log1p(-x) + a * Math.log(x) - logBeta) * betaContinuedFraction(b, a, 1.0 - x) / b;
    }

    private static double betaContinuedFraction(double a, double b, double x) {
        double tiny = 1e-300;
        double c = 1.0;
        double d = 1.0 - (a + b) * x / (a + 1.0);
        if (Math.abs(d) < tiny) {
            d = tiny;
        }
        d = 1.0 / d;
        double fraction = d;

        for (int m = 1; m <= 300; m++) {
            int even = 2 * m;
            double numerator = m * (b - m) * x / ((a + even - 1.0) * (a + even));
            d = 1.0 + numerator * d;
            if (Math.abs(d) < tiny) {
                d = tiny;
            }
            c = 1.0 + numerator / c;
            if (Math.abs(c) < tiny) {
                c = tiny;
            }
            d = 1.0 / d;
            fraction *= d * c;

            numerator = -(a + m) * (a + b + m) * x / ((a + even) * (a + even + 1.0));
            d = 1.0 + numerator * d;
            if (Math.abs(d) < tiny) {
                d = tiny;
            }
            c = 1.0 + numerator / c;
            if (Math.abs(c) < tiny) {
                c = tiny;
            }
            d = 1.0 / d;
            double step = d * c;
            fraction *= step;

            if (Math.abs(step - 1.0) < 1e-15) {
                break;
            }
        }
        return fraction;
    }

    /**
     * Returns the logarithm of the gamma function, using the Lanczos approximation.
     *
     * @param x the point to evaluate at, strictly positive
     * @return {@code log(gamma(x))}
     */
    private static double logGamma(double x) {
        double shifted = x - 1.0;
        double series = LANCZOS[0];
        for (int i = 1; i < LANCZOS.length; i++) {
            series += LANCZOS[i] / (shifted + i);
        }
        double t = shifted + 7.5;
        return 0.5 * Math.log(2.0 * Math.PI) + (shifted + 0.5) * Math.log(t) - t + Math.log(series);
    }
}
