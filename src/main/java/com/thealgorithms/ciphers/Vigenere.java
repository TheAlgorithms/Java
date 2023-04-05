package com.thealgorithms.ciphers;

/**
 * A Java implementation of Vigenere Cipher.
 *
 * @author straiffix
 * @author beingmartinbmc
 */

abstract class VigenereBase {
    protected int getKeyChar(final String key, final int index) {
        return key.charAt(index % key.length());
    }

    protected char shiftChar(final char c, final int shift) {
        if (!Character.isLetter(c)) {
            return c;
        }
        final int base = Character.isUpperCase(c) ? 'A' : 'a';
        return (char) (((c - base + shift) % 26 + 26) % 26 + base);
    }

    public abstract String process(final String message, final String key);
}

class VigenereEncryptor extends VigenereBase {
    @Override
    public String process(final String message, final String key) {
        final StringBuilder result = new StringBuilder();
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            final char c = message.charAt(i);
            final int shift = getKeyChar(key, j++) - 'a';
            result.append(shiftChar(c, shift));
        }
        return result.toString();
    }
}

class VigenereDecryptor extends VigenereBase {
    @Override
    public String process(final String message, final String key) {
        final StringBuilder result = new StringBuilder();
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            final char c = message.charAt(i);
            final int shift = 'z' - getKeyChar(key, j++) + 1;
            result.append(shiftChar(c, shift));
        }
        return result.toString();
    }
}

public class Vigenere {
    public static void main(final String[] args) {
        final String message = "HELLO WORLD";
        final String key = "SECRET";

        final VigenereEncryptor encryptor = new VigenereEncryptor();
        final VigenereDecryptor decryptor = new VigenereDecryptor();

        final String encrypted = encryptor.process(message, key);
        System.out.println("Encrypted message: " + encrypted);

        final String decrypted = decryptor.process(encrypted, key);
        System.out.println("Decrypted message: " + decrypted);
    }
}
