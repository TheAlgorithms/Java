package com.ciphers;

public class CaesarBruteForce {

    /** \
     * Recursively Brute forces a parsed encrypted text, trying out all shifting keys from 1-26, printing out all decryption attempts
     * @param message (String) The encrypted text.
     * @param Key (int) The key used to decrypt the encrypted text and is increment upon a recursive call.
     * @return (String) Concatenated string of all decryption attempts (For unit testing purposes).
     */
    public String decrypt(String message, int Key) {
        final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (Key > 26){ System.out.println(); return null; }

        StringBuilder plainText = new StringBuilder();
        for (char character : message.toUpperCase().toCharArray()) {
            int index = LETTERS.indexOf(character);

            if (index != -1) {
                index -= Key;
                //Wrap around index value range[1-26]
                if (index < 0) { index += LETTERS.length(); }
                plainText.append(LETTERS.toCharArray()[index]);
            } else { plainText.append(character); }
        }
        System.out.println(String.format("Current Decryption Key %d : %s", Key, plainText));
        return plainText.append(decrypt(message, Key+1)).toString();
    }
}
