package com.thealgorithms.others;

import java.util.*;

class GoldenRatio{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    System.out.println(nthFibonacci(n));
  }

  public static long nthFibonacci(long n){
        return (long) ((long)1 / Math.sqrt(5) * ((Math.pow(((1 + Math.sqrt(5))* 0.5), n)) - (Math.pow(((1 - Math.sqrt(5))* 0.5), n))));
    }
}
