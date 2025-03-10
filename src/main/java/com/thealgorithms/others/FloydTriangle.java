package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.List;

final class FloydTriangle {
    private FloydTriangle() {
    }

    /**
     * Generates a Floyd Triangle with the specified number of rows.
     *
     * @param rows The number of rows in the triangle.
     * @return A List representing the Floyd Triangle.
     */
    public static List<List<Integer>> generateFloydTriangle(int rows) {
        List<List<Integer>> triangle = new ArrayList<>();
        int number = 1;

        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                row.add(number++);
            }
            triangle.add(row);
        }

        return triangle;
    }
}
