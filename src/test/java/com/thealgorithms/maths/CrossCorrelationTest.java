package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/**
 * Test class for CrossCorrelation class
 *
 * @author Athina-Frederiki Swinkels
 * @version 1.0
 */
public class CrossCorrelationTest {

    @Test
    void testCrossCorrelation(){
        double[] x={1,2,1,1};
        double[] y={1,1,2,1};
        double[] x_y={1,4,6,6,5,2,1};
        double[] x1={1,2,3};
        double[] y1={1,2,3,4,5};
        double[] x1_y1={5,14,26,20,14,8,3};
        double[] y1_x1={3,8,14,20,26,14,5};
        assertArrayEquals(x_y,CrossCorrelation.crossCorrelation(x,y));
        assertArrayEquals(x1_y1,CrossCorrelation.crossCorrelation(x1,y1));
        assertArrayEquals(y1_x1,CrossCorrelation.crossCorrelation(y1,x1));
    }

}
