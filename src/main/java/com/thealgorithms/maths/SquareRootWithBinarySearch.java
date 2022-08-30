package com.thealgorithms.maths;


// Calculating Square root of a number which is not necessarily perfect square
// using Binary Search i.e. is using decrease and conquer approach
// Time: O(log(n))
public class SquareRootWithBinarySearch {
    public static void main(String[] args) {
        int n = 40; // number whose square root is to be calculated
        int p = 3; // decimal precision required

        System.out.printf("%.3f", sqrt(n, p));
    }
    static double sqrt(int n, int p) {
        int s = 0;
        int e = n;

        double root = 0.0;

        while (s <= e) {
            int m = s + (e - s) / 2; //this method of calculating middle element avoids integer overflow

            if (m * m == n) { //the middle element is the required sqrt
                return m;
            }
            else if (m * m > n) { //sqrt lies on the left part
                e = m - 1;
            }
            else { // sqrt lies in the right part
                s = m + 1;
                root = m;
            }
        }
        //now the root contains the nearest integer to the required square root
        //now we need to add decimal precision to the root so that we can get as close to the exact sqrt
        double incr = 0.1; //initialise
        for (int i = 0; i < p; i++) {
            while (root * root <= n) { //if the square of the root we have is less than the number
                root += incr; //we will add incr decimal point each time
            }
            root -= incr;
            incr /= 10; //here we increase the decimal point
        }

        return root; //here we have root upto p decimal point
    }
}
}
