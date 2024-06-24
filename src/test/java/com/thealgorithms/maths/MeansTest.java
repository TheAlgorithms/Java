package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;

class MeansTest {

    @Test
    void arithmeticMeanZeroNumbers() throws IllegalArgumentException {
        List<Double> numbers = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> Means.arithmetic(numbers));
    }

    @Test
    void geometricMeanZeroNumbers() throws IllegalArgumentException {
        List<Double> numbers = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> Means.geometric(numbers));
    }

    @Test
    void harmonicMeanZeroNumbers() throws IllegalArgumentException {
        List<Double> numbers = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> Means.harmonic(numbers));
    }

    @Test
    void arithmeticMeanSingleNumber() {
        List<Double> numbers = Lists.newArrayList(2.5);
        assertEquals(2.5, Means.arithmetic(numbers));
    }

    @Test
    void geometricMeanSingleNumber() {
        Set<Double> numbers = Sets.newHashSet(Lists.newArrayList(2.5));
        assertEquals(2.5, Means.geometric(numbers));
    }

    @Test
    void harmonicMeanSingleNumber() {
        LinkedHashSet<Double> numbers = Sets.newLinkedHashSet(2.5);
        assertEquals(2.5, Means.harmonic(numbers));
    }

    @Test
    void arithmeticMeanMultipleNumbers() {
        Set<Double> numbers = Sets.newTreeSet(1d, 2.5, 83.3, 25.9999, 46.0001, 74.7, 74.5);
        assertEquals(44, Means.arithmetic(numbers));
    }

    @Test
    void geometricMeanMultipleNumbers() {
        LinkedList<Double> numbers = new LinkedList<>();
        numbers.addAll(Lists.newArrayList(1d, 2d, 3d, 4d, 5d, 6d, 1.25));
        assertEquals(2.6426195539300585, Means.geometric(numbers));
    }

    @Test
    void harmonicMeanMultipleNumbers() {
        Vector<Double> numbers = new Vector<>(Lists.newArrayList(1d, 2.5, 83.3, 25.9999, 46.0001, 74.7, 74.5));
        assertEquals(4.6697322801074135, Means.harmonic(numbers));
    }
}
