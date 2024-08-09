package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ColumnarTranspositionCipherTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void encrypt(String key, String word) {
        assertNotEquals(word, ColumnarTranspositionCipher.encrypt(word, key));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void decrypt(String key, String word) {
        String encrypted = ColumnarTranspositionCipher.encrypt(word, key);
        assertEquals(word, ColumnarTranspositionCipher.decrypt(encrypted, key));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of("asd215", "This is a test of the Columnar Transposition Cipher"), //
            Arguments.of("test123456", "test"), //
            Arguments.of("my long secret pass 1234567890", "short message"), //
            Arguments.of("secret", "My secret message") //
        );
    }
}
