package com.thealgorithms.maths;
/*  x= 4;
     lo = 1, hi = 4
  In this case, the input is a perfect square (2 * 2), and the expected output is also a whole number, which is 2
 */
public class SquareRootUsingBisectionMethod {
    public static int  findSquareRoot(int x){

        if(x == 0) return 0; // if x is 0 then we directly return 0 no need to calculate.

        long low = 1;
        long high = x;
        long ans = 0;

        while(low <= high){
            long mid = low + (high - low) /2;

            long square = mid * mid;

            if(square == x){
                return (int) mid;
            }else if(square < x){
                low = mid + 1;
                ans = mid;     // Update the ans the current mid value.
            }else{
                high = mid - 1;
            }

        }

        return (int)ans;
    }
    public static void main(String[] args) {
        int x = 4;
         System.out.println("The Square Root using Bisection Method: "+ findSquareRoot(x));
    }
}

/* Time complexity = O(log(x)) where x is the input, this is because each iteration and search space divided by half which means algorithms
reduces the range of possible answer. It takes a logarithmic number of steps to find the square.


Space Complexity = O(1) constant time space it doesn't depends on size of input.

 */
