ackage ads.set02.median;

import java.util.Arrays;

/**
 * Find Median.
 * @author github.com/b3z
 */
public class FindMedian2 {

    /**
     * Computes and retrieves the lower median of the given array of pairwise
     * distinct numbers.
     * 
     * @param numbers array with pairwise distinct numbers.
     * @return the lower median.
     * @throw IllegalArgumentException if the array is {@code null} or empty.
     */
    public static int lowerMedian(int[] numbers) {

        if (numbers == null || numbers.length == 0) // validate input.
            throw new IllegalArgumentException();

        return findMedian(numbers, ((numbers.length + 1) / 2) - 1); // compute median of array.
    }

    /**
     * Partitions 5er groups and recalls.
     * 
     * @param numbers     input array.
     * @param indexMedian index of median to search by.
     * @return sorted by pivot. Also called median.
     */
    public static int findMedian(int[] numbers, int indexMedian) {

        int pivot;
        if (numbers.length <= 5)
            pivot = subMedian(numbers);
        else {
            int groups = numbers.length / 5; // Define how many groups are necessary.
            groups += (numbers.length % 5 != 0) ? 1 : 0; // Handle incomplete groups (simply add one).

            int[] submedians = grouping(groups, numbers); // make 5er groups and save submedian.

            pivot = (submedians.length <= 5) ? subMedian(submedians) // if only 5 are left calculate median.
                    : findMedian(submedians, ((submedians.length - 1) / 2)); // else repeat.
        }
        return splitLists(numbers, pivot, indexMedian);
    }

    /**
     * Groups the array and saves their submedians.
     * 
     * @param size    groups we have.
     * @param numbers original numbers to group.
     * @return submedians
     */
    private static int[] grouping(int size, int[] numbers) {
        int[] submedians = new int[size];
        int[] tmp = new int[5];
        int pointer = 0;

        for (int i = 0; i < numbers.length / 5; i++) {
            for (int k = 0; k < 5; k++)
                tmp[k] = numbers[pointer++];

            submedians[i] = subMedian(tmp);
        }

        if (numbers.length % 5 != 0) {  // handle groups smaller than 5.
            int[] subGroup = new int[numbers.length % 5];
            for (int i = 0; i < (numbers.length % 5); i++)
                subGroup[i] = numbers[pointer++];

            submedians[numbers.length / 5] = subMedian(subGroup);
        }
        return submedians;
    }

    /**
     * Median of subgroup sorted.
     * 
     * @param subGroups
     * @return submedian
     */
    public static int subMedian(int[] subGroups) {
        int size = subGroups.length;
        int min, tmp;

        for (int i = 0; i < size - 1; i++) {
            min = i;
            for (int k = i + 1; k < size; k++)
                min = (subGroups[k] < subGroups[min]) ? k : min;

            tmp = subGroups[min];
            subGroups[min] = subGroups[i];
            subGroups[i] = tmp;
        }

        if (size < 5) { // handle smaller group than 5.
            int x = (size + 1) / 2;
            return (x > 0) ? subGroups[x - 1] : 0;
        } else
            return subGroups[2]; // 2 since it is the middle of a list with length = 5.
    }

    /**
     * Split lists by pivot.
     * 
     * @param numbers List to split.
     * @param pivot   splitting index.
     */
    private static int splitLists(int[] numbers, int pivot, int medianIndex) {

        int[] left = new int[numbers.length]; // numbers to stand left of the pivot element.
        int[] right = new int[numbers.length]; // numbers to stand right of the pivot element.
        int leftPointer = 0; 
        int rightPointer = 0;

        for (int i = 0; i < numbers.length; i++) { // split into sorted lists by pivot.
            if (numbers[i] < pivot)
                left[leftPointer++] = numbers[i];
            else if (numbers[i] > pivot)
                right[rightPointer++] = numbers[i];
        }

        if (leftPointer == medianIndex) { // case: found median - Yayy.
            return pivot;
        } else if (medianIndex < leftPointer) { // case: its in the left part
            return findMedian(Arrays.copyOfRange(left, 0, leftPointer), medianIndex);
        } else // case: its in right part.
            return findMedian(Arrays.copyOfRange(right, 0, rightPointer), medianIndex - leftPointer - 1);
    }
}
