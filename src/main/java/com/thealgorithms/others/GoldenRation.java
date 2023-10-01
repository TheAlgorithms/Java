package com.thealgorithms.others;

import java.util.*;

class GoldenRatio{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    System.out.println(nthFibonacci(n));
  }

  public static long nthFibonacci(long n){
    double a = Math.sqrt(5);
    double b = Math.pow((1 + a) * 0.5, n);
    double c = Math.pow((1 - a) * 0.5, n);
    double ans = (1/a) * (b-c);
    return (long) ans;
  }
}
