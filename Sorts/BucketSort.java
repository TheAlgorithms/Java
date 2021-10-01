package Sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** Wikipedia: https://en.wikipedia.org/wiki/Bucket_sort */
public class BucketSort {
  public static void main(String[] args) {
    int[] arr = new int[10];

    /* generate 10 random numbers from -50 to 49 */
    Random random = new Random();
    for (int i = 0; i < arr.length; ++i) {
      arr[i] = random.nextInt(100) - 50;
    }

    bucketSort(arr);

    /* check array is sorted or not */
    for (int i = 0, limit = arr.length - 1; i < limit; ++i) {
      assert arr[i] <= arr[i + 1];
    }
  }

  /**
   * BucketSort algorithms implements
   *
   * @param arr the array contains elements
   */
  private static void bucketSort(int[] arr) {
    /* get max value of arr */
    int max = max(arr);

    /* get min value of arr */
    int min = min(arr);

    /* number of buckets */
    int numberOfBuckets = max - min + 1;

    List<List<Integer>> buckets = new ArrayList<>(numberOfBuckets);

    /* init buckets */
    for (int i = 0; i < numberOfBuckets; ++i) {
      buckets.add(new ArrayList<>());
    }

    /* store elements to buckets */
    for (int value : arr) {
      int hash = hash(value, min, numberOfBuckets);
      buckets.get(hash).add(value);
    }

    /* sort individual bucket */
    for (List<Integer> bucket : buckets) {
      Collections.sort(bucket);
    }

    /* concatenate buckets to origin array */
    int index = 0;
    for (List<Integer> bucket : buckets) {
      for (int value : bucket) {
        arr[index++] = value;
      }
    }
  }

  /**
   * Get index of bucket which of our elements gets placed into it.
   *
   * @param elem the element of array to be sorted
   * @param min min value of array
   * @param numberOfBucket the number of bucket
   * @return index of bucket
   */
  private static int hash(int elem, int min, int numberOfBucket) {
    return (elem - min) / numberOfBucket;
  }

  /**
   * Calculate max value of array
   *
   * @param arr the array contains elements
   * @return max value of given array
   */
  public static int max(int[] arr) {
    int max = arr[0];
    for (int value : arr) {
      if (value > max) {
        max = value;
      }
    }
    return max;
  }

  /**
   * Calculate min value of array
   *
   * @param arr the array contains elements
   * @return min value of given array
   */
  public static int min(int[] arr) {
    int min = arr[0];
    for (int value : arr) {
      if (value < min) {
        min = value;
      }
    }
    return min;
  }
}
