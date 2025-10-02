package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Base64Test{

    @Test
    // Test encoding and decoding normal strings
    public void testEncodeAndDecode() {
        String original = "Hello, World!";
        String encoded = Base64Util.encode(original);
        String decoded = Base64Util.decode(encoded);

        assertEquals(original, decoded);
    }

    @Test
    // Test encoding and decoding empty string
    public void testEmptyString() {
        String encoded = Base64Util.encode("");
        String decoded = Base64Util.decode(encoded);

        assertEquals("", decoded);
    }

    @Test
    // Test encoding null input
    public void testNullInputEncode() {
        assertThrows(IllegalArgumentException.class, () -> Base64Util.encode(null));
    }

    @Test
    // Test decoding null input
    public void testNullInputDecode() {
        assertThrows(IllegalArgumentException.class, () -> Base64Util.decode(null));
    }

    @ParameterizedTest
    @CsvSource({
            "invalid@@base64",
            "12345$%",
            "====",
            "abc?def"
    })
        // Test decoding invalid Base64 strings
    void testInvalidBase64Decode(String invalidBase64) {
        assertThrows(IllegalArgumentException.class, () -> Base64Util.decode(invalidBase64));
    }

}

