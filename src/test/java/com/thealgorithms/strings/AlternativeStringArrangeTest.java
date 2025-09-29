package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlternativeStringArrangeTest {

    // Method to provide test data
    private static Stream<Object[]> provideTestData() {
        return Stream.of(
                new Object[]{"abc", "12345", "a1b2c345"},
                new Object[]{"abcd", "12", "a1b2cd"},
                new Object[]{"", "123", "123"},
                new Object[]{"abc", "", "abc"},
                new Object[]{"a", "1", "a1"},
                new Object[]{"ab", "12", "a1b2"},
                new Object[]{"abcdef", "123", "a1b2c3def"},
                new Object[]{"ab", "123456", "a1b23456"}
        );
    }

    // Parameterized test using the provided test data
    @ParameterizedTest(name = "{0} and {1} should return {2}")
    @MethodSource("provideTestData")
    void arrangeTest(String input1, String input2, String expected) {
        assertEquals(expected, AlternativeStringArrange.arrange(input1, input2));
    }

    // Testing private constructor to prevent instantiation
    @Test
    void preventInstantiationTest() {
        try {
            var constructor = AlternativeStringArrange.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getCause().getClass());
            assertEquals("Utility class", e.getCause().getMessage());
        }
    }
}
