package Maths;
//https://www.geeksforgeeks.org/factorial-large-number/

import java.math.BigInteger;
import java.util.Scanner;
public class Biginteger {
    static BigInteger factorial(int N)
    {
        // Initialize result
        BigInteger f
                = new BigInteger("1"); // Or BigInteger.ONE

        // Multiply f with 2, 3, ...N
        for (int i = 2; i <= N; i++)
            f = f.multiply(BigInteger.valueOf(i));

        return f;
    }

    // Driver method
    public static void main(String args[]) throws Exception
    {
        int N = 120;
        System.out.println(factorial(N));
    }
}
