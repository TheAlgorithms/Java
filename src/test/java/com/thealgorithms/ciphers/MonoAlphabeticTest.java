package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MonoAlphabeticTest {

    // Test for both encryption and decryption with different keys
    @ParameterizedTest
    @MethodSource("provideTestData")
    public void testEncryptDecrypt(String plainText, String key, String encryptedText) {
        // Test encryption
        assertEquals(encryptedText, MonoAlphabetic.encrypt(plainText, key));

        // Test decryption
        assertEquals(plainText, MonoAlphabetic.decrypt(encryptedText, key));
    }

    // Provide test data for both encryption and decryption
    private static Stream<Arguments> provideTestData() {
        return Stream.of(Arguments.of("HELLO", "MNBVCXZLKJHGFDSAPOIUYTREWQ", "LCGGS"), Arguments.of("JAVA", "MNBVCXZLKJHGFDSAPOIUYTREWQ", "JMTM"), Arguments.of("HELLO", "QWERTYUIOPLKJHGFDSAZXCVBNM", "UJJYU"), Arguments.of("JAVA", "QWERTYUIOPLKJHGFDSAZXCVBNM", "KZHS"));
    }
}
