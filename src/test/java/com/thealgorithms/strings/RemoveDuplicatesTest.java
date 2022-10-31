package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveDuplicatesTest {
  
  int[] intArray_1 = new int[]{ 1,1,2 }; 
  int[] intArray_2 = new int[]{ 0,0,1,1,1,2,2,3,3,4}; 

    @Test
    void testOne() {
        assertEquals(2, RemoveDuplicates.removeDuplicates(intArray_1));
    }

    @Test
    void testTwo() {
        assertEquals(5, RemoveDuplicates.removeDuplicates(intArray_2));
    }

}
