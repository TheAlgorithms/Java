package com.thealgorithms.datastructures.heaps;

/**
 * Interface common to heap data structures.<br>
 *
 * <p>
 * Heaps are tree-like data structures that allow storing elements in a specific
 * way. Each node corresponds to an element and has one parent node (except for
 * the root) and at most two children nodes. Every element contains a key, and
 * those keys indicate how the tree shall be built. For instance, for a
 * min-heap, the key of a node shall be greater than or equal to its parent's
 * and lower than or equal to its children's (the opposite rule applies to a
 * max-heap).
 *
 * <p>
 * All heap-related operations (inserting or deleting an element, extracting the
 * min or max) are performed in O(log n) time.
 *
 * @author Nicolas Renard
 */
public interface Heap {
    /**
     * @return the top element in the heap, the one with lowest key for min-heap
     * or with the highest key for max-heap
     * @throws EmptyHeapException if heap is empty
     */
    HeapElement getElement() throws EmptyHeapException;

    /**
     * Inserts an element in the heap. Adds it to then end and toggle it until
     * it finds its right position.
     *
     * @param element an instance of the HeapElement class.
     */
    void insertElement(HeapElement element);

    /**
     * Delete an element in the heap.
     *
     * @param elementIndex int containing the position in the heap of the
     * element to be deleted.
     */
    void deleteElement(int elementIndex);
}
