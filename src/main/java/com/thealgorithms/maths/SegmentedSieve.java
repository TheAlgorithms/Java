package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegmentedSieve {

    // fillPrime function fills primes from 2 to sqrt of high in chprime ArrayList    
    public static void fillPrime(ArrayList<Integer> chprime, int high) {
        boolean[] ck = new boolean[high + 1];
        Arrays.fill(ck, true);
        ck[1] = false;
        ck[0] = false;

        for (int i = 2; (i * i) <= high; i++) {
            if (ck[i]) {
                for (int j = i * i; j <= Math.sqrt(high); j = j + i) {
                    ck[j] = false;
                }
            }
        }
        for (int i = 2; i * i <= high; i++) {
            if (ck[i]) {
                chprime.add(i);
            }
        }
    }

    // in segmented sieve we check for prime from range [low, high]
    public static List<Integer> segmentedSieve(int low, int high) {
        if (low > high || low < 2) {
            throw new IllegalArgumentException("Invalid range");
        }

        ArrayList<Integer> chprime = new ArrayList<>();
        fillPrime(chprime, high);

        boolean[] prime = new boolean[high - low + 1];
        Arrays.fill(prime, true);

        for (int i : chprime) {
            int lower = (low / i);
            if (lower <= 1) {
                lower = i + i;
            } else if (low % i != 0) {
                lower = (lower * i) + i;
            } else {
                lower = (lower * i);
            }
            for (int j = lower; j <= high; j = j + i) {
                prime[j - low] = false;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = low; i <= high; i++) {
            if (prime[i - low]) {
                result.add(i);
            }
        }

        return result;
    }
}
