package src.main.java.com.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static src.main.java.com.sorts.SortUtils.less;
import static src.main.java.com.sorts.SortUtils.swap;

public class HeapSort {


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
    Heap(T[] heap) {
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
      if (rightIndex <= lastChild) {
        // if has right and left children
        T left = heap[leftIndex];
        T right = heap[rightIndex];
        if (less(left, right) && less(left, root)) {
          swap(heap, leftIndex, rootIndex);
          heapSubtree(leftIndex, lastChild);
        } else if (less(right, root)) {
          swap(heap, rightIndex, rootIndex);
          heapSubtree(rightIndex, lastChild);
        }
      } else if (leftIndex <= lastChild) {
        // if no right child, but has left child
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
      if (hasRightChild) {
        //if has left and right
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
      // return old root
      return heap[size];
    }


  }

  public <T extends Comparable<T>> T[] sort(T[] unsorted) {
    return sort(Arrays.asList(unsorted)).toArray(unsorted);
  }

  private <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
    int size = unsorted.size();

    @SuppressWarnings("unchecked")
    Heap<T> heap = new Heap<>(unsorted.toArray((T[]) new Comparable[unsorted.size()]));

    // make min heap using index 0 as root.
    heap.makeMinHeap(0);
    List<T> sorted = new ArrayList<>(size);
    while (size > 0) {
      T min = heap.getRoot(--size);
      sorted.add(min);
    }

    return sorted;
  }
}