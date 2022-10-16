package com.thealgorithms.maths;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * @author: caos321
 * @date: 31 October 2021 (Sunday)
 */
public class MatrixUtil {

    public static boolean isValid(final BigDecimal[][] matrix) {
        return matrix != null && matrix.length > 0 && matrix[0].length > 0;
    }

    public static boolean hasEqualSizes(
        final BigDecimal[][] matrix1,
        final BigDecimal[][] matrix2
    ) {
        return (
            isValid(matrix1) &&
            isValid(matrix2) &&
            matrix1.length == matrix2.length &&
            matrix1[0].length == matrix2[0].length
        );
    }

    public static boolean canMultiply(
        final BigDecimal[][] matrix1,
        final BigDecimal[][] matrix2
    ) {
        return (
            isValid(matrix1) &&
            isValid(matrix2) &&
            matrix1[0].length == matrix2.length
        );
    }

    public static Optional<BigDecimal[][]> operate(
        final BigDecimal[][] matrix1,
        final BigDecimal[][] matrix2,
        final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation
    ) {
        if (!hasEqualSizes(matrix1, matrix2)) {
            return Optional.empty();
        }

        final int rowSize = matrix1.length;
        final int columnSize = matrix1[0].length;

        final BigDecimal[][] result = new BigDecimal[rowSize][columnSize];

        IntStream
            .range(0, rowSize)
            .forEach(rowIndex ->
                IntStream
                    .range(0, columnSize)
                    .forEach(columnIndex -> {
                        final BigDecimal value1 =
                            matrix1[rowIndex][columnIndex];
                        final BigDecimal value2 =
                            matrix2[rowIndex][columnIndex];

                        result[rowIndex][columnIndex] =
                            operation.apply(value1, value2);
                    })
            );

        return Optional.of(result);
    }

    public static Optional<BigDecimal[][]> add(
        final BigDecimal[][] matrix1,
        final BigDecimal[][] matrix2
    ) {
        return operate(matrix1, matrix2, BigDecimal::add);
    }

    public static Optional<BigDecimal[][]> subtract(
        final BigDecimal[][] matrix1,
        final BigDecimal[][] matrix2
    ) {
        return operate(matrix1, matrix2, BigDecimal::subtract);
    }

    public static Optional<BigDecimal[][]> multiply(
        final BigDecimal[][] matrix1,
        final BigDecimal[][] matrix2
    ) {
        if (!canMultiply(matrix1, matrix2)) {
            return Optional.empty();
        }

        final int size = matrix1[0].length;

        final int matrix1RowSize = matrix1.length;
        final int matrix2ColumnSize = matrix2[0].length;

        final BigDecimal[][] result = new BigDecimal[matrix1RowSize][matrix2ColumnSize];

        IntStream
            .range(0, matrix1RowSize)
            .forEach(rowIndex ->
                IntStream
                    .range(0, matrix2ColumnSize)
                    .forEach(columnIndex ->
                        result[rowIndex][columnIndex] =
                            IntStream
                                .range(0, size)
                                .mapToObj(index -> {
                                    final BigDecimal value1 =
                                        matrix1[rowIndex][index];
                                    final BigDecimal value2 =
                                        matrix2[index][columnIndex];

                                    return value1.multiply(value2);
                                })
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                    )
            );

        return Optional.of(result);
    }

    public static void assertThat(
        final BigDecimal[][] actual,
        final BigDecimal[][] expected
    ) {
        if (!Objects.deepEquals(actual, expected)) {
            throw new AssertionError(
                String.format(
                    "expected=%s but was actual=%s",
                    Arrays.deepToString(expected),
                    Arrays.deepToString(actual)
                )
            );
        }
    }

    public static void main(final String[] args) {
        {
            final BigDecimal[][] matrix1 = {
                { new BigDecimal(3), new BigDecimal(2) },
                { new BigDecimal(0), new BigDecimal(1) },
            };

            final BigDecimal[][] matrix2 = {
                { new BigDecimal(1), new BigDecimal(3) },
                { new BigDecimal(2), new BigDecimal(0) },
            };

            final BigDecimal[][] actual = add(matrix1, matrix2)
                .orElseThrow(() ->
                    new AssertionError("Could not compute matrix!")
                );

            final BigDecimal[][] expected = {
                { new BigDecimal(4), new BigDecimal(5) },
                { new BigDecimal(2), new BigDecimal(1) },
            };

            assertThat(actual, expected);
        }

        {
            final BigDecimal[][] matrix1 = {
                { new BigDecimal(1), new BigDecimal(4) },
                { new BigDecimal(5), new BigDecimal(6) },
            };

            final BigDecimal[][] matrix2 = {
                { new BigDecimal(2), new BigDecimal(0) },
                { new BigDecimal(-2), new BigDecimal(-3) },
            };

            final BigDecimal[][] actual = subtract(matrix1, matrix2)
                .orElseThrow(() ->
                    new AssertionError("Could not compute matrix!")
                );

            final BigDecimal[][] expected = {
                { new BigDecimal(-1), new BigDecimal(4) },
                { new BigDecimal(7), new BigDecimal(9) },
            };

            assertThat(actual, expected);
        }

        {
            final BigDecimal[][] matrix1 = {
                { new BigDecimal(1), new BigDecimal(2), new BigDecimal(3) },
                { new BigDecimal(4), new BigDecimal(5), new BigDecimal(6) },
                { new BigDecimal(7), new BigDecimal(8), new BigDecimal(9) },
            };

            final BigDecimal[][] matrix2 = {
                { new BigDecimal(1), new BigDecimal(2) },
                { new BigDecimal(3), new BigDecimal(4) },
                { new BigDecimal(5), new BigDecimal(6) },
            };

            final BigDecimal[][] actual = multiply(matrix1, matrix2)
                .orElseThrow(() ->
                    new AssertionError("Could not compute matrix!")
                );

            final BigDecimal[][] expected = {
                { new BigDecimal(22), new BigDecimal(28) },
                { new BigDecimal(49), new BigDecimal(64) },
                { new BigDecimal(76), new BigDecimal(100) },
            };

            assertThat(actual, expected);
        }
    }
}
