// https://en.wikipedia.org/wiki/One-time_pad

import java.util.Random;

public class OneTimePad {

    // Function to generate a random key of the same length as the message
    private static String generateKey(int length) {
        Random random = new Random();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < length; i++) {
            key.append((char) (random.nextInt(26) + 'A')); // Assuming uppercase letters
        }
        return key.toString();
    }

    // Function to encrypt the message using OTP
    private static String encrypt(String message, String key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i);
            char keyChar = key.charAt(i);
            // XOR the characters to get the ciphertext character
            char encryptedChar = (char) (messageChar ^ keyChar);
            ciphertext.append(encryptedChar);
        }
        return ciphertext.toString();
    }

    // Function to decrypt the ciphertext using OTP (which is the same as encryption)
    private static String decrypt(String ciphertext, String key) {
        return encrypt(ciphertext, key);
    }

    public static void main(String[] args) {
        String message = "HELLOOTP";
        
        // Generate a random key of the same length as the message
        String key = generateKey(message.length());

        // Encrypt the message
        String encryptedMessage = encrypt(message, key);

        // Decrypt the ciphertext
        String decryptedMessage = decrypt(encryptedMessage, key);

        System.out.println("Original Message: " + message);
        System.out.println("Key: " + key);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
