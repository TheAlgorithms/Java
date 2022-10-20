package com.thealgorithms.maths;

import java.util.*;
import java.lang.Math;

public class Factors {

    /**
     * Function to calculate factors of a given number.
     *
     * @param n The number whose factors we need to calculate.
     * @return all factors of number.
     */
    public static ArrayList<Long> factors(int n) {
        ArrayList<Long> ans = new ArrayList<Long>();
        if (n < 1) return ans;
        ans.add(Long.valueOf(1));
        long d = 0;
        if (n == 1) return ans;
        for (long i = 2; i <= Math.sqrt(n);i++) {
            if (n % i == 0) {
                ans.add(i);
                d = n / i;
                if (d != i) {
                    ans.add(d);
                }
            }
        }

        ans.add(Long.valueOf(n));
        Collections.sort(ans);
        return ans;
    }
}
