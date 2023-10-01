// Test File for ArticulationPointsAndBridges

// Author: Prabhat-Kumar-42
// GitHub: https://github.com/Prabhat-Kumar-42

// Import necessary packages and classes
package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.datastructures.graphs.ArticulationPointsAndBridges.Edge;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArticulationPointsAndBridgesTest {

    private ArticulationPointsAndBridges graph;

    @BeforeEach
    public void setUp() {
        // Initialize the graph with 5 vertices
        int V = 5;
        graph = new ArticulationPointsAndBridges(V);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // Find articulation points and bridges in the graph
        graph.findArticulationPointsAndBridges();
    }

    @Test
    public void testArticulationPoints() {
        // Get the list of articulation points from the graph
        ArrayList<Integer> articulationPoints = graph.getArticulationPoints();

        // Define the expected articulation points
        int[] expectedArticulationPoints = {2, 3};

        // Check if each expected articulation point is present in the result
        for (int point : expectedArticulationPoints) {
            assertTrue(articulationPoints.contains(point));
        }
    }

    @Test
    public void testBridges() {
        // Get the list of bridges from the graph
        ArrayList<Edge> bridges = graph.getBridges();

        // Print the list of bridges (for debugging purposes)
        System.out.println("bridges is: " + bridges);

        // Define the expected bridges as Edge objects
        Edge[] expectedBridges = {new Edge(2, 3), new Edge(3, 4)};

        // Check if each expected bridge is present in the result
        for (Edge bridge : expectedBridges) {
            assertTrue(bridges.contains(bridge));
        }
    }
}
