package Searches;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static java.lang.String.format;

/**
 * A iterative version of a ternary search algorithm
 * This is better way to implement the ternary search, because a recursive version adds some overhead to a stack.
 * But in java the compile can transform the recursive version to iterative implicitly,
 * so there are no much differences between these two algorithms
 * <p>
 * Worst-case performance	Θ(log3(N))
 * Best-case performance	O(1)
 * Average performance	Θ(log3(N))
 * Worst-case space complexity	O(1)
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SearchAlgorithm
 * @see TernarySearch
 * @since 2018-04-13
 */

public class IterativeTernarySearch implements SearchAlgorithm {


    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        int left = 0;
        int right = array.length - 1;

        while (right > left) {

            int leftCmp = array[left].compareTo(key);
            int rightCmp = array[right].compareTo(key);
            if (leftCmp == 0) return left;
            if (rightCmp == 0) return right;

            int leftThird = left + (right - left) / 3 + 1;
            int rightThird = right - (right - left) / 3 - 1;


            if (array[leftThird].compareTo(key) <= 0) {
                left = leftThird;
            } else {
                right = rightThird;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        //just generate data
        Random r = new Random();
        int size = 100;
        int maxElement = 100000;
        Integer[] integers = Stream.generate(() -> r.nextInt(maxElement))
                .limit(size)
                .sorted()
                .toArray(Integer[]::new);


        //the element that should be found
        Integer shouldBeFound = integers[r.nextInt(size - 1)];

        IterativeTernarySearch search = new IterativeTernarySearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.println(format("Should be found: %d. Found %d at index %d. An array length %d",
                shouldBeFound, integers[atIndex], atIndex, size));

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(format("Found by system method at an index: %d. Is equal: %b",
                toCheck, toCheck == atIndex));

    }


}
