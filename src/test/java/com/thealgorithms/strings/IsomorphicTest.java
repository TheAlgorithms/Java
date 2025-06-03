package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public final class IsomorphicTest {

    @ParameterizedTest
    @MethodSource("inputs")
    public void testCheckStrings(String str1, String str2, Boolean expected) {
        assertEquals(expected, Isomorphic.checkStrings(str1, str2));
        assertEquals(expected, Isomorphic.checkStrings(str2, str1));
    }

    private static Stream<Arguments> inputs() {
        return Stream.of(Arguments.of("", "", Boolean.TRUE), Arguments.of("", "a", Boolean.FALSE), Arguments.of("aaa", "aa", Boolean.FALSE), Arguments.of("abbbbaac", "kffffkkd", Boolean.TRUE), Arguments.of("xyxyxy", "bnbnbn", Boolean.TRUE), Arguments.of("ghjknnmm", "wertpopo", Boolean.FALSE),
            Arguments.of("aaammmnnn", "ggghhhbbj", Boolean.FALSE));
    }
}
