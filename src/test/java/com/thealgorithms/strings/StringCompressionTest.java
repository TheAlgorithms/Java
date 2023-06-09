package com.thealgorithms.strings;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringCompressionTest {
    @ParameterizedTest
    @CsvSource({"a,a", "aabbb,a2b3", "abbbc,ab3c", "aabccd,a2bc2d"})
    void stringCompressionTest(String input, String expectedOutput) {
        String output = StringCompression.compress(input);
        assertEquals(expectedOutput, output);
    }
}
