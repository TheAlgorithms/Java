package com.thealgorithms.maths.prime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.thealgorithms.maths.Prime.MobiusFunction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MobiusFunctionTest {

    @Test
    void testMobiusForZero() {
        // given
        int number = 0;
        String expectedMessage = "Number must be greater than zero.";

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { MobiusFunction.mobius(number); });
        String actualMessage = exception.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testMobiusForNegativeNumber() {
        // given
        int number = -1;
        String expectedMessage = "Number must be greater than zero.";

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { MobiusFunction.mobius(number); });
        String actualMessage = exception.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testMobiusFunction() {
        int[] expectedResultArray = {
            1,
            -1,
            -1,
            0,
            -1,
            1,
            -1,
            0,
            0,
            1,
            -1,
            0,
            -1,
            1,
            1,
            0,
            -1,
            0,
            -1,
            0,
            1,
            1,
            -1,
            0,
            0,
            1,
            0,
            0,
            -1,
            -1,
            -1,
            0,
            1,
            1,
            1,
            0,
            -1,
            1,
            1,
            0,
            -1,
            -1,
            -1,
            0,
            0,
            1,
            -1,
            0,
            0,
            0,
            1,
            0,
            -1,
            0,
            1,
            0,
            1,
            1,
            -1,
            0,
            -1,
            1,
            0,
            0,
            1,
            -1,
            -1,
            0,
            1,
            -1,
            -1,
            0,
            -1,
            1,
            0,
            0,
            1,
            -1,
            -1,
            0,
            0,
            1,
            -1,
            0,
            1,
            1,
            1,
            0,
            -1,
            0,
            1,
            0,
            1,
            1,
            1,
            0,
            -1,
            0,
            0,
            0,
        };

        for (int i = 1; i <= 100; i++) {
            // given
            int expectedValue = expectedResultArray[i - 1];

            // when
            int actualValue = MobiusFunction.mobius(i);

            // then
            assertEquals(expectedValue, actualValue);
        }
    }

    /**
     * Large inputs whose smallest square divisor test used to overflow, most notably
     * {@code Integer.MAX_VALUE}, whose square wraps around to 1 and made every number look like it
     * had a squared prime factor.
     */
    @ParameterizedTest
    @CsvSource({"2147483647, -1", "2147483646, 0", "2147483645, -1", "2147483644, 0", "2147483629, -1", "2147395600, 0", "1073741824, 0", "1073741789, -1", "999999937, -1", "999999999, 0", "2146689000, 0"})
    void testMobiusForLargeNumbers(int number, int expected) {
        assertEquals(expected, MobiusFunction.mobius(number));
    }
}
