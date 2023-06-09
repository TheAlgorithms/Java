package com.thealgorithms.misc;

import java.util.*;

/**
 * The array is divided into four sections: a[1..Lo-1] zeroes a[Lo..Mid-1] ones
 * a[Mid..Hi] unknown a[Hi+1..N] twos If array [mid] =0, then swap array [mid]
 * with array [low] and increment both pointers once. If array [mid] = 1, then
 * no swapping is required. Increment mid pointer once. If array [mid] = 2, then
 * we swap array [mid] with array [high] and decrement the high pointer once.
 * For more information on the Dutch national flag algorithm refer
 * https://en.wikipedia.org/wiki/Dutch_national_flag_problem
 */
public class Sort012D {

    public static void main(String[] args) {
        Scanner np = new Scanner(System.in);
        int n = np.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = np.nextInt();
        }
        sort012(a);
    }

    public static void sort012(int[] a) {
        int l = 0;
        int h = a.length - 1;
        int mid = 0;
        int temp;
        while (mid <= h) {
            switch (a[mid]) {
            case 0: {
                temp = a[l];
                a[l] = a[mid];
                a[mid] = temp;
                l++;
                mid++;
                break;
            }
            case 1:
                mid++;
                break;
            case 2: {
                temp = a[mid];
                a[mid] = a[h];
                a[h] = temp;
                h--;
                break;
            }
            }
        }
        System.out.println("the Sorted array is ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(+a[i] + " ");
        }
    }
}
