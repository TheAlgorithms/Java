package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class AverageTest {
    double [] double_numbers = {3.5, 6.5, 9, 12};
    int [] int_numbers = {6,7,8,4};
    @Test 
    //partition : test average value of array of double numbers

    public void testAverage_double() {
        
        Assertions.assertEquals(7.75, Average.average(double_numbers));
    }
    @Test
    //partition: test average value of array of integer numbers
    public void testAverage_int(){
        Assertions.assertEquals(7.75, Average.average(int_numbers));
    }

}


