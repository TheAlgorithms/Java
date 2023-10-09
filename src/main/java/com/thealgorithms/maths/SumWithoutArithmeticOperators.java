package com.thealgorithms.maths;

public class SumWithoutArithmeticOperators {

    /**
     * Calculate the sum of two numbers a and b without using any arithmetic operators (+, -, *, /).
     * All the integers associated are unsigned 32-bit integers
     *https://stackoverflow.com/questions/365522/what-is-the-best-way-to-add-two-numbers-without-using-the-operator
     *@param a - It is the first number
     *@param b - It is the second number
     *@return returns an integer which is the sum of the first and second number
     */

    public int getSum(int a, int b) {
        if (b == 0) return a;
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return getSum(sum, carry);
    }
}
