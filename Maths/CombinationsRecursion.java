package Maths;

public class CombinationRecursion {
    public static void main(String[] args) {
        assert combinations(1, 1) == 2;
        assert combinations(10, 5) == 252;
        assert combinations(6, 3) == 20;
        assert combinations(1200, 5) == 20563703400240L;
    }

    /**
     * Wrapper : combinations with Memoization
     *
     * @param n number of elements
     * @param k number of selected numbers
     * @return combinations of given {@code n} and {@code k}
     */
    public static long combinations(int n, int k) {
        long map[][] = new long[n + 1][k + 1];
        // initialize. (n,1) (n,0) is n
        for (int i = 1; i <= n; i++) {
            map[i][0] = i;
            map[i][1] = i;
        }
        return combinations(map, n, k);
    }

    /**
     * Recursive combinations with Memoization
     *
     * @param map 2-d array which has sub result
     * @param n   number of elements
     * @param k   number of selected numbers
     * @return combinations of given {@code n} and {@code k}
     */
    private static long combinations(long[][] map, int n, int k) {
        if (n < 0)
            return 0;
        if (map[n][k] != 0)
            return map[n][k];
        else {
            map[n][k] = combinations(map, n - 1, k) + combinations(map, n - 1, k - 1);
            return map[n][k];
        }
    }
}
