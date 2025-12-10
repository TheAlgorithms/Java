package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class CeilTest {

    @ParameterizedTest
    @CsvSource({"7.057, 8", "7.004, 8", "-13.004, -13", "0.98, 1", "-11.357, -11"})
    void testCeil(double input, int expected) {
        assertEquals(expected, Ceil.ceil(input));
    }

    @ParameterizedTest
    @MethodSource("edgeCaseProvider")
    void testEdgeCases(TestData data) {
        assertEquals(Ceil.ceil(data.input), data.expected);
    }

    record TestData(double input, double expected) {
    }

    static Stream<TestData> edgeCaseProvider() {
        return Stream.of(new TestData(Double.MAX_VALUE, Double.MAX_VALUE), new TestData(Double.MIN_VALUE, Math.ceil(Double.MIN_VALUE)), new TestData(0.0, Math.ceil(0.0)), new TestData(-0.0, Math.ceil(-0.0)), new TestData(Double.NaN, Math.ceil(Double.NaN)),
            new TestData(Double.NEGATIVE_INFINITY, Math.ceil(Double.NEGATIVE_INFINITY)), new TestData(Double.POSITIVE_INFINITY, Math.ceil(Double.POSITIVE_INFINITY)));
    }
}
