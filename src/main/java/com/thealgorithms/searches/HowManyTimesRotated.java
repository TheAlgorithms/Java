package com.thealgorithms.searches;

import java.util.Scanner;

/*
    Problem Statement:
    Given an array, find out how many times it has to been rotated
    from its initial sorted position.
    Input-Output:
    Eg. [11,12,15,18,2,5,6,8]
    It has been rotated: 4 times
    (One rotation means putting the first element to the end)
    Note: The array cannot contain duplicates

    Logic:
    The position of the minimum element will give the number of times the array has been rotated
    from its initial sorted position.
    Eg. For [2,5,6,8,11,12,15,18], 1 rotation gives [5,6,8,11,12,15,18,2], 2 rotations
   [6,8,11,12,15,18,2,5] and so on. Finding the minimum element will take O(N) time but, we can use
   Binary Search to reduce the complexity to O(log N): at each step compare a[mid] with a[high].
   If a[mid] > a[high], the minimum lies to the right, so low = mid + 1; otherwise it lies at mid
   or to the left, so high = mid. This converges to the minimum's index without ever reading
   a[mid-1] or a[mid+1], so it also works on arrays of size 0-2 and unrotated arrays.

    Some other test cases:
    1. [1,2,3,4] Number of rotations: 0 or 4(Both valid)
    2. [15,17,2,3,5] Number of rotations: 2
 */
final class HowManyTimesRotated {
    private HowManyTimesRotated() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println("The array has been rotated " + rotated(a) + " times");
        sc.close();
    }

    public static int rotated(int[] a) {
        int low = 0;
        int high = a.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] > a[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
