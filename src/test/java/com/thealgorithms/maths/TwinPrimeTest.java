package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TwinPrimeTest {

	@Test
	void shouldReturn7() {
		//given
		int number = 5;
		int expectedResult = 7;
		
		//when
		int actualResult = TwinPrime.getTwinPrime(number);
		
		//then
		assertEquals(expectedResult,actualResult);
	}
	
	@Test
	void shouldReturn5() {
		//given
		int number = 3;
		int expectedResult = 5;
		
		//when
		int actualResult = TwinPrime.getTwinPrime(number);
		
		//then
		assertEquals(expectedResult,actualResult);
	}
	
	@Test
	void shouldReturnNegative1() {
		//given
		int number = 4;
		int expectedResult = -1;
		
		//when
		int actualResult = TwinPrime.getTwinPrime(number);
		
		//then
		assertEquals(expectedResult,actualResult);
	}
	
	@Test
	void shouldReturn19() {
		//given
		int number = 17;
		int expectedResult = 19;
		
		//when
		int actualResult = TwinPrime.getTwinPrime(number);
		
		//then
		assertEquals(expectedResult,actualResult);
	}
}
