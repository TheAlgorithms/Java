package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/**
 * Test class for AutoCorrelation class
 *
 * @author Athina-Frederiki Swinkels
 * @version 1.0
 */
public class AutoCorrelationTest {

    @Test
    void testAutoCorrelation(){
        double[] x={1,2,1,1};
        double[] y={1,2,3,4,5};
        double[] xx={1,3,5,7,5,3,1};
        double[] yy={5,14,26,40,55,40,26,14,5};

        assertArrayEquals(xx,AutoCorrelation.autoCorrelation(x));
        assertArrayEquals(yy,AutoCorrelation.autoCorrelation(y));
    }

}