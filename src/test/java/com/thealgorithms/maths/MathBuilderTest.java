package com.thealgorithms.maths;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MathBuilderTest {

    @Test
    void simpleMath() {
        double result = new MathBuilder.Builder(100).add(200).multiply(10).print().divideIf(20, (a, b) -> a % 2 == 0).sqrt().print().ceil().build().get();
        Assertions.assertEquals(13, result);
    }

    @Test
    void memoryTest() {
        long result = new MathBuilder.Builder().set(100).sqrt().remember().add(21).recallIf(a -> a < 50, true).mod(2).build().toLong();
        Assertions.assertEquals(0, result);
    }

    @Test
    void freeFallDistance() {
        long distance = new MathBuilder.Builder(9.81).multiply(0.5).multiply(5 * 5).round().build().toLong();
        Assertions.assertEquals(123, distance); // Expected result: 0.5 * 9.81 * 25 = 122.625 ≈ 123
    }

    @Test
    void batchSalaryProcessing() {
        double[] salaries = {2000, 3000, 4000, 5000};
        long[] processedSalaries = new long[salaries.length];
        for (int i = 0; i < salaries.length; i++) {
            processedSalaries[i] = new MathBuilder.Builder(salaries[i]).addIf(salaries[i] * 0.1, (sal, bonus) -> sal > 2500).multiply(0.92).round().build().toLong();
        }
        long[] expectedSalaries = {1840, 3036, 4048, 5060};
        Assertions.assertArrayEquals(expectedSalaries, processedSalaries);
    }

    @Test
    void parenthesis() {
        // 10 + (20*5) - 40 + (100 / 10) = 80
        double result = new MathBuilder.Builder(10).openParenthesis(20).multiply(5).closeParenthesisAndPlus().minus(40).openParenthesis(100).divide(10).closeParenthesisAndPlus().build().get();
        Assertions.assertEquals(80, result);
    }

    @Test
    void areaOfCircle() {
        // Radius is 4
        double area = new MathBuilder.Builder().pi().openParenthesis(4).multiply(4).closeParenthesisAndMultiply().build().get();
        Assertions.assertEquals(Math.PI * 4 * 4, area);
    }

    @Test
    @DisplayName("Floor Test")
    void floorTest() {
        // floor(10.5 + (20+2.1))
        double actual = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.1).closeParenthesisAndPlus().floor().build().get();
        double expected = Math.floor(10.5 + 20 + 2.1);

        // 10.5 + floor((20+2.1))
        double actual2 = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.1).floor().closeParenthesisAndPlus().build().get();
        double expected2 = 10.5 + Math.floor(20 + 2.1);

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    @DisplayName("Close parenthesis Test")
    void closeParenthesisTest() {
        // 10.5 - (20+2.1)
        double actual = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.1).closeParenthesisAndMinus().build().get();
        double expected = 10.5 - (20 + 2.1);

        // 10.5 / (20+2.1)
        double actual2 = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.1).closeParenthesisAndDivide().build().get();
        double expected2 = 10.5 / (20 + 2.1);

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    @DisplayName("open parenthesis Test")
    void openParenthesisAndABSTest() {
        // 10.5 - (20+2.1)
        double actual = new MathBuilder.Builder(10.5).openParenthesis(20).minus(2.1).closeParenthesisAndMinus().build().get();
        double expected = 10.5 - (20 - 2.1);

        // 10.5 / (20+2.1)
        double actual2 = new MathBuilder.Builder(10.5).openParenthesis(20).add(2.2).abs().closeParenthesisAndPlus().abs().build().get();
        double expected2 = 10.5 + (20 + 2.2);

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    @DisplayName("Runtime Errors Tests")
    void runtimeErrorTest() {
        MathBuilder.Builder actual = new MathBuilder.Builder(10.5);

        Executable randCheck = () -> Assertions.assertThrows(RuntimeException.class, () -> actual.rand(1));
        Executable rangeCheck = () -> Assertions.assertThrows(RuntimeException.class, () -> actual.randomInRange(1, 10));
        Executable piCheck = () -> Assertions.assertThrows(RuntimeException.class, actual::pi);
        Executable eCheck = () -> Assertions.assertThrows(RuntimeException.class, actual::e);
        Executable setCheck = () -> Assertions.assertThrows(RuntimeException.class, () -> actual.set(1));

        Assertions.assertAll(randCheck, rangeCheck, piCheck, eCheck, setCheck);
    }


    @Test
    @DisplayName("Should divide 10 by 2")
    void divideByNum() {
        double actual = new MathBuilder.Builder(10).divide(2).build().get();

        double expected = 10.0 / 2.0;
        double expected2 = 10.0 / 4.0;

        Assertions.assertEquals(expected, actual);
        Assertions.assertNotEquals(expected2, actual);
    }

    @ParameterizedTest
    @MethodSource("divideDoubleByZeroHelper")
    @DisplayName("Test that ensures dividing a double by zero follows IEEE 754")
    void divideDoubleByZero(double expected, MathBuilder.Builder actual, String error) {
        Executable noThrowOnBuild = () -> Assertions.assertDoesNotThrow(() -> actual.build().get(), "Dividing a double with zero should not throw");
        Executable noThrowOnDivideZero = () -> Assertions.assertDoesNotThrow(() -> actual.divide(0).build().get(), "Dividing infinity with 0 should not throw");
        Executable resultIsInfinite = () -> Assertions.assertTrue(Double.isInfinite(actual.build().get()), "Dividing a double by zero should result in infinity");
        Executable equalsExpected = () -> Assertions.assertEquals(expected, actual.build().get(), error);

        Assertions.assertAll(noThrowOnBuild, noThrowOnDivideZero, resultIsInfinite, equalsExpected);
    }


    static List<Arguments> divideDoubleByZeroHelper() {
        return List.of(Arguments.of(Double.POSITIVE_INFINITY, new MathBuilder.Builder(10.5).openParenthesis(0).closeParenthesisAndDivide(), "10.5 / 0 should be +Infinity"),
            Arguments.of(Double.NEGATIVE_INFINITY, new MathBuilder.Builder(-10.5).openParenthesis(0).closeParenthesisAndDivide(), "-10.5 / 0 should be -Infinity"));
    }

    @Test
    void randomFunctionsTest() {
        double minValue = 0.0;
        double maxValue = 2.1;

        double actual = new MathBuilder.Builder().rand(2L).build().get();
        double actual2 = new MathBuilder.Builder().randomInRange(minValue, maxValue).build().get();

        Executable isBelowMax = () -> Assertions.assertTrue(actual < maxValue, "Random value should be less than maxValue");
        Executable isAboveMin = () -> Assertions.assertTrue(actual2 >= minValue, "RandomInRange value should be >= minValue");
        Executable isWithinRange = () -> Assertions.assertTrue(actual2 <= maxValue, "RandomInRange value should be <= maxValue");

        Assertions.assertAll(isBelowMax, isAboveMin, isWithinRange);
    }


    @ParameterizedTest
    @MethodSource("radiansHelper")
    void toRadiansTests(double expectedAngle, double actualAngle) {
        Assertions.assertEquals(expectedAngle, actualAngle);
    }

    private static List<Arguments> radiansHelper() {
        return List.of(Arguments.of(Math.toRadians(10), new MathBuilder.Builder(10).toRadians().build().get()), Arguments.of(2 + Math.toRadians(10), new MathBuilder.Builder(2).openParenthesis(10).toRadians().closeParenthesisAndPlus().build().get()));
    }

    @Test
    void roundCielABSTest() {
        double actual = new MathBuilder.Builder(10).openParenthesis(10.5).round().closeParenthesisAndPlus().build().get();
        double expected = 10 + Math.round(10.5);

        double actual2 = new MathBuilder.Builder(10).openParenthesis(10.5).ceil().closeParenthesisAndPlus().build().get();
        double expected2 = 10 + Math.ceil(10.5);

        double actual3 = new MathBuilder.Builder(10).openParenthesis(10.5).abs().closeParenthesisAndPlus().build().get();
        double expected3 = 10 + Math.abs(10.5);

        double actual4 = new MathBuilder.Builder(10).openParenthesis(10.5).closeParenthesisAndPlus().abs().build().get();
        double expected4 = Math.abs(10 + 10.5);

        Executable roundIsNotZero = () -> Assertions.assertNotEquals(0, actual);
        Executable ceilIsNotOne = () -> Assertions.assertNotEquals(1, actual2);
        Executable roundIsCorrect = () -> Assertions.assertEquals(expected, actual);
        Executable ceilIsCorrect = () -> Assertions.assertEquals(expected2, actual2);
        Executable absIsCorrect = () -> Assertions.assertEquals(expected3, actual3);
        Executable absAfterPlusIsCorrect = () -> Assertions.assertEquals(expected4, actual4);

        Assertions.assertAll(roundIsNotZero, ceilIsNotOne, roundIsCorrect, ceilIsCorrect, absIsCorrect, absAfterPlusIsCorrect);
    }

    @Test
    void toLongTest() {
        MathBuilder posOverflow = new MathBuilder.Builder(10.5).openParenthesis(0).closeParenthesisAndDivide().divide(0).build();
        MathBuilder negOverflow = new MathBuilder.Builder(10.5).openParenthesis(0).closeParenthesisAndDivide().divide(0).multiply(-1).build();
        MathBuilder maxRange = new MathBuilder.Builder(1999999999).multiply(2139999999).multiply(3).build();
        MathBuilder minRange = new MathBuilder.Builder(1999999999).multiply(2139999999).multiply(-3).build();

        Executable posMaxCheck = () -> Assertions.assertEquals(Long.MAX_VALUE, posOverflow.toLong());
        Executable negMinCheck = () -> Assertions.assertEquals(Long.MIN_VALUE, negOverflow.toLong());
        Executable maxRangeCheck = () -> Assertions.assertEquals(Long.MAX_VALUE, maxRange.toLong());
        Executable minRangeCheck = () -> Assertions.assertEquals(Long.MIN_VALUE, minRange.toLong());
        Executable notZeroCheck = () -> Assertions.assertNotEquals(0, posOverflow.toLong());
        Executable notOneCheck = () -> Assertions.assertNotEquals(1, negOverflow.toLong());

        Assertions.assertAll(posMaxCheck, negMinCheck, maxRangeCheck, minRangeCheck, notZeroCheck, notOneCheck);
    }

    @Test
    void maxTest() {
        MathBuilder actual = new MathBuilder.Builder(10.5).max(20).build();
        MathBuilder actual2 = new MathBuilder.Builder(13.5).max(10).build();
        MathBuilder actual3 = new MathBuilder.Builder(10.5).openParenthesis(10).max(20).closeParenthesisAndPlus().build();
        MathBuilder actual4 = new MathBuilder.Builder(12.5).openParenthesis(10).closeParenthesisAndPlus().max(20).build();

        Executable maxCheck1 = () -> Assertions.assertEquals(20, actual.get());
        Executable maxCheck2 = () -> Assertions.assertEquals(13.5, actual2.get());
        Executable maxCheck3 = () -> Assertions.assertEquals(30.5, actual3.get());
        Executable maxCheck4 = () -> Assertions.assertEquals(22.5, actual4.get());
        Executable notEqualsCheck1 = () -> Assertions.assertNotEquals(30, actual4.get());
        Executable notEqualsCheck2 = () -> Assertions.assertNotEquals(5, actual4.get());

        Assertions.assertAll(maxCheck1, maxCheck2, maxCheck3, maxCheck4, notEqualsCheck1, notEqualsCheck2);
    }

    @Test
    void minTest() {
        MathBuilder actual = new MathBuilder.Builder(10.5).min(20).build();
        MathBuilder actual2 = new MathBuilder.Builder(8.5).min(10).build();
        MathBuilder actual3 = new MathBuilder.Builder(10.5).openParenthesis(10).min(20).closeParenthesisAndPlus().build();
        MathBuilder actual4 = new MathBuilder.Builder(12.5).openParenthesis(10).closeParenthesisAndPlus().min(20).build();

        Executable minCheck1 = () -> Assertions.assertEquals(10.5, actual.get());
        Executable minCheck2 = () -> Assertions.assertEquals(8.5, actual2.get());
        Executable minCheck3 = () -> Assertions.assertEquals(20.5, actual3.get());
        Executable minCheck4 = () -> Assertions.assertEquals(20, actual4.get());
        Executable notEqualsCheck1 = () -> Assertions.assertNotEquals(5, actual.get());
        Executable notEqualsCheck2 = () -> Assertions.assertNotEquals(-1000, actual3.get());

        Assertions.assertAll(minCheck1, minCheck2, minCheck3, minCheck4, notEqualsCheck1, notEqualsCheck2);
    }

}
