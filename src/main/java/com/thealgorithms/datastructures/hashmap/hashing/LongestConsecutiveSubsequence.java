package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.HashSet;

/*
This is a famous Hashing Problem asked in coding interviews.
Q) Find the Longest Consecutive Subsequence in a Given Array.
Ex: Array=[1, 9, 3, 10, 4, 20, 2]
    Ans - 4
    Explanation - The subsequence 1,3,4,2 is the longest subsequence of consecutive elements.
*/

public class LongestConsecutiveSubsequence {
    public static void main(String[] args) {
        int[] arr={1, 9, 3, 10, 4, 20, 2};

        System.out.println(solution(arr));
    }

    static int solution(int[] arr) {
        int result = 0;
        HashSet<Integer> hSet = new HashSet<Integer>();
        
        for (int k : arr) {
            hSet.add(k);
        }

        for (int k : arr) {
            int temp = k, count = 0;
            
            if (!(hSet.contains(temp - 1))) { // This condition is used to check if the current element is the starting point of our subsequence. If the current element is the starting point of consecutive subsequence, we run a loop from the current element and find the length of it and store it in the result.
                while (hSet.contains(temp++)) {
                    count++;
                }

            }
            result = Math.max(count, result);
        }

        return result;
    }
}
