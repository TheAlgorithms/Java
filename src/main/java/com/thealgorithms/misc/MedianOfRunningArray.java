package com.thealgorithms.misc;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author shrutisheoran
 */
public class MedianOfRunningArray<T extends Number & Comparable<T>> {

    private PriorityQueue<T> maxHeap;
    private PriorityQueue<T> minHeap;

    // Constructor
    public MedianOfRunningArray() {
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max Heap
        this.minHeap = new PriorityQueue<>(); // Min Heap
    }

    /*
      Inserting lower half of array to max Heap
      and upper half to min heap
     */
    public void insert(T e) {
        if (!minHeap.isEmpty() && e.compareTo(minHeap.peek()) < 0 ) {
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
    public T median() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            throw new IllegalArgumentException("Enter at least 1 element, Median of empty list is not defined!");
        }

        if (maxHeap.size() == minHeap.size()) {
        	T maxHeapTop = maxHeap.peek();
            T minHeapTop = minHeap.peek();
            return calculateAverage(maxHeapTop,minHeapTop);
        }
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
    }
    
    private T calculateAverage(T a, T b) {
        if (a instanceof Integer) {
            int sum = ((Integer) a) + ((Integer) b);
            return (T) Integer.valueOf(sum / 2);
        } else if (a instanceof Double) {
            double sum = ((Double) a) + ((Double) b);
            double roundedAverage = Math.round(sum / 2.0 * 100.0) / 100.0;
            return (T) Double.valueOf(roundedAverage);
        } else if (a instanceof Float) {
            float sum = ((Float) a) + ((Float) b);
            float roundedAverage = (float) (Math.round(sum / 2.0 * 100.0) / 100.0);
            return (T) Float.valueOf(roundedAverage);
        } else if (a instanceof Long) {
            long sum = ((Long) a) + ((Long) b);
            return (T) Long.valueOf(sum / 2);
        } else if (a instanceof Short) {
            int sum = ((Short) a) + ((Short) b);
            return (T) Short.valueOf((short) (sum / 2));
        } else if (a instanceof Byte) {
            int sum = ((Byte) a) + ((Byte) b);
            return (T) Byte.valueOf((byte) (sum / 2));
        } else {
            throw new IllegalArgumentException("Unsupported numeric type");
        }
    }
    
}
