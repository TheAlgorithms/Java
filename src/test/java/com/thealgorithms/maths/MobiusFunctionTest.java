package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MobiusFunctionTest {

	@Test
	void testMobiusForZero() {
		//given
		int number = 0;
		String expectedMessage = "Number must be greater than zero.";

		//when
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			MobiusFunction.mobius(number);
		});
		String actualMessage = exception.getMessage();

		//then
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testMobiusForNegativeNumber() {
		//given
		int number = -1;
		int expectedValue = 1;

		//when
		int actualValue = MobiusFunction.mobius(number);

		//then
		assertEquals(expectedValue, actualValue);
	}

	@Test
	void testMobiusFunction(){
		int[] expectedResultArray = {
				1, 1, -1, -1, 0, -1, 1, -1, 0, 0, 1, -1, 0, -1, 1, 1, 0, -1, 0, -1, 0, 1, 1, -1, 0, 
				0, 1, 0, 0, -1, -1, -1, 0, 1, 1, 1, 0, -1, 1, 1, 0, -1, -1, -1, 0, 0, 1, -1, 0, 0, 
				0, 1, 0, -1, 0, 1, 0, 1, 1, -1, 0, -1, 1, 0, 0, 1, -1, -1, 0, 1, -1, -1, 0, -1, 1, 
				0, 0, 1, -1, -1, 0, 0, 1, -1, 0, 1, 1, 1, 0, -1, 0, 1, 0, 1, 1, 1, 0, -1, 0, 0, 0
		};

		for(int i = 0; i <= 100; i++) {

			//given
			int expectedValue = expectedResultArray[i];

			//when
			int actualValue = MobiusFunction.mobius(i);

			//then
			assertEquals(expectedValue,actualValue);
		}
	}

}
