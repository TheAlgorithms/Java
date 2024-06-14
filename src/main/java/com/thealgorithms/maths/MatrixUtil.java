package com.thealgorithms.maths;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * @author: caos321
 * @date: 31 October 2021 (Sunday)
 */
public final class MatrixUtil {
    private MatrixUtil() {
    }

    private static boolean isValid(final BigDecimal[][] matrix) {
        return matrix != null && matrix.length > 0 && matrix[0].length > 0;
    }

    private static boolean hasEqualSizes(final BigDecimal[][] matrix1, final BigDecimal[][] matrix2) {
        return (isValid(matrix1) && isValid(matrix2) && matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length);
    }

    private static boolean canMultiply(final BigDecimal[][] matrix1, final BigDecimal[][] matrix2) {
        return (isValid(matrix1) && isValid(matrix2) && matrix1[0].length == matrix2.length);
    }

    private static Optional<BigDecimal[][]> operate(final BigDecimal[][] matrix1, final BigDecimal[][] matrix2, final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation) {
        if (!hasEqualSizes(matrix1, matrix2)) {
            return Optional.empty();
        }

        final int rowSize = matrix1.length;
        final int columnSize = matrix1[0].length;

        final BigDecimal[][] result = new BigDecimal[rowSize][columnSize];

        IntStream.range(0, rowSize).forEach(rowIndex -> IntStream.range(0, columnSize).forEach(columnIndex -> {
            final BigDecimal value1 = matrix1[rowIndex][columnIndex];
            final BigDecimal value2 = matrix2[rowIndex][columnIndex];

            result[rowIndex][columnIndex] = operation.apply(value1, value2);
        }));

        return Optional.of(result);
    }

    public static Optional<BigDecimal[][]> add(final BigDecimal[][] matrix1, final BigDecimal[][] matrix2) {
        return operate(matrix1, matrix2, BigDecimal::add);
    }

    public static Optional<BigDecimal[][]> subtract(final BigDecimal[][] matrix1, final BigDecimal[][] matrix2) {
        return operate(matrix1, matrix2, BigDecimal::subtract);
    }

    public static Optional<BigDecimal[][]> multiply(final BigDecimal[][] matrix1, final BigDecimal[][] matrix2) {
        if (!canMultiply(matrix1, matrix2)) {
            return Optional.empty();
        }

        final int size = matrix1[0].length;

        final int matrix1RowSize = matrix1.length;
        final int matrix2ColumnSize = matrix2[0].length;

        final BigDecimal[][] result = new BigDecimal[matrix1RowSize][matrix2ColumnSize];

        IntStream.range(0, matrix1RowSize)
            .forEach(rowIndex
                -> IntStream.range(0, matrix2ColumnSize)
                       .forEach(columnIndex
                           -> result[rowIndex][columnIndex] = IntStream.range(0, size)
                                                                  .mapToObj(index -> {
                                                                      final BigDecimal value1 = matrix1[rowIndex][index];
                                                                      final BigDecimal value2 = matrix2[index][columnIndex];

                                                                      return value1.multiply(value2);
                                                                  })
                                                                  .reduce(BigDecimal.ZERO, BigDecimal::add)));

        return Optional.of(result);
    }
}
