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
 * The CipherMap class is responsible for creating a mapping between characters used in encoding/decoding of messages.
 * It creates a map from the input cipher strings.
 */
class CipherMap {
    private Map<Character, Character> cipherMap;

    /**
     * Constructs a CipherMap object.
     *
     * @param cipherSmall a string of lowercase letters to be used in encoding/decoding.
     * @param type the type of operation to be performed (encode/decode).
     */
    public CipherMap(String cipherSmall, String type) {
        cipherMap = new HashMap<>();
        char beginSmallLetter = 'a';
        char beginCapitalLetter = 'A';
        cipherSmall = cipherSmall.toLowerCase();
        String cipherCapital = cipherSmall.toUpperCase();

        for (int i = 0; i < cipherSmall.length(); i++) {
            if(type.equals("encode")){
                cipherMap.put(beginSmallLetter++, cipherSmall.charAt(i));
                cipherMap.put(beginCapitalLetter++, cipherCapital.charAt(i));
            }
            if(type.equals("decode")){
                cipherMap.put(cipherSmall.charAt(i), beginSmallLetter++);
                cipherMap.put(cipherCapital.charAt(i), beginCapitalLetter++);
            }
        }
    }

    /**
     * Maps the characters in the message to their encoded characters.
     *
     * @param message the message to be encoded.
     * @return the encoded message as a string.
     */
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

    /**
     * Maps the characters in the encrypted message to their original characters.
     *
     * @param encryptedMessage the message to be decoded.
     * @return the decoded message as a string.
     */
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
