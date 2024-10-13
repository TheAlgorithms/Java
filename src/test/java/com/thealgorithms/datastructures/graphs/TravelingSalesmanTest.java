package com.thealgorithms.datastructures.graphs;

public class TravelingSalesmanTest {
    public static void main(String[] args) {
        // Test case: Distance matrix representing the cities
        int[][] dist = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
        };

        // Calling the tsp method from the TravelingSalesmanDP class
        int result = TravelingSalesmanDP.tsp(dist);

        // Output the result
        System.out.println("Minimum cost: " + result);
        // Expected output: Minimum cost: 80
    }
}

