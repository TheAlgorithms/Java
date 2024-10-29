package com.thealgorithms.datastructures.heaps;

import java.util.ArrayList;
import java.util.List;

/**
 * A Max Heap implementation where each node's key is higher than or equal to its children's keys.
 * This data structure provides O(log n) time complexity for insertion and deletion operations,
 * and O(1) for retrieving the maximum element.
 *
 * Properties:
 * 1. Complete Binary Tree
 * 2. Parent node's key ≥ Children nodes' keys
 * 3. Root contains the maximum element
 *
 * Example usage:
 * <pre>
 * List<HeapElement> elements = Arrays.asList(
 *     new HeapElement(5, "Five"),
 *     new HeapElement(2, "Two")
 * );
 * MaxHeap heap = new MaxHeap(elements);
 * heap.insertElement(new HeapElement(7, "Seven"));
 * HeapElement max = heap.getElement(); // Returns and removes the maximum element
 * </pre>
 *
 * @author Nicolas Renard
 */
public class MaxHeap implements Heap {

    /** The internal list that stores heap elements */
    private final List<HeapElement> maxHeap;

    /**
     * Constructs a new MaxHeap from a list of elements.
     * Null elements in the input list are ignored.
     *
     * @param listElements List of HeapElement objects to initialize the heap
     * @throws IllegalArgumentException if the input list is null
     */
    public MaxHeap(List<HeapElement> listElements) {
        if (listElements == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }

        maxHeap = new ArrayList<>();

        // Safe initialization: directly add non-null elements first
        for (HeapElement heapElement : listElements) {
            if (heapElement != null) {
                maxHeap.add(heapElement);
            }
        }

        // Heapify the array bottom-up
        for (int i = maxHeap.size() / 2; i >= 0; i--) {
            heapifyDown(i + 1); // +1 because heapifyDown expects 1-based index
        }
    }

    /**
     * Maintains heap properties by moving an element down the heap.
     * Similar to toggleDown but used specifically during initialization.
     *
     * @param elementIndex 1-based index of the element to heapify
     */
    private void heapifyDown(int elementIndex) {
        int largest = elementIndex - 1;
        int leftChild = 2 * elementIndex - 1;
        int rightChild = 2 * elementIndex;

        if (leftChild < maxHeap.size() && maxHeap.get(leftChild).getKey() > maxHeap.get(largest).getKey()) {
            largest = leftChild;
        }

        if (rightChild < maxHeap.size() && maxHeap.get(rightChild).getKey() > maxHeap.get(largest).getKey()) {
            largest = rightChild;
        }

        if (largest != elementIndex - 1) {
            HeapElement swap = maxHeap.get(elementIndex - 1);
            maxHeap.set(elementIndex - 1, maxHeap.get(largest));
            maxHeap.set(largest, swap);

            heapifyDown(largest + 1);
        }
    }

    /**
     * Retrieves the element at the specified index without removing it.
     * Note: The index is 1-based for consistency with heap operations.
     *
     * @param elementIndex 1-based index of the element to retrieve
     * @return HeapElement at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public HeapElement getElement(int elementIndex) {
        if ((elementIndex <= 0) || (elementIndex > maxHeap.size())) {
            throw new IndexOutOfBoundsException("Index " + elementIndex + " is out of heap range [1, " + maxHeap.size() + "]");
        }
        return maxHeap.get(elementIndex - 1);
    }

    /**
     * Retrieves the key value of an element at the specified index.
     *
     * @param elementIndex 1-based index of the element
     * @return double value representing the key
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    private double getElementKey(int elementIndex) {
        if ((elementIndex <= 0) || (elementIndex > maxHeap.size())) {
            throw new IndexOutOfBoundsException("Index " + elementIndex + " is out of heap range [1, " + maxHeap.size() + "]");
        }
        return maxHeap.get(elementIndex - 1).getKey();
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param index1 1-based index of first element
     * @param index2 1-based index of second element
     */
    private void swap(int index1, int index2) {
        HeapElement temporaryElement = maxHeap.get(index1 - 1);
        maxHeap.set(index1 - 1, maxHeap.get(index2 - 1));
        maxHeap.set(index2 - 1, temporaryElement);
    }

    /**
     * Moves an element up the heap until heap properties are satisfied.
     * This operation is called after insertion to maintain heap properties.
     *
     * @param elementIndex 1-based index of the element to move up
     */
    private void toggleUp(int elementIndex) {
        double key = maxHeap.get(elementIndex - 1).getKey();
        while (elementIndex > 1 && getElementKey((int) Math.floor(elementIndex / 2.0)) < key) {
            swap(elementIndex, (int) Math.floor(elementIndex / 2.0));
            elementIndex = (int) Math.floor(elementIndex / 2.0);
        }
    }

    /**
     * Moves an element down the heap until heap properties are satisfied.
     * This operation is called after deletion to maintain heap properties.
     *
     * @param elementIndex 1-based index of the element to move down
     */
    private void toggleDown(int elementIndex) {
        double key = maxHeap.get(elementIndex - 1).getKey();
        boolean wrongOrder = (2 * elementIndex <= maxHeap.size() && key < getElementKey(elementIndex * 2)) || (2 * elementIndex + 1 <= maxHeap.size() && key < getElementKey(elementIndex * 2 + 1));

        while (2 * elementIndex <= maxHeap.size() && wrongOrder) {
            int largerChildIndex;
            if (2 * elementIndex + 1 <= maxHeap.size() && getElementKey(elementIndex * 2 + 1) > getElementKey(elementIndex * 2)) {
                largerChildIndex = 2 * elementIndex + 1;
            } else {
                largerChildIndex = 2 * elementIndex;
            }

            swap(elementIndex, largerChildIndex);
            elementIndex = largerChildIndex;

            wrongOrder = (2 * elementIndex <= maxHeap.size() && key < getElementKey(elementIndex * 2)) || (2 * elementIndex + 1 <= maxHeap.size() && key < getElementKey(elementIndex * 2 + 1));
        }
    }

    /**
     * Extracts and returns the maximum element from the heap.
     *
     * @return HeapElement with the highest key
     * @throws EmptyHeapException if the heap is empty
     */
    private HeapElement extractMax() throws EmptyHeapException {
        if (maxHeap.isEmpty()) {
            throw new EmptyHeapException("Cannot extract from an empty heap");
        }
        HeapElement result = maxHeap.getFirst();
        deleteElement(1);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertElement(HeapElement element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot insert null element");
        }
        maxHeap.add(element);
        toggleUp(maxHeap.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteElement(int elementIndex) throws EmptyHeapException {
        if (maxHeap.isEmpty()) {
            throw new EmptyHeapException("Cannot delete from an empty heap");
        }
        if ((elementIndex > maxHeap.size()) || (elementIndex <= 0)) {
            throw new IndexOutOfBoundsException("Index " + elementIndex + " is out of heap range [1, " + maxHeap.size() + "]");
        }

        // Replace with last element and remove last position
        maxHeap.set(elementIndex - 1, maxHeap.getLast());
        maxHeap.removeLast();

        // No need to toggle if we just removed the last element
        if (!maxHeap.isEmpty() && elementIndex <= maxHeap.size()) {
            // Determine whether to toggle up or down
            if (elementIndex > 1 && getElementKey(elementIndex) > getElementKey((int) Math.floor(elementIndex / 2.0))) {
                toggleUp(elementIndex);
            } else {
                toggleDown(elementIndex);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HeapElement getElement() throws EmptyHeapException {
        return extractMax();
    }

    /**
     * Returns the current size of the heap.
     *
     * @return number of elements in the heap
     */
    public int size() {
        return maxHeap.size();
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap contains no elements
     */
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
