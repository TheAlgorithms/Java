package com.thealgorithms.maths;

import java.util.Scanner;

public class PowerUsingBinaryExponentiation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the base:");
        int base = sc.nextInt();
        
        System.out.println("Enter the exponent:");
        int exponent = sc.nextInt();
        
        System.out.println("Enter the mod value (a prime number):");
        int mod = sc.nextInt();
        
        long result = calculatePower(base, exponent, mod);
        
        System.out.println("Result: " + result);
        
        sc.close();
    }
    
    public static long calculatePower(int base, int exponent, int mod) {
        if (exponent == 0) {
            return 1 % mod;
        }
        
        long p = calculatePower(base, exponent / 2, mod);
        
        if (exponent % 2 == 0) {
            // Even exponent
            return (p * p) % mod;
        } else {
            // Odd exponent
            if (base < 0) {
                // Handle negative remainder in Java due to a negative base.
                return (((p * p) % mod) * (mod + (base % mod))) % mod;
            } else {
                return (((p * p) % mod) * (base % mod)) % mod;
            }
        }
    }
}
