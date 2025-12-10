package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test class for CrossCorrelation class
 *
 * @author Athina-Frederiki Swinkels
 * @version 2.0
 */
public class CrossCorrelationTest {

    @ParameterizedTest
    @CsvSource({"1;2;1;1, 1;1;2;1, 1;4;6;6;5;2;1", "1;2;3, 1;2;3;4;5, 5;14;26;20;14;8;3", "1;2;3;4;5, 1;2;3, 3;8;14;20;26;14;5", "1.5;2.3;3.1;4.2, 1.1;2.2;3.3, 4.95;10.89;16.94;23.21;12.65;4.62"})

    public void testCrossCorrelationParameterized(String input1, String input2, String expected) {
        double[] array1 = convertStringToArray(input1);
        double[] array2 = convertStringToArray(input2);
        double[] expectedResult = convertStringToArray(expected);

        double[] result = CrossCorrelation.crossCorrelation(array1, array2);

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
