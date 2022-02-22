package com.thealgorithms.others;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.NewManShanksPrime;
public class NewManShanksPrimeTest {
    @Test
	void testForOneElement() 
	{
		assertTrue(NewManShanksPrime.newManShanksPrime(1,1));
	}

	@Test
	void testForTwoElements() 
	{
		assertTrue(NewManShanksPrime.newManShanksPrime(2,3));
	}

	@Test
	void testForThreeElements() 
	{
		assertTrue(NewManShanksPrime.newManShanksPrime(3,7));
	}

	@Test
	void testForFourElements() 
	{
		assertTrue(NewManShanksPrime.newManShanksPrime(4,17));
	}

	@Test
	void testForFiveElements() 
	{
		assertTrue(NewManShanksPrime.newManShanksPrime(5,41));
	}

	@Test
	void testForSixElements() 
	{
		assertTrue(NewManShanksPrime.newManShanksPrime(6,99));
	}

	@Test
	void testForSevenElements() 
	{
		assertTrue(NewManShanksPrime.newManShanksPrime(7,239));
    }

	@Test
	void testForEightElements() 
	{
		assertTrue(NewManShanksPrime.newManShanksPrime(8,577));
	}
}