package com.thealgorithms.ciphers;

import org.junit.Test;

import static org.junit.Assert.*;

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
		String actResult = Caesar.encode("bcd", 1);
		assertEquals(expResult, actResult);
	}
}
