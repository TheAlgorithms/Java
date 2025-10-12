package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link HeronsFormula}.
 */
class HeronsFormulaTest {

    private static final double EPSILON = 1e-10;

    @Test
    void testRightTriangleThreeFourFive() {
        Assertions.assertEquals(6.0, HeronsFormula.herons(3, 4, 5), EPSILON);
    }

    @Test
    void testTriangleTwentyFourThirtyEighteen() {
        Assertions.assertEquals(216.0, HeronsFormula.herons(24, 30, 18), EPSILON);
    }

    @Test
    void testEquilateralTriangle() {
        Assertions.assertEquals(0.4330127018922193, HeronsFormula.herons(1, 1, 1), EPSILON);
    }

    @Test
    void testScaleneTriangleFourFiveEight() {
        Assertions.assertEquals(8.181534085976786, HeronsFormula.herons(4, 5, 8), EPSILON);
    }

    @Test
    void testEquilateralTriangleLargeSides() {
        final double side = 10.0;
        final double expectedArea = Math.sqrt(3) / 4 * side * side;
        Assertions.assertEquals(expectedArea, HeronsFormula.herons(side, side, side), EPSILON);
    }

    @Test
    void testIsoscelesTriangle() {
        Assertions.assertEquals(12.0, HeronsFormula.herons(5, 5, 6), EPSILON);
    }

    @Test
    void testSmallTriangle() {
        Assertions.assertEquals(0.4330127018922193, HeronsFormula.herons(1.0, 1.0, 1.0), EPSILON);
    }

    @Test
    void testLargeTriangle() {
        Assertions.assertEquals(600.0, HeronsFormula.herons(30, 40, 50), EPSILON);
    }

    @Test
    void testDecimalSides() {
        final double area = HeronsFormula.herons(2.5, 3.5, 4.0);
        Assertions.assertTrue(area > 0);
        Assertions.assertEquals(4.330127018922194, area, EPSILON);
    }

    @Test
    void testDegenerateTriangleEqualToSumOfOtherTwo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(1, 2, 3); });
    }

    @Test
    void testDegenerateTriangleVariant2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(2, 1, 3); });
    }

    @Test
    void testDegenerateTriangleVariant3() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(3, 2, 1); });
    }

    @Test
    void testDegenerateTriangleVariant4() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(1, 3, 2); });
    }

    @Test
    void testInvalidTriangleSideGreaterThanSum() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(1, 1, 5); });
    }

    @Test
    void testZeroFirstSide() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(0, 1, 1); });
    }

    @Test
    void testZeroSecondSide() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(1, 0, 1); });
    }

    @Test
    void testZeroThirdSide() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(1, 1, 0); });
    }

    @Test
    void testNegativeFirstSide() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(-1, 2, 2); });
    }

    @Test
    void testNegativeSecondSide() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(2, -1, 2); });
    }

    @Test
    void testNegativeThirdSide() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(2, 2, -1); });
    }

    @Test
    void testAllNegativeSides() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(-1, -2, -3); });
    }

    @Test
    void testAllZeroSides() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(0, 0, 0); });
    }

    @Test
    void testVerySmallTriangle() {
        final double result = HeronsFormula.herons(0.001, 0.001, 0.001);
        Assertions.assertTrue(result > 0);
        Assertions.assertTrue(result < 0.001);
    }

    @Test
    void testRightTriangleFiveTwelveThirteen() {
        Assertions.assertEquals(30.0, HeronsFormula.herons(5, 12, 13), EPSILON);
    }

    @Test
    void testRightTriangleEightFifteenSeventeen() {
        Assertions.assertEquals(60.0, HeronsFormula.herons(8, 15, 17), EPSILON);
    }
}
