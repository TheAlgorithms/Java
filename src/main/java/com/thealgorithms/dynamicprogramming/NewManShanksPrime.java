/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - To find the New Man Shanks Prime. */
/** Wikipedia Link - https://en.wikipedia.org/wiki/Newman%E2%80%93Shanks%E2%80%93Williams_prime */

package com.thealgorithms.dynamicprogramming;

public class NewManShanksPrime {

    public static boolean nthManShanksPrime(int n, int expected_answer) {
        int a[] = new int[n + 1];
        // array of n+1 size is initialized
        a[0] = a[1] = 1;
        // The 0th and 1st index position values are fixed. They are initialized as 1
        for (int i = 2; i <= n; i++) {
            a[i] = 2 * a[i - 1] + a[i - 2];
        }
        // The loop is continued till n
        return a[n] == expected_answer;
        // returns true if calculated answer matches with expected answer
    }
}
