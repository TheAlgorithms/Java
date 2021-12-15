package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KaprekarNumbersTest {

	@Test
	void testFor1() 
	{
		assertTrue(KaprekarNumbers.isKaprekarNumber(1));
	}

	@Test
	void testFor45() 
	{
		assertTrue(KaprekarNumbers.isKaprekarNumber(45));
	}

	@Test
	void testFor297() 
	{
		assertTrue(KaprekarNumbers.isKaprekarNumber(297));
	}

	@Test
	void testFor2223() 
	{
		assertTrue(KaprekarNumbers.isKaprekarNumber(2223));
	}

	@Test
	void testFor857143() 
	{
		assertTrue(KaprekarNumbers.isKaprekarNumber(857143));
	}


	@Test
	void testFor3() 
	{
		assertFalse(KaprekarNumbers.isKaprekarNumber(3));
	}

	@Test
	void testFor26() 
	{
		assertFalse(KaprekarNumbers.isKaprekarNumber(26));
	}

	@Test
	void testFor98() 
	{
		assertFalse(KaprekarNumbers.isKaprekarNumber(98));
	}

}