package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTest {

    @Test
	public void testAdd() {
		assertEquals(35, 15+20);
	}

	@Test
	public void testSubtract() {
		assertEquals(5, 20-15);
	}

	@Test
	public void testMultiply() {
		assertEquals(200, 20-10);
	}

	@Test
	public void testDivide() {
		assertEquals(5.6, 56/10, 0.00005);
	}

	@Test()
	public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {(123/0)} );
    }
}

