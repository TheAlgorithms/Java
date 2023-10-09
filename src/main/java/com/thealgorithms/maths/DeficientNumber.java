package com.thealgorithms.maths;
import java.util.*;

//A deficient number is a number for which the sum of its proper divisors is less than n.
//This  program  takes an integer n as input and outputs "Yes" if it is a deficient number, and "No" otherwise.
public class DeficientNumber {
    /**
     * Check if {@code n} is palindrome number or not
     *
     * @param number the number
     * @return {@code true} if {@code n} is palindrome number, otherwise
     * {@code false}
     */

    public static boolean isDeficientNumber(long number){
        if (number < 0) {
            throw new IllegalArgumentException("Input parameter must not be negative!");
        }
        long t = number;
        long sum =1;
        int sqr = (int)Math.sqrt(number);
        for(int i=2;i<=sqr;i++){
            if(number%i==0)
            sum+=i+number/i;
        }
        if((long) sqr *sqr==number)
        sum-=sqr;
        return sum<t;
    }
    // public static void main(String[] args){
        // Scanner in = new Scanner(System.in);
        // System.out.println("Enter the number:");
        // long n = in.nextLong();
        //System.out.println(isDeficientNumber(n));
    // }
    
}
