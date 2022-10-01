package com.thealgorithms.maths;

// Euler's Totient Function takes in 
// some value n and returns the number of integers in {1,...,n-1} 
// that are relatively prime to n. 
// https://en.wikipedia.org/wiki/Euler%27s_totient_function

public class TotientFunction
{
    //Recursive GCD function
    public static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    //Simple iterative Euler Totient Function
    public static int phi(int n)
    {
        //Initialize result
        int totient = 1;
        //Loop over the set of n-1 numbers
        for (int i = 2; i < n; ++i)
        {
            //i is relatively prime to n if gcd(i, n) == 1 so we increment totient
            if (gcd(i, n) == 1)
                totient++;
        }
        //return the resulting totient
        return totient;
    }
}