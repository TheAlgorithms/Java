package com.thealgorithms.maths;

import java.util.stream.StreamSupport;
import org.apache.commons.collections4.IterableUtils;

/**
 * Utility class for computing various types of statistical means.
 * <p>
 * This class provides static methods to calculate different types of means
 * (averages)
 * from a collection of numbers. All methods accept any {@link Iterable}
 * collection of
 * {@link Double} values and return the computed mean as a {@link Double}.
 * </p>
 *
 * <p>
 * Supported means:
 * <ul>
 * <li><b>Arithmetic Mean</b>: The sum of all values divided by the count</li>
 * <li><b>Geometric Mean</b>: The nth root of the product of n values</li>
 * <li><b>Harmonic Mean</b>: The reciprocal of the arithmetic mean of
 * reciprocals</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Mean">Mean (Wikipedia)</a>
 * @author Punit Patel
 */
public final class Means {

    private Means() {
    }

    /**
     * Computes the arithmetic mean (average) of the given numbers.
     * <p>
     * The arithmetic mean is calculated as: (x₁ + x₂ + ... + xₙ) / n
     * </p>
     * <p>
     * Example: For numbers [2, 4, 6], the arithmetic mean is (2+4+6)/3 = 4.0
     * </p>
     *
     * @param numbers the input numbers (must not be empty)
     * @return the arithmetic mean of the input numbers
     * @throws IllegalArgumentException if the input is empty
     * @see <a href="https://en.wikipedia.org/wiki/Arithmetic_mean">Arithmetic
     *      Mean</a>
     */
    public static Double arithmetic(final Iterable<Double> numbers) {
        checkIfNotEmpty(numbers);
        double sum = StreamSupport.stream(numbers.spliterator(), false).reduce(0d, (x, y) -> x + y);
        int size = IterableUtils.size(numbers);
        return sum / size;
    }

    /**
     * Computes the geometric mean of the given numbers.
     * <p>
     * The geometric mean is calculated as: ⁿ√(x₁ × x₂ × ... × xₙ)
     * </p>
     * <p>
     * Example: For numbers [2, 8], the geometric mean is √(2×8) = √16 = 4.0
     * </p>
     * <p>
     * Note: This method may produce unexpected results for negative numbers,
     * as it computes the real-valued nth root which may not exist for negative
     * products.
     * </p>
     *
     * @param numbers the input numbers (must not be empty)
     * @return the geometric mean of the input numbers
     * @throws IllegalArgumentException if the input is empty
     * @see <a href="https://en.wikipedia.org/wiki/Geometric_mean">Geometric
     *      Mean</a>
     */
    public static Double geometric(final Iterable<Double> numbers) {
        checkIfNotEmpty(numbers);
        double product = StreamSupport.stream(numbers.spliterator(), false).reduce(1d, (x, y) -> x * y);
        int size = IterableUtils.size(numbers);
        return Math.pow(product, 1.0 / size);
    }

    /**
     * Computes the harmonic mean of the given numbers.
     * <p>
     * The harmonic mean is calculated as: n / (1/x₁ + 1/x₂ + ... + 1/xₙ)
     * </p>
     * <p>
     * Example: For numbers [1, 2, 4], the harmonic mean is 3/(1/1 + 1/2 + 1/4) =
     * 3/1.75 ≈ 1.714
     * </p>
     * <p>
     * Note: This method will produce unexpected results if any input number is
     * zero,
     * as it involves computing reciprocals.
     * </p>
     *
     * @param numbers the input numbers (must not be empty)
     * @return the harmonic mean of the input numbers
     * @throws IllegalArgumentException if the input is empty
     * @see <a href="https://en.wikipedia.org/wiki/Harmonic_mean">Harmonic Mean</a>
     */
    public static Double harmonic(final Iterable<Double> numbers) {
        checkIfNotEmpty(numbers);
        double sumOfReciprocals = StreamSupport.stream(numbers.spliterator(), false).reduce(0d, (x, y) -> x + 1d / y);
        int size = IterableUtils.size(numbers);
        return size / sumOfReciprocals;
    }

    /**
     * Validates that the input iterable is not empty.
     *
     * @param numbers the input numbers to validate
     * @throws IllegalArgumentException if the input is empty
     */
    private static void checkIfNotEmpty(final Iterable<Double> numbers) {
        if (!numbers.iterator().hasNext()) {
            throw new IllegalArgumentException("Empty list given for Mean computation.");
        }
    }
}
