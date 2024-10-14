package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AtbashTest {

    @Test
    public void atbashEncrypt() {
        AtbashCipher normalToEncrypt = new AtbashCipher("Hello World! 123, @cipher abcDEF ZYX 987 madam zzZ Palindrome!");
        String expectedText = "Svool Dliow! 123, @xrksvi zyxWVU ABC 987 nzwzn aaA Kzormwilnv!";

        normalToEncrypt.setString(normalToEncrypt.convert());

        assertEquals(expectedText, normalToEncrypt.getString());
    }

    @Test
    public void atbashDecrypt() {
        AtbashCipher encryptToNormal = new AtbashCipher("Svool Dliow! 123, @xrksvi zyxWVU ABC 987 nzwzn aaA Kzormwilnv!");
        String expectedText = "Hello World! 123, @cipher abcDEF ZYX 987 madam zzZ Palindrome!";

        encryptToNormal.setString(encryptToNormal.convert());

        assertEquals(expectedText, encryptToNormal.getString());
    }
}
