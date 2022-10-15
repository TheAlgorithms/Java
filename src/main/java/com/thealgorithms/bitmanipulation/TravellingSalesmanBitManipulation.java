package com.thealgorithms.bitmanipulation;


import java.util.Arrays;

public class TravellingSalesmanBitManipulation {

    /** 
     * @brief
     * The below algorithm is called as Travelling Salesman Using Bit-manipulation.
     * We can use this algorithm to improve the  naive algorithm’s performance.
     * @detail
     * Given the distance/cost(as and adjacency matrix) between each city/node to the other city/node,
     * the problem is to find the shortest possible route that visits every city exactly once
     * and returns to the starting point, or we can say the minimum cost of whole tour.

     * Explanation
     * INPUT -> You are given with an adjacency matrix A = {} which contains the distance between two cities/node.
     * OUTPUT ->  Minimum cost of whole tour from starting point
     * The Worst Case Time Complexity: O(n^2 * 2^n)
     * Space complexity: O(n)
       [Travelling Salesman problem using bit-masking](https://www.geeksforgeeks.org/travelling-salesman-problem-set-1/)

     * @author
     * [Utkarsh Yadav](https://github.com/Rytnix)

     * @param dist is a 2D Array contains distance between 2 cities.
     * @param setOfCities takes Integer number to represent the city/node.
     * @param city takes an Integer number to track the current city.
     * @param n takes an Integer no to tell how many no of
     * @param dp is a 2D Array for memorizing the states to avoid re-computation
     * @return min cost of travelling from start to destination and back to start position.
     */
    public static int travellingSalesmanBits(int[][] dist,
            /*  setOfCities as a bit represent the cities/nodes. Ex: if setOfCities = 2 => 0010(in binary)
            means representing the city/node B if city/nodes are represented as D->C->B->A. */
            int setOfCities,
            int city,  // city is taken to ;track our current city/node movement,where we are currently.
            int n, //n is the no of cities we have.
            int[][] dp) { // dp is used to memorize the state to avoid re-computation.
        if (setOfCities == (1 << n) - 1)
            return dist[city][0];
        if (dp[setOfCities][city] != -1)
            return dp[setOfCities][city];
        int ans =  2147483647;
        for (int choice = 0; choice < n; choice++) {
            if ((setOfCities & (1 << choice)) == 0) {
                int subProb = dist[city][choice] +
                              travellingSalesmanBits(dist, setOfCities | (1 << choice), choice, n, dp);
                // Here we are doing a recursive call to tsp with the updated set of city/node
                // and choice which tells that where we are currently.
                ans = Math.min(ans, subProb);
            }
        }
        dp[setOfCities][city] = ans;
        return ans;
    }

     /**
       @output 97
     */
    public static void main(String[] args) {
        int[][] dist = {{0, 20, 42, 35}, {20, 0, 30, 34}, {42, 30, 0, 12}, {35, 34, 12, 0}};
        int s = dist.length;
        int[][] dp = new int[1 << s][s];
        for (int[] i : dp)
            Arrays.fill(i, -1);

        System.out.println( travellingSalesmanBits(dist, 1, 0, s, dp));
    }
}