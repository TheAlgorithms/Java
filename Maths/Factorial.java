package Maths;

import java.util.*;

public class Factorial {

    // Method to find factorial of given number 
    static int factorial(int n) 
    { 
        int res = 1, i; 
        for (i=2; i<=n; i++) 
            res *= i; 
        return res; 
    } 
      
    // Driver method 
    public static void main(String[] args)  
    { 
        try (Scanner sc = new Scanner(System.in)) {
			int num=sc.nextInt();//enter the you want
			System.out.println("Factorial of "+ num + " is " + factorial(5));
		} 
    } 
}
