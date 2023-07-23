package test.java.com.thealgorithms.BigInteger;

import java.math.BigInteger;
import java.util.Scanner;

public class ProbablePrime {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /*
         * BigInteger Class is use to do arithmetic operations on very large integer values 
         * which are not in range of Integer Class.
         * 
         * It is expensive to to check if such a BigInteger is Prime or Not Prime , That's why Probability is 
         * find out
         */

         BigInteger a=new BigInteger(in.next());
         /*
          * How to initialize BigInteger?
          * BigInteger A,B,C;
          * A=new BigInteger(String s);
          * B=BigInteger.valueOf(int n);
          * Some Constants are already there such as
          * C=BigInteger.ONE;
          */

        Boolean primBoolean=a.isProbablePrime(1);
        //isProbablePrime(int Certainity);
        //certaininty can be 0 , 1,-1
        // 0 and -1 always returns true , 1 actually checks if number is prime or not
        System.out.println(primBoolean);
        in.close();
    }
}