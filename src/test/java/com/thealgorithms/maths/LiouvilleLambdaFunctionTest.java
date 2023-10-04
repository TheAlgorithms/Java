package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LiouvilleLambdaFunctionTest {

    @Test
    void testLiouvilleLambdaMustThrowExceptionIfNumberIsZero() {
        // given
        int number = 0;
        String expectedMessage = "Number must be greater than zero.";

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { LiouvilleLambdaFunction.liouvilleLambda(number); });
        String actualMessage = exception.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testLiouvilleLambdaMustThrowExceptionIfNumberIsNegative() {
        // given
        int number = -1;
        String expectedMessage = "Number must be greater than zero.";

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { LiouvilleLambdaFunction.liouvilleLambda(number); });
        String actualMessage = exception.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testLiouvilleLambdaMustReturnNegativeOne() {
        // given
        int number = 11;
        int expectedOutput = -1;

        // when
        int actualOutput = LiouvilleLambdaFunction.liouvilleLambda(number);

        // then
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testLiouvilleLambdaMustReturnPositiveOne() {
        // given
        int number = 10;
        int expectedOutput = 1;

        // when
        int actualOutput = LiouvilleLambdaFunction.liouvilleLambda(number);

        // then
        assertEquals(expectedOutput, actualOutput);
    }
}
