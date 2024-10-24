package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EgyptianFractionTest {

    @ParameterizedTest
    @MethodSource("fractionProvider")
    public void testGetEgyptianFraction(int numerator, int denominator, List<String> expected) {
        assertEquals(expected, EgyptianFraction.getEgyptianFraction(numerator, denominator));
    }

    private static Stream<Arguments> fractionProvider() {
        return Stream.of(Arguments.of(2, 3, List.of("1/2", "1/6")), Arguments.of(3, 10, List.of("1/4", "1/20")), Arguments.of(1, 3, List.of("1/3")), Arguments.of(1, 2, List.of("1/2")), Arguments.of(4, 13, List.of("1/4", "1/18", "1/468")));
    }
}
