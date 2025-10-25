package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Means}.
 * <p>
 * This class provides comprehensive test coverage for all mean calculation
 * methods,
 * including edge cases, various collection types, and error conditions.
 * </p>
 */
class MeansTest {

    private static final double EPSILON = 1e-9;

    // ========== Arithmetic Mean Tests ==========

    @Test
    void testArithmeticMeanThrowsExceptionForEmptyList() {
        List<Double> numbers = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Means.arithmetic(numbers));
        assertTrue(exception.getMessage().contains("Empty list"));
    }

    @Test
    void testArithmeticMeanSingleNumber() {
        List<Double> numbers = Arrays.asList(2.5);
        assertEquals(2.5, Means.arithmetic(numbers), EPSILON);
    }

    @Test
    void testArithmeticMeanTwoNumbers() {
        List<Double> numbers = Arrays.asList(2.0, 4.0);
        assertEquals(3.0, Means.arithmetic(numbers), EPSILON);
    }

    @Test
    void testArithmeticMeanMultipleNumbers() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        assertEquals(3.0, Means.arithmetic(numbers), EPSILON);
    }

    @Test
    void testArithmeticMeanWithTreeSet() {
        Set<Double> numbers = new TreeSet<>(Arrays.asList(1.0, 2.5, 83.3, 25.9999, 46.0001, 74.7, 74.5));
        assertEquals(44.0, Means.arithmetic(numbers), EPSILON);
    }

    @Test
    void testArithmeticMeanWithNegativeNumbers() {
        List<Double> numbers = Arrays.asList(-5.0, -3.0, -1.0, 1.0, 3.0, 5.0);
        assertEquals(0.0, Means.arithmetic(numbers), EPSILON);
    }

    @Test
    void testArithmeticMeanWithDecimalNumbers() {
        List<Double> numbers = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5);
        assertEquals(3.3, Means.arithmetic(numbers), EPSILON);
    }

    @Test
    void testArithmeticMeanWithVector() {
        Vector<Double> numbers = new Vector<>(Arrays.asList(10.0, 20.0, 30.0));
        assertEquals(20.0, Means.arithmetic(numbers), EPSILON);
    }

    // ========== Geometric Mean Tests ==========

    @Test
    void testGeometricMeanThrowsExceptionForEmptyList() {
        List<Double> numbers = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Means.geometric(numbers));
        assertTrue(exception.getMessage().contains("Empty list"));
    }

    @Test
    void testGeometricMeanSingleNumber() {
        Set<Double> numbers = new LinkedHashSet<>(Arrays.asList(2.5));
        assertEquals(2.5, Means.geometric(numbers), EPSILON);
    }

    @Test
    void testGeometricMeanTwoNumbers() {
        List<Double> numbers = Arrays.asList(2.0, 8.0);
        assertEquals(4.0, Means.geometric(numbers), EPSILON);
    }

    @Test
    void testGeometricMeanMultipleNumbers() {
        LinkedList<Double> numbers = new LinkedList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 1.25));
        assertEquals(2.6426195539300585, Means.geometric(numbers), EPSILON);
    }

    @Test
    void testGeometricMeanPerfectSquares() {
        List<Double> numbers = Arrays.asList(1.0, 4.0, 9.0, 16.0);
        double expected = Math.pow(1.0 * 4.0 * 9.0 * 16.0, 1.0 / 4.0);
        assertEquals(expected, Means.geometric(numbers), EPSILON);
    }

    @Test
    void testGeometricMeanIdenticalNumbers() {
        List<Double> numbers = Arrays.asList(5.0, 5.0, 5.0, 5.0);
        assertEquals(5.0, Means.geometric(numbers), EPSILON);
    }

    @Test
    void testGeometricMeanWithLinkedHashSet() {
        LinkedHashSet<Double> numbers = new LinkedHashSet<>(Arrays.asList(2.0, 4.0, 8.0));
        double expected = Math.pow(2.0 * 4.0 * 8.0, 1.0 / 3.0);
        assertEquals(expected, Means.geometric(numbers), EPSILON);
    }

    // ========== Harmonic Mean Tests ==========

    @Test
    void testHarmonicMeanThrowsExceptionForEmptyList() {
        List<Double> numbers = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Means.harmonic(numbers));
        assertTrue(exception.getMessage().contains("Empty list"));
    }

    @Test
    void testHarmonicMeanSingleNumber() {
        LinkedHashSet<Double> numbers = new LinkedHashSet<>(Arrays.asList(2.5));
        assertEquals(2.5, Means.harmonic(numbers), EPSILON);
    }

    @Test
    void testHarmonicMeanTwoNumbers() {
        List<Double> numbers = Arrays.asList(2.0, 4.0);
        double expected = 2.0 / (1.0 / 2.0 + 1.0 / 4.0);
        assertEquals(expected, Means.harmonic(numbers), EPSILON);
    }

    @Test
    void testHarmonicMeanMultipleNumbers() {
        Vector<Double> numbers = new Vector<>(Arrays.asList(1.0, 2.5, 83.3, 25.9999, 46.0001, 74.7, 74.5));
        assertEquals(4.6697322801074135, Means.harmonic(numbers), EPSILON);
    }

    @Test
    void testHarmonicMeanThreeNumbers() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 4.0);
        double expected = 3.0 / (1.0 / 1.0 + 1.0 / 2.0 + 1.0 / 4.0);
        assertEquals(expected, Means.harmonic(numbers), EPSILON);
    }

    @Test
    void testHarmonicMeanIdenticalNumbers() {
        List<Double> numbers = Arrays.asList(6.0, 6.0, 6.0);
        assertEquals(6.0, Means.harmonic(numbers), EPSILON);
    }

    @Test
    void testHarmonicMeanWithLinkedList() {
        LinkedList<Double> numbers = new LinkedList<>(Arrays.asList(3.0, 6.0, 9.0));
        double expected = 3.0 / (1.0 / 3.0 + 1.0 / 6.0 + 1.0 / 9.0);
        assertEquals(expected, Means.harmonic(numbers), EPSILON);
    }

    // ========== Additional Edge Case Tests ==========

    @Test
    void testArithmeticMeanWithVeryLargeNumbers() {
        List<Double> numbers = Arrays.asList(1e100, 2e100, 3e100);
        assertEquals(2e100, Means.arithmetic(numbers), 1e90);
    }

    @Test
    void testArithmeticMeanWithVerySmallNumbers() {
        List<Double> numbers = Arrays.asList(1e-100, 2e-100, 3e-100);
        assertEquals(2e-100, Means.arithmetic(numbers), 1e-110);
    }

    @Test
    void testGeometricMeanWithOnes() {
        List<Double> numbers = Arrays.asList(1.0, 1.0, 1.0, 1.0);
        assertEquals(1.0, Means.geometric(numbers), EPSILON);
    }

    @Test
    void testAllMeansConsistencyForIdenticalValues() {
        List<Double> numbers = Arrays.asList(7.5, 7.5, 7.5, 7.5);
        double arithmetic = Means.arithmetic(numbers);
        double geometric = Means.geometric(numbers);
        double harmonic = Means.harmonic(numbers);

        assertEquals(7.5, arithmetic, EPSILON);
        assertEquals(7.5, geometric, EPSILON);
        assertEquals(7.5, harmonic, EPSILON);
    }

    @Test
    void testMeansRelationship() {
        // For positive numbers, harmonic mean ≤ geometric mean ≤ arithmetic mean
        List<Double> numbers = Arrays.asList(2.0, 4.0, 8.0);
        double arithmetic = Means.arithmetic(numbers);
        double geometric = Means.geometric(numbers);
        double harmonic = Means.harmonic(numbers);

        assertTrue(harmonic <= geometric, "Harmonic mean should be ≤ geometric mean");
        assertTrue(geometric <= arithmetic, "Geometric mean should be ≤ arithmetic mean");
    }
}
