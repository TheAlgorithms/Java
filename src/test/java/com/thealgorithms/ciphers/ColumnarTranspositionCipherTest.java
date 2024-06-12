package com.thealgorithms.ciphers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ColumnarTranspositionCipherTest {

    String keywordForExample = "asd215";
    String wordBeingEncrypted = "This is a test of the Columnar Transposition Cipher";

    @Test
    void encrpyter() {
        assertNotEquals(wordBeingEncrypted, ColumnarTranspositionCipher.encrpyter(wordBeingEncrypted, keywordForExample));
    }

    @Test
    void decrypter() {
        String encrypted=ColumnarTranspositionCipher.encrpyter(wordBeingEncrypted, keywordForExample);
        assertEquals(wordBeingEncrypted, ColumnarTranspositionCipher.decrypter());
    }
}
