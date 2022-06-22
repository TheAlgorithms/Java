package com.thealgorithms.maths;

public class GCD {


    public static int gcd(int num1, int num2) {
        if (num1 < 0 || num2 < 0) {
            throw new ArithmeticException();
        }

        if (num1 == 0 || num2 == 0) {
            return Math.abs(num1 - num2);
        }

        while (num1 % num2 != 0) {
            int remainder = num1 % num2;
            num1 = num2;
            num2 = remainder;
        }
        return num2;
    }


    public static int gcd(int[] number) {
        int result = number[0];
        for (int i = 1; i < number.length; i++) // call gcd function (input two value)
        {
            result = gcd(result, number[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] myIntArray = {4, 16, 32};

        // call gcd function (input array)
        System.out.println(gcd(myIntArray)); // => 4
        System.out.printf("gcd(40,24)=%d gcd(24,40)=%d%n", gcd(40, 24), gcd(24, 40)); // => 8
    }
}
