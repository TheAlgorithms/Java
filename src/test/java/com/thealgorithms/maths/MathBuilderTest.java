package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.doubleThat;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
	@DisplayName("Close parenthesis Test")
	void closeParenthesisTest() {
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
    @DisplayName("open parenthesis Test")
    void openParenthesisAndABSTest() {
        // 10.5 - (20+2.1)
        double actual = new MathBuilder.Builder(10.5).openParenthesis(20).minus(2.1).closeParenthesisAndMinus()
                .build().get();
        double expected = 10.5 - (20 - 2.1);

        // 10.5 / (20+2.1)
        double actual2 = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.2).abs().closeParenthesisAndPlus().abs()
                .build().get();
        double expected2 = 10.5 + (20 + 2.2);

        assertEquals(expected, actual);
        assertEquals(expected2, actual2);
    }
	@Test
	@DisplayName("Runtime Errors Tests")
	void runtimeErrorTest() {
		MathBuilder.Builder actual = new MathBuilder.Builder(10.5);


     assertAll(
	         () ->	assertThrows(RuntimeException.class, () -> actual.rand(1)),
	         () ->	assertThrows(RuntimeException.class, () -> actual.randomInRange(1, 10)),
	         () ->	assertThrows(RuntimeException.class, () -> actual.pi()),
            () ->	assertThrows(RuntimeException.class, () -> actual.e()),
            () ->	assertThrows(RuntimeException.class, () -> actual.set(1))
             );
	}

	@Test
	@DisplayName("Should divide 10 by 2")
	void divideByNum() {
		double actual = new MathBuilder.Builder(10).divide(2).build().get();

		double expected = 10 / 2;
        double expected2 = 10 / 4;

		assertEquals(expected, actual);
        assertNotEquals(expected2,actual);
	}

	@Test
	@DisplayName("follows IEEE 754")
	void divideByZero() {



        MathBuilder actual = new MathBuilder.Builder(10.5).openParenthesis(0)
                .closeParenthesisAndDivide().divide(0).build();

        MathBuilder.Builder actual2 = new MathBuilder.Builder(10.5).openParenthesis(0)
				.closeParenthesisAndDivide();

        MathBuilder actual3 = new MathBuilder.Builder(-10.5).openParenthesis(0)
                .closeParenthesisAndDivide().divide(0).build();

        assertAll(
                () -> assertTrue(Double.isInfinite(actual.get())),
                () -> assertDoesNotThrow(() -> actual2.build().get()),
                () -> assertDoesNotThrow(() -> actual2.divide(0).build().get()),
                () -> assertEquals(Double.POSITIVE_INFINITY, actual2.divide(0).build().get()),
                () -> assertEquals(Double.NEGATIVE_INFINITY, actual3.get())
        );


	}

	@Test
	void randomFunctions() {

		double minValue = 0.0;
		double maxValue = 2.1;
		double actual = new MathBuilder.Builder().rand(2L).build().get();
		double actual2 = new MathBuilder.Builder().randomInRange(minValue, maxValue).build().get();
        assertAll(
                ()   ->assertTrue(actual < maxValue),
                ()   ->assertTrue(actual2 >= minValue),
                ()   ->assertTrue(actual2 <= maxValue)

        );


	}

    @ParameterizedTest
    @MethodSource("radiansHelper")
    void toRadiansTests(double expectedAngle, double actualAngle) {
        assertEquals(expectedAngle, actualAngle);
    }

    private static List<Arguments> radiansHelper() {
        return List.of(
                Arguments.of(
                        Math.toRadians(10),
                        new MathBuilder.Builder(10).toRadians().build().get()
                ),
                Arguments.of(
                        2 + Math.toRadians(10),
                        new MathBuilder.Builder(2).openParenthesis(10).toRadians().closeParenthesisAndPlus()
                                .build().get()
                )
        );
    }
    @Test
    void roundCielABSTest() {

        double actual = new MathBuilder.Builder(10).openParenthesis(10.5).round().closeParenthesisAndPlus().build().get();
        double expected = 10+ (Math.round(10.5));

        double expected2 = 10 + Math.ceil(10.5);
        double actual2 = new MathBuilder.Builder(10).openParenthesis(10.5).ceil().closeParenthesisAndPlus().build().get();

        double expected3 = 10 + Math.abs(10.5);
        double actual3 = new MathBuilder.Builder(10).openParenthesis(10.5).abs().closeParenthesisAndPlus().build().get();

        double expected4 = Math.abs(10 + 10.5);
        double actual4 = new MathBuilder.Builder(10).openParenthesis(10.5).closeParenthesisAndPlus().abs().build().get();

        assertAll(
                () -> assertNotEquals(0,actual),
                () -> assertNotEquals(1,actual2),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected2, actual2),
                () -> assertEquals(expected3, actual3),
                () -> assertEquals(expected4, actual4)
        );
    }


    @Test
    void toLongTest() {


        MathBuilder actual = new MathBuilder.Builder(10.5).openParenthesis(0)
                .closeParenthesisAndDivide().divide(0).build();

        MathBuilder actual2 = new MathBuilder.Builder(10.5).openParenthesis(0)
                .closeParenthesisAndDivide().divide(0).multiply(-1).build();

        MathBuilder actual3 = new MathBuilder.Builder(1999999999).multiply(2139999999).multiply(3).build();
        MathBuilder actual4 = new MathBuilder.Builder(1999999999).multiply(2139999999).multiply(-3).build();
        assertAll(
            () -> assertEquals(Long.MAX_VALUE,actual.toLong()),
            () -> assertEquals(Long.MIN_VALUE,actual2.toLong()),
            () -> assertEquals(Long.MAX_VALUE,actual3.toLong()),
            () -> assertEquals(Long.MIN_VALUE,actual4.toLong()),
            () -> assertNotEquals(0,actual.toLong()),
            () ->  assertNotEquals(1,actual2.toLong())
        );
    }

    @Test
    void maxTest() {
        MathBuilder actual = new MathBuilder.Builder(10.5).max(20).build();
        MathBuilder actual2 = new MathBuilder.Builder(13.5).max(10).build();

        MathBuilder actual3 = new MathBuilder.Builder(10.5).openParenthesis(10).max(20).closeParenthesisAndPlus().build();
        MathBuilder actual4 = new MathBuilder.Builder(12.5).openParenthesis(10).closeParenthesisAndPlus().max(20).build();
        assertAll(
            () -> assertEquals(20,actual.get()),
            () -> assertEquals(13.5,actual2.get()),
            () -> assertEquals(30.5,actual3.get()),
            () -> assertEquals(22.5,actual4.get()),
            () -> assertNotEquals(30,actual4.get()),
            () ->  assertNotEquals(5,actual4.get())
        );
    }
    @Test
    void minTest() {
        MathBuilder actual = new MathBuilder.Builder(10.5).min(20).build();
        MathBuilder actual2 = new MathBuilder.Builder(8.5).min(10).build();

        MathBuilder actual3 = new MathBuilder.Builder(10.5).openParenthesis(10).min(20).closeParenthesisAndPlus().build();
        MathBuilder actual4 = new MathBuilder.Builder(12.5).openParenthesis(10).closeParenthesisAndPlus().min(20).build();
assertAll(
    () -> assertEquals(10.5,actual.get()),
    () -> assertEquals(8.5,actual2.get()),
    () -> assertEquals(20.5,actual3.get()),
    () -> assertEquals(20,actual4.get()),
    () -> assertNotEquals(5, actual.get()),
    () -> assertNotEquals(-1000, actual3.get())
);
    }
}
