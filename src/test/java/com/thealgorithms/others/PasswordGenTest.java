package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PasswordGenTest {

    @Test
    public void failGenerationWithSameMinMaxLengthTest() {
        int length = 10;
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                PasswordGen.generatePassword(length, length);
            }
        );
    }

    @Test
    public void generateOneCharacterPassword() {
        String tempPassword = PasswordGen.generatePassword(1, 2);
        assertTrue(tempPassword.length() == 1);
    }

    @Test
    public void failGenerationWithMinLengthSmallerThanMaxLengthTest() {
        int minLength = 10;
        int maxLength = 5;
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                PasswordGen.generatePassword(minLength, maxLength);
            }
        );
    }

    @Test
    public void generatePasswordNonEmptyTest() {
        String tempPassword = PasswordGen.generatePassword(8, 16);
        assertTrue(tempPassword.length() != 0);
    }
}
