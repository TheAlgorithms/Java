package com.thealgorithms.maths;

/**
 * This is Extended Euclid Algorithm which is used to finda a way to represent GCD 
 * in terms of a and b , i.e. coefficients x and y 
 * 
 *
 * @author ahmeh360 02/10/22
 */

pubilc class ExtendedEuclid {

    /**
     * get combination value
     *
     * @param num1 the first number
     * @param num2 the second number
     * @param num3 the first coeffecient
     * @param num4 the second coefficient
     * @return gcd value
     */

    public static int gcdExtended(int a, int b, int x, int y)
    {
        if (a == 0) {
            x = 0;
            y = 1;
            return b;
        }
  
        int x1 = 1, y1 = 1; 
        int gcd = gcdExtended(b % a, a, x1, y1);
  
        x = y1 - (b / a) * x1;
        y = x1;
  
        return gcd;
    }
  
    public static void main(String[] args)
    {
        int x = 1, y = 1;
        int a = 35, b = 15;
        int g = gcdExtended(a, b, x, y);
        System.out.print("gcd(" + a + ", " + b + ") = " + g);
    }
}

