package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordGenTest {

    @Test
    public void failGenerationWithSameMinMaxLengthTest() {
        int length = 10;
        assertThrows(IllegalArgumentException.class, () -> PasswordGen.generatePassword(length, length));
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
        assertThrows(IllegalArgumentException.class, () -> PasswordGen.generatePassword(minLength, maxLength));
    }

    @Test
    public void generatePasswordNonEmptyTest() {
        String tempPassword = PasswordGen.generatePassword(8, 16);
        assertTrue(tempPassword.length() != 0);
    }

    @Test
    public void testGeneratePasswordWithMinGreaterThanMax() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PasswordGen.generatePassword(12, 8));
        assertEquals("Incorrect length parameters: minLength must be <= maxLength and both must be > 0", exception.getMessage());
    }

    @Test
    public void testGeneratePasswordWithNegativeLength() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PasswordGen.generatePassword(-5, 10));
        assertEquals("Incorrect length parameters: minLength must be <= maxLength and both must be > 0", exception.getMessage());
    }

    @Test
    public void testGeneratePasswordWithZeroLength() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PasswordGen.generatePassword(0, 0));
        assertEquals("Incorrect length parameters: minLength must be <= maxLength and both must be > 0", exception.getMessage());
    }
}
