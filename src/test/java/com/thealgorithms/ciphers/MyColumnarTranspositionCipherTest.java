package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyColumnarTranspositionCipherTest {
    private String keyword;
    private String plaintext;

    @BeforeEach
    public void setUp() {
        keyword = "keyword";
        plaintext = "This is a test message for Columnar Transposition Cipher";
    }

    @Test
    void shouldNotProduceNullOrEmptyEncryptedText() {
        String encrypted = ColumnarTranspositionCipher.encrypt(plaintext, keyword);
        
        assertNotNull(encrypted, "Encrypted text should not be null");
        assertFalse(encrypted.isEmpty(), "Encrypted text should not be empty");
    }

    @Test
    void shouldChangePlaintextToEncrypted() {
        String encrypted = ColumnarTranspositionCipher.encrypt(plaintext, keyword);

        assertNotEquals(plaintext, encrypted, "Encrypted text should differ from plaintext");
    }

    @Test
    void shouldEncryptDetermenistically() {
        String encrypted1 = ColumnarTranspositionCipher.encrypt(plaintext, keyword);
        String encrypted2 = ColumnarTranspositionCipher.encrypt(plaintext, keyword);

        assertEquals(encrypted1, encrypted2, "Encryptions should be equal");
    }

    @Test
    void shouldProduceDifferentEncryptionsWithDifferentKeywoards() {
        String keyword2 = keyword + "a";

        String encrypted1 = ColumnarTranspositionCipher.encrypt(plaintext, keyword);
        String encrypted2 = ColumnarTranspositionCipher.encrypt(plaintext, keyword2);

        assertNotEquals(encrypted1, encrypted2, "Should produce different encryptions");
    }

    @Test
    void shouldMatchWithUnsortedKeyword() {
        String myPlaintext = "123456789";
        String myKeyword = "unsorted";

        String encrypted = ColumnarTranspositionCipher.encrypt(myPlaintext, myKeyword);
        String expected = "8≈7≈2≈4≈5≈3≈6≈19";
        
        assertEquals(expected, encrypted, "Should match");
    }

    @Test
    void shouldMatchEncryptionAndDecryptionWithNoSpacesOrPadding() {
        String myPlaintext = "NoSpacesOrPadding";

        String encrypted = ColumnarTranspositionCipher.encrypt(myPlaintext, keyword);
        String decrypted = ColumnarTranspositionCipher.decrypt();

        assertEquals(myPlaintext, decrypted, "Decrypted text should match original plaintext");
    }

    @Test
    void shouldNotContainPaddingInDecryption() {
        String myPlaintext = "text";

        String encrypted = ColumnarTranspositionCipher.encrypt(myPlaintext, keyword);
        String decrypted = ColumnarTranspositionCipher.decrypt();

        assertFalse(decrypted.contains("≈"), "Should not contain padding characters");
    }

    @Test
    void shouldEncryptWithKeywordLongerThanPlaintext() {
        String myPlaintext = "text";
        String myKeyword = "averylongkeyword";

        String encryption = ColumnarTranspositionCipher.encrypt(myPlaintext, myKeyword);

        assertNotNull(encryption, "Should encrypt where plaintext.length() < keyword.length()");
    }

    @Test
    void shouldEncryptWithKeywordWithSameLengthAsPlaintext() {
        String myPlaintext = "textaslongaskeyword";
        String myKeyword = "keywordaslongastext";

        String encryption = ColumnarTranspositionCipher.encrypt(myPlaintext, myKeyword);

        assertNotNull(encryption, "Should encrypt where plaintext.length() == keyword.length()");
    }

    @Test
    void shouldProduceDifferentEncryptionForSameKeywordButSortedDifferently() {
        String unsertedKeyword1 = "EFGHABCD";
        String unsertedKeyword2 = "AEBFCGDH";

        String encrypted1 = ColumnarTranspositionCipher.encrypt(plaintext, unsertedKeyword1);
        String encrypted2 = ColumnarTranspositionCipher.encrypt(plaintext, unsertedKeyword2);

        assertNotEquals(encrypted1, encrypted2, "Should differ with different keywords");
    }

    @Test
    void shouldEncryptWithCustomAbecedarium() {
        String myAbecedarium = "abcdefghijklmnopqrstuvwxyz";

        String encryption = ColumnarTranspositionCipher.encrypt(plaintext, keyword, myAbecedarium);

        assertNotNull(encryption, "Should encrypt with custom abecedarium");
    }

    @Test
    void shouldNotEncryptWithInvalidAbecedarium() {
        String myAbecedarium = "abcde";

        assertThrows(NullPointerException.class, () -> ColumnarTranspositionCipher.encrypt(plaintext, keyword, myAbecedarium), "Should throw error when keyword contains characters not present in abecedarium");
    }
}
