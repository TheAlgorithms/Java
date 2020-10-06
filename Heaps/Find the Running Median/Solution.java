// github.com/RodneyShag

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Collections;

// - We use 2 Heaps to keep track of median
// - We make sure that 1 of the following conditions is always true:
//    1) maxHeap.size() == minHeap.size()
//    2) maxHeap.size() - 1 = minHeap.size()

public class Solution {
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder()); // keeps track of the SMALL numbers
    private static PriorityQueue<Integer> minHeap = new PriorityQueue();                           // keeps track of the LARGE numbers
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int [] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scan.nextInt();
        }
        scan.close();
        medianTracker(array);
    }
    
    public static void medianTracker(int [] array) {
        for (int i = 0; i < array.length; i++) {
            addNumber(array[i]);
            System.out.println(getMedian());
        }
    }
    
    private static void addNumber(int n) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(n);
        } else if (maxHeap.size() == minHeap.size()) {
            if (n < minHeap.peek()) {
                maxHeap.add(n);
            } else {
                minHeap.add(n);
                maxHeap.add(minHeap.remove());
            }
        } else if (maxHeap.size() > minHeap.size()) {
            if (n > maxHeap.peek()) {
                minHeap.add(n);
            } else {
                maxHeap.add(n);
                minHeap.add(maxHeap.remove());
            }
        }
        // maxHeap will never have fewer elements than minHeap
    }
    
    private static double getMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        } else if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else { // maxHeap must have more elements than minHeap
            return maxHeap.peek();
        }
    }
}
