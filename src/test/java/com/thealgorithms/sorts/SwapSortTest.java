package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SwapSortTest {

	
	@Test
	void swapSortWithIntegerArrayShouldPass()
	{
		SwapSort swapsort=new SwapSort();
		
	    Integer[] array = {3, 7, 45, 1, 33, 5, 2, 9};
	    Integer[] expected = {1, 2, 3, 5, 7, 9, 33, 45};
	   
	    swapsort.sort(array);
	    assertArrayEquals(expected, array);
	}
	
	
	@Test
	void swapSortWitStringArrayShouldPass()
	{
		SwapSort swapsort=new SwapSort();
		
	    String[] array = {"banana", "berry", "orange", "grape", "peach", "cherry", "apple", "pineapple"};
	    String[] expected = {"apple", "banana", "berry", "cherry", "grape", "orange", "peach", "pineapple"};
	   
	    swapsort.sort(array);
	    assertArrayEquals(expected, array);
	}
	

}
