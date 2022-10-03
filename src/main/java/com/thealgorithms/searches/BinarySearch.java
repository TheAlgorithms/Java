package com.thealgorithms.searches;

import static java.lang.String.format;

import com.thealgorithms.devutils.searches.SearchAlgorithm;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Binary search is one of the most popular algorithms The algorithm finds the
 * position of a target value within a sorted array
 *
 * <p>
 * Worst-case performance O(log n) Best-case performance O(1) Average
 * performance O(log n) Worst-case space complexity O(1)
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SearchAlgorithm
 * @see IterativeBinarySearch
 */
class BinarySearch implements SearchAlgorithm {

    static int binarySearch(int [] arr, int target){
        int start = 0;
        int end = arr.length-1;
        while (start <= end){
            int mid = start + (end-start)/2;
            if (target < arr[mid]){
                end = mid-1;
            }
            else if (target > arr[mid]){
                start = mid+1;
            }
            else{
                return mid;     //Will return the index of the element, if found
            }
        }
        return -1;      //Else it'll return -1
    }

    // Driver Program
    public static void main(String[] args) {
        // Just generate data
        Random r = ThreadLocalRandom.current();

        int size = 100;
        int maxElement = 100000;

        Integer[] integers = IntStream
            .generate(() -> r.nextInt(maxElement))
            .limit(size)
            .sorted()
            .boxed()
            .toArray(Integer[]::new);

        // The element that should be found
        int shouldBeFound = integers[r.nextInt(size - 1)];

        BinarySearch search = new BinarySearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.println(
            format(
                "Should be found: %d. Found %d at index %d. An array length %d",
                shouldBeFound,
                integers[atIndex],
                atIndex,
                size
            )
        );

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(
            format(
                "Found by system method at an index: %d. Is equal: %b",
                toCheck,
                toCheck == atIndex
            )
        );
    }
}
