package com.thealgorithms.maths;

/**
 * The trinomial triangle is a variation of Pascal’s triangle. The difference
 * between the two is that an entry in the trinomial triangle is the sum of the
 * three (rather than the two in Pasacal’s triangle) entries above it
 *
 * Example Input: n = 4 Output 1 1 1 1 1 2 3 2 1 1 3 6 7 6 3 1
 */
public class TrinomialTriangle {

    public static int TrinomialValue(int n, int k) {
        if (n == 0 && k == 0) {
            return 1;
        }

        if (k < -n || k > n) {
            return 0;
        }

        return TrinomialValue(n - 1, k - 1) + TrinomialValue(n - 1, k) + TrinomialValue(n - 1, k + 1);
    }

    public static void printTrinomial(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = -i; j <= 0; j++) {
                System.out.print(TrinomialValue(i, j) + " ");
            }

            for (int j = 1; j <= i; j++) {
                System.out.print(TrinomialValue(i, j) + " ");
            }

            System.out.println();
        }
    }

    public static void main(String argc[]) {
        int n = 6;
        printTrinomial(n);
    }
}
