package com.thealgorithms.datastructures.heaps;

import java.util.ArrayList;
import java.util.List;

/**
 * Heap tree where a node's key is higher than or equal to its parent's and
 * lower than or equal to its children's.
 *
 * @author Nicolas Renard
 */
public class MaxHeap implements Heap {

    private final List<HeapElement> maxHeap;

    public MaxHeap(List<HeapElement> listElements) {
        maxHeap = new ArrayList<>();
        for (HeapElement heapElement : listElements) {
            if (heapElement != null) {
                insertElement(heapElement);
            } else {
                System.out.println("Null element. Not added to heap");
            }
        }
        if (maxHeap.size() == 0) {
            System.out.println("No element has been added, empty heap.");
        }
    }

    /**
     * Get the element at a given index. The key for the list is equal to index
     * value - 1
     *
     * @param elementIndex index
     * @return heapElement
     */
    public HeapElement getElement(int elementIndex) {
        if ((elementIndex <= 0) || (elementIndex > maxHeap.size())) {
            throw new IndexOutOfBoundsException("Index out of heap range");
        }
        return maxHeap.get(elementIndex - 1);
    }

    // Get the key of the element at a given index
    private double getElementKey(int elementIndex) {
        return maxHeap.get(elementIndex - 1).getKey();
    }

    // Swaps two elements in the heap
    private void swap(int index1, int index2) {
        HeapElement temporaryElement = maxHeap.get(index1 - 1);
        maxHeap.set(index1 - 1, maxHeap.get(index2 - 1));
        maxHeap.set(index2 - 1, temporaryElement);
    }

    // Toggle an element up to its right place as long as its key is lower than its parent's
    private void toggleUp(int elementIndex) {
        double key = maxHeap.get(elementIndex - 1).getKey();
        while (getElementKey((int) Math.floor(elementIndex / 2.0)) < key) {
            swap(elementIndex, (int) Math.floor(elementIndex / 2.0));
            elementIndex = (int) Math.floor(elementIndex / 2.0);
        }
    }

    // Toggle an element down to its right place as long as its key is higher
    // than any of its children's
    private void toggleDown(int elementIndex) {
        double key = maxHeap.get(elementIndex - 1).getKey();
        boolean wrongOrder
                = (key < getElementKey(elementIndex * 2))
                || (key < getElementKey(Math.min(elementIndex * 2, maxHeap.size())));
        while ((2 * elementIndex <= maxHeap.size()) && wrongOrder) {
            // Check whether it shall swap the element with its left child or its right one if any.
            if ((2 * elementIndex < maxHeap.size())
                    && (getElementKey(elementIndex * 2 + 1) > getElementKey(elementIndex * 2))) {
                swap(elementIndex, 2 * elementIndex + 1);
                elementIndex = 2 * elementIndex + 1;
            } else {
                swap(elementIndex, 2 * elementIndex);
                elementIndex = 2 * elementIndex;
            }
            wrongOrder
                    = (key < getElementKey(elementIndex * 2))
                    || (key < getElementKey(Math.min(elementIndex * 2, maxHeap.size())));
        }
    }

    private HeapElement extractMax() {
        HeapElement result = maxHeap.get(0);
        deleteElement(0);
        return result;
    }

    @Override
    public void insertElement(HeapElement element) {
        maxHeap.add(element);
        toggleUp(maxHeap.size());
    }

    @Override
    public void deleteElement(int elementIndex) {
        if (maxHeap.isEmpty())
      try {
            throw new EmptyHeapException("Attempt to delete an element from an empty heap");
        } catch (EmptyHeapException e) {
            e.printStackTrace();
        }
        if ((elementIndex > maxHeap.size()) || (elementIndex <= 0)) {
            throw new IndexOutOfBoundsException("Index out of heap range");
        }
        // The last element in heap replaces the one to be deleted
        maxHeap.set(elementIndex - 1, getElement(maxHeap.size()));
        maxHeap.remove(maxHeap.size());
        // Shall the new element be moved up...
        if (getElementKey(elementIndex) > getElementKey((int) Math.floor(elementIndex / 2.0))) {
            toggleUp(elementIndex);
        } // ... or down ?
        else if (((2 * elementIndex <= maxHeap.size())
                && (getElementKey(elementIndex) < getElementKey(elementIndex * 2)))
                || ((2 * elementIndex < maxHeap.size())
                && (getElementKey(elementIndex) < getElementKey(elementIndex * 2)))) {
            toggleDown(elementIndex);
        }
    }

    @Override
    public HeapElement getElement() throws EmptyHeapException {
        try {
            return extractMax();
        } catch (Exception e) {
            throw new EmptyHeapException("Heap is empty. Error retrieving element");
        }
    }
}
