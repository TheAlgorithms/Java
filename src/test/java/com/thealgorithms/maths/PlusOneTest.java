package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlusOneTest {

int[] intArray_1 = new int[]{ 1,2,4 }; 
int[] intArray_2 = new int[]{ 4,3,2,2 }; 
int[] intArray_3 = new int[]{ 1,0}; 

int[] intArray_1_test = new int[]{ 1,2,3 }; 
int[] intArray_2_test = new int[]{ 4,3,2,1 }; 
int[] intArray_3_test = new int[]{ 9}; 

    @Test
    void testOne() {
        assertEquals(Arrays.toString(intArray_1), Arrays.toString(PlusOne.plusOne(intArray_1_test)));
    }

      @Test
    void testTwo() {
        assertEquals(Arrays.toString(intArray_2), Arrays.toString(PlusOne.plusOne(intArray_2_test)));
    }


      @Test
    void testThree() {
        assertEquals(Arrays.toString(intArray_3), Arrays.toString(PlusOne.plusOne(intArray_3_test)));
    }

}
