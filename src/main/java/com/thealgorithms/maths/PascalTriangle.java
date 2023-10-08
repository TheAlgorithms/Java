package com.thealgorithms.maths;

public class PascalTriangle {

    /**
     *In mathematics, Pascal's triangle is a triangular array of the binomial coefficients that
     *arises in probability theory, combinatorics, and algebra. In much of the Western world, it is
     *named after the French mathematician Blaise Pascal, although other mathematicians studied it
     *centuries before him in India, Persia, China, Germany, and Italy.
     *
     * The rows of Pascal's triangle are conventionally enumerated starting with row n=0 at the top
     *(the 0th row). The entries in each row are numbered from the left beginning with k=0 and are
     *usually staggered relative to the numbers in the adjacent rows. The triangle may be
     *constructed in the following manner: In row 0 (the topmost row), there is a unique nonzero
     *entry 1. Each entry of each subsequent row is constructed by adding the number above and to
     *the left with the number above and to the right, treating blank entries as 0. For example, the
     *initial number in the first (or any other) row is 1 (the sum of 0 and 1), whereas the numbers
     *1 and 3 in the third row are added to produce the number 4 in the fourth row. *
     *
     *<p>
     *     link:-https://en.wikipedia.org/wiki/Pascal%27s_triangle
     *
     * <p>
     *     Example:-
     *                  1
     *                1   1
     *              1   2   1
     *            1   3   3   1
     *          1   4   6   4   1
     *        1   5  10   10  5   1
     *      1   6  15  20   15  6   1
     *    1   7  21  35   35  21  7   1
     *  1   8  28  56  70   56   28  8   1
     *
     */

    public static int[][] pascal(int n) {
        /**
         * @param arr  An auxiliary array to store generated pascal triangle values
         * @return
         */
        int[][] arr = new int[n][n];
        /**
         * @param line Iterate through every line and print integer(s) in it
         * @param i Represents the column number of the element we are currently on
         */
        for (int line = 0; line < n; line++) {
            /**
             *  @Every line has number of integers equal to line number
             */
            for (int i = 0; i <= line; i++) {
                // First and last values in every row are 1
                if (line == i || i == 0) arr[line][i] = 1;
                // The rest elements are sum of values just above and left of above
                else
                    arr[line][i] = arr[line - 1][i - 1] + arr[line - 1][i];
            }
        }

        return arr;
    }
}
