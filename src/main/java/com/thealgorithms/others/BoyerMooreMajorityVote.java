package com.thealgorithms.others;

import java.util.Optional;

/**
 * Boyer-Moore Majority Vote Algorithm (renamed for clarity).
 *
 * <p>Resolves naming conflict: "BoyerMoore" in others referred to the majority vote algorithm,
 * while searches.BoyerMoore is the string-search algorithm. This class is the majority-vote implementation.
 *
 * <p>Finds the majority element in an array (element that appears more than n/2 times).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm">Boyer–Moore majority vote algorithm</a>
 */
public final class BoyerMooreMajorityVote {
    private BoyerMooreMajorityVote() {
    }

    /**
     * Finds the majority element in the given array if it exists.
     *
     * @param array the input array
     * @return an Optional containing the majority element if it exists, otherwise an empty Optional
     */
    public static Optional<Integer> findMajorityElement(int[] array) {
        if (array == null || array.length == 0) {
            return Optional.empty();
        }

        int candidate = findCandidate(array);
        int count = countOccurrences(candidate, array);

        if (isMajority(count, array.length)) {
            return Optional.of(candidate);
        }
        return Optional.empty();
    }

    private static int findCandidate(final int[] array) {
        int count = 0;
        int candidate = -1;
        for (int value : array) {
            if (count == 0) {
                candidate = value;
            }
            count += (value == candidate) ? 1 : -1;
        }
        return candidate;
    }

    private static int countOccurrences(final int candidate, final int[] array) {
        int count = 0;
        for (int value : array) {
            if (value == candidate) {
                count++;
            }
        }
        return count;
    }

    private static boolean isMajority(int count, int totalCount) {
        return 2 * count > totalCount;
    }
}
