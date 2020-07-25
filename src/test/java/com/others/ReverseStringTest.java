package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReverseStringTest {

	private static final String BLANK = "";

	@Test
	void testReverse() {
		String originalString = "ORIGINALSTRING";
		String reverseString = "GNIRTSLANIGIRO";

		Assertions.assertEquals(reverseString, ReverseString.reverse(originalString));
	}

	@Test
	void testNullAndEmptyString() {
		String nullString = null;
		String emptyString = BLANK;

		Assertions.assertNull(ReverseString.reverse(nullString));
		Assertions.assertEquals(BLANK, ReverseString.reverse(emptyString));
	}

}
