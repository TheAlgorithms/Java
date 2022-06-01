package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FindMinTest {

    @Test
    public void findMinPositiveValueTest(){
	FindMin min = new FindMin();
        assertEquals(6, min.findMin(new int[] {100,20,6,12,15,21,30,45,19,10}));
 
   }

    @Test
    public void findMinNegativeValueTest(){
	FindMin min = new FindMin();
        assertEquals(-6, min.findMin(new int[] {1,2,-6,12,15,21,3,5,19,10}));
    }
}