package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StockProfitCalculatorTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testMaxProfit(int[] prices, int expected) {
        assertEquals(expected, StockProfitCalculator.maxProfit(prices));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new int[] {7, 1, 5, 3, 6, 4}, 5), Arguments.of(new int[] {7, 6, 4, 3, 1}, 0), Arguments.of(new int[] {5, 5, 5, 5, 5}, 0), Arguments.of(new int[] {10}, 0), Arguments.of(new int[] {1, 5}, 4), Arguments.of(new int[] {2, 4, 1, 3, 7, 5}, 6));
    }
}
