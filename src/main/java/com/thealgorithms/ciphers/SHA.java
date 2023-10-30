package com.thealgorithms.ciphers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author suxiaocha123 on 23-Oct-30.
 */
public class SHA {
    public static void main(String[] args) {
        String input = "BlockChain";

        try {
            // Create a MessageDigest object with the SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convert the input string to a byte array
            byte[] data = input.getBytes();

            // Calculate the hash value
            byte[] hash = digest.digest(data);

            // Convert the hash value to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }

            String sha256Hash = hexString.toString();

            System.out.println("SHA-256 Hash: " + sha256Hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}