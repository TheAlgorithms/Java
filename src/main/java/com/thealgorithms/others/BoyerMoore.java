/* this Code is the illustration of Boyer moore's voting algorithm to
find the majority element is an array that appears more than n/2 times in an array
where "n" is the length of the array.
For more information on the algorithm refer
https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
 */
package com.thealgorithms.others;

import java.util.*;

public class BoyerMoore {

    public static int findmajor(int[] a) {
        int count = 0;
        int cand = -1;
        for (int i = 0; i < a.length; i++) {
            if (count == 0) {
                cand = a[i];
                count = 1;
            } else {
                if (a[i] == cand) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == cand) {
                count++;
            }
        }
        if (count > (a.length / 2)) {
            return cand;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        System.out.println("the majority element is " + findmajor(a));
    }
}
