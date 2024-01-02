package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test class for AutoCorrelation class
 *
 * @author Athina-Frederiki Swinkels
 * @version 2.0
 */

public class AutoCorrelationTest {

    @ParameterizedTest
    @CsvSource({"1;2;1;1, 1;3;5;7;5;3;1", "1;2;3, 3;8;14;8;3", "1.5;2.3;3.1;4.2, 6.3;14.31;23.6;34.79;23.6;14.31;6.3"})

    public void testAutoCorrelationParameterized(String input, String expected) {
        double[] array = convertStringToArray(input);
        double[] expectedResult = convertStringToArray(expected);

        double[] result = AutoCorrelation.autoCorrelation(array);

        assertArrayEquals(expectedResult, result, 0.0001);
    }

    private double[] convertStringToArray(String input) {
        String[] elements = input.split(";");
        double[] result = new double[elements.length];
        for (int i = 0; i < elements.length; i++) {
            result[i] = Double.parseDouble(elements[i]);
        }
        return result;
    }
}
