package com.thealgorithms.ciphers;

import java.util.HashMap;
import java.util.Map;

/**
 * The simple substitution cipher is a cipher that has been in use for many
 * hundreds of years (an excellent history is given in Simon Singhs 'the Code
 * Book'). It basically consists of substituting every plaintext character for a
 * different ciphertext character. It differs from the Caesar cipher in that the
 * cipher alphabet is not simply the alphabet shifted, it is completely jumbled.
 *
 * @author Hassan Elseoudy
 */
public class SimpleSubstitutionCipher {

    /**
     * Encrypt text by replacing each element with its opposite character.
     *
     * @return Encrypted message
     */
    public static String encode(String message, String cipherSmall) {
        StringBuilder encoded = new StringBuilder();

        // This map is used to encode
        Map<Character, Character> cipherMap = new HashMap<>();

        char beginSmallLetter = 'a';
        char beginCapitalLetter = 'A';

        cipherSmall = cipherSmall.toLowerCase();
        String cipherCapital = cipherSmall.toUpperCase();

        // To handle Small and Capital letters
        for (int i = 0; i < cipherSmall.length(); i++) {
            cipherMap.put(beginSmallLetter++, cipherSmall.charAt(i));
            cipherMap.put(beginCapitalLetter++, cipherCapital.charAt(i));
        }

        for (int i = 0; i < message.length(); i++) {
            if (Character.isAlphabetic(message.charAt(i))) {
                encoded.append(cipherMap.get(message.charAt(i)));
            } else {
                encoded.append(message.charAt(i));
            }
        }

        return encoded.toString();
    }

    /**
     * Decrypt message by replacing each element with its opposite character in
     * cipher.
     *
     * @return message
     */
    public static String decode(String encryptedMessage, String cipherSmall) {
        StringBuilder decoded = new StringBuilder();

        Map<Character, Character> cipherMap = new HashMap<>();

        char beginSmallLetter = 'a';
        char beginCapitalLetter = 'A';

        cipherSmall = cipherSmall.toLowerCase();
        String cipherCapital = cipherSmall.toUpperCase();

        for (int i = 0; i < cipherSmall.length(); i++) {
            cipherMap.put(cipherSmall.charAt(i), beginSmallLetter++);
            cipherMap.put(cipherCapital.charAt(i), beginCapitalLetter++);
        }

        for (int i = 0; i < encryptedMessage.length(); i++) {
            if (Character.isAlphabetic(encryptedMessage.charAt(i))) {
                decoded.append(cipherMap.get(encryptedMessage.charAt(i)));
            } else {
                decoded.append(encryptedMessage.charAt(i));
            }
        }

        return decoded.toString();
    }
}
