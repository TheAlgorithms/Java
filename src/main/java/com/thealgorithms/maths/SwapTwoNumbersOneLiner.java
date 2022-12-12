package com.thealgorithms.maths;

public class SwapTwoNumbersOneLiner {
    public static void main(String[] args) {
        int x,y;
        x = 7;
        y = 9;
      
        System.out.println("Before Swap");
        System.out.println("x: = " + x);
        System.out.println("y: = " + y);

        y = x+y-(x=y);
        System.out.println("After Swap");
        System.out.println("x: = " + x);
        System.out.println("y: = " + y);
    }
}
