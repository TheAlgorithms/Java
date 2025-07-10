package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.maths.Ceil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CeilTest {

    @ParameterizedTest
    @CsvSource({"7.057, 8", "7.004, 8", "-13.004, -13", "0.98, 1", "-11.357, -11"})
    void testCeil(double input, int expected) {
        assertEquals(expected, Ceil.ceil(input));
    }
}
