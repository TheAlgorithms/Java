package DataStructures.Graphs;

import java.util.*;

/*
Considering 2D matrix tspMatrix[][] as an input, where each consists of array of distances from that indexed city to all the other cities
and -1 represents of non-existant path between those two indexed cities.
The goal is to print at the lowest possible cost during the tspMatrix cycle.
*/

/*
 * Time Complexity: O(N2*log2N)
 * Auxiliary Space: O(N)
 */

public class TravelSalesmanProblem {
  // function to find the minimum cost path
  static void findMinpath(int[][] tspMatrix) {

    int sum = 0, min = Integer.MAX_VALUE;
    int i = 0, j = 0, counter = 0;

    List<Integer> visitedPaths = new ArrayList<Integer>();

    // Starting from the 0th indexed city i.e., the first city
    visitedPaths.add(0);
    int[] path = new int[tspMatrix.length];

    // Traverse the tspMatrix[][]
    while (i < tspMatrix.length && j < tspMatrix[i].length) {

      // Corner of the Matrix
      if (counter >= tspMatrix[i].length - 1) {
        break;
      }

      // If path is unvisted and is cheaper, the store this cost
      if (j != i && !(visitedPaths.contains(j))) {
        if (tspMatrix[i][j] < min) {
          min = tspMatrix[i][j];
          path[counter] = j + 1;
        }
      }
      j++;

      // Check all paths from the ith indexed city
      if (j == tspMatrix[i].length) {
        sum += min;
        min = Integer.MAX_VALUE;
        visitedPaths.add(path[counter] - 1);
        j = 0;
        i = path[counter] - 1;
        counter++;
      }
    }

    // Update the ending city in array from city which was last visited
    i = path[counter - 1] - 1;

    for (j = 0; j < tspMatrix.length; j++) {

      if ((i != j) && tspMatrix[i][j] < min) {
        min = tspMatrix[i][j];
        path[counter] = j + 1;
      }
    }
    sum += min;

    // Started from the node where we finished as well.
    System.out.print("Minimum Cost is : ");
    System.out.println(sum);
  }

  // Driver Code
  public static void main(String[] args) {
    // Input Matrix
    int[][] tspMatrix = {
      {-1, 10, 15, 20},
      {10, -1, 35, 25},
      {15, 35, -1, 30},
      {20, 25, 30, -1}
    };

    // Function Call
    findMinpath(tspMatrix);
  }
}
