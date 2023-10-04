package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SimpleSubstitutionCipherTest {

    @Test
    void testEncode() {
        // Given
        String message = "HELLOWORLD";
        String key = "phqgiumeaylnofdxjkrcvstzwb";

        // When
        String actual = SimpleSubstitutionCipher.encode(message, key);

        // Then
        assertEquals("EINNDTDKNG", actual);
    }

    @Test
    void testDecode() {
        // Given
        String message = "EINNDTDKNG";
        String key = "phqgiumeaylnofdxjkrcvstzwb";

        // When
        String actual = SimpleSubstitutionCipher.decode(message, key);

        // Then
        assertEquals("HELLOWORLD", actual);
    }

    @Test
    void testIsTextTheSameAfterEncodeAndDecode() {
        // Given
        String text = "HELLOWORLD";
        String key = "phqgiumeaylnofdxjkrcvstzwb";

        // When
        String encodedText = SimpleSubstitutionCipher.encode(text, key);
        String decodedText = SimpleSubstitutionCipher.decode(encodedText, key);

        // Then
        assertEquals(text, decodedText);
    }
}
