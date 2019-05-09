package Sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Sorts.SortUtils.*;

/**
 * Heap Sort Algorithm
 * Implements MinHeap
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 */
public class HeapSort implements SortAlgorithm {


    private static class Heap<T extends Comparable<T>> {
        /**
         * Array to store heap
         */
        private T[] heap;

        /**
         * Constructor
         *
         * @param heap array of unordered integers
         */
        public Heap(T[] heap) {
            this.heap = heap;
        }

        /**
         * Heapifies subtree from top as root to last as last child
         *
         * @param rootIndex index of root
         * @param lastChild index of last child
         */
        private void heapSubtree(int rootIndex, int lastChild) {
            int leftIndex = rootIndex * 2 + 1;
            int rightIndex = rootIndex * 2 + 2;
            T root = heap[rootIndex];
            if (rightIndex <= lastChild) { // if has right and left children
                T left = heap[leftIndex];
                T right = heap[rightIndex];
                if (less(left, right) && less(left, root)) {
                    swap(heap, leftIndex, rootIndex);
                    heapSubtree(leftIndex, lastChild);
                } else if (less(right, root)) {
                    swap(heap, rightIndex, rootIndex);
                    heapSubtree(rightIndex, lastChild);
                }
            } else if (leftIndex <= lastChild) { // if no right child, but has left child
                T left = heap[leftIndex];
                if (less(left, root)) {
                    swap(heap, leftIndex, rootIndex);
                    heapSubtree(leftIndex, lastChild);
                }
            }
        }


        /**
         * Makes heap with root as root
         *
         * @param root index of root of heap
         */
        private void makeMinHeap(int root) {
            int leftIndex = root * 2 + 1;
            int rightIndex = root * 2 + 2;
            boolean hasLeftChild = leftIndex < heap.length;
            boolean hasRightChild = rightIndex < heap.length;
            if (hasRightChild) { //if has left and right
                makeMinHeap(leftIndex);
                makeMinHeap(rightIndex);
                heapSubtree(root, heap.length - 1);
            } else if (hasLeftChild) {
                heapSubtree(root, heap.length - 1);
            }
        }

        /**
         * Gets the root of heap
         *
         * @return root of heap
         */
        private T getRoot(int size) {
            swap(heap, 0, size);
            heapSubtree(0, size - 1);
            return heap[size]; // return old root
        }


    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        return sort(Arrays.asList(unsorted)).toArray(unsorted);
    }

    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
        int size = unsorted.size();

        @SuppressWarnings("unchecked")
        Heap<T> heap = new Heap<>(unsorted.toArray((T[]) new Comparable[unsorted.size()]));

        heap.makeMinHeap(0); // make min heap using index 0 as root.
        List<T> sorted = new ArrayList<>(size);
        while (size > 0) {
            T min = heap.getRoot(--size);
            sorted.add(min);
        }

        return sorted;
    }

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer[] heap = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        HeapSort heapSort = new HeapSort();
        print(heapSort.sort(heap));
    }

}
