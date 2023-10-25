package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberOfDigitsForBaseTest {
	@Test
	void testNumberOfDigitsForBase() {
		assertEquals(3, NumberOfDigitsForBase.NumberOfDigitsForBase(5, 2).get());
		assertEquals(2, NumberOfDigitsForBase.NumberOfDigitsForBase(54, 10).get());
		assertEquals(4, NumberOfDigitsForBase.NumberOfDigitsForBase(10, 2).get());
		assertEquals(2, NumberOfDigitsForBase.NumberOfDigitsForBase(18, 8).get());
	}
}
