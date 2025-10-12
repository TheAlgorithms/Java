package com.thealgorithms.strings;

import java.util.Scanner;

public class StringRotation {

    /**
     * Checks if str2 is a rotation of str1
     * @param str1 Original string
     * @param str2 String to check for rotation
     * @return true if str2 is a rotation of str1, false otherwise
     */
    public static boolean isRotation(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }

        if (str1.length() != str2.length()) {
            return false;
        }

        String concatenated = str1 + str1;
        return concatenated.contains(str2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();

        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();

        boolean result = isRotation(str1, str2);

        if (result) {
            System.out.println("\"" + str2 + "\" is a rotation of \"" + str1 + "\".");
        } else {
            System.out.println("\"" + str2 + "\" is NOT a rotation of \"" + str1 + "\".");
        }

        scanner.close();
    }
}
