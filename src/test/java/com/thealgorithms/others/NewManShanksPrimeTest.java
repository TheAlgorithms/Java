package com.thealgorithms.others;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.NewManShanksPrime;
public class NewManShanksPrimeTest {
    @Test
	void testOne() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(1,1));
	}

	@Test
	void testTwo() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(2,3));
	}

	@Test
	void testThree() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(3,7));
	}

	@Test
	void testFour() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(4,17));
	}

	@Test
	void testFive() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(5,41));
	}

	@Test
	void testSix() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(6,99));
	}

    @Test
	void testSeven() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(7,239));
    }

	@Test
	void testEight() 
	{
		assertTrue(NewManShanksPrime.nthManShanksPrime(8,577));
	}
	
}