package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BalancedBracketsTest {

    @ParameterizedTest
    @MethodSource("providePairedBracketsForTrue")
    void testIsPairedTrue(char opening, char closing) {
        assertTrue(BalancedBrackets.isPaired(opening, closing));
    }

    @ParameterizedTest
    @MethodSource("providePairedBracketsForFalse")
    void testIsPairedFalse(char opening, char closing) {
        assertFalse(BalancedBrackets.isPaired(opening, closing));
    }

    @ParameterizedTest
    @MethodSource("provideBalancedStringsForTrue")
    void testIsBalancedTrue(String input) {
        assertTrue(BalancedBrackets.isBalanced(input));
    }

    @ParameterizedTest
    @MethodSource("provideBalancedStringsForFalse")
    void testIsBalancedFalse(String input) {
        assertFalse(BalancedBrackets.isBalanced(input));
    }

    @Test
    void testIsBalancedNull() {
        assertThrows(IllegalArgumentException.class, () -> BalancedBrackets.isBalanced(null));
    }

    private static Stream<Arguments> providePairedBracketsForTrue() {return Stream.of(Arguments.of('(', ')'), Arguments.of('[', ']'), Arguments.of('{', '}'), Arguments.of('<', '>'));
    }

    private static Stream<Arguments> providePairedBracketsForFalse() {
        return Stream.of(Arguments.of('(', ']'), Arguments.of('[', ')'), Arguments.of('{', '>'), Arguments.of('<', ')'), Arguments.of('a', 'b'), Arguments.of('!', '@'));
    }

    private static Stream<Arguments> provideBalancedStringsForTrue() {
        return Stream.of(Arguments.of("[()]{}{[()()]()}"), Arguments.of("()"), Arguments.of("[]"), Arguments.of("{}"), Arguments.of("<>"), Arguments.of("[{<>}]"), Arguments.of(""));
    }

    private static Stream<Arguments> provideBalancedStringsForFalse() {
        return Stream.of(Arguments.of("[(])"), Arguments.of("([)]"), Arguments.of("{[<]>}"), Arguments.of("["), Arguments.of(")"), Arguments.of("[{"), Arguments.of("]"), Arguments.of("[a+b]"), Arguments.of("a+b"));
    }
}
