package com.thealgorithms.datastructures.heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * An addressable (indexed) min-priority queue with O(log n) updates.
 *
 * <p>Key features:
 * <ul>
 *   <li>Each element E is tracked by a handle (its current heap index) via a map,
 *       enabling O(log n) {@code remove(e)} and O(log n) key updates
 *       ({@code changeKey/decreaseKey/increaseKey}).</li>
 *   <li>The queue order is determined by the provided {@link Comparator}. If the
 *       comparator is {@code null}, elements must implement {@link Comparable}
 *       (same contract as {@link java.util.PriorityQueue}).</li>
 *   <li>By default this implementation uses {@link IdentityHashMap} for the index
 *       mapping to avoid issues with duplicate-equals elements or mutable equals/hashCode.
 *       If you need value-based equality, switch to {@code HashMap} and read the caveats
 *       in the class-level Javadoc carefully.</li>
 * </ul>
 *
 * <h2>IMPORTANT contracts</h2>
 * <ul>
 *   <li><b>Do not mutate comparator-relevant fields of an element directly</b> while it is
 *       inside the queue. Always use {@code changeKey}/{@code decreaseKey}/{@code increaseKey}
 *       so the heap can be restored accordingly.</li>
 *   <li>If you replace {@link IdentityHashMap} with {@link HashMap}, you must ensure:
 *       (a) no two distinct elements are {@code equals()}-equal at the same time in the queue, and
 *       (b) {@code equals/hashCode} of elements remain stable while enqueued.</li>
 *   <li>{@code peek()} returns {@code null} when empty (matching {@link java.util.PriorityQueue}).</li>
 *   <li>Not thread-safe.</li>
 * </ul>
 *
 * <p>Complexities:
 * {@code offer, poll, remove(e), changeKey, decreaseKey, increaseKey} are O(log n);
 * {@code peek, isEmpty, size, contains} are O(1).
 */
public class IndexedPriorityQueue<E> {

    /** Binary heap storage (min-heap). */
    private Object[] heap;

    /** Number of elements in the heap. */
    private int size;

    /** Comparator used for ordering; if null, elements must be Comparable. */
    private final Comparator<? super E> cmp;

    /**
     * Index map: element -> current heap index.
     * <p>We use IdentityHashMap by default to:
     * <ul>
     *   <li>allow duplicate-equals elements;</li>
     *   <li>avoid corruption when equals/hashCode are mutable or not ID-based.</li>
     * </ul>
     * If you prefer value-based semantics, replace with HashMap<E,Integer> and
     * respect the warnings in the class Javadoc.
     */
    private final IdentityHashMap<E, Integer> index;

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    public IndexedPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public IndexedPriorityQueue(Comparator<? super E> cmp) {
        this(DEFAULT_INITIAL_CAPACITY, cmp);
    }

