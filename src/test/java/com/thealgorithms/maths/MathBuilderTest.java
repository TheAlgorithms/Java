package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class MathBuilderTest {

	@Test
	void simpleMath() {
		double result = new MathBuilder.Builder(100).add(200).multiply(10).print()
				.divideIf(20, (a, b) -> a % 2 == 0).sqrt().print().ceil().build().get();
		assertEquals(13, result);
	}

	@Test
	void memoryTest() {
		long result = new MathBuilder.Builder().set(100).sqrt().remember().add(21).recallIf(a -> a < 50, true)
				.mod(2).build().toLong();
		assertEquals(0, result);
	}

	@Test
	void freeFallDistance() {
		long distance = new MathBuilder.Builder(9.81).multiply(0.5).multiply(5 * 5).round().build().toLong();
		assertEquals(123, distance); // Expected result: 0.5 * 9.81 * 25 = 122.625 ≈ 123
	}

	@Test
	void batchSalaryProcessing() {
		double[] salaries = { 2000, 3000, 4000, 5000 };
		long[] processedSalaries = new long[salaries.length];
		for (int i = 0; i < salaries.length; i++) {
			processedSalaries[i] = new MathBuilder.Builder(salaries[i])
					.addIf(salaries[i] * 0.1, (sal, bonus) -> sal > 2500).multiply(0.92).round()
					.build().toLong();
		}
		long[] expectedSalaries = { 1840, 3036, 4048, 5060 };
		assertArrayEquals(expectedSalaries, processedSalaries);
	}

	@Test
	void parenthesis() {
		// 10 + (20*5) - 40 + (100 / 10) = 80
		double result = new MathBuilder.Builder(10).openParenthesis(20).multiply(5).closeParenthesisAndPlus()
				.minus(40).openParenthesis(100).divide(10).closeParenthesisAndPlus().build().get();
		assertEquals(80, result);
	}

	@Test
	void areaOfCircle() {
		// Radius is 4
		double area = new MathBuilder.Builder().pi().openParenthesis(4).multiply(4)
				.closeParenthesisAndMultiply().build().get();
		assertEquals(Math.PI * 4 * 4, area);
	}

	@Test
	@DisplayName("Floor Test")
	void floorTest() {
		// floor(10.5 + (20+2.1))
		double actual = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.1).closeParenthesisAndPlus()
				.floor().build().get();
		double expected = Math.floor(10.5 + 20 + 2.1);

		// 10.5 + floor((20+2.1))
		double actual2 = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.1).floor()
				.closeParenthesisAndPlus().build().get();
		double expected2 = 10.5 + Math.floor(20 + 2.1);

		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}

	@Test
	@DisplayName("Close parntasis Test")
	void closeParenthesisAndOtherTest() {
		// 10.5 - (20+2.1)
		double actual = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.1).closeParenthesisAndMinus()
				.build().get();
		double expected = 10.5 - (20 + 2.1);

		// 10.5 / (20+2.1)
		double actual2 = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.1).closeParenthesisAndDivide()
				.build().get();
		double expected2 = 10.5 / (20 + 2.1);

		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}

	@Test
	@DisplayName("Runtime Errors Tests")
	void runtimeErrorTest() {
		// 10.5 - (20+2.1)
		MathBuilder.Builder actual = new MathBuilder.Builder(10.5);

		// 10.5 / (20+2.1)

		assertThrows(RuntimeException.class, () -> actual.rand(1));
		assertThrows(RuntimeException.class, () -> actual.randomInRange(1, 10));
		assertThrows(RuntimeException.class, () -> actual.pi());
		assertThrows(RuntimeException.class, () -> actual.e());
		assertThrows(RuntimeException.class, () -> actual.set(1));
	}

	@Test
	@DisplayName("Should divide 10 by 2")
	void divideByNum() {
		double actual = new MathBuilder.Builder(10).divide(2).build().get();

		double expected = 10 / 2;

		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("This Should throw but does not")
	void divideByZero() {
		MathBuilder.Builder actual2 = new MathBuilder.Builder(10.5).openParenthesis(0)
				.closeParenthesisAndDivide();

		assertDoesNotThrow(() -> actual2.build().get());
		assertDoesNotThrow(() -> actual2.divide(0).build().get());

	}

	@Test
	void randomFunctions() {

		double minValue = 0.0;
		double maxValue = 2.1;
		double actual = new MathBuilder.Builder().rand(2L).build().get();
		double actual2 = new MathBuilder.Builder().randomInRange(minValue,maxValue).build().get();

		assertTrue(actual < maxValue);
		assertTrue(actual2 >= minValue);
		assertTrue(actual2 <= maxValue);

	}
	@Test
	void toRadiansTest() {
 
		double expected = Math.toRadians(10);
		double expected2 = 2+Math.toRadians(10);

		double actual = new MathBuilder.Builder(10).toRadians().build().get();
		double actual2 = new MathBuilder.Builder(2).openParenthesis(10).toRadians().closeParenthesisAndPlus().build().get();

		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}
}
