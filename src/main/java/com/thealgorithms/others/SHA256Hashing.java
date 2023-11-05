package com.thealgorithms.others;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

/* Implements a SHA-256 algorithm to hash passwords or other messages
 * in a String format. Link to Wikipedia: https://en.wikipedia.org/wiki/SHA-2.
 */
public class SHA256Hashing {
    // Empty constructor
    private SHA256Hashing() {
    }


    /* Converts a String input to a hexadecimal String using a SHA-256 algorithm in 
     * the MessageDigest class
     * 
     * @param input the String to be hashed
     * @return a string of the hashed input
     */
    public static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(input.getBytes());

        // Convert the byte array to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        // for each byte in the byte array, convert to a HexString for readable format
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}