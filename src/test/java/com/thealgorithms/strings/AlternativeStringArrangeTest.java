package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AlternativeStringArrangeTest {

    // Method to provide test data
    private static Stream<Object[]> provideTestData() {
        return Stream.of(new Object[] {"abc", "12345", "a1b2c345"}, new Object[] {"abcd", "12", "a1b2cd"}, new Object[] {"", "123", "123"}, new Object[] {"abc", "", "abc"}, new Object[] {"a", "1", "a1"}, new Object[] {"ab", "12", "a1b2"}, new Object[] {"abcdef", "123", "a1b2c3def"},
            new Object[] {"ab", "123456", "a1b23456"});
    }

    // Parameterized test using the provided test data
    @ParameterizedTest(name = "{0} and {1} should return {2}")
    @MethodSource("provideTestData")
    void arrangeTest(String input1, String input2, String expected) {
        assertEquals(expected, AlternativeStringArrange.arrange(input1, input2));
    }

    @ParameterizedTest(name = "null input ({0}, {1}) should throw IllegalArgumentException")
    @MethodSource("provideNullInputs")
    void arrangeThrowsOnNullInput(String input1, String input2) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> AlternativeStringArrange.arrange(input1, input2));
        assertEquals("Input strings must not be null", ex.getMessage());
    }

    private static Stream<Arguments> provideNullInputs() {
        return Stream.of(Arguments.of(null, "abc"), Arguments.of("abc", null), Arguments.of(null, null));
    }
}
