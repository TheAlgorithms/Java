package com.thealgorithms.maths;

import javax.annotation.processing.Generated;

public class FactorialTest {
	@Test
	public void testFactorial1() throws Exception {
		int n = 0;
		long result;

		// test 1
		n = 0;
		result = Factorial.factorial(n);
		Assert.assertEquals(null, result);
	}
	@Test
	public void testFactorial2() throws Exception {
		int n = 0;
		long result;
		// test 2
		n = -1;
		result = Factorial.factorial(n);
		Assert.assertEquals(null, result);
	}
	@Test
	public void testFactorial3() throws Exception {
		int n = 0;
		long result;
		// test 3
		n = 1;
		result = Factorial.factorial(n);
		Assert.assertEquals(null, result);
	}
}
