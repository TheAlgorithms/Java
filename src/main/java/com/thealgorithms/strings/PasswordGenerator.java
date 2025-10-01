
package com.thealgorithms.strings;
import java.util.Random;

/**
 * A simple password generator that creates random passwords
 * containing letters, digits, and special characters.
 */
public class PasswordGenerator {

    public static String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    // Example usage
    public static void main(String[] args) {
        System.out.println("Generated Password: " + generatePassword(12));
    }
}