package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PerfectSquareTest{
	
	@Test
	public void TestPerfectSquare(){
		int number = 9;
		
		boolean result = PerfectSquare.isPerfectSquare(number);
		
		assertTrue(result)
	}
	
	@Test
	public void TestPerfectSquare(){
		int number = 3;
		
		boolean result = PerfectSquare.isPerfectSquare(number);
		
		assertFalse(result)
	}
}
