package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberOfDigitsForBaseTest {
	@Test
	void testNumberOfDigitsForBase() {
		assertEquals(3, NumberOfDigitsForBase.numberOfDigitsForBase(5, 2).get());
		assertEquals(2, NumberOfDigitsForBase.numberOfDigitsForBase(54, 10).get());
		assertEquals(4, NumberOfDigitsForBase.numberOfDigitsForBase(10, 2).get());
		assertEquals(2, NumberOfDigitsForBase.numberOfDigitsForBase(18, 8).get());
	}
}
