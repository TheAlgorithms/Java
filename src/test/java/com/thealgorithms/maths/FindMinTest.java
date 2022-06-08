package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMinTest {

   @Test
    public void testFindMinValue(){
        assertEquals(1, FindMin.findMin(new int[] {1,2,3,4,5,6,7,8,9,10}));
    }
    //Find The Min value in array
    
	@Test
    void findMinAtFinalPosition() {
	//Is the min value at final position in the array
		int arr[] = {6,9,33,77,2};
		FindMin test = new FindMin();
		int output = test.min(arr);
		assertEquals(2, output);
	}
    
    @Test
    void findMinAtFirstPosition() {
	//Is the min value at first position in the array
		int arr[] = {-5,9,33,77,2};
		FindMin test = new FindMin();
		int output = test.min(arr);
	    assertEquals(-5, output);
	}


    @Test
    void findMinAtMidPosition() {
	//Is the min value at mid position in the array
		int arr[] = {10,9,-5,77,2};
		FindMin test = new FindMin();
		int output = test.min(arr);
		assertEquals(-5, output);
	}
	

    @Test
    void findMinAtAnyPosition() {
	//Is the min value at any position in the array
		int arr[] = {10,9,77,-5,2};
		FindMin test = new FindMin();
		int output = test.min(arr);
		assertEquals(-5, output);
	}
} 