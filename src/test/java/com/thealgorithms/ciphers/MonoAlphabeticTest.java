package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MonoAlphabeticTest {

    private static final String key = "MNBVCXZLKJHGFDSAPOIUYTREWQ";

    // Test for encryption
    @ParameterizedTest
    @MethodSource("provideEncryptionData")
    public void testEncrypt(String data, String key, String expected) {
        assertEquals(expected, MonoAlphabetic.encrypt(data, key));
    }

    // Test for decryption
    @ParameterizedTest
    @MethodSource("provideDecryptionData")
    public void testDecrypt(String data, String key, String expected) {
        assertEquals(expected, MonoAlphabetic.decrypt(data, key));
    }

    // Provide test data for encryption
    private static Stream<Arguments> provideEncryptionData() {
        return Stream.of(Arguments.of("HELLO", key, "YQJJQ"), Arguments.of("JAVA", key, "HQLI"));
    }

    // Provide test data for decryption
    private static Stream<Arguments> provideDecryptionData() {
        return Stream.of(Arguments.of("YQJJQ", key, "HELLO"), Arguments.of("HQLI", key, "JAVA"));
    }
}
