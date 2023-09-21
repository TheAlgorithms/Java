package com.thealgorithms.misc;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author shrutisheoran
 */
public class MedianOfRunningArray<Value extends Number & Comparable<Value>> {

    private PriorityQueue<Value> maxHeap;
    private PriorityQueue<Value> minHeap;

    // Constructor
    public MedianOfRunningArray() {
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max Heap
        this.minHeap = new PriorityQueue<>(); // Min Heap
    }

    /*
      Inserting lower half of array to max Heap
      and upper half to min heap
     */
    public void insert(Value e) {
        if (!minHeap.isEmpty() && e.compareValueo(minHeap.peek()) < 0) {
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
    public Value median() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            throw new IllegalArgumentException("Enter at least 1 element, Median of empty list is not defined!");
        }

        if (maxHeap.size() == minHeap.size()) {
        	Value maxHeapValueop = maxHeap.peek();
            Value minHeapValueop = minHeap.peek();
            return calculateAverage(maxHeapValueop, minHeapValueop);
        }
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
    }
    
    private Value calculateAverage(Value a, Value b) {
        if (a instanceof Integer) {
            int sum = ((Integer) a) + ((Integer) b);
            return (Value) Integer.valueOf(sum / 2);
        } else if (a instanceof Double) {
            double sum = ((Double) a) + ((Double) b);
            double roundedAverage = Math.round(sum / 2.0 * 100.0) / 100.0;
            return (Value) Double.valueOf(roundedAverage);
        } else if (a instanceof Float) {
            float sum = ((Float) a) + ((Float) b);
            float roundedAverage = (float) (Math.round(sum / 2.0 * 100.0) / 100.0);
            return (Value) Float.valueOf(roundedAverage);
        } else if (a instanceof Long) {
            long sum = ((Long) a) + ((Long) b);
            return (Value) Long.valueOf(sum / 2);
        } else if (a instanceof Short) {
            int sum = ((Short) a) + ((Short) b);
            return (Value) Short.valueOf((short) (sum / 2));
        } else if (a instanceof Byte) {
            int sum = ((Byte) a) + ((Byte) b);
            return (Value) Byte.valueOf((byte) (sum / 2));
        } else {
            throw new IllegalArgumentException("Unsupported numeric type");
        }
    }
}
