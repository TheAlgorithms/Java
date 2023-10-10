package com.thealgorithms.greedyalgorithms;

import java.util.Arrays;

// Problem Link: https://en.wikipedia.org/wiki/Graph_coloring

public class GraphColoring {
    // Function that assigns a color to each node of a graph.
    // Note: Input is an array with length equal to the number of
    // vertices of the graph and for a specific vertex named i
    // graph[i] is an array with the vertices that i has an edge with
    public static int[] graphColoring(int[][] graph) {
        // Edge case for null input, just return an empty array of
        // colors to avoid a NullPointerException
        if (graph == null) {
            return new int[] {};
        }
        // Number of vertices in the graph
        int vertices = graph.length;
        // color[] contains a color value for each node of the graph
        int[] color = new int[vertices];
        // Check if graph is empty
        if (vertices == 0) {
            return color;
        }
        // At start no vertex has a color assigned
        Arrays.fill(color, -1);
        // available[] shows the availability of a color
        boolean[] available = new boolean[vertices];
        // Assign the first color to the first vertex
        color[0] = 0;

        // Find the color of all the vertices, i is the current vertex.
        for (int i = 1; i < vertices; ++i) {
            // For each vertex that is connected with current vertex
            // and it has a color assigned, set the value True for this
            // color in available[]. This means that this color cannot
            // be used for current vertex since a neighbor uses it
            for (int connected : graph[i]) {
                if (color[connected] != -1) {
                    available[color[connected]] = true;
                }
            }
            // Find the first available color for current vertex
            int clr = 0;
            while (clr < vertices && available[clr]) {
                ++clr;
            }
            // Assign color to vertex in color[]
            color[i] = clr;
            // Reset available[] to False for next iteration
            Arrays.fill(available, false);
        }
        return color;
    }
}
