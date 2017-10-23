import java.util.*;

/*
 * Java implementation of BucketSort.
 * BucketSort is used for evenly distributed data over a range.
 * BucketSort is O(n).
 */
public class BucketSort{

   public static void sort(int[] a, int maxVal) {
      // Create buckets
      int[] bucket = new int[maxVal+1];

      // Initialize buckets to zero.
      for (int i = 0; i < bucket.length; i++) {
         bucket[i] = 0;
      }

      // Increment the number at respective bucket to show how many
      // of each value are in the data.
      for (int i = 0; i < a.length; i++) {
         bucket[a[i]]++;
      }

      // Add all values into new sorted array.
      int outPos = 0;
      for (int i = 0; i < bucket.length; i++) {
         for (int j = 0; j < bucket[i]; j++) {
            a[outPos++] = i;
         }
      }
   }
}
