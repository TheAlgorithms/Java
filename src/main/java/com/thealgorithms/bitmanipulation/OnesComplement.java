package com.thealgorithms.bitmanipulation;

import java.util.Scanner;

public class OnesComplement {

    // Function to get the 1's complement of a binary number
    public static String onesComplement(String binary) {
        StringBuilder complement = new StringBuilder();
        
        // Invert each bit to get the 1's complement
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                complement.append('1');
            } else {
                complement.append('0');
            }
        }
        return complement.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input a binary number
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        String binary = Integer.toBinaryString(number);

        // Compute and print the 1's complement
        String result = onesComplement(binary);
        int n = Integer.parseInt(result, 2);
        System.out.println("1's complement of " + number +"("+binary+")"+" is: " + n + "("+result+")");

        scanner.close();
    }
}

