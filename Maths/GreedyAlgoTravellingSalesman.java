package Maths;

import java.util.*;
 
public class GreedyAlgoTravellingSalesman {
 
    /**
	 * Function to find the minimum
     * cost path for all the paths
     * 
     * @param tsp 2D matrix where each row has the array of distances from indexed city to all other cities and -1 if no such path exists
     */
    static void findMinRoute(int[][] tsp)
    {
        int sum = 0;
        int counter = 0;
        int j = 0, i = 0;
        int min = Integer.MAX_VALUE;
        
        List<Integer> visitedRouteList = new ArrayList<>();
 
        visitedRouteList.add(0);
        int[] route = new int[tsp.length];
 
        while (i < tsp.length && j < tsp[i].length) {
 
            if (counter >= tsp[i].length - 1) {
                break;
            }
 
            if (j != i && !(visitedRouteList.contains(j))) {
                if (tsp[i][j] < min) {
                    min = tsp[i][j];
                    route[counter] = j + 1;
                }
            }
            j++;
 
            if (j == tsp[i].length) {
                sum += min;
                min = Integer.MAX_VALUE;
                visitedRouteList.add(route[counter] - 1);
                j = 0;
                i = route[counter] - 1;
                counter++;
            }
        }
 
        i = route[counter - 1] - 1;
 
        for (j = 0; j < tsp.length; j++) {
 
            if ((i != j) && tsp[i][j] < min) {
                min = tsp[i][j];
                route[counter] = j + 1;
            }
        }
        sum += min;
 
        System.out.print("Minimum Cost is : ");
        System.out.println(sum);
    }
 
    /**
     * Driver code
     * @param args
     */
    public static void main(String[] args)
    {
        int[][] tsp = {
            { -1, 10, 15, 20 },
            { 10, -1, 35, 25 },
            { 15, 35, -1, 30 },
            { 20, 25, 30, -1 }
        };
 
        findMinRoute(tsp);
    }
}