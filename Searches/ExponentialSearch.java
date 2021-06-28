package Searches;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.lang.String.format;

/**
 * Exponential search algorithm implementation
 * The Algorithm determining a range that the search key resides in and performing a binary search within that range
 * Worst-case performance	  O(Log n)
 * Best-case performance	O(1)
 * Average performance	O(log n)
 * Worst-case space complexity	O(1)
 *
 * @author Vishnu P (https://github.com/vishnu0pothan)
 */

public class ExponentialSearch {

    /**
     * @param array is an array where the element should be found
     * @param key   is an element which should be found
     * @param <T>   is any comparable type
     * @return The index position of the key in the array, returns -1 for empty array
     */
    // Returns position of first occurrence of 
    // x in array 
    static int find(int arr[], int n, int x) 
    { 
        // If x is present at firt location itself 
        if (arr[0] == x) 
            return 0; 
      
        // Find range for binary search by 
        // repeated doubling 
        int i = 1; 
        while (i < n && arr[i] <= x) 
            i = i*2; 
      
        // Call binary search for the found range. 
        return Arrays.binarySearch(arr, i/2,Math.min(i, n), x); 
    } 

    // Driver code 
    public static void main(String args[]) 
    { 
        // Just generate data
        Random r = ThreadLocalRandom.current();

        int size = 100;
        int maxElement = 100000;
 
        //int[] integers = IntStream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().boxed().toArray(int[]::new);
        int[] integers = IntStream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().toArray();
 
        // The element that should be found
        int shouldBeFound = integers[r.nextInt(size - 1)];
        ExponentialSearch search = new ExponentialSearch();

        int atIndex = search.find(integers, integers.length , shouldBeFound);

        System.out.println(String.format(
            "Should be found: %d. Found %d at index %d. An array length %d",
            shouldBeFound, integers[atIndex], atIndex, size));
       
        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(format("Found by system method at an index: %d. Is equal: %b", toCheck, toCheck == atIndex));
    } 
}