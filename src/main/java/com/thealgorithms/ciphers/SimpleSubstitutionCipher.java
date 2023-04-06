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

    public static String encode(String message, String cipherSmall) {
        String type = "encode";
        CipherMap cipherMap = new CipherMap(cipherSmall, type);
        return cipherMap.mapCharacters(message);
    }

    public static String decode(String encryptedMessage, String cipherSmall) {
        String type = "decode";
        CipherMap cipherMap = new CipherMap(cipherSmall, type);
        return cipherMap.reverseMapCharacters(encryptedMessage);
    }
}

/**
 * A CipherMap class that provides functionality to map characters using a substitution cipher.
 * It generates a map of character pairs where each character in the English alphabet
 * is mapped to another unique character in the provided cipher key.
 * This class provides two methods:
 *  1. mapCharacters: maps the characters in a given message using the cipher map.
 *  2. reverseMapCharacters: maps the characters in an encrypted message back to the original message using the cipher map.
*/
class CipherMap {
    private Map<Character, Character> cipherMap;

    public CipherMap(String cipherSmall, String type) {
        cipherMap = new HashMap<>();
        char beginSmallLetter = 'a';
        char beginCapitalLetter = 'A';
        cipherSmall = cipherSmall.toLowerCase();
        String cipherCapital = cipherSmall.toUpperCase();

        for (int i = 0; i < cipherSmall.length(); i++) {
            if(type == "encode"){
                cipherMap.put(beginSmallLetter++, cipherSmall.charAt(i));
                cipherMap.put(beginCapitalLetter++, cipherCapital.charAt(i));
            }
            if(type == "decode"){
                cipherMap.put(cipherSmall.charAt(i), beginSmallLetter++);
                cipherMap.put(cipherCapital.charAt(i), beginCapitalLetter++);   
            }
        }
    }

    public String mapCharacters(String message) {
        StringBuilder encoded = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (Character.isAlphabetic(message.charAt(i))) {
                encoded.append(cipherMap.get(message.charAt(i)));
            } else {
                encoded.append(message.charAt(i));
            }
        }

        return encoded.toString();
    }

    public String reverseMapCharacters(String encryptedMessage) {
        StringBuilder decoded = new StringBuilder();
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
