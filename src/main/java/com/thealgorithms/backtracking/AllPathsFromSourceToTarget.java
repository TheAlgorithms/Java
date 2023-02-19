/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - To find all possible paths from source to destination*/

/**Wikipedia link -> https://en.wikipedia.org/wiki/Shortest_path_problem */
package com.thealgorithms.backtracking;

// JAVA program to print all paths from a source to destination.
import java.util.*;

// A directed graph using adjacency list representation


public class AllPathsFromSourceToTarget {

    // No. of vertices in graph
    private int v;

    // adjacency list
    private ArrayList<Integer>[] adjList;

    // Constructor
    public AllPathsFromSourceToTarget(int vertices)
    {

        // initialise vertex count
        this.v = vertices;

        // initialise adjacency list
        initAdjList();
    }

    // utility method to initialise adjacency list
    private void initAdjList()
    {
        adjList = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from u to v
    public void addEdge(int u, int v)
    {
        // Add v to u's list.
        adjList[u].add(v);
    }

    // Prints all paths from 's' to 'd'

    public int printAllPaths(int s, int d)
    {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(s);
        int a[]=new int[1];
        // Call recursive utility
        printAllPathsUtil(s, d, isVisited, pathList,a);
        return a[0];
    }

    // A recursive function to print all paths from 'u' to 'd'.
    // isVisited[] keeps track of vertices in current path.
    // localPathList<> stores actual vertices in the current path 
    private int printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList, int a[])
    {

        if (u.equals(d)) {
            System.out.println(localPathList);
            a[0]++;
            // if match found then no need to traverse more till depth
            return a[0];
        }

        // Mark the current node
        isVisited[u] = true;

        // Recursion for all the vertices adjacent to current vertex
        
        for (Integer i : adjList[u]) {
            if (!isVisited[i]) {
                // store current node in path[]
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList,a);

                // remove current node in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[u] = false;
        return a[0];
        // returns number of paths from source to destination
    }

    // Driver program
    public static int[][] allPathsFromSourceToTarget(int vertices, int a[][], int source, int destination, int num_of_paths)
    {
        // Create a sample graph
        AllPathsFromSourceToTarget g = new AllPathsFromSourceToTarget(vertices);
        for(int i=0 ; i<a.length ; i++)
        {
            g.addEdge(a[i][0], a[i][1]);
            // edges are added
        }
        System.out.println("Following are all different paths from "+ source + " to " + destination);
        int c = g.printAllPaths(source, destination);
        // method call to find number of paths
        return c == num_of_paths;
        // returns true if number of paths calculated from source to destination matches with given paths
    }
}