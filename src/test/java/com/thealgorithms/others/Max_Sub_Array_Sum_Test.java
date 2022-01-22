package com.thealgorithms.others;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.Max_Sub_Array_Sum;
public class Max_Sub_Array_Sum_Test {
    @Test
	void testForOneElement() 
	{
        int a[]={-1};
		assertTrue(Max_Sub_Array_Sum.max_Sum(a));
	}

	@Test
	void testForTwoElements() 
	{
        int a[]={-2,1};
		assertFalse(Max_Sub_Array_Sum.max_Sum(a));
	}

	@Test
	void testForThreeElements() 
	{
		int a[]={5,3,12};
		assertTrue(Max_Sub_Array_Sum.max_Sum(a));
	}

	@Test
	void testForFourElements() 
	{
		int a[]={-1,-3,-7,-4};
		assertFalse(Max_Sub_Array_Sum.max_Sum(a));
	}

	@Test
	void testForFiveElements() 
	{
		int a[]={4,5,3,0,2};
		assertTrue(Max_Sub_Array_Sum.max_Sum(a));
	}


	@Test
	void testForSixElements() 
	{
		int a[]={-43,-45,47,12,87,-13};
		assertFalse(Max_Sub_Array_Sum.max_Sum(a));
	}

	@Test
	void testForSevenElements() 
	{
		int a[]={9,8,2,23,13,6,7};
		assertTrue(Max_Sub_Array_Sum.max_Sum(a));
    }

	@Test
	void testForEightElements() 
	{
		int a[]={9,-5,-5,-2,4,5,0,1};
		assertFalse(Max_Sub_Array_Sum.max_Sum(a));
	}
}
