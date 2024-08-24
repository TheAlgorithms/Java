package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Creates a random password from ASCII letters Given password length bounds
 *
 * @author AKS1996
 * @date 2017.10.25
 */
final class PasswordGen {
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*(){}?";
    private static final String ALL_CHARACTERS = UPPERCASE_LETTERS + LOWERCASE_LETTERS + DIGITS + SPECIAL_CHARACTERS;

    private PasswordGen() {
    }

    /**
     * Generates a random password with a length between minLength and maxLength.
     *
     * @param minLength The minimum length of the password.
     * @param maxLength The maximum length of the password.
     * @return A randomly generated password.
     * @throws IllegalArgumentException if minLength is greater than maxLength or if either is non-positive.
     */
    public static String generatePassword(int minLength, int maxLength) {
        if (minLength > maxLength || minLength <= 0 || maxLength <= 0) {
            throw new IllegalArgumentException("Incorrect length parameters: minLength must be <= maxLength and both must be > 0");
        }

        Random random = new Random();

        List<Character> letters = new ArrayList<>();
        for (char c : ALL_CHARACTERS.toCharArray()) {
            letters.add(c);
        }

        // Inbuilt method to randomly shuffle a elements of a list
        Collections.shuffle(letters);
        StringBuilder password = new StringBuilder();

        // Note that size of the password is also random
        for (int i = random.nextInt(maxLength - minLength) + minLength; i > 0; --i) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        return password.toString();
    }
}
