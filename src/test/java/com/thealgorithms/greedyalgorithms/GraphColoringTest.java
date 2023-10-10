package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class GraphColoringTest {
    // Since there are multiple correct color patterns that are valid
    // we just have to count each time the number of different colors
    // that have been used
    @Test
    public void testConnectedGraphColoring() {
        int[][] inputGraph = {{1, 4, 5}, {0, 2, 6}, {1, 3, 7}, {2, 4, 8}, {0, 3, 9}, {0, 7, 8}, {1, 8, 9}, {2, 5, 9}, {3, 5, 6}, {4, 6, 7}};
        int expectedNumberOfColors = 3;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }

    @Test
    public void testConnectedGraphColoring2() {
        int[][] inputGraph = {{1, 3}, {0, 2}, {1}, {0, 1}};
        int expectedNumberOfColors = 3;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }

    @Test
    public void testConnectedGraphColoring3() {
        int[][] inputGraph = {{1, 4, 5}, {0, 3, 4}, {3, 4}, {1, 2}, {0, 1, 2, 5}, {0, 4}};
        int expectedNumberOfColors = 3;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }

    @Test
    public void testConnectedGraphColoring4() {
        int[][] inputGraph = {{1, 7}, {0, 2, 6}, {1, 3, 5}, {2, 4}, {3, 5}, {2, 4, 6}, {1, 5, 7}, {0, 6}};
        int expectedNumberOfColors = 2;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }

    @Test
    public void testDisconnectedGraphColoring() {
        int[][] inputGraph = {{1}, {0}, {3}, {2}};
        int expectedNumberOfColors = 2;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }

    @Test
    public void testDisconnectedGraphColoring2() {
        int[][] inputGraph = {{1, 3}, {0, 2, 3}, {1}, {0, 1}, {5, 6}, {4, 6}, {4, 5, 7}, {6}, {9, 10}, {8}, {8}};
        int expectedNumberOfColors = 3;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }

    @Test
    public void testEmptyGraphColoring() {
        int[][] inputGraph = {};
        int expectedNumberOfColors = 0;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }

    @Test
    public void testNullGraphColoring() {
        int[][] inputGraph = null;
        int expectedNumberOfColors = 0;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }

    @Test
    public void testSingleVertexGraphColoring() {
        int[][] inputGraph = {{}};
        int expectedNumberOfColors = 1;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }

    @Test
    public void testTwoVertexWithSingleEdgeGraphColoring() {
        int[][] inputGraph = {{1}, {0}};
        int expectedNumberOfColors = 2;
        int[] color = GraphColoring.graphColoring(inputGraph);
        Set<Integer> colors = new HashSet<>();
        for (int clr : color) {
            colors.add(clr);
        }
        int numberOfColors = colors.size();
        assertEquals(numberOfColors, expectedNumberOfColors);
    }
}
