package com.thealgorithms.recursion;

/*
    The Fibonacci series is a sequence of numbers where each number is the sum of the two preceding ones,
    starting with 0 and 1.
       NUMBER    0 1 2 3 4 5 6 7  8  9  10 ...
       FIBONACCI 0 1 1 2 3 5 8 13 21 34 55 ...
*/

/*T his is optimized solution for fibonacci series. It computes fibonacci series in linear(O(n)) time complexity */

public final class FibonacciOptimized {
    static int fibonacci(int limit, int num1, int num2){
        if(limit==1){
            return num1;
        }
        else{
            return fibonacci(limit-1, num2, num1+num2);
        }
    }
    
}

