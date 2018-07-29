package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Moha (https://github.com/mohasaid)
 * <p>
 * BucketSort algorithm
 * <p>
 * - Distribute elements into buckets
 * - Non comparative sort
 * - Integer sort
 * - Stable
 * <p>
 * Big-O:
 * <p>
 * - Best: n*log_n (due to buckets sort)
 * - Average: n*log_n (due to buckets sort)
 * - Worst: n*log_n (due to buckets sort)
 * - Memory: n
 * <p>
 * Steps to implement BucketSort:
 * <p>
 * 1) Setup a list of empty buckets
 * 2) Scatter: go over original list, placing items in appropriate bucket
 * 3) Sort each non-empty bucket (recursively)
 * 4) Gather: visit the buckets in order and place into original.
 */
class BucketSort {

    /**
     * Implementation of bucketSort algorithm
     * <p>
     * - Creates N buckets, where N is the length of list
     * - Every bucket can have {@code bucketRange} elements
     * - Buckets are sorted using mergeSort
     *
     * @param unorderedList list to be ordered
     */
    private static void bucketSort(int[] unorderedList) {
        int maximumNumber = Arrays.stream(unorderedList).max().getAsInt();
        int minimumNumber = Arrays.stream(unorderedList).min().getAsInt();
        int numberOfBuckets = unorderedList.length;
        int bucketRange = (maximumNumber - minimumNumber + 1) / numberOfBuckets;

        List<List<Integer>> buckets = new ArrayList<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; ++i) {
            buckets.add(new ArrayList<>());
        }

        for (int listElement : unorderedList) {
            int bucketIndex = ((listElement - minimumNumber) / bucketRange) % numberOfBuckets;
            buckets.get(bucketIndex).add(listElement);
        }

        int pointer = 0;
        for (int i = 0; i < numberOfBuckets; ++i) {
            Collections.sort(buckets.get(i));
            for (int j = 0; j < buckets.get(i).size(); ++j) {
                unorderedList[pointer] = buckets.get(i).get(j);
                ++pointer;
            }
        }
    }

    /**
     * Main method to test generic bucketSort algorithm
     *
     * @param args no arguments
     */
    public static void main(String[] args) {
        int[] unorderedList = {3, 4, 1, 32, 0, 1, 5, 12, 2, 5, 7, 8, 9, 2, 44, 111, 5};
        bucketSort(unorderedList);
        System.out.println(Arrays.toString(unorderedList));
    }
}
