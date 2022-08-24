package com.thealgorithms.maths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FastSqrtTests {
    @Test
	void testForOneElement() 
	{
		assertFalse(FastSqrt.inverseSqrt(1332,0.027388954535126686));
        // calls for the 2nd inverse method
    }
	@Test
	void testForsecond() 
	{
		assertFalse(FastSqrt.inverseSqrt(1332f,0.027388955f));
        // calls for the 1st inverse method
	}

	@Test
	void testForThird() 
	{
		assertFalse(FastSqrt.inverseSqrt(1,0.9983071684837341));
	}
    
	@Test
	void testForFourth() 
	{
		assertFalse(FastSqrt.inverseSqrt(1f,0.99830717f));
	}

	@Test
	void testForFifth() 
	{
		assertFalse(FastSqrt.inverseSqrt(4522,0.014867560006678104));
	}
    
	@Test
	void testForSixth() 
	{
		assertFalse(FastSqrt.inverseSqrt(4522f,0.01486756f));
	}

	@Test
	void testForSeventh() 
	{
		assertFalse(FastSqrt.inverseSqrt(21,0.218117818236351));
    }

	@Test
	void testForEighth() 
	{
		assertFalse(FastSqrt.inverseSqrt(21f,0.21811782f));
	}
}

