package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PermutationCipherTest {

    private final PermutationCipher cipher = new PermutationCipher();

    @Test
    void testBasicEncryption() {
        // given
        String plaintext = "HELLO";
        int[] key = {3, 1, 2}; // Move 3rd position to 1st, 1st to 2nd, 2nd to 3rd

        // when
        String encrypted = cipher.encrypt(plaintext, key);

        // then
        // "HELLO" becomes "HEL" + "LOX" (padded)
        // "HEL" with key {3,1,2} becomes "LHE" (L=3rd, H=1st, E=2nd)
        // "LOX" with key {3,1,2} becomes "XLO" (X=3rd, L=1st, O=2nd)
        assertEquals("LHEXLO", encrypted);
    }

    @Test
    void testBasicDecryption() {
        // given
        String ciphertext = "LHEXLO";
        int[] key = {3, 1, 2};

        // when
        String decrypted = cipher.decrypt(ciphertext, key);

        // then
        assertEquals("HELLO", decrypted);
    }

    @Test
    void testEncryptDecryptRoundTrip() {
        // given
        String plaintext = "THIS IS A TEST MESSAGE";
        int[] key = {4, 2, 1, 3};

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("THISISATESTMESSAGE", decrypted); // Spaces are removed during encryption
    }

    @Test
    void testSingleCharacterKey() {
        // given
        String plaintext = "ABCDEF";
        int[] key = {1}; // Identity permutation

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("ABCDEF", encrypted); // Should remain unchanged
        assertEquals("ABCDEF", decrypted);
    }

    @Test
    void testLargerKey() {
        // given
        String plaintext = "PERMUTATION";
        int[] key = {5, 3, 1, 4, 2}; // 5-character permutation

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("PERMUTATION", decrypted);
    }

    @Test
    void testExactBlockSize() {
        // given
        String plaintext = "ABCDEF"; // Length 6, divisible by key length 3
        int[] key = {2, 3, 1};

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("ABCDEF", decrypted);
    }

    @Test
    void testEmptyString() {
        // given
        String plaintext = "";
        int[] key = {2, 1, 3};

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("", encrypted);
        assertEquals("", decrypted);
    }

    @Test
    void testNullString() {
        // given
        String plaintext = null;
        int[] key = {2, 1, 3};

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals(null, encrypted);
        assertEquals(null, decrypted);
    }

    @Test
    void testStringWithSpaces() {
        // given
        String plaintext = "A B C D E F";
        int[] key = {2, 1};

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("ABCDEF", decrypted); // Spaces should be removed
    }

    @Test
    void testLowercaseConversion() {
        // given
        String plaintext = "hello world";
        int[] key = {3, 1, 2};

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("HELLOWORLD", decrypted); // Should be converted to uppercase
    }

    @Test
    void testInvalidKeyNull() {
        // given
        String plaintext = "HELLO";
        int[] key = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt(plaintext, key));
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt(plaintext, key));
    }

    @Test
    void testInvalidKeyEmpty() {
        // given
        String plaintext = "HELLO";
        int[] key = {};

        // when & then
        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt(plaintext, key));
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt(plaintext, key));
    }

    @Test
    void testInvalidKeyOutOfRange() {
        // given
        String plaintext = "HELLO";
        int[] key = {1, 2, 4}; // 4 is out of range for key length 3

        // when & then
        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt(plaintext, key));
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt(plaintext, key));
    }

    @Test
    void testInvalidKeyZero() {
        // given
        String plaintext = "HELLO";
        int[] key = {0, 1, 2}; // 0 is invalid (should be 1-based)

        // when & then
        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt(plaintext, key));
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt(plaintext, key));
    }

    @Test
    void testInvalidKeyDuplicate() {
        // given
        String plaintext = "HELLO";
        int[] key = {1, 2, 2}; // Duplicate position

        // when & then
        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt(plaintext, key));
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt(plaintext, key));
    }

    @Test
    void testInvalidKeyMissingPosition() {
        // given
        String plaintext = "HELLO";
        int[] key = {1, 3}; // Missing position 2

        // when & then
        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt(plaintext, key));
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt(plaintext, key));
    }

    @Test
    void testReverseKey() {
        // given
        String plaintext = "ABCD";
        int[] key = {4, 3, 2, 1}; // Reverse order

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("DCBA", encrypted); // Should be reversed
        assertEquals("ABCD", decrypted);
    }

    @Test
    void testSpecificExampleFromDescription() {
        // given
        String plaintext = "HELLO";
        int[] key = {3, 1, 2};

        // when
        String encrypted = cipher.encrypt(plaintext, key);

        // then
        // Block 1: "HEL" -> positions {3,1,2} -> "LHE"
        // Block 2: "LOX" -> positions {3,1,2} -> "XLO"
        assertEquals("LHEXLO", encrypted);
        // Verify decryption
        String decrypted = cipher.decrypt(encrypted, key);
        assertEquals("HELLO", decrypted);
    }

    @Test
    void testPaddingCharacterGetter() {
        // when
        char paddingChar = cipher.getPaddingChar();

        // then
        assertEquals('X', paddingChar);
    }

    @Test
    void testLongText() {
        // given
        String plaintext = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG";
        int[] key = {4, 1, 3, 2};

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG", decrypted);
    }

    @Test
    void testIdentityPermutation() {
        // given
        String plaintext = "IDENTITY";
        int[] key = {1, 2, 3, 4}; // Identity permutation

        // when
        String encrypted = cipher.encrypt(plaintext, key);
        String decrypted = cipher.decrypt(encrypted, key);

        // then
        assertEquals("IDENTITY", encrypted); // Should remain unchanged
        assertEquals("IDENTITY", decrypted);
    }

    @Test
    void testEmptyStringRemovePadding() {
        // given - Test to cover line 178 (empty string case in removePadding)
        String ciphertext = "";
        int[] key = {2, 1, 3};

        // when
        String decrypted = cipher.decrypt(ciphertext, key);

        // then
        assertEquals("", decrypted); // Should return empty string directly
    }

    @Test
    void testBlockShorterThanKey() {
        // given - Test to cover line 139 (block length != key length case)
        // This is a defensive case where permuteBlock might receive a block shorter than key
        // We can test this by manually creating a scenario with malformed ciphertext
        String malformedCiphertext = "AB"; // Length 2, but key length is 3
        int[] key = {3, 1, 2}; // Key length is 3

        // when - This should trigger the padding logic in permuteBlock during decryption
        String decrypted = cipher.decrypt(malformedCiphertext, key);

        // then - The method should handle the short block gracefully
        // "AB" gets padded to "ABX", then permuted with inverse key {2,3,1}
        // inverse key {2,3,1} means: pos 2→1st, pos 3→2nd, pos 1→3rd = "BXA"
        // Padding removal only removes trailing X's, so "BXA" remains as is
        assertEquals("BXA", decrypted);
    }
}
