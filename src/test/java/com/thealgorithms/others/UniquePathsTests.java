package com.thealgorithms.others;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.UniquePaths;


public class UniquePathsTests {
    @Test
	void testForOneElement() 
	{
		assertTrue(UniquePaths.uniquePaths(3,7,28));
	}

	@Test
	void testForTwoElements() 
	{
		assertTrue(UniquePaths.uniquePaths(3,2,3));
	}

	@Test
	void testForThreeElements() 
	{
		assertTrue(UniquePaths.uniquePaths(3,3,6));
	}

	@Test
	void testForFourElements() 
	{
		assertTrue(UniquePaths.uniquePaths(4,6,56));
	}

	@Test
	void testForFiveElements() 
	{
		assertTrue(UniquePaths.uniquePaths2(3,5,15));
	}

	@Test
	void testForSixElements() 
	{
		assertTrue(UniquePaths.uniquePaths2(6,2,6));
	}

	@Test
	void testForSevenElements() 
	{
		assertTrue(UniquePaths.uniquePaths2(5,9,495));
    }

	@Test
	void testForEightElements() 
	{
		assertTrue(UniquePaths.uniquePaths2(4,8,120));
	}
}