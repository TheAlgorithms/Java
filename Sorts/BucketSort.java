package Sorts;

import java.util.List;
import java.util.ArrayList;

public class BucketSort {
	
    int BUCKET_NUM = 5;
	
    /**
     * This method implements Bucket Sort.
     * 
     * Here, we use insertion sort at the final step.
     * You can use other sorting algorithm. 
     * 
     * Time Complexity:
     * Worst Case O(n^2); Average Case O(n + (n^2)/k + k); Best Case O(n)
     *
     * @param arr The array to be sorted
     *            Sorts the array in ascending order
     **/
    public int[] sort(int[] arr) {
        // Get the minimum and maximum of the array
        int max = arr[0];
        int min = arr[0];
        for (int element : arr) {
            if (element > max)
                max = element;
			
            if (element < min)
                min = element;
        }
		
        // Create buckets
        List<List<Integer>> buckList = new ArrayList<List<Integer>>();
        for (int i = 0; i < BUCKET_NUM; i++) {
            buckList.add(new ArrayList<Integer>());
        }
		
        // Push into the bucket
        int bucket_size = (max - min) / BUCKET_NUM + 1;
        for (int i = 0; i < arr.length; i++) {
            int index = getBucketIdx(arr[i], min, bucket_size);
            (buckList.get(index)).add(arr[i]);
        }
		
        int[] sorted = new int[arr.length];
        int index = 0;
		
        // Sort each non-empty bucket and visit the buckets in order 
        // to get the final result
        for (int i = 0; i < BUCKET_NUM; i++) {
            List<Integer> bucket = buckList.get(i);
            insertionSort(bucket);
			
            for (int k : bucket) {
                sorted[index] = k;
                index++;
            }
        }
		
        return sorted;
    }
	
    /**
     * This method get the bucket index for the current number
     * 
     * @param num  Current processing number
     * @param min  The minimum number in the array
     * @param step The difference between two successive buckets
     **/
    private int getBucketIdx(int num, int min, int step) {
        return (num - min) / step;
    }
	
    /**
     * This method implements Insertion Sort
     * Sorts the array in ascending order
     *
     * @param bucket The array to be sorted
     **/
    private void insertionSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
			
            for (; j >= 0 && bucket.get(j) > temp; j--) {
                bucket.set(j + 1, bucket.get(j));
            }
            bucket.set(j + 1, temp);
        }
    }
	
    void print(int[] arr) {
        for (int element : arr) {
            System.out.print(element + " ");
        }
    }

    // Driver Program
    public static void main(String[] args) {
        // Integer Input
        int[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
		
        BucketSort bucketSort = new BucketSort();
        int[] sorted = bucketSort.sort(integers);
		
        bucketSort.print(sorted);
    }

}