    public IndexedPriorityQueue(int initialCapacity, Comparator<? super E> cmp) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("initialCapacity < 1");
        }
        this.heap = new Object[initialCapacity];
        this.cmp = cmp;
        this.index = new IdentityHashMap<>();
    }

    /** Returns current number of elements. */
    public int size() {
        return size;
    }

    /** Returns {@code true} if empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the minimum element without removing it, or {@code null} if empty.
     * Matches {@link java.util.PriorityQueue#peek()} behavior.
     */
    @SuppressWarnings("unchecked")
    public E peek() {
        return size == 0 ? null : (E) heap[0];
    }

    /**
     * Inserts the specified element (O(log n)).
     * @throws NullPointerException if {@code e} is null
     * @throws ClassCastException if {@code cmp == null} and {@code e} is not Comparable,
     *                            or if incompatible with other elements
     */
    public boolean offer(E e) {
        Objects.requireNonNull(e, "element is null");
        if (size >= heap.length) {
            grow(size + 1);
        }
        // Insert at the end and bubble up. siftUp will maintain 'index' for all touched nodes.
        siftUp(size, e);
        size++;
        return true;
    }

    /**
     * Removes and returns the minimum element (O(log n)), or {@code null} if empty.
     */
    @SuppressWarnings("unchecked")
    public E poll() {
        if (size == 0) {
            return null;
        }
        E min = (E) heap[0];
        removeAt(0); // updates map and heap structure
        return min;
    }

    /**
     * Removes one occurrence of the specified element e (O(log n)) if present.
     * Uses the index map for O(1) lookup.
     */
    public boolean remove(Object o) {
        Integer i = index.get(o);
        if (i == null) {
            return false;
        }
        removeAt(i);
        return true;
    }

    /** O(1): returns whether the queue currently contains the given element reference. */
    public boolean contains(Object o) {
        return index.containsKey(o);
    }

    /** Clears the heap and the index map. */
    public void clear() {
        Arrays.fill(heap, 0, size, null);
        index.clear();
        size = 0;
    }

    // ------------------------------------------------------------------------------------
    // Key update API
    // ------------------------------------------------------------------------------------

    /**
     * Changes comparator-relevant fields of {@code e} via the provided {@code mutator},
     * then restores the heap in O(log n) by bubbling in the correct direction.
     *
     * <p><b>IMPORTANT:</b> The mutator must not change {@code equals/hashCode} of {@code e}
     * if you migrate this implementation to value-based indexing (HashMap).
     *
     * @throws IllegalArgumentException if {@code e} is not in the queue
     */
    public void changeKey(E e, Consumer<E> mutator) {
        Integer i = index.get(e);
        if (i == null) {
            throw new IllegalArgumentException("Element not in queue");
        }
        // Mutate fields used by comparator (do NOT mutate equality/hash if using value-based map)
        mutator.accept(e);
        // Try bubbling up; if no movement occurred, bubble down.
        if (!siftUp(i)) {
            siftDown(i);
        }
    }

    /**
     * Faster variant if the new key is strictly smaller (higher priority).
     * Performs a single sift-up (O(log n)).
     */
    public void decreaseKey(E e, Consumer<E> mutator) {
        Integer i = index.get(e);
        if (i == null) {
            throw new IllegalArgumentException("Element not in queue");
        }
        mutator.accept(e);
        siftUp(i);
    }

    /**
     * Faster variant if the new key is strictly larger (lower priority).
     * Performs a single sift-down (O(log n)).
     */
    public void increaseKey(E e, Consumer<E> mutator) {
        Integer i = index.get(e);
        if (i == null) {
            throw new IllegalArgumentException("Element not in queue");
        }
        mutator.accept(e);
        siftDown(i);
    }

    // ------------------------------------------------------------------------------------
    // Internal utilities
    // ------------------------------------------------------------------------------------

    /** Grows the internal array to accommodate at least {@code minCapacity}. */
    private void grow(int minCapacity) {
        int old = heap.length;
        int pref = (old < 64) ? old + 2 : old + (old >> 1); // +2 if small, else +50%
        int newCap = Math.max(minCapacity, pref);
        heap = Arrays.copyOf(heap, newCap);
    }

    @SuppressWarnings("unchecked")
    private int compare(E a, E b) {
        if (cmp != null) {
            return cmp.compare(a, b);
        }
        return ((Comparable<? super E>) a).compareTo(b);
    }

    /**
     * Inserts item {@code x} at position {@code k}, bubbling up while maintaining the heap.
     * Also maintains the index map for all moved elements.
     */
    @SuppressWarnings("unchecked")
    private void siftUp(int k, E x) {
        while (k > 0) {
            int p = (k - 1) >>> 1;
            E e = (E) heap[p];
            if (compare(x, e) >= 0) {
                break;
            }
            heap[k] = e;
            index.put(e, k);
            k = p;
        }
        heap[k] = x;
        index.put(x, k);
    }

    /**
     * Attempts to bubble up the element currently at {@code k}.
     * @return true if it moved; false otherwise.
     */
    @SuppressWarnings("unchecked")
    private boolean siftUp(int k) {
        int orig = k;
        E x = (E) heap[k];
        while (k > 0) {
            int p = (k - 1) >>> 1;
            E e = (E) heap[p];
            if (compare(x, e) >= 0) {
                break;
            }
            heap[k] = e;
            index.put(e, k);
            k = p;
        }
        if (k != orig) {
            heap[k] = x;
            index.put(x, k);
            return true;
        }
        return false;
    }

    /** Bubbles down the element currently at {@code k}. */
    @SuppressWarnings("unchecked")
    private void siftDown(int k) {
        int n = size;
        E x = (E) heap[k];
        int half = n >>> 1; // loop while k has at least one child
        while (k < half) {
            int child = (k << 1) + 1; // assume left is smaller
            E c = (E) heap[child];
            int r = child + 1;
            if (r < n && compare(c, (E) heap[r]) > 0) {
                child = r;
                c = (E) heap[child];
            }
            if (compare(x, c) <= 0) {
                break;
            }
            heap[k] = c;
            index.put(c, k);
            k = child;
        }
        heap[k] = x;
        index.put(x, k);
    }

    /**
     * Removes the element at heap index {@code i}, restoring the heap afterwards.
     * <p>Returns nothing; the standard {@code PriorityQueue} returns a displaced
     * element in a rare case to help its iterator. We don't need that here, so
     * we keep the API simple.
     */
    @SuppressWarnings("unchecked")
    private void removeAt(int i) {
        int n = --size; // last index after removal
        E moved = (E) heap[n];
        E removed = (E) heap[i];
        heap[n] = null; // help GC
        index.remove(removed); // drop mapping for removed element

        if (i == n) {
            return; // removed last element; done
        }

        heap[i] = moved;
        index.put(moved, i);

        // Try sift-up first (cheap if key decreased); if no movement, sift-down.
        if (!siftUp(i)) {
            siftDown(i);
        }
    }
}
