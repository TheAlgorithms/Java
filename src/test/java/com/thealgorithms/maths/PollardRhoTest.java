package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PollardRhoTest {

    @Test
    void testPollardRhoForNumber315MustReturn5() {
        //given
        int number = 315;
        int expectedResult = 5;

        //when
        int actualResult = PollardRho.pollardRho(number);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testPollardRhoForNumber187MustReturn11() {
        //given
        int number = 187;
        int expectedResult = 11;

        //when
        int actualResult = PollardRho.pollardRho(number);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testPollardRhoForNumber239MustThrowException() {
        //given
        int number = 239;
        String expectedMessage = "GCD cannot be found.";

        //when
        Exception exception = assertThrows(
            RuntimeException.class,
            () -> {
                PollardRho.pollardRho(number);
            }
        );
        String actualMessage = exception.getMessage();

        //then
        assertEquals(expectedMessage, actualMessage);
    }
}
