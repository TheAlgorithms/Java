package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Computes the n-th ugly number (sorted ascending) for a given set of base factors.
 *
 * <p>Ugly numbers are all positive integers expressible as a product of non-negative
 * powers of the given base factors. For example:
 * <ul>
 *   <li>Base [2, 3, 5] produces the 5-smooth numbers: 1, 2, 3, 4, 5, 6, 8, 9, 10, ...
 *       (see <a href="https://oeis.org/A051037">OEIS A051037</a>)</li>
 *   <li>Base [2, 3, 5, 7] produces the 7-smooth numbers
 *       (see <a href="https://oeis.org/A002473">OEIS A002473</a>)</li>
 *   <li>Base [2] produces non-negative powers of 2.</li>
 * </ul>
 *
 * <p>Design notes (SOLID):
 * <ul>
 *   <li><b>SRP</b>: Candidate generation is isolated in {@link BaseFactorCursor};
 *       caching/sequencing lives only in {@link UglyNumberCache};
 *       this class is a thin public facade.</li>
 *   <li><b>OCP</b>: The candidate-selection strategy is expressed through
 *       {@link CandidateSelector}, making it open for extension without modifying
 *       this class.</li>
 *   <li><b>DIP</b>: No dependency on Apache Commons or any concrete library type;
 *       all collaborators are either JDK types or local abstractions.</li>
 * </ul>
 */
public class NthUglyNumber {

    private final UglyNumberCache cache;

    /**
     * Constructs an instance for the given base factors.
     *
     * @param baseFactors non-empty array of positive integers that form the multiplication base
     * @throws IllegalArgumentException if {@code baseFactors} is empty
     */
    public NthUglyNumber(final int[] baseFactors) {
        if (baseFactors == null || baseFactors.length == 0) {
            throw new IllegalArgumentException("baseFactors must be non-empty.");
        }
        List<BaseFactorCursor> cursors = buildCursors(baseFactors);
        this.cache = new UglyNumberCache(cursors, new MinimalCandidateSelector());
    }

    /**
     * Returns the n-th ugly number (zero-based index; index 0 returns 1).
     *
     * @param n zero-based index; must be non-negative
     * @return the n-th ugly number
     * @throws IllegalArgumentException if {@code n} is negative
     */
    public long get(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative.");
        }
        return cache.get(n);
    }

    // -------------------------------------------------------------------------
    // Private factory helper
    // -------------------------------------------------------------------------

    private static List<BaseFactorCursor> buildCursors(final int[] baseFactors) {
        List<BaseFactorCursor> cursors = new ArrayList<>(baseFactors.length);
        for (int factor : baseFactors) {
            cursors.add(new BaseFactorCursor(factor));
        }
        return Collections.unmodifiableList(cursors);
    }

    // =========================================================================
    // SRP helper 1 — tracks one base factor and its current index in the cache
    // =========================================================================

    /**
     * Encapsulates a single base factor and a pointer into the ugly-number sequence.
     * Knows how to compute its next candidate and advance its pointer.
     */
    static final class BaseFactorCursor {

        private final int factor;
        private int index; // points to the ugly number this cursor will multiply next

        BaseFactorCursor(final int factor) {
            this.factor = factor;
            this.index = 0;
        }

        /**
         * Computes the candidate produced by this cursor: factor × uglyNumbers[index].
         *
         * @param uglyNumbers the current list of computed ugly numbers
         * @return the candidate value
         */
        long candidate(final List<Long> uglyNumbers) {
            return (long) factor * uglyNumbers.get(index);
        }

        /**
         * Advances the internal pointer if the supplied value equals this cursor's
         * current candidate, ensuring no duplicate ugly number is produced.
         *
         * @param lastAdded the value most recently appended to the ugly-number sequence
         * @param uglyNumbers the current list of computed ugly numbers
         */
        void advanceIfMatch(final long lastAdded, final List<Long> uglyNumbers) {
            if (candidate(uglyNumbers) == lastAdded) {
                index++;
            }
        }
    }

    // =========================================================================
    // SRP helper 2 — strategy abstraction for picking the next ugly number
    // =========================================================================

    /**
     * Strategy for selecting the next ugly number from a set of cursors.
     * Implement this interface to swap in alternative selection algorithms
     * (e.g. a heap-based selector) without touching {@link UglyNumberCache}.
     */
    interface CandidateSelector {
        /**
         * @param cursors     current cursor state
         * @param uglyNumbers accumulated ugly numbers so far
         * @return the smallest candidate across all cursors
         */
        long selectNext(List<BaseFactorCursor> cursors, List<Long> uglyNumbers);
    }

    // =========================================================================
    // OCP — concrete selector; extend by providing a different CandidateSelector
    // =========================================================================

    /**
     * Default {@link CandidateSelector} that performs a linear scan to find the
     * minimum candidate. O(k) per step, where k is the number of base factors.
     */
    static final class MinimalCandidateSelector implements CandidateSelector {

        @Override
        public long selectNext(final List<BaseFactorCursor> cursors,
                               final List<Long> uglyNumbers) {
            long minimum = Long.MAX_VALUE;
            for (BaseFactorCursor cursor : cursors) {
                minimum = Math.min(minimum, cursor.candidate(uglyNumbers));
            }
            return minimum;
        }
    }

    // =========================================================================
    // SRP helper 3 — owns the cached sequence and the generation loop
    // =========================================================================

    /**
     * Maintains the lazily-grown list of ugly numbers and delegates candidate
     * selection to the injected {@link CandidateSelector}.
     *
     * <p>Responsibilities: caching, growth, and cursor synchronisation only.
     * It does not know how to pick the next value; that belongs to the selector.
     */
    static final class UglyNumberCache {

        private final List<Long> uglyNumbers = new ArrayList<>();
        private final List<BaseFactorCursor> cursors;
        private final CandidateSelector selector;

        UglyNumberCache(final List<BaseFactorCursor> cursors,
                        final CandidateSelector selector) {
            this.cursors = cursors;
            this.selector = selector;
            uglyNumbers.add(1L); // 1 is always the first ugly number
        }

        /**
         * Returns the n-th ugly number, growing the cache as needed.
         */
        long get(final int n) {
            while (uglyNumbers.size() <= n) {
                growByOne();
            }
            return uglyNumbers.get(n);
        }

        private void growByOne() {
            long next = selector.selectNext(cursors, uglyNumbers);
            uglyNumbers.add(next);
            advanceMatchingCursors(next);
        }

        /**
         * Advances every cursor whose candidate equals the value just appended,
         * which prevents duplicates in the sequence.
         */
        private void advanceMatchingCursors(final long justAdded) {
            for (BaseFactorCursor cursor : cursors) {
                cursor.advanceIfMatch(justAdded, uglyNumbers);
            }
        }
    }
}