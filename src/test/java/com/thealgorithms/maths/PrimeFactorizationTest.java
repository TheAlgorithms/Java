package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrimeFactorizationTest {

    @ParameterizedTest
    @MethodSource("provideNumbersAndFactors")
    void testPrimeFactorization(int number, List<Integer> expectedFactors) {
        assertEquals(expectedFactors, PrimeFactorization.pfactors(number), "Prime factors for number: " + number);
    }

    @ParameterizedTest
    @MethodSource("provideNumbersAndSizes")
    void testPrimeFactorsSize(int number, int expectedSize) {
        assertEquals(expectedSize, PrimeFactorization.pfactors(number).size(), "Size of prime factors list for number: " + number);
    }

    private static Stream<Arguments> provideNumbersAndFactors() {
        return Stream.of(Arguments.of(0, List.of()), Arguments.of(1, List.of()), Arguments.of(2, List.of(2)), Arguments.of(3, List.of(3)), Arguments.of(4, List.of(2, 2)), Arguments.of(18, List.of(2, 3, 3)), Arguments.of(100, List.of(2, 2, 5, 5)), Arguments.of(198, List.of(2, 3, 3, 11)));
    }

    private static Stream<Arguments> provideNumbersAndSizes() {
        return Stream.of(Arguments.of(2, 1), Arguments.of(3, 1), Arguments.of(4, 2), Arguments.of(18, 3), Arguments.of(100, 4), Arguments.of(198, 4));
    }
}
