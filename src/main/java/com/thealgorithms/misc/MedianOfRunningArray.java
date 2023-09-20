package com.thealgorithms.misc;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author shrutisheoran
 */
public class MedianOfRunningArray {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    // Constructor
    public MedianOfRunningArray() {
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max Heap
        this.minHeap = new PriorityQueue<>(); // Min Heap
    }

    /*
      Inserting lower half of array to max Heap
      and upper half to min heap
     */
    public void insert(Integer e) {
        if (!minHeap.isEmpty() && e < minHeap.peek()) {
            maxHeap.offer(e);
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(e);
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    /*
      Returns median at any given point
     */
    public Double median() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.size() > minHeap.size() ? maxHeap.peek()*1.0 : minHeap.peek()*1.0;
    }

    public static void main(String[] args) {
        /*
        Testing the median function
         */

        MedianOfRunningArray p = new MedianOfRunningArray();
        int[] arr = {30,20,10};
        for (int i = 0; i < arr.length; i++) {
            p.insert(arr[i]);
            System.out.print(p.median() + " ");
        }
    }
}
