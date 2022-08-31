package com.thealgorithms.ciphers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaesarTest {
	
	@Test
	public void testEncode() {
		String expResult = "bcd";
		String actResult = Caesar.encode("abc", 1);
		assertEquals(expResult, actResult);
	}
	@Test
	public void testDecode() {
		String expResult = "abc";
		String actResult = Caesar.decode("bcd", 1);
		assertEquals(expResult, actResult);
	}
}
