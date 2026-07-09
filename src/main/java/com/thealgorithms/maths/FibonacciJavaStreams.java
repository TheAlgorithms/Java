package com.thealgorithms.maths;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Calculates Fibonacci numbers using a functional programming paradigm with Java Streams.
 * <p>
 * This specific implementation uses {@link java.util.stream.Stream#iterate} and reductions to generate terms.
 * <p>
 * For alternative approaches to compute or verify Fibonacci numbers, see:
 * <ul>
 * <li>{@link com.thealgorithms.maths.FibonacciLoop} - Standard Iterative (Loop) approach</li>
 * <li>{@link com.thealgorithms.recursion.FibonacciSeries} - Naive Recursive approach</li>
 * <li>{@link com.thealgorithms.dynamicprogramming.Fibonacci} - Dynamic Programming approaches (Memoization, Bottom-Up, Optimized)</li>
 * <li>{@link com.thealgorithms.maths.FibonacciNumberGoldenRation} - Closed-form expression using Binet's formula</li>
 * <li>{@link com.thealgorithms.maths.FibonacciNumberCheck} - Utility to check if a given number is a Fibonacci number</li>
 * <li>{@link com.thealgorithms.matrix.matrixexponentiation.Fibonacci} - O(log n) Matrix Exponentiation approach</li>
 * </ul>
 * * @author caos321
 * @date 14 October 2021 (Thursday)
 */

public final class FibonacciJavaStreams {
    private FibonacciJavaStreams() {
    }

    public static Optional<BigDecimal> calculate(final BigDecimal index) {
        if (index == null || index.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Input index cannot be null or negative!");
        }

        if (index.compareTo(BigDecimal.ONE) < 0) {
            return Optional.of(BigDecimal.ZERO);
        }

        if (index.compareTo(BigDecimal.TWO) < 0) {
            return Optional.of(BigDecimal.ONE);
        }

        final List<BigDecimal> results = Stream.iterate(index, x -> x.compareTo(BigDecimal.ZERO) > 0, x -> x.subtract(BigDecimal.ONE))
                                             .reduce(List.of(), (list, current) -> list.isEmpty() || list.size() < 2 ? List.of(BigDecimal.ZERO, BigDecimal.ONE) : List.of(list.get(1), list.get(0).add(list.get(1))), (list1, list2) -> list1);

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(results.size() - 1));
    }
}
