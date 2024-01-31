package com.thealgorithms.datastructures.graphs;

import java.util.LinkedList;
import java.util.Scanner;

/*
 *  The Welsh-Powell algorithm is a graph coloring algorithm
 *  used for coloring a graph with the minimum number of colors.
 *  https://en.wikipedia.org/wiki/Graph_coloring
 */
public class WelshPowell {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for the number of vertices in the graph
        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();
        scanner.nextLine();
        // Initialize the graph with the given number of vertices
        WPGraph graph = new WPGraph(numVertices);

        // Input for the number of edges
        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();
        scanner.nextLine();
        // Adding edges to the graph based on user input
        for (int i = 0; i < numEdges; i++) {
            System.out.print("Enter both vertices for edge " + (i + 1) + " (format: 'src dest'): ");
            String[] vertices = scanner.nextLine().split(" ");
            int src = Integer.parseInt(vertices[0]);
            int dest = Integer.parseInt(vertices[1]);

            graph.addEdge(src, dest);
        }

        // Apply Welsh Powell coloring algorithm
        int colors[] = graph.welshPowellColoring();

        // Output the color of each vertex
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vertex " + (i + 1) + " is colored with color " + colors[i]);
        }

        scanner.close();
    }

    public static class WPGraph {

        private int numVer; // Number of vertices in the graph
        public LinkedList<Integer>[] adjLists; // Adjacency lists for the graph

        // Constructor to initialize the graph with a certain number of vertices
        public WPGraph(int vertices) {
            numVer = vertices;
            adjLists = new LinkedList[vertices];

            for (int i = 0; i < vertices; i++) {
                adjLists[i] = new LinkedList<>();
            }
        }

        // Method to add an edge to the graph (undirected)
        public void addEdge(int src, int dest) {
            // Check to prevent duplicate edges
            if (!adjLists[src].contains(dest)) {
                adjLists[src].add(dest);
                adjLists[dest].add(src); // For undirected graphs, add the reverse edge as well
            }
        }

        // Method implementing Welsh Powell coloring algorithm
        public int[] welshPowellColoring() {
            int[] colors = new int[numVer]; // Array to store color of each vertex
            for (int i = 0; i < numVer; i++) {
                colors[i] = -1; // Initialize all vertices as uncolored
            }

            // Creating a list of vertices sorted by descending degree
            LinkedList<Integer> sortedVertices = new LinkedList<>();
            for (int i = 0; i < numVer; i++) {
                sortedVertices.add(i);
            }
            sortedVertices.sort((v1, v2) -> adjLists[v2].size() - adjLists[v1].size());

            // Coloring vertices
            for (int vertex : sortedVertices) {
                if (colors[vertex] == -1) {
                    boolean[] usedColors = new boolean[numVer]; // Track used colors
                    for (int neighbor : adjLists[vertex]) {
                        if (colors[neighbor] != -1) {
                            usedColors[colors[neighbor]] = true; // Mark neighbor's color as used
                        }
                    }

                    // Assign the first unused color
                    for (int color = 0; color < numVer; color++) {
                        if (!usedColors[color]) {
                            colors[vertex] = color;
                            break;
                        }
                    }
                }
            }

            return colors; // Return the array of colors for each vertex
        }
    }
}
