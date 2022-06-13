package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PerfectSquareTest{
	
	@Test
	public void TestPerfectSquare(){
		//Valid Partition
		int number = 9;
		
		boolean result = PerfectSquare.isPerfectSquare(number);
		
		assertTrue(result)
	}
	
	@Test
	public void TestPerfectSquare(){
		//Invalid Partition 1
		int number = 3;
		
		boolean result = PerfectSquare.isPerfectSquare(number);
		
		assertFalse(result)
	}
	@Test
	public void TestPerfectSquareifisNegativeNumber(){
		//Invalid Partition 2
		int number = -10;

		boolean result = PerfectSquare.isPerfectSquare(number);

		assertFalse(result);
	}
}
