package com.thealgorithms.maths;

import java.util.stream.StreamSupport;
import org.apache.commons.collections4.IterableUtils;

/**
 * https://en.wikipedia.org/wiki/Mean
 * <p>
 * by: Punit Patel
 */
public final class Means {

    private Means() {
    }

    /**
     * @brief computes the [Arithmetic Mean](https://en.wikipedia.org/wiki/Arithmetic_mean) of the input
     * @param numbers the input numbers
     * @throws IllegalArgumentException empty input
     * @return the arithmetic mean of the input numbers
     */
    public static Double arithmetic(final Iterable<Double> numbers) {
        if (!numbers.iterator().hasNext()) {
            throw new IllegalArgumentException("Emtpy list given for Mean computation.");
        }

        return StreamSupport.stream(numbers.spliterator(), false).reduce((x, y) -> x + y).get() / IterableUtils.size(numbers);
    }

    /**
     * @brief computes the [Geometric Mean](https://en.wikipedia.org/wiki/Geometric_mean) of the input
     * @param numbers the input numbers
     * @throws IllegalArgumentException empty input
     * @return the geometric mean of the input numbers
     */
    public static Double geometric(final Iterable<Double> numbers) {
        if (!numbers.iterator().hasNext()) {
            throw new IllegalArgumentException("Emtpy list given for Mean computation.");
        }

        return Math.pow(StreamSupport.stream(numbers.spliterator(), false).reduce((x, y) -> x * y).get(), 1.0 / IterableUtils.size(numbers));
    }

    /**
     * @brief computes the [Harmonic Mean](https://en.wikipedia.org/wiki/Harmonic_mean) of the input
     * @param numbers the input numbers
     * @throws IllegalArgumentException empty input
     * @return the harmonic mean of the input numbers
     */
    public static double harmonic(final Iterable<Double> numbers) {
        if (!numbers.iterator().hasNext()) {
            throw new IllegalArgumentException("Emtpy list given for Mean computation.");
        }

        return IterableUtils.size(numbers) / StreamSupport.stream(numbers.spliterator(), false).map(x -> 1 / x).reduce((x, y) -> x + y).get();
    }
}
