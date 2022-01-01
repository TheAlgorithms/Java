package com.thealgorithms.ciphers;

import java.util.Scanner;

/**
 * A Java implementation of Caesar Cipher. /It is a type of substitution cipher
 * in which each letter in the plaintext is replaced by a letter some fixed
 * number of positions down the alphabet. /
 *
 * @author FAHRI YARDIMCI
 * @author khalil2535
 */
public class Caesar {

    /**
     * Encrypt text by shifting every Latin char by add number shift for ASCII
     * Example : A + 1 -> B
     *
     * @return Encrypted message
     */
    public static String encode(String message, int shift) {
        StringBuilder encoded = new StringBuilder();

        shift %= 26;

        final int length = message.length();
        for (int i = 0; i < length; i++) {

            //            int current = message.charAt(i); //using char to shift characters because ascii
            // is in-order latin alphabet
            char current = message.charAt(i); // Java law : char + int = char

            if (IsCapitalLatinLetter(current)) {

                current += shift;
                encoded.append((char) (current > 'Z' ? current - 26 : current)); // 26 = number of latin letters

            } else if (IsSmallLatinLetter(current)) {

                current += shift;
                encoded.append((char) (current > 'z' ? current - 26 : current)); // 26 = number of latin letters

            } else {
                encoded.append(current);
            }
        }
        return encoded.toString();
    }

    /**
     * Decrypt message by shifting back every Latin char to previous the ASCII
     * Example : B - 1 -> A
     *
     * @return message
     */
    public static String decode(String encryptedMessage, int shift) {
        StringBuilder decoded = new StringBuilder();

        shift %= 26;

        final int length = encryptedMessage.length();
        for (int i = 0; i < length; i++) {
            char current = encryptedMessage.charAt(i);
            if (IsCapitalLatinLetter(current)) {

                current -= shift;
                decoded.append((char) (current < 'A' ? current + 26 : current)); // 26 = number of latin letters

            } else if (IsSmallLatinLetter(current)) {

                current -= shift;
                decoded.append((char) (current < 'a' ? current + 26 : current)); // 26 = number of latin letters

            } else {
                decoded.append(current);
            }
        }
        return decoded.toString();
    }

    /**
     * @return true if character is capital Latin letter or false for others
     */
    private static boolean IsCapitalLatinLetter(char c) {
        return c >= 'A' && c <= 'Z';
    }

    /**
     * @return true if character is small Latin letter or false for others
     */
    private static boolean IsSmallLatinLetter(char c) {
        return c >= 'a' && c <= 'z';
    }
    /**
     *  @return string array which contains all the possible decoded combination.
     */
    public static String[] bruteforce(String encryptedMessage) {
        String[] listOfAllTheAnswers = new String[27];
        for (int i=0; i<=26; i++) {
            listOfAllTheAnswers[i] = decode(encryptedMessage, i);
        }

        return listOfAllTheAnswers;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int shift = 0;
        System.out.println("Please enter the message (Latin Alphabet)");
        String message = input.nextLine();
        System.out.println(message);
        System.out.println("(E)ncode or (D)ecode or (B)ruteforce?");
        char choice = input.next().charAt(0);
        switch (choice) {
            case 'E':
            case 'e':
                System.out.println("Please enter the shift number");
                shift = input.nextInt() % 26;
                System.out.println(
                        "ENCODED MESSAGE IS \n" + encode(message, shift)); // send our function to handle
                break;
            case 'D':
            case 'd':
                System.out.println("Please enter the shift number");
                shift = input.nextInt() % 26;
                System.out.println("DECODED MESSAGE IS \n" + decode(message, shift));
                break;
            case 'B':
            case 'b':
                String[] listOfAllTheAnswers = bruteforce(message);
                for (int i =0; i<=26; i++) {
                    System.out.println("FOR SHIFT " + String.valueOf(i) + " decoded message is " + listOfAllTheAnswers[i]);
                }
            default:
                System.out.println("default case");
        }
        
        input.close();
    }
}
