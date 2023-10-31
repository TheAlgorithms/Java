/* this Code is the illustration of Boyer moore's voting algorithm to
find the majority element is an array that appears more than n/2 times in an array
where "n" is the length of the array.
For more information on the algorithm refer
https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
 */
package com.thealgorithms.others;
import java.util.Optional;

public final class BoyerMoore {
    private BoyerMoore() {
    }

    public static Optional<Integer> findMajor(final int[] a) {
        final var candidate = findCandidate(a);
        final var count = countOccurrences(a, candidate);
        if (count > (a.length / 2)) {
            return Optional.of(candidate);
        }
        return Optional.empty();
    }

    private static int findCandidate(final int[] a) {
        int count = 0;
        int candidate = -1;
        for (final int k : a) {
            if (count == 0) {
                candidate = k;
                count = 1;
            } else {
                if (k == candidate) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return candidate;
    }

    private static int countOccurrences(final int[] a, final int candidate) {
        int count = 0;
        for (final int j : a) {
            if (j == candidate) {
                count++;
            }
        }
        return count;
    }
}
