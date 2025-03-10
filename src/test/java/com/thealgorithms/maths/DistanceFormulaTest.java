package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistanceFormulaTest {

    @Test
    void euclideanTest1() {
        Assertions.assertEquals(DistanceFormula.euclideanDistance(1, 1, 2, 2), 1.4142135623730951);
    }

    @Test
    void euclideanTest2() {
        Assertions.assertEquals(DistanceFormula.euclideanDistance(1, 3, 8, 0), 7.0710678118654755);
    }

    @Test
    void euclideanTest3() {
        Assertions.assertEquals(DistanceFormula.euclideanDistance(2.4, 9.1, 55.1, 100), 110.91911467371168);
    }

    @Test
    void euclideanTest4() {
        Assertions.assertEquals(DistanceFormula.euclideanDistance(1000, 13, 20000, 84), 19022.067605809836);
    }

    @Test
    public void manhattantest1() {
        assertEquals(DistanceFormula.manhattanDistance(1, 2, 3, 4), 4);
    }

    @Test
    public void manhattantest2() {
        assertEquals(DistanceFormula.manhattanDistance(6.5, 8.4, 20.1, 13.6), 18.8);
    }

    @Test
    public void manhattanTest3() {
        assertEquals(DistanceFormula.manhattanDistance(10.112, 50, 8, 25.67), 26.442);
    }

    @Test
    public void hammingTest1() {
        int[] array1 = {1, 1, 1, 1};
        int[] array2 = {0, 0, 0, 0};
        assertEquals(DistanceFormula.hammingDistance(array1, array2), 4);
    }

    @Test
    public void hammingTest2() {
        int[] array1 = {1, 1, 1, 1};
        int[] array2 = {1, 1, 1, 1};
        assertEquals(DistanceFormula.hammingDistance(array1, array2), 0);
    }

    @Test
    public void hammingTest3() {
        int[] array1 = {1, 0, 0, 1, 1, 0, 1, 1, 0};
        int[] array2 = {0, 1, 0, 0, 1, 1, 1, 0, 0};
        assertEquals(DistanceFormula.hammingDistance(array1, array2), 5);
    }

    @Test
    public void minkowskiTest1() {
        double[] array1 = {1, 3, 8, 5};
        double[] array2 = {4, 2, 6, 9};
        assertEquals(DistanceFormula.minkowskiDistance(array1, array2, 1), 10);
    }

    @Test
    public void minkowskiTest2() {
        double[] array1 = {1, 3, 8, 5};
        double[] array2 = {4, 2, 6, 9};
        assertEquals(DistanceFormula.minkowskiDistance(array1, array2, 2), 5.477225575051661);
    }

    @Test
    public void minkowskiTest3() {
        double[] array1 = {1, 3, 8, 5};
        double[] array2 = {4, 2, 6, 9};
        assertEquals(DistanceFormula.minkowskiDistance(array1, array2, 3), 4.641588833612778);
    }
}
