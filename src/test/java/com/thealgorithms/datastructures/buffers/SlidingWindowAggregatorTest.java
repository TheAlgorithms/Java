package com.thealgorithms.datastructures.buffers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SlidingWindowAggregatorTest {

    private static final Comparator<Integer> NATURAL = Comparator.naturalOrder();

    private static SlidingWindowAggregator<Integer, Integer> sumWindow(int capacity) {
        return SlidingWindowAggregator.of(capacity, Integer::sum);
    }

    @Nested
    @DisplayName("construction")
    class Construction {

        @ParameterizedTest
        @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
        void rejectsNonPositiveCapacity(int capacity) {
            assertThrows(IllegalArgumentException.class, () -> sumWindow(capacity));
        }

        @Test
        void rejectsNullMapper() {
            assertThrows(NullPointerException.class, () -> new SlidingWindowAggregator<Integer, Integer>(4, null, Integer::sum));
        }

        @Test
        void rejectsNullCombiner() {
            assertThrows(NullPointerException.class, () -> new SlidingWindowAggregator<Integer, Integer>(4, Function.identity(), null));
        }

        @Test
        void startsEmpty() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(3);
            assertTrue(window.isEmpty());
            assertFalse(window.isFull());
            assertEquals(0, window.size());
            assertEquals(3, window.capacity());
            assertTrue(window.toList().isEmpty());
            assertEquals("[]", window.toString());
        }
    }

    @Nested
    @DisplayName("empty window")
    class EmptyWindow {

        private final SlidingWindowAggregator<Integer, Integer> window = sumWindow(3);

        @Test
        void aggregateThrows() {
            assertThrows(NoSuchElementException.class, window::aggregate);
        }

        @Test
        void peeksThrow() {
            assertThrows(NoSuchElementException.class, window::peekOldest);
            assertThrows(NoSuchElementException.class, window::peekNewest);
        }

        @Test
        void removeThrows() {
            assertThrows(NoSuchElementException.class, window::removeOldest);
        }

        @Test
        void getThrows() {
            assertThrows(IndexOutOfBoundsException.class, () -> window.get(0));
        }

        @Test
        void iteratorIsExhausted() {
            Iterator<Integer> iterator = window.iterator();
            assertFalse(iterator.hasNext());
            assertThrows(NoSuchElementException.class, iterator::next);
        }
    }

    @Nested
    @DisplayName("aggregation")
    class Aggregation {

        @Test
        void aggregatesWhileFilling() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(4);
            window.add(1);
            assertEquals(1, window.aggregate());
            window.add(2);
            assertEquals(3, window.aggregate());
            window.add(3);
            assertEquals(6, window.aggregate());
            window.add(4);
            assertEquals(10, window.aggregate());
            assertTrue(window.isFull());
        }

        @Test
        void slidesOnceFull() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(3);
            window.addAll(List.of(1, 2, 3));
            assertEquals(6, window.aggregate());

            assertEquals(1, window.add(4));
            assertEquals(9, window.aggregate());
            assertEquals(List.of(2, 3, 4), window.toList());

            assertEquals(2, window.add(5));
            assertEquals(12, window.aggregate());
        }

        @Test
        void addReturnsNullWhileTheWindowIsNotFull() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(2);
            Assertions.assertNull(window.add(1));
            Assertions.assertNull(window.add(2));
            assertEquals(1, window.add(3));
        }

        @Test
        @DisplayName("keeps the maximum of the last three readings")
        void tracksMaximum() {
            SlidingWindowAggregator<Integer, Integer> window = SlidingWindowAggregator.maximum(3, NATURAL);
            List<Integer> observed = new ArrayList<>();
            for (int value : new int[] {1, 3, 2, 5, 0, 0, 0}) {
                window.add(value);
                observed.add(window.aggregate());
            }
            assertEquals(List.of(1, 3, 3, 5, 5, 5, 0), observed);
        }

        @Test
        void tracksMinimum() {
            SlidingWindowAggregator<Integer, Integer> window = SlidingWindowAggregator.minimum(3, NATURAL);
            window.addAll(List.of(5, 4, 6, 7, 8));
            assertEquals(6, window.aggregate());
        }

        @Test
        @DisplayName("combines oldest first, so non-commutative operators work")
        void preservesOrderForNonCommutativeOperators() {
            SlidingWindowAggregator<String, String> window = SlidingWindowAggregator.of(3, (a, b) -> a + b);
            window.addAll(List.of("a", "b", "c", "d", "e"));
            assertEquals("cde", window.aggregate());
            window.removeOldest();
            assertEquals("de", window.aggregate());
        }

        @Test
        void supportsAggregatesOfADifferentType() {
            SlidingWindowAggregator<String, Integer> lengths = new SlidingWindowAggregator<>(3, String::length, Integer::sum);
            lengths.addAll(List.of("a", "bb", "ccc", "dddd"));
            assertEquals(2 + 3 + 4, lengths.aggregate());
        }

        @Test
        void movingAverageIsSumOverSize() {
            SlidingWindowAggregator<Double, Double> window = SlidingWindowAggregator.sumOfDoubles(3);
            window.addAll(List.of(1.0, 2.0, 3.0, 6.0));
            assertEquals(11.0 / 3.0, window.aggregate() / window.size(), 1e-12);
        }

        @Test
        void sumOfLongsFactoryWorks() {
            SlidingWindowAggregator<Long, Long> window = SlidingWindowAggregator.sumOfLongs(2);
            window.addAll(List.of(1L, 2L, 3L));
            assertEquals(5L, window.aggregate());
        }

        @Test
        @DisplayName("the mapper runs exactly once per element")
        void mapperIsCalledOncePerElement() {
            AtomicInteger calls = new AtomicInteger();
            SlidingWindowAggregator<Integer, Integer> window = new SlidingWindowAggregator<>(3, value -> {
                calls.incrementAndGet();
                return value;
            }, Integer::sum);
            for (int i = 0; i < 100; i++) {
                window.add(i);
                window.aggregate();
            }
            assertEquals(100, calls.get());
        }
    }

    @Nested
    @DisplayName("null handling")
    class NullHandling {

        @Test
        void rejectsNullElements() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(2);
            assertThrows(NullPointerException.class, () -> window.add(null));
        }

        @Test
        @DisplayName("a null hidden inside a collection is rejected, and the elements before it stay")
        void rejectsNullElementsInsideACollection() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(2);
            List<Integer> items = new ArrayList<>();
            items.add(1);
            items.add(null);

            assertThrows(NullPointerException.class, () -> window.addAll(items));
            assertEquals(1, window.size());
            assertEquals(1, window.aggregate());
        }

        @Test
        void rejectsMappersReturningNull() {
            SlidingWindowAggregator<Integer, Integer> window = new SlidingWindowAggregator<>(2, value -> null, Integer::sum);
            assertThrows(NullPointerException.class, () -> window.add(1));
        }

        @Test
        void rejectsCombinersReturningNull() {
            SlidingWindowAggregator<Integer, Integer> window = SlidingWindowAggregator.of(2, (a, b) -> null);
            window.add(1);
            assertThrows(NullPointerException.class, () -> window.add(2));
        }
    }

    @Nested
    @DisplayName("access and iteration")
    class Access {

        @Test
        void indexedAccessStartsAtTheOldestElement() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(3);
            window.addAll(List.of(10, 20, 30, 40));
            assertEquals(20, window.get(0));
            assertEquals(30, window.get(1));
            assertEquals(40, window.get(2));
            assertEquals(20, window.peekOldest());
            assertEquals(40, window.peekNewest());
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 3, 100})
        void rejectsOutOfBoundsIndices(int index) {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(5);
            window.addAll(List.of(1, 2, 3));
            assertThrows(IndexOutOfBoundsException.class, () -> window.get(index));
        }

        @Test
        void iteratesFromOldestToNewest() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(3);
            window.addAll(List.of(1, 2, 3, 4, 5));
            List<Integer> seen = new ArrayList<>();
            for (int value : window) {
                seen.add(value);
            }
            assertEquals(List.of(3, 4, 5), seen);
            assertEquals("[3, 4, 5]", window.toString());
        }

        @Test
        void iteratorIsFailFast() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(4);
            window.addAll(List.of(1, 2, 3));
            Iterator<Integer> iterator = window.iterator();
            assertEquals(1, iterator.next());
            window.add(4);
            assertThrows(ConcurrentModificationException.class, iterator::next);
        }

        @Test
        void toListIsASnapshot() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(3);
            window.addAll(List.of(1, 2, 3));
            List<Integer> snapshot = window.toList();
            window.add(4);
            assertEquals(List.of(1, 2, 3), snapshot);
        }
    }

    @Nested
    @DisplayName("removal and reuse")
    class Removal {

        @Test
        void removeOldestShrinksTheWindow() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(4);
            window.addAll(List.of(1, 2, 3, 4));
            assertEquals(1, window.removeOldest());
            assertEquals(9, window.aggregate());
            assertEquals(3, window.size());
            assertFalse(window.isFull());

            assertEquals(2, window.removeOldest());
            assertEquals(3, window.removeOldest());
            assertEquals(4, window.aggregate());
            assertEquals(4, window.removeOldest());
            assertTrue(window.isEmpty());
            assertThrows(NoSuchElementException.class, window::aggregate);
        }

        @Test
        void clearRestoresTheInitialState() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(3);
            window.addAll(List.of(1, 2, 3, 4, 5));
            window.clear();
            assertTrue(window.isEmpty());
            assertEquals(0, window.size());
            assertThrows(NoSuchElementException.class, window::aggregate);

            window.addAll(List.of(7, 8));
            assertEquals(15, window.aggregate());
            assertEquals(List.of(7, 8), window.toList());
        }

        @Test
        @DisplayName("a capacity of one keeps only the latest element")
        void capacityOfOne() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(1);
            window.add(1);
            assertEquals(1, window.aggregate());
            assertEquals(1, window.add(2));
            assertEquals(2, window.aggregate());
            assertEquals(2, window.add(3));
            assertEquals(3, window.aggregate());
            assertEquals(1, window.size());
        }

        @Test
        @DisplayName("survives far more insertions than its capacity")
        void survivesManyRotations() {
            SlidingWindowAggregator<Integer, Integer> window = sumWindow(3);
            for (int i = 1; i <= 100_000; i++) {
                window.add(i);
            }
            assertEquals(99_998 + 99_999 + 100_000, window.aggregate());
            assertEquals(3, window.size());
        }
    }

    @Nested
    @DisplayName("agreement with a brute force reference")
    class BruteForceAgreement {

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 5, 8, 13})
        void matchesBruteForceSum(int capacity) {
            assertMatchesBruteForce(capacity, Integer::sum);
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 5, 8, 13})
        void matchesBruteForceMaximum(int capacity) {
            assertMatchesBruteForce(capacity, BinaryOperator.maxBy(NATURAL));
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 5, 8, 13})
        void matchesBruteForceMinimum(int capacity) {
            assertMatchesBruteForce(capacity, BinaryOperator.minBy(NATURAL));
        }

        private void assertMatchesBruteForce(int capacity, BinaryOperator<Integer> combiner) {
            SlidingWindowAggregator<Integer, Integer> window = SlidingWindowAggregator.of(capacity, combiner);
            Deque<Integer> reference = new ArrayDeque<>();
            Random random = new Random(20240517L + capacity);

            for (int step = 0; step < 2_000; step++) {
                int value = random.nextInt(2_000) - 1_000;
                window.add(value);
                reference.addLast(value);
                if (reference.size() > capacity) {
                    reference.removeFirst();
                }
                assertEquals(fold(reference, combiner), window.aggregate(), "after step " + step);
            }
        }

        @Test
        @DisplayName("interleaved insertions and removals stay in sync with the reference")
        void matchesBruteForceUnderInterleavedOperations() {
            int capacity = 7;
            SlidingWindowAggregator<Integer, Integer> window = SlidingWindowAggregator.of(capacity, Integer::sum);
            Deque<Integer> reference = new ArrayDeque<>();
            Random random = new Random(987654321L);

            for (int step = 0; step < 20_000; step++) {
                if (reference.isEmpty() || random.nextInt(3) > 0) {
                    int value = random.nextInt(100);
                    window.add(value);
                    reference.addLast(value);
                    if (reference.size() > capacity) {
                        reference.removeFirst();
                    }
                } else {
                    assertEquals(reference.removeFirst(), window.removeOldest());
                }

                assertEquals(reference.size(), window.size());
                assertEquals(new ArrayList<>(reference), window.toList());
                if (reference.isEmpty()) {
                    assertThrows(NoSuchElementException.class, window::aggregate);
                } else {
                    assertEquals(fold(reference, Integer::sum), window.aggregate(), "after step " + step);
                }
            }
        }

        private Integer fold(Iterable<Integer> values, BinaryOperator<Integer> combiner) {
            Integer accumulator = null;
            for (Integer value : values) {
                accumulator = accumulator == null ? value : combiner.apply(accumulator, value);
            }
            return accumulator;
        }
    }

    @Test
    @DisplayName("the documented example from the class javadoc")
    void documentedExample() {
        SlidingWindowAggregator<Integer, Integer> window = SlidingWindowAggregator.maximum(3, Comparator.naturalOrder());
        List<Integer> printed = new ArrayList<>();
        for (int value : new int[] {1, 3, 2, 5, 0}) {
            window.add(value);
            printed.add(window.aggregate());
        }
        assertEquals(List.of(1, 3, 3, 5, 5), printed);
        Assertions.assertDoesNotThrow(window::clear);
    }
}
