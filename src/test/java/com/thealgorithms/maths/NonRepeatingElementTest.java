package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class NonRepeatingElementTest {

    private record TestData(int[] input, int[] expected) {
    }

    private static Stream<TestData> provideTestCases() {
        return Stream.of(new TestData(new int[] {1, 2, 1, 3, 2, 4}, new int[] {3, 4}), new TestData(new int[] {-1, -2, -1, -3, -2, -4}, new int[] {-3, -4}), new TestData(new int[] {-1, 2, 2, -3, -1, 4}, new int[] {-3, 4}));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testFindNonRepeatingElements(TestData testData) {
        int[] result = NonRepeatingElement.findNonRepeatingElements(testData.input);
        assertArrayEquals(testData.expected, result);
    }

    @Test
    public void testFindNonRepeatingElementsWithLargeNumbers() {
        assertArrayEquals(new int[] {200000, 400000}, NonRepeatingElement.findNonRepeatingElements(new int[] {100000, 200000, 100000, 300000, 400000, 300000}));
    }
}
