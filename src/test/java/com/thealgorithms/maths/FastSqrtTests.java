package com.thealgorithms.maths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FastSqrtTests {
    @Test
	void testForOneElement() 
	{
		assertTrue(FastSqrt.inverseSqrt(1332,0.027388954535126686));
        // calls for the 2nd inverse method
    }
	@Test
	void testForTwoElements() 
	{
		assertTrue(FastSqrt.inverseSqrt(1332f,0.027388955));
        // calls for the 1st inverse method
	}

	@Test
	void testForThreeElements() 
	{
		assertTrue(FastSqrt.inverseSqrt(1,0.9983071684837341));
	}
    
	@Test
	void testForFourElements() 
	{
		assertTrue(FastSqrt.inverseSqrt(1f,0.99830717));
	}

	@Test
	void testForFiveElements() 
	{
		assertTrue(FastSqrt.inverseSqrt(4522,0.014867560006678104));
	}
    
	@Test
	void testForSixElements() 
	{
		assertTrue(FastSqrt.inverseSqrt(4522f,0.01486756));
	}

	@Test
	void testForSevenElements() 
	{
		assertTrue(FastSqrt.inverseSqrt(21,0.218117818236351));
    }

	@Test
	void testForEightElements() 
	{
		assertTrue(FastSqrt.inverseSqrt(21f,0.21811782));
	}
}

