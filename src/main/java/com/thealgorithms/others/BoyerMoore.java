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

    public static int findmajor(final int[] a) {
        int count = 0;
        int cand = -1;
        for (final var k : a) {
            if (count == 0) {
                cand = k;
                count = 1;
            } else {
                if (k == cand) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        count = 0;
        for (final var j : a) {
            if (j == cand) {
                count++;
            }
        }
        if (count > (a.length / 2)) {
            return cand;
        }
        return Optional.empty();
    }
}
