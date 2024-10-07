package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CountCharTest {

    @ParameterizedTest(name = "\"{0}\" should have {1} non-whitespace characters")
    @CsvSource({"'', 0", "'   ', 0", "'a', 1", "'abc', 3", "'a b c', 3", "'   a   b   c   ', 3", "'\tabc\n', 3", "'  a   b\tc  ', 3", "' 12345 ', 5", "'Hello, World!', 12"})
    @DisplayName("Test countCharacters with various inputs")
    void testCountCharacters(String input, int expected) {
        assertEquals(expected, CountChar.countCharacters(input));
    }

    @Test
    @DisplayName("Test countCharacters with null input")
    void testCountCharactersNullInput() {
        assertEquals(0, CountChar.countCharacters(null));
    }
}
