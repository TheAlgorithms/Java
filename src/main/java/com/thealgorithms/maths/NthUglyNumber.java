// Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers.
// By convention, 1 is included.
// A program to find the nth Ugly number
// Algorithm :
// Initialize three-pointers two, three, and five pointing to zero.
// Take 3 variables nm2, nm3, and nm5 to keep track of next multiple of 2,3 and 5.
// Make an array of size n to store the ugly numbers with 1 at 0th index.
// Initialize a variable next which stores the value of the last element in the array.
// Run a loop n-1 times and perform steps 6,7 and 8.
// Update the values of nm2, nm3, nm5 as ugly[two]*2, ugly[three]*3, ugly[5]*5 respectively.
// Select the minimum value from nm2, nm3, and nm5 and increment the pointer related to it.
// Store the minimum value in variable next and array.
// Return next.
package com.thealgorithms.maths;

import java.util.*;

class NthUglyNumber {

    /* Function to get the nth ugly number*/
    public long getNthUglyNo(int n) {
        long[] ugly = new long[n];
        int two = 0, three = 0, five = 0;
        long nm2 = 2, nm3 = 3, nm5 = 5;
        long next = 1;

        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            next = Math.min(nm2, Math.min(nm3, nm5));

            ugly[i] = next;
            if (next == nm2) {
                two = two + 1;
                nm2 = ugly[two] * 2;
            }
            if (next == nm3) {
                three = three + 1;
                nm3 = ugly[three] * 3;
            }
            if (next == nm5) {
                five = five + 1;
                nm5 = ugly[five] * 5;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of n : ");
        int n = sc.nextInt();
        NthUglyNumber ob = new NthUglyNumber();
        long ugly = ob.getNthUglyNo(n);
        System.out.println("nth Ugly number is : " + ugly);
    }
}
