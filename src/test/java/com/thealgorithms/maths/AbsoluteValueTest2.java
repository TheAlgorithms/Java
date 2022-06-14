package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AbsoluteValueTest2 {
	AbsoluteValue test = new AbsoluteValue();
	@Test
	void testAbsoluteValue0() {
		assertTrue(2 == test.getAbsValue(2));
	}
	
	@Test
	public void testAbsoluteValue1(){
		assertTrue(1 == test.getAbsValue(-1));
	}

	@Test
	public void testAbsoluteValue2(){
		assertTrue(0 == test.getAbsValue(0));
	}
	
}
