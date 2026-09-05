package com.thealgorithms.datastructures.buffers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * A fixed-capacity ring (circular) buffer that additionally keeps an aggregate of every element it
 * currently holds, i.e. a <em>sliding window with aggregation</em>.
 *
 * <p>The window slides automatically: once the buffer is full, adding a new element evicts the
 * oldest one, and the aggregate is kept in sync without rescanning the window.
 *
 * <h2>How the aggregate is maintained</h2>
 *
 * <p>The naive way to keep a windowed sum is "add the new value, subtract the evicted one". That
 * trick only works for <em>invertible</em> operators: there is no way to subtract a value from a
 * minimum. This class instead uses the classic <b>two-stack sliding window aggregation</b>
 * algorithm, which needs nothing but associativity:
 *
 * <ul>
 *   <li>The window is split into a <i>front</i> section {@code [head, flip)} and a <i>back</i>
 *       section {@code [flip, tail)}.</li>
 *   <li>For every slot of the front section the buffer stores the aggregate of that element and all
 *       <em>younger</em> elements of the front section (a suffix aggregate), so the aggregate of the
 *       whole front section is readable from the single slot pointed at by {@code head}.</li>
 *   <li>The back section is summarised by one running aggregate, updated on every insertion.</li>
 *   <li>{@link #aggregate()} combines the two, in that order, so the operator does not need to be
 *       commutative.</li>
 *   <li>When the front section runs out, the back section is turned into a new front section by one
 *       backwards pass. Every element takes part in at most one such pass, which makes the cost
 *       <b>O(1) amortised</b> per element.</li>
 * </ul>
 *
 * <p>A pleasant side effect is numerical stability: because aggregates are recomputed from scratch
 * on every flip, floating-point error cannot accumulate indefinitely the way it does with the
 * add-then-subtract approach.
 *
 * <h2>Complexity</h2>
 *
 * <table border="1">
 *   <caption>Time and space complexity</caption>
 *   <tr><th>Operation</th><th>Complexity</th></tr>
 *   <tr><td>{@link #add(Object)}</td><td>O(1) amortised, O(n) worst case</td></tr>
 *   <tr><td>{@link #removeOldest()}</td><td>O(1) amortised, O(n) worst case</td></tr>
 *   <tr><td>{@link #aggregate()}</td><td>O(1)</td></tr>
 *   <tr><td>{@link #get(int)}, {@link #peekOldest()}, {@link #peekNewest()}</td><td>O(1)</td></tr>
 *   <tr><td>memory</td><td>O(capacity), no allocation after construction</td></tr>
 * </table>
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * // Maximum of the last three readings.
 * SlidingWindowAggregator<Integer, Integer> window = SlidingWindowAggregator.maximum(3, Comparator.naturalOrder());
 * for (int value : new int[] {1, 3, 2, 5, 0}) {
 *     window.add(value);
 *     System.out.println(window.aggregate());
 * }
 * // prints 1, 3, 3, 5, 5
 *
 * // Moving average of the last 100 samples: the window knows both the sum and its own size.
 * SlidingWindowAggregator<Double, Double> sum = SlidingWindowAggregator.sumOfDoubles(100);
 * sum.add(sample);
 * double movingAverage = sum.aggregate() / sum.size();
 * }</pre>
 *
 * <h2>Contract</h2>
 *
 * <ul>
 *   <li>{@code null} elements are rejected, and neither the mapper nor the combiner may return
 *       {@code null}.</li>
 *   <li>The combiner must be <b>associative</b>; it does <b>not</b> have to be commutative and no
 *       identity element is required. Elements are always combined oldest-first.</li>
 *   <li>The mapper is applied exactly once per element, at insertion time.</li>
 *   <li>This class is not thread-safe.</li>
 * </ul>
 *
 * @param <E> type of the elements stored in the window
 * @param <A> type of the aggregate; use {@code A == E} for operators such as min, max or sum
 * @see CircularBuffer
 * @see <a href="https://en.wikipedia.org/wiki/Circular_buffer">Circular buffer</a>
 * @see <a href="https://www.vldb.org/pvldb/vol8/p702-tangwongsan.pdf">K. Tangwongsan, M. Hirzel, S. Schneider, K.-L. Wu, General incremental sliding-window aggregation (VLDB 2015)</a>
 */
public final class SlidingWindowAggregator<E, A> implements Iterable<E> {

    private final Object[] elements;

    /**
     * Parallel ring holding one aggregate per occupied slot. The meaning depends on the section the
     * slot belongs to: inside the front section it is the aggregate of the element and every younger
     * element of that section, inside the back section it is simply the mapped element itself.
     */
    private final Object[] aggregates;

    private final Function<? super E, ? extends A> mapper;
    private final BinaryOperator<A> combiner;
    private final int capacity;

    /** Absolute index of the oldest element; {@code head <= flip <= tail} always holds. */
    private long head;

    /** Absolute index of the boundary between the front and the back section. */
    private long flip;

    /** Absolute index one past the newest element. */
    private long tail;

    /** Aggregate of the back section, or {@code null} when that section is empty. */
    private A backAggregate;

    private int modCount;

    /**
     * Creates an empty window.
     *
     * @param capacity maximum number of elements held at once; adding beyond it evicts the oldest
     * @param mapper turns an element into the value the aggregate is computed over
     * @param combiner associative operator used to merge two aggregates
     * @throws IllegalArgumentException if {@code capacity} is not positive
     * @throws NullPointerException if {@code mapper} or {@code combiner} is {@code null}
     */
    public SlidingWindowAggregator(int capacity, Function<? super E, ? extends A> mapper, BinaryOperator<A> combiner) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Window capacity must be positive, but was " + capacity);
        }
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.aggregates = new Object[capacity];
        this.mapper = Objects.requireNonNull(mapper, "mapper must not be null");
        this.combiner = Objects.requireNonNull(combiner, "combiner must not be null");
    }

    /**
     * Creates a window whose aggregate has the same type as its elements.
     *
     * @param capacity maximum number of elements held at once
     * @param combiner associative operator used to merge two elements
     * @param <E> type of the elements and of the aggregate
     * @return a new window
     */
    public static <E> SlidingWindowAggregator<E, E> of(int capacity, BinaryOperator<E> combiner) {
        return new SlidingWindowAggregator<>(capacity, Function.identity(), combiner);
    }

    /**
     * Creates a window that keeps the sum of the last {@code capacity} values.
     *
     * @param capacity maximum number of elements held at once
     * @return a new window aggregating with {@link Long#sum(long, long)}
     */
    public static SlidingWindowAggregator<Long, Long> sumOfLongs(int capacity) {
        return of(capacity, Long::sum);
    }

    /**
     * Creates a window that keeps the sum of the last {@code capacity} values. Combined with
     * {@link #size()} this is the cheapest way to obtain a moving average.
     *
     * @param capacity maximum number of elements held at once
     * @return a new window aggregating with {@link Double#sum(double, double)}
     */
    public static SlidingWindowAggregator<Double, Double> sumOfDoubles(int capacity) {
        return of(capacity, Double::sum);
    }

    /**
     * Creates a window that keeps the smallest of the last {@code capacity} values.
     *
     * @param capacity maximum number of elements held at once
     * @param comparator ordering used to pick the minimum
     * @param <E> type of the elements
     * @return a new window aggregating with {@link BinaryOperator#minBy(Comparator)}
     */
    public static <E> SlidingWindowAggregator<E, E> minimum(int capacity, Comparator<? super E> comparator) {
        return of(capacity, BinaryOperator.minBy(comparator));
    }

    /**
     * Creates a window that keeps the largest of the last {@code capacity} values.
     *
     * @param capacity maximum number of elements held at once
     * @param comparator ordering used to pick the maximum
     * @param <E> type of the elements
     * @return a new window aggregating with {@link BinaryOperator#maxBy(Comparator)}
     */
    public static <E> SlidingWindowAggregator<E, E> maximum(int capacity, Comparator<? super E> comparator) {
        return of(capacity, BinaryOperator.maxBy(comparator));
    }

    /**
     * Appends an element, evicting the oldest one if the window is already full.
     *
     * @param element the element to append
     * @return the evicted element, or {@code null} if the window was not full
     * @throws NullPointerException if {@code element} is {@code null} or the mapper returns {@code null}
     */
    public E add(E element) {
        Objects.requireNonNull(element, "This window does not accept null elements");
        E evicted = isFull() ? removeOldest() : null;

        int slot = slotOf(tail);
        elements[slot] = element;
        A mapped = requireNonNullResult(mapper.apply(element), "mapper");
        aggregates[slot] = mapped;
        backAggregate = tail == flip ? mapped : requireNonNullResult(combiner.apply(backAggregate, mapped), "combiner");
        tail++;
        modCount++;
        return evicted;
    }

    /**
     * Appends every given element in order. The insertion is not atomic: if an element turns out to be
     * {@code null}, the ones before it are already in the window.
     *
     * @param items the elements to append
     * @throws NullPointerException if {@code items} or any of its elements is {@code null}
     */
    public void addAll(Iterable<? extends E> items) {
        Objects.requireNonNull(items, "items must not be null");
        for (E item : items) {
            add(item);
        }
    }

    /**
     * Removes the oldest element, shrinking the window.
     *
     * @return the removed element
     * @throws NoSuchElementException if the window is empty
     */
    public E removeOldest() {
        if (isEmpty()) {
            throw new NoSuchElementException("The window is empty");
        }
        if (head == flip) {
            rotateBackSectionToFront();
        }

        int slot = slotOf(head);
        E oldest = elementAt(slot);
        elements[slot] = null;
        aggregates[slot] = null;
        head++;
        modCount++;
        return oldest;
    }

    /**
     * Returns the aggregate of every element currently in the window, combined from the oldest to
     * the newest.
     *
     * @return the aggregate of the window
     * @throws NoSuchElementException if the window is empty
     */
    public A aggregate() {
        if (isEmpty()) {
            throw new NoSuchElementException("The aggregate of an empty window is undefined");
        }
        if (head == flip) {
            return backAggregate;
        }
        A frontAggregate = aggregateAt(slotOf(head));
        return tail == flip ? frontAggregate : requireNonNullResult(combiner.apply(frontAggregate, backAggregate), "combiner");
    }

    /**
     * Returns the element at the given position, counting from the oldest one.
     *
     * @param index zero-based position, {@code 0} being the oldest element in the window
     * @return the element at that position
     * @throws IndexOutOfBoundsException if {@code index} is negative or not smaller than {@link #size()}
     */
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for a window of size " + size());
        }
        return elementAt(slotOf(head + index));
    }

    /**
     * Returns the oldest element without removing it.
     *
     * @return the element that would be evicted next
     * @throws NoSuchElementException if the window is empty
     */
    public E peekOldest() {
        if (isEmpty()) {
            throw new NoSuchElementException("The window is empty");
        }
        return elementAt(slotOf(head));
    }

    /**
     * Returns the most recently added element without removing it.
     *
     * @return the newest element
     * @throws NoSuchElementException if the window is empty
     */
    public E peekNewest() {
        if (isEmpty()) {
            throw new NoSuchElementException("The window is empty");
        }
        return elementAt(slotOf(tail - 1));
    }

    /**
     * Returns the number of elements currently in the window.
     *
     * @return the current size, never greater than {@link #capacity()}
     */
    public int size() {
        return (int) (tail - head);
    }

    /**
     * Returns the maximum number of elements the window can hold.
     *
     * @return the capacity given at construction time
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Tells whether the window holds no elements.
     *
     * @return {@code true} if the window is empty
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * Tells whether the next insertion will evict the oldest element.
     *
     * @return {@code true} if the window is saturated
     */
    public boolean isFull() {
        return size() == capacity;
    }

    /**
     * Discards every element, leaving the window as if freshly constructed.
     */
    public void clear() {
        Arrays.fill(elements, null);
        Arrays.fill(aggregates, null);
        head = 0;
        flip = 0;
        tail = 0;
        backAggregate = null;
        modCount++;
    }

    /**
     * Returns a snapshot of the window, ordered from the oldest element to the newest one. The list
     * is detached from the window: later insertions do not affect it.
     *
     * @return a new list holding the current contents of the window
     */
    public List<E> toList() {
        List<E> snapshot = new ArrayList<>(size());
        for (long i = head; i < tail; i++) {
            snapshot.add(elementAt(slotOf(i)));
        }
        return snapshot;
    }

    /**
     * Returns a fail-fast iterator walking the window from the oldest element to the newest one.
     *
     * @return an iterator over the current contents of the window
     */
    @Override
    public Iterator<E> iterator() {
        return new WindowIterator();
    }

    @Override
    public String toString() {
        return toList().toString();
    }

    /**
     * Turns the back section into the front section by walking it backwards and storing, for every
     * slot, the aggregate of that element and all younger ones. Called only when the front section is
     * exhausted, so each element takes part in at most one such pass.
     */
    private void rotateBackSectionToFront() {
        A suffix = null;
        for (long i = tail - 1; i >= head; i--) {
            int slot = slotOf(i);
            A own = aggregateAt(slot);
            suffix = suffix == null ? own : requireNonNullResult(combiner.apply(own, suffix), "combiner");
            aggregates[slot] = suffix;
        }
        flip = tail;
        backAggregate = null;
    }

    private int slotOf(long absoluteIndex) {
        return (int) (absoluteIndex % capacity);
    }

    @SuppressWarnings("unchecked")
    private E elementAt(int slot) {
        return (E) elements[slot];
    }

    @SuppressWarnings("unchecked")
    private A aggregateAt(int slot) {
        return (A) aggregates[slot];
    }

    private static <T> T requireNonNullResult(T value, String producer) {
        return Objects.requireNonNull(value, "The " + producer + " of a SlidingWindowAggregator must not return null");
    }

    private final class WindowIterator implements Iterator<E> {
        private long cursor = head;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor < tail;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException("The iterator has been exhausted");
            }
            return elementAt(slotOf(cursor++));
        }
    }
}
