package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrimeFactorizationTest {

	@Test
	void testpFactorsMustReturnEmptyList() {
		//given
		int n = 0; 
		
		//then
		assertTrue(PrimeFactorization.pfactors(n).isEmpty());
	}
	
	@Test
	void testpFactorsMustReturnNonEmptyList() {
		//given
		int n = 4;
		int expectedResult = 2;
		
		//when
		int actualResult = PrimeFactorization.pfactors(n).size();
		
		//then
		assertEquals(expectedResult, actualResult);
	}
}
