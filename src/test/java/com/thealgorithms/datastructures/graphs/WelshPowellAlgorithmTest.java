package com.thealgorithms.datastructures.graphs;

import com.thealgorithms.datastructures.graphs.Welsh_Powell.WPGraph;
public class WelshPowellAlgorithmTest {

        public static void main(String[] args) {
            // Creating the graph for the test case
            int numVertices = 6; // Number of vertices
            WPGraph graph = new WPGraph(numVertices);

            // Adding the edges
            graph.addEdge(0, 1);
            graph.addEdge(0, 2);
            graph.addEdge(1, 3);
            graph.addEdge(2, 3);
            graph.addEdge(2, 4);
            graph.addEdge(3, 4);
            graph.addEdge(3, 5);
            graph.addEdge(4, 5);

            // Executing the method
            int[] colors = graph.welshPowellColoring();

            // Checking the results
            boolean testPassed = true;
            for (int i = 0; i < numVertices; i++) {
                for (int neighbor : graph.adjLists[i]) {
                    if (colors[i] == colors[neighbor]) {
                        testPassed = false;
                        break;
                    }
                }
                if (!testPassed) {
                    break;
                }
            }

            if (testPassed) {
                System.out.println("Test Passed.");
            } else {
                System.out.println("Test Failed.");
            }
        }

}
