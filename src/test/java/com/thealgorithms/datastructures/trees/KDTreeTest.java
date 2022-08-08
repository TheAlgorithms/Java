package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KDTreeTest {

    @Test
    void findMin() {
        int[][] coordinates = {
                {30, 40},
                {5, 25},
                {70, 70},
                {10, 12},
                {50, 30},
                {35, 45}
        };
        KDTree.Point[] points = Arrays.stream(coordinates)
                .map(KDTree.Point::new)
                .toArray(KDTree.Point[]::new);
        KDTree kdTree = new KDTree(points);

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
                {35, 45}
        };
        KDTree.Point[] points = Arrays.stream(coordinates)
                .map(KDTree.Point::new)
                .toArray(KDTree.Point[]::new);
        KDTree kdTree = new KDTree(points);

        kdTree.delete(points[0]);
        assertEquals(35, kdTree.getRoot().getPoint().getCoordinate(0));
        assertEquals(45, kdTree.getRoot().getPoint().getCoordinate(1));
    }

}
