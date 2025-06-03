package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public final class IsomorphicTest {

    @ParameterizedTest
    @MethodSource("isomorphicProvider")
    public void testCheckStrings(String str1, String str2, boolean expected) {
        assertEquals(expected, Isomorphic.checkStrings(str1, str2));
    }
    
    private static Stream<Arguments> isomorphicProvider() {
        return Stream.of(
            Arguments.of("abbbbaac", "kffffkkd", true),
            Arguments.of("xyxyxy", "bnbnbn", true),
            Arguments.of("ghjknnmm", "wertpopo", false),
            Arguments.of("aaammmnnn", "ggghhhbbj", false)
        );
    }
}
