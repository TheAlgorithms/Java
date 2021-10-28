package Others;

import java.util.Arrays;
import java.util.Scanner;

/**
 * To find triplet equals to given sum in complexity O(n*log(n))
 *
 * <p>Array must be sorted
 *
 * @author Ujjawal Joshi
 * @date 2020.05.18
 *     <p>Test Cases: Input: 6 //Length of array 12 3 4 1 6 9 target=24 Output:3 9 12 Explanation:
 *     There is a triplet (12, 3 and 9) present in the array whose sum is 24.
 */
class ThreeSum {
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); // Length of an array

    int a[] = new int[n];

    for (int i = 0; i < n; i++) {
      a[i] = sc.nextInt();
    }
    System.out.println("Target");
    int n_find = sc.nextInt();

    Arrays.sort(a); // Sort the array if array is not sorted

    for (int i = 0; i < n; i++) {

      int l = i + 1, r = n - 1;

      while (l < r) {
        if (a[i] + a[l] + a[r] == n_find) {
          System.out.println(a[i] + " " + a[l] + " " + a[r]);
          break;
        } // if you want all the triplets write l++;r--; insted of break;
        else if (a[i] + a[l] + a[r] < n_find) l++;
        else r--;
      }
    }

    sc.close();
  }
}
