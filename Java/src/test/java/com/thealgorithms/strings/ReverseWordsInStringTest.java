package com.thealgorithms.strings;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ReverseWordsInStringTest {
    @ParameterizedTest
    @MethodSource("inputStream")
    void numberTests(String expected, String input) {
        Assertions.assertEquals(expected, ReverseWordsInString.reverseWordsInString(input));
    }

    private static Stream<Arguments> inputStream() {
        return Stream.of(Arguments.of("blue is Sky", "Sky is blue"), Arguments.of("blue is Sky", "Sky \n is \t \n  blue "), Arguments.of("", ""), Arguments.of("", "    "), Arguments.of("", "\t"));
    }
}
