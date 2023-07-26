package com.thealgorithms.maths;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AmicableNumberTest {
    private static final String INVALID_RANGE_EXCEPTION_MESSAGE = "Given range of values is invalid!";
    private static final String INVALID_NUMBERS_EXCEPTION_MESSAGE = "Input numbers must be natural!";

    @Test
    public void testShouldThrowExceptionWhenInvalidRangeProvided() {
        checkInvalidRange(0, 0);
        checkInvalidRange(0, 1);
        checkInvalidRange(1, 0);
        checkInvalidRange(10, -1);
        checkInvalidRange(-1, 10);
    }

    @Test
    public void testShouldThrowExceptionWhenInvalidNumbersProvided() {
        checkInvalidNumbers(0, 0);
        checkInvalidNumbers(0, 1);
        checkInvalidNumbers(1, 0);
    }

    @Test
    public void testAmicableNumbers() {
        assertThat(AmicableNumber.isAmicableNumber(220, 284)).isTrue();
        assertThat(AmicableNumber.isAmicableNumber(1184, 1210)).isTrue();
        assertThat(AmicableNumber.isAmicableNumber(2620, 2924)).isTrue();
    }

    @Test
    public void testShouldFindAllAmicableNumbersInRange() {
        // given
        var expectedResult = Set.of(Pair.of(220, 284), Pair.of(1184, 1210), Pair.of(2620, 2924));

        // when
        Set<Pair<Integer, Integer>> result = AmicableNumber.findAllInRange(1, 3000);

        // then
        Assertions.assertTrue(result.containsAll(expectedResult));
    }

    private static void checkInvalidRange(int from, int to) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> AmicableNumber.findAllInRange(from, to));
        Assertions.assertEquals(exception.getMessage(), INVALID_RANGE_EXCEPTION_MESSAGE);
    }

    private static void checkInvalidNumbers(int a, int b) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> AmicableNumber.isAmicableNumber(a, b));
        Assertions.assertEquals(exception.getMessage(), INVALID_NUMBERS_EXCEPTION_MESSAGE);
    }
}
