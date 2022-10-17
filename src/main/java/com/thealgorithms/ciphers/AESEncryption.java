package com.thealgorithms.ciphers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;

/**
 * This example program shows how AES encryption and decryption can be done in
 * Java. Please note that secret key and encrypted text is unreadable binary and
 * hence in the following program we display it in hexadecimal format of the
 * underlying bytes.
 */
public class AESEncryption {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static Cipher aesCipher;

    /**
     * 1. Generate a plain text for encryption 2. Get a secret key (printed in
     * hexadecimal form). In actual use this must by encrypted and kept safe.
     * The same key is required for decryption.
     */
    public static void main(String[] args) throws Exception {
        String plainText = "Hello World";
        SecretKey secKey = getSecretEncryptionKey();
        byte[] cipherText = encryptText(plainText, secKey);
        String decryptedText = decryptText(cipherText, secKey);

        System.out.println("Original Text:" + plainText);
        System.out.println(
            "AES Key (Hex Form):" + bytesToHex(secKey.getEncoded())
        );
        System.out.println(
            "Encrypted Text (Hex Form):" + bytesToHex(cipherText)
        );
        System.out.println("Descrypted Text:" + decryptedText);
    }

    /**
     * gets the AES encryption key. In your actual programs, this should be
     * safely stored.
     *
     * @return secKey (Secret key that we encrypt using it)
     * @throws NoSuchAlgorithmException (from KeyGenrator)
     */
    public static SecretKey getSecretEncryptionKey()
        throws NoSuchAlgorithmException {
        KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");
        aesKeyGenerator.init(128); // The AES key size in number of bits
        return aesKeyGenerator.generateKey();
    }

    /**
     * Encrypts plainText in AES using the secret key
     *
     * @return byteCipherText (The encrypted text)
     * @throws NoSuchPaddingException (from Cipher)
     * @throws NoSuchAlgorithmException (from Cipher)
     * @throws InvalidKeyException (from Cipher)
     * @throws BadPaddingException (from Cipher)
     * @throws IllegalBlockSizeException (from Cipher)
     */
    public static byte[] encryptText(String plainText, SecretKey secKey)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        return aesCipher.doFinal(plainText.getBytes());
    }

    /**
     * Decrypts encrypted byte array using the key used for encryption.
     *
     * @return plainText
     */
    public static String decryptText(byte[] byteCipherText, SecretKey secKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, aesCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE, secKey, gcmParameterSpec);
        byte[] bytePlainText = decryptionCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }

    /**
     * Convert a binary byte array into readable hex form Old library is
     * deprecated on OpenJdk 11 and this is faster regarding other solution is
     * using StringBuilder
     *
     * @return hexHash
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
