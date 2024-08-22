package com.thealgorithms.others;

final class FloydTriangle {
    private FloydTriangle() {
    }

    /**
     * Generates a Floyd Triangle with the specified number of rows.
     *
     * @param rows The number of rows in the triangle.
     * @return A string representing the Floyd Triangle.
     */
    public static String generateFloydTriangle(int rows) {
        StringBuilder triangle = new StringBuilder();
        int number = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= i; j++) {
                triangle.append(number++).append(" ");
            }
            if (i < rows - 1) {
                triangle.append("\n");
            }
        }

        return triangle.toString();
    }
}
