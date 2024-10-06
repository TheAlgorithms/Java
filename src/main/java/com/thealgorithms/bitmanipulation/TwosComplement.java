package com.thealgorithms.bitmanipulation;

import java.util.Scanner;

public class TwosComplement {

    // Function to get the 2's complement of a binary number
    public static String twosComplement(String binary) {
        StringBuilder onesComplement = new StringBuilder();
        
        // Step 1: Find the 1's complement (invert the bits)
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                onesComplement.append('1');
            } else {
                onesComplement.append('0');
            }
        }

        // Step 2: Add 1 to the 1's complement
        StringBuilder twosComplement = new StringBuilder(onesComplement);
        boolean carry = true;
        for (int i = onesComplement.length() - 1; i >= 0; i--) {
            if (onesComplement.charAt(i) == '1' && carry) {
                twosComplement.setCharAt(i, '0');
            } else if (onesComplement.charAt(i) == '0' && carry) {
                twosComplement.setCharAt(i, '1');
                carry = false;
            }
        }

        // If there is still a carry, append '1' at the beginning
        if (carry) {
            twosComplement.insert(0, '1');
        }

        return twosComplement.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input a binary number
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        String binary = Integer.toBinaryString(n);

        // Compute and print the 2's complement
        String result = twosComplement(binary);
        int num = Integer.parseInt(result, 2);
        System.out.println("2's complement of " + n +"("+binary+")" +" is: " + num + "("+ result+")");

        scanner.close();
    }
}
