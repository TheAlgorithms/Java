// github.com/RodneyShag

import java.util.Scanner;

// XOR represents binary addition without the "carry" for each digit. We want to see
// how many values of x that will give us n+x = n^x, which is when XOR is the same as
// ADDITION. This happens when there are no "carries". To make sure there are no
// carries, for each digit in "n" that is a 1, we must have the corresponding digit in
// "x" be a 0. For each digit in "n" that is a 0, we can have the corresponding digit 
// in "x" be either 0 or 1. Now we calculate the number of possibilities for "x" by 
// counting the number of 0s in "n" (up to the most significant 1 in n), and doing 
// 2^(number of 0s) (where ^ is exponentiation in this case) to count all combinations.

static long sumXor(long n) {
    /* Calculate number of "x" values that will satisfy n+x = n^x */
    int zeroes = numZeroes(n);
    return 1L << zeroes; // same as (long) Math.pow(2, zeroes);
}

private static int numZeroes(long n) {
    int count = 0;
    while (n > 0) {
        if ((n & 1) == 0) {
            count++;
        }
        n >>= 1; // divides by 2
    }
    return count;
}

// Discuss on HackerRank: https://www.hackerrank.com/challenges/sum-vs-xor/forum/comments/284300
