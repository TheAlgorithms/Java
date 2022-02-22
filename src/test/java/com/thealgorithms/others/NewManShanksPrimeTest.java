package com.thealgorithms.others;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.NewManShanksPrime;
public class NewManShanksPrimeTest {
    @Test
	void testForOneElement() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(1,1));
	}

	@Test
	void testForTwoElements() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(2,3));
	}

	@Test
	void testForThreeElements() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(3,7));
	}

	@Test
	void testForFourElements() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(4,17));
	}

	@Test
	void testForFiveElements() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(5,41));
	}

	@Test
	void testForSixElements() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(6,99));
	}

	@Test
	void testForSevenElements() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(7,239));
    }

	@Test
	void testForEightElements() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(8,577));
	}
}