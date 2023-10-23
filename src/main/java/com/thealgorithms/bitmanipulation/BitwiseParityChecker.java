package com.thealgorithms.bitmanipulation;

// Problem Link: https://en.wikipedia.org/wiki/Parity_bit

public class BitwiseParityChecker {
    // Check if the number has even parity
    public static boolean checkEvenParity(int num) {
        int count = 0;
        while (num > 0) {
            count ^= (num & 1); // XOR operation to count set bits
            num >>= 1; // Right shift to check the next bit
        }
        return count == 0; // Even parity if count is 0
    }

    // Check if the number has odd parity
    public static boolean checkOddParity(int num) {
        return !checkEvenParity(num); // Odd parity is the opposite of even parity
    }

    public static void main(String[] args) {
        int number = 0b1101101; // Example binary number (1101101 in binary)

        boolean isEvenParity = checkEvenParity(number);
        boolean isOddParity = checkOddParity(number);

        System.out.println("Binary Number: " + Integer.toBinaryString(number));
        System.out.println("Has Even Parity: " + isEvenParity);
        System.out.println("Has Odd Parity: " + isOddParity);
    }
}
