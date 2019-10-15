package com.dataStructures;

import java.util.*;

/**
 * This class implements a queue-based data structure that maintain an underlying deque of extremes among elements stored in an underlying queue using a comparator.
 * The less-than comparison of the comparator is used for the extremes.
 * @param <E> is the type of element contained in the queue.
 * @param <TComparator> is the comparator class.
 */
public class CompareQueue<E extends Comparable<E>> {
    private Queue<E> _queue;
    private Deque<E> _deque;
    private Comparator<E> _comparator;

    public CompareQueue() {
        this._queue = new LinkedList<E>();
        this._deque = new ArrayDeque<E>();
    }

    /**
     * Constructs a compare-queue with a given comparator.
     * @param comparator is the comparator used for comparing elements. If null, the natural ordering is used.
     */
    public CompareQueue(Comparator<E> comparator) {
        this();
        this._comparator = comparator;
    }

    /**
     * Adds an element into the underlying queue.
     * The underlying deque is then updated using the provided comparator.
     * @param e is the element to be added.
     * @return True if the element is added successfully.
     */
    public boolean add(E e) {
        if (!this.isEmpty())
            while (!this._deque.isEmpty() && (this._comparator == null ? Comparator.<E>naturalOrder().compare(e, this._deque.getLast()) : this._comparator.compare(e, this._deque.getLast())) < 0)
                this._deque.removeLast();
        return this._queue.add(e) && this._deque.add(e);
    }

    /**
     * Adds all elements in a collection into the underlying queue using {@link CompareQueue#add(Object) add(E)}.
     * @param c is the collection of the elements to be added.
     * @return True if all the elements are added successfully.
     */
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) if (!this.add(e)) return false;
        return true;
    }

    /**
     * Adds an element into the underlying queue.
     * The underlying deque is then updated using the provided comparator.
     * @param e is the element to be added.
     * @return True if the element is added successfully.
     */
    public boolean offer(E e) {
        if (!this.isEmpty())
            while (!this._deque.isEmpty() && (this._comparator == null ? Comparator.<E>naturalOrder().compare(e, this._deque.getLast()) : this._comparator.compare(e, this._deque.getLast())) < 0)
                this._deque.removeLast();
        return this._queue.offer(e) && this._deque.offer(e);
    }

    /**
     * Retrieves and remove the front element in the underlying queue.
     * Time complexity: Constant.
     */
    public E remove() {
        if (this._queue.element() == this._deque.element()) this._deque.remove();
        return this._queue.remove();
    }

    /**
     * Retrieves and remove the front element in the underlying queue.
     * Time complexity: Constant.
     */
    public E poll() {
        if (!this.isEmpty() && this._queue.element() == this._deque.element()) this._deque.poll();
        return this._queue.poll();
    }

    /**
     * Retrieves, but does not remove, the extreme among all elements stored in the underlying queue.
     * @return An element: The extreme among all elements stored in the underlying queue.
     */
    public E peek() {
        return this._deque.peek();
    }

    /**
     * Retrieves, but does not remove, the extreme among all elements stored in the underlying queue.
     * @return An element: The extreme among all elements stored in the underlying queue.
     */
    public E element() {
        return this._deque.element();
    }

    /**
     * @return True if the underlying queue is empty; false otherwise.
     */
    public boolean isEmpty() {
        return this._queue.isEmpty();
    }
}