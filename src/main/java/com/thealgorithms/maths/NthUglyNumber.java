package com.thealgorithms.maths;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @brief class computing the n-th ugly number (when they are sorted)
 * @details the ugly numbers with base [2, 3, 5] are all numbers of the form 2^a*3^b^5^c,
 *   where the exponents a, b, c are non-negative integers.
 *   Some properties of ugly numbers:
 *     - base [2, 3, 5] ugly numbers are the 5-smooth numbers, cf. https://oeis.org/A051037
 *     - base [2, 3, 5, 7] ugly numbers are 7-smooth numbers, cf. https://oeis.org/A002473
 *     - base [2] ugly numbers are the non-negative powers of 2,
 *     - the base [2, 3, 5] ugly numbers are the same as base [5, 6, 2, 3, 5] ugly numbers
 */
public class NthUglyNumber {
    ArrayList<Long> uglyNumbers = new ArrayList<>(Arrays.asList(1L));
    final int[] baseNumbers;
    HashMap<Integer, Integer> positions = new HashMap<>();

    /**
     * @brief initialized the object allowing to compute ugly numbers with given base
     * @param baseNumbers the given base of ugly numbers
     * @exception IllegalArgumentException baseNumber is empty
     */
    NthUglyNumber(int[] baseNumbers) {
        if (baseNumbers.length == 0) {
            throw new IllegalArgumentException("baseNumbers must be non-empty.");
        }

        this.baseNumbers = baseNumbers;
        for (final var baseNumber : baseNumbers) {
            this.positions.put(baseNumber, 0);
        }
    }

    /**
     * @param n the zero-based-index of the queried ugly number
     * @exception IllegalArgumentException n is negative
     * @return the n-th ugly number (starting from index 0)
     */
    public Long get(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative.");
        }

        while (uglyNumbers.size() <= n) {
            addUglyNumber();
        }

        return uglyNumbers.get(n);
    }

    private void addUglyNumber() {
        uglyNumbers.add(computeMinimalCandidate());
        updatePositions();
    }

    private void updatePositions() {
        final var lastUglyNumber = uglyNumbers.get(uglyNumbers.size() - 1);
        for (final var baseNumber : baseNumbers) {
            if (computeCandidate(baseNumber) == lastUglyNumber) {
                positions.put(baseNumber, positions.get(baseNumber) + 1);
            }
        }
    }

    private long computeCandidate(int candidateBase) {
        return candidateBase * uglyNumbers.get(positions.get(candidateBase));
    }

    private long computeMinimalCandidate() {
        long res = Long.MAX_VALUE;
        for (final var baseNumber : baseNumbers) {
            res = Math.min(res, computeCandidate(baseNumber));
        }
        return res;
    }
}
