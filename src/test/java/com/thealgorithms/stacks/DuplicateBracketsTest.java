package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class DuplicateBracketsTest {

    @ParameterizedTest
    @CsvSource({"'((a + b) + (c + d))'", "'(a + b)'", "'a + b'", "'('", "''", "'a + (b * c) - d'", "'(x + y) * (z)'", "'(a + (b - c))'"})
    void testInputReturnsFalse(String input) {
        assertFalse(DuplicateBrackets.check(input));
    }

    @ParameterizedTest
    @CsvSource({"'(a + b) + ((c + d))'", "'((a + b))'", "'((((a + b)))))'", "'((x))'", "'((a + (b)))'", "'(a + ((b)))'", "'(((a)))'", "'(((())))'"})
    void testInputReturnsTrue(String input) {
        assertTrue(DuplicateBrackets.check(input));
    }

    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> DuplicateBrackets.check(null));
    }

    @ParameterizedTest(name = "Should be true: \"{0}\"")
    @MethodSource("provideInputsThatShouldReturnTrue")
    void testDuplicateBracketsTrueCases(String input) {
        assertTrue(DuplicateBrackets.check(input));
    }

    static Stream<Arguments> provideInputsThatShouldReturnTrue() {
        return Stream.of(Arguments.of("()"), Arguments.of("(( ))"));
    }

    @ParameterizedTest(name = "Should be false: \"{0}\"")
    @MethodSource("provideInputsThatShouldReturnFalse")
    void testDuplicateBracketsFalseCases(String input) {
        assertFalse(DuplicateBrackets.check(input));
    }

    static Stream<Arguments> provideInputsThatShouldReturnFalse() {
        return Stream.of(Arguments.of("( )"), // whitespace inside brackets
            Arguments.of("abc + def"), // no brackets
            Arguments.of("(a + (b * c)) - (d / e)") // complex, but no duplicates
        );
    }
}
