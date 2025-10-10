import java.util.*;

public class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // left half (small)
    private PriorityQueue<Integer> minHeap; // right half (larger)

    public MedianFinder() {
        // Max-heap stores the smaller half
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Min-heap stores the larger half
        minHeap = new PriorityQueue<>();
    }

    
    public void addNum(int num) {
        // Step 1: Add to maxHeap
        maxHeap.offer(num);

        // Step 2: Balance heaps (ensure all in maxHeap <= all in minHeap)
        minHeap.offer(maxHeap.poll());

        // Step 3: Maintain size property (maxHeap can be larger by 1)
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            // Even number of elements → average of two middles
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            // Odd → top of maxHeap
            return maxHeap.peek();
        }
    }


    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        
        mf.addNum(1);
        System.out.println("After adding 1, Median = " + mf.findMedian());
        
        mf.addNum(2);
        System.out.println("After adding 2, Median = " + mf.findMedian());
        
        mf.addNum(3);
        System.out.println("After adding 3, Median = " + mf.findMedian());
        
        mf.addNum(4);
        System.out.println("After adding 4, Median = " + mf.findMedian());
        
        mf.addNum(5);
        System.out.println("After adding 5, Median = " + mf.findMedian());
    }
}
