// github.com/RodneyShag

import java.util.Scanner;

// XOR properties:
//   1) x ^ x = 0
//   2) x ^ 0 = x
    
// We know that a number XORed with itself is 0. Instead of calculating the subarrays directly,
// we calculate the number of times each number appears in any subarray. If it appears an even
// number of times, then (x ^ x ... ^ x) where there is an even number of "x" values will give 0.
// If there are an odd number of "x" values, the result will be "x".
//
//                              *** Case 1: n is even ***
//
// Each element appears an even number of times throughout the subarrays, so the answer is 0.
//
//                              *** Case 2: n is odd ***
//
// We notice that the odd-indexed elements appear an even number of times throughout the 
// subarrays, so xoring them together will give 0, so we can ignore the odd-indexed elements.
//
// The even-indexed elements in the original subarray will appear an odd number of times
// throughout the subarrays. We can go ahead and XOR the values of the even-indexed elements
// in the original array to get our final answer.

//  Time Complexity: O(n)
// Space Complexity: O(1)

static int sansaXor(int[] array) {
    if (array.length % 2 == 0) { // Case 1
        return 0;
    } else { // Case 2
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                result ^= array[i];
            }
        }
       return result;
    }
}

// Discuss on HackerRank: https://www.hackerrank.com/challenges/sansa-and-xor/forum/comments/284330
