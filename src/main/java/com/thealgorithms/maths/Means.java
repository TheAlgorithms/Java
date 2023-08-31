package com.thealgorithms.maths;

import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Mean
 * <p>
 * by: Punit Patel
 */
public final class Means {

    private Means() {}
    /**
     * Arithmetic / Pythagorean Mean = (x₁ + x₂ + ... +  xₙ) / n
     */
    public static Double arithmetic(final List<Double> numbers) {
        if (numbers.isEmpty()) throw new IllegalArgumentException("Emtpy list given for Mean computation.");

        return numbers.stream().reduce((x, y) -> x + y).get() / numbers.size();
    }

    /**
     * Geometric Mean = (x₁ · x₂ · ... · xₙ) ^ 1/n
     */
    public static Double geometric(final List<Double> numbers) {
        if (numbers.isEmpty()) throw new IllegalArgumentException("Emtpy list given for Mean computation.");

        return Math.pow(numbers.stream().reduce((x, y) -> x * y).get(), 1.0 / numbers.size());
    }

    /**
     * Harmonic Mean = n / (1/x₁ + 1/x₂ + ... +  1/xₙ)
     */
    public static double harmonic(final List<Double> numbers) {
        if (numbers.isEmpty()) throw new IllegalArgumentException("Emtpy list given for Mean computation.");

        return numbers.size() / numbers.stream().map(x -> 1 / x).reduce((x, y) -> x + y).get();
    }
}
