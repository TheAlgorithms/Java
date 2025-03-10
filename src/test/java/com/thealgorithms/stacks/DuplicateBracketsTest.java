package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DuplicateBracketsTest {

    @ParameterizedTest
    @CsvSource({"'((a + b) + (c + d))'", "'(a + b)'", "'a + b'", "'('", "''"})
    void testInputReturnsFalse(String input) {
        assertFalse(DuplicateBrackets.check(input));
    }

    @ParameterizedTest
    @CsvSource({"'(a + b) + ((c + d))'", "'((a + b))'", "'((((a + b)))))'"})
    void testInputReturnsTrue(String input) {
        assertTrue(DuplicateBrackets.check(input));
    }

    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> DuplicateBrackets.check(null));
    }
}
