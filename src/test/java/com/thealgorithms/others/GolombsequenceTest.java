package com.thealgorithms.others;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.Golombsequence;
public class GolombsequenceTest {
    @Test
	void testForOneElement() 
	{
        int a[] = {1,2,2};
		assertTrue(Golombsequence.golombsequence(3,a));
	}

	@Test
	void testForTwoElements() 
	{
        int a[] = {1,2,2,3};
		assertTrue(Golombsequence.golombsequence(4,a));
	}

	@Test
	void testForThreeElements() 
	{
        int a[] = {1,2,2,3,3};
		assertTrue(Golombsequence.golombsequence(5,a));
	}

	@Test
	void testForFourElements() 
	{
        int a[] = {1,2,2,3,3,4};
		assertTrue(Golombsequence.golombsequence(6,a));
	}

	@Test
	void testForFiveElements() 
	{
        int a[] = {1,2,2,3,3,4,4};
		assertTrue(Golombsequence.golombsequence(7,a));
	}

	@Test
	void testForSixElements() 
	{
        int a[] = {1,2,2,3,3,4,4,4};
		assertTrue(Golombsequence.golombsequence(8,a));
	}

	@Test
	void testForSevenElements() 
	{
        int a[] = {1,2,2,3,3,4,4,4,5};
		assertTrue(Golombsequence.golombsequence(9,a));
    }

	@Test
	void testForEightElements() 
	{
        int a[] = {1,2,2,3,3,4,4,4,5,5};
		assertTrue(Golombsequence.golombsequence(10,a));
	}
}