package com.thealgorithms.datastructures.heaps;

import java.util.ArrayList;
import java.util.List;

/**
 * A Min Heap implementation where each node's key is lower than or equal to its children's keys.
 * This data structure provides O(log n) time complexity for insertion and deletion operations,
 * and O(1) for retrieving the minimum element.
 *
 * Properties:
 * 1. Complete Binary Tree
 * 2. Parent node's key ≤ Children nodes' keys
 * 3. Root contains the minimum element
 *
 * Example usage:
 * ```java
 * List<HeapElement> elements = Arrays.asList(
 *     new HeapElement(5, "Five"),
 *     new HeapElement(2, "Two")
 * );
 * MinHeap heap = new MinHeap(elements);
 * heap.insertElement(new HeapElement(1, "One"));
 * HeapElement min = heap.getElement(); // Returns and removes the minimum element
 * ```
 *
 * @author Nicolas Renard
 */
public class MinHeap implements Heap {

    private final List<HeapElement> minHeap;

    /**
     * Constructs a new MinHeap from a list of elements.
     * Null elements in the input list are ignored with a warning message.
     *
     * @param listElements List of HeapElement objects to initialize the heap
     * @throws IllegalArgumentException if the input list is null
     */
    public MinHeap(List<HeapElement> listElements) {
        if (listElements == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }

        minHeap = new ArrayList<>();

        // Safe initialization: directly add elements first
        for (HeapElement heapElement : listElements) {
            if (heapElement != null) {
                minHeap.add(heapElement);
            } else {
                System.out.println("Null element. Not added to heap");
            }
        }

        // Heapify the array bottom-up
        for (int i = minHeap.size() / 2; i >= 0; i--) {
            heapifyDown(i + 1);
        }

        if (minHeap.isEmpty()) {
            System.out.println("No element has been added, empty heap.");
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
        if ((elementIndex <= 0) || (elementIndex > minHeap.size())) {
            throw new IndexOutOfBoundsException("Index " + elementIndex + " is out of heap range [1, " + minHeap.size() + "]");
        }
        return minHeap.get(elementIndex - 1);
    }

    /**
     * Retrieves the key value of an element at the specified index.
     *
     * @param elementIndex 1-based index of the element
     * @return double value representing the key
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    private double getElementKey(int elementIndex) {
        if ((elementIndex <= 0) || (elementIndex > minHeap.size())) {
            throw new IndexOutOfBoundsException("Index " + elementIndex + " is out of heap range [1, " + minHeap.size() + "]");
        }
        return minHeap.get(elementIndex - 1).getKey();
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param index1 1-based index of first element
     * @param index2 1-based index of second element
     */
    private void swap(int index1, int index2) {
        HeapElement temporaryElement = minHeap.get(index1 - 1);
        minHeap.set(index1 - 1, minHeap.get(index2 - 1));
        minHeap.set(index2 - 1, temporaryElement);
    }

    /**
     * Maintains heap properties by moving an element down the heap.
     * Used specifically during initialization.
     *
     * @param elementIndex 1-based index of the element to heapify
     */
    private void heapifyDown(int elementIndex) {
        int smallest = elementIndex - 1; // Convert to 0-based index
        int leftChild = 2 * elementIndex - 1;
        int rightChild = 2 * elementIndex;

        // Check if left child is smaller than root
        if (leftChild < minHeap.size() && minHeap.get(leftChild).getKey() < minHeap.get(smallest).getKey()) {
            smallest = leftChild;
        }

        // Check if right child is smaller than smallest so far
        if (rightChild < minHeap.size() && minHeap.get(rightChild).getKey() < minHeap.get(smallest).getKey()) {
            smallest = rightChild;
        }

        // If smallest is not root
        if (smallest != elementIndex - 1) {
            HeapElement swap = minHeap.get(elementIndex - 1);
            minHeap.set(elementIndex - 1, minHeap.get(smallest));
            minHeap.set(smallest, swap);

            // Recursively heapify the affected sub-tree
            heapifyDown(smallest + 1); // Convert back to 1-based index
        }
    }

    /**
     * Moves an element up the heap until heap properties are satisfied.
     * This operation is called after insertion to maintain heap properties.
     *
     * @param elementIndex 1-based index of the element to move up
     */
    private void toggleUp(int elementIndex) {
        if (elementIndex <= 1) {
            return;
        }

        double key = minHeap.get(elementIndex - 1).getKey();
        int parentIndex = (int) Math.floor(elementIndex / 2.0);

        while (elementIndex > 1 && getElementKey(parentIndex) > key) {
            swap(elementIndex, parentIndex);
            elementIndex = parentIndex;
            parentIndex = (int) Math.floor(elementIndex / 2.0);
        }
    }

    /**
     * Moves an element down the heap until heap properties are satisfied.
     * This operation is called after deletion to maintain heap properties.
     *
     * @param elementIndex 1-based index of the element to move down
     */
    private void toggleDown(int elementIndex) {
        double key = minHeap.get(elementIndex - 1).getKey();
        int size = minHeap.size();

        while (true) {
            int smallest = elementIndex;
            int leftChild = 2 * elementIndex;
            int rightChild = 2 * elementIndex + 1;

            if (leftChild <= size && getElementKey(leftChild) < key) {
                smallest = leftChild;
            }

            if (rightChild <= size && getElementKey(rightChild) < getElementKey(smallest)) {
                smallest = rightChild;
            }

            if (smallest == elementIndex) {
                break;
            }

            swap(elementIndex, smallest);
            elementIndex = smallest;
        }
    }

    /**
     * Extracts and returns the minimum element from the heap.
     *
     * @return HeapElement with the lowest key
     * @throws EmptyHeapException if the heap is empty
     */
    private HeapElement extractMin() throws EmptyHeapException {
        if (minHeap.isEmpty()) {
            throw new EmptyHeapException("Cannot extract from empty heap");
        }
        HeapElement result = minHeap.getFirst();
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
        minHeap.add(element);
        toggleUp(minHeap.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteElement(int elementIndex) throws EmptyHeapException {
        if (minHeap.isEmpty()) {
            throw new EmptyHeapException("Cannot delete from empty heap");
        }
        if ((elementIndex > minHeap.size()) || (elementIndex <= 0)) {
            throw new IndexOutOfBoundsException("Index " + elementIndex + " is out of heap range [1, " + minHeap.size() + "]");
        }

        // Replace with last element and remove last position
        minHeap.set(elementIndex - 1, minHeap.getLast());
        minHeap.removeLast();

        // No need to toggle if we just removed the last element
        if (!minHeap.isEmpty() && elementIndex <= minHeap.size()) {
            // Determine whether to toggle up or down
            if (elementIndex > 1 && getElementKey(elementIndex) < getElementKey((int) Math.floor(elementIndex / 2.0))) {
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
        return extractMin();
    }

    /**
     * Returns the current size of the heap.
     *
     * @return number of elements in the heap
     */
    public int size() {
        return minHeap.size();
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap contains no elements
     */
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }
}
