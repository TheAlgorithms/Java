package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KDTreeTest {

    KDTree.Point pointOf(int x, int y) {
        return new KDTree.Point(new int[] {x, y});
    }

    @Test
    void findMin() {
        int[][] coordinates = {
            {30, 40},
            {5, 25},
            {70, 70},
            {10, 12},
            {50, 30},
            {35, 45},
        };
        KDTree kdTree = new KDTree(coordinates);

        assertEquals(5, kdTree.findMin(0).getCoordinate(0));
        assertEquals(12, kdTree.findMin(1).getCoordinate(1));
    }

    @Test
    void delete() {
        int[][] coordinates = {
            {30, 40},
            {5, 25},
            {70, 70},
            {10, 12},
            {50, 30},
            {35, 45},
        };
        KDTree kdTree = new KDTree(coordinates);

        kdTree.delete(pointOf(30, 40));
        assertEquals(35, kdTree.getRoot().getPoint().getCoordinate(0));
        assertEquals(45, kdTree.getRoot().getPoint().getCoordinate(1));
    }

    @Test
    void findNearest() {
        int[][] coordinates = {
            {2, 3},
            {5, 4},
            {9, 6},
            {4, 7},
            {8, 1},
            {7, 2},
        };
        KDTree kdTree = new KDTree(coordinates);

        assertEquals(pointOf(7, 2), kdTree.findNearest(pointOf(7, 2)));
        assertEquals(pointOf(8, 1), kdTree.findNearest(pointOf(8, 1)));
        assertEquals(pointOf(2, 3), kdTree.findNearest(pointOf(1, 1)));
        assertEquals(pointOf(5, 4), kdTree.findNearest(pointOf(5, 5)));
    }
}
