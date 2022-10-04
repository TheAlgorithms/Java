/** Author : Suraj Kumar Modi
 * https://github.com/skmodi649
 */
/** You are given a number n. You need to find the digital root of n.
 * DigitalRoot of a number is the recursive sum of its digits until we get a single digit number.
 *
 * Test Case 1:
 * Input:
 * n = 1
 * Output:  1
 * Explanation: Digital root of 1 is 1
 *
 * Test Case 2:
 * Input:
 * n = 99999
 * Output: 9
 * Explanation: Sum of digits of 99999 is 45
 * which is not a single digit number, hence
 * sum of digit of 45 is 9 which is a single
 * digit number.
 */
/** Algorithm :
 * Step 1 : Define a method digitalRoot(int n)
 * Step 2 : Define another method single(int n)
 * Step 3 : digitalRoot(int n) method takes output of single(int n) as input
 * if(single(int n) <= 9)
 * return single(n)
 * else
 * return digitalRoot(single(n))
 * Step 4 : single(int n) calculates the sum of digits of number n recursively
 * if(n<=9)
 * return n;
 * else
 * return (n%10) + (n/10)
 * Step 5 : In main method simply take n as input and then call digitalRoot(int n) function and print the result
 */
package com.thealgorithms.maths;

class DigitalRoot {

    public static int digitalRoot(int n) {
        if (single(n) <= 9) { // If n is already single digit than simply call single method and return the value
            return single(n);
        } else {
            return digitalRoot(single(n));
        }
    }

    // This function is used for finding the sum of digits of number
    public static int single(int n) {
        if (n <= 9) { // if n becomes less than 10 than return n
            return n;
        } else {
            return (n % 10) + single(n / 10); // n % 10 for extracting digits one by one
        }
    } // n / 10 is the number obtainded after removing the digit one by one
    // Sum of digits is stored in the Stack memory and then finally returned

}
/**
 * Time Complexity : O((Number of Digits)^2) Auxiliary Space Complexity :
 * O(Number of Digits) Constraints : 1 <= n <= 10^7
 */
