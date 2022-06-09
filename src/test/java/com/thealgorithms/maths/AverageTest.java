package com.thealgorithms.maths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AverageTest{
    @Test
    public void testArrayWithOneElement(){
        //Partition 1: array with 1 element
        int[] array = {3};
        assertEquals(3, Average.average(array));
    }

    @Test
    public void testArrayWithManyElements(){
        //Partition 2: array with more than 1 element
        int[] array = {9,6,8,5};
        assertEquals(7, Average.average(array));
    }

    @Test
    public void testForEmptyArray(){
        //Partition 3: array with no element
        int[] array = {};
        fail("Cannot find the average of an empty array");
    }
}