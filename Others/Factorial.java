import java.util.*;
import java.lang.*;
import java.io.*;
class Sunil { 
      
    // This function finds factorial of  
    // large numbers and prints them 
    static void factorial(int n) 
    { 
        int res[] = new int[500]; 
  
        // Initialize result 
        res[0] = 1; 
        int res_size = 1; 
        for (int x = 2; x <= n; x++) 
            res_size = multiply(x, res, res_size); 
  
        System.out.println("Factorial of given number is "); 
        for (int i = res_size - 1; i >= 0; i--) 
            System.out.print(res[i]); 
    } 
    static int multiply(int x, int res[], int res_size) 
    { 
        int carry = 0; // Initialize carry 
  
        // One by one multiply n with individual  
        // digits of res[] 
        for (int i = 0; i < res_size; i++) 
        { 
            int prod = res[i] * x + carry; 
            res[i] = prod % 10;  
                               
            carry = prod/10;
  
        // Put carry in res and increase result size 
        while (carry!=0) 
        { 
            res[res_size] = carry % 10; 
            carry = carry / 10; 
            res_size++; 
        } 
        return res_size; 
    } 
  
    // Driver program 
    public static void main(String args[]) 
    { 
        factorial(100); 
    } 
} 
