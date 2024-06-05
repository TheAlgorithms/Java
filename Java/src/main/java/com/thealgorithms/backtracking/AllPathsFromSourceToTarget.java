/**
 * Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - To find all possible paths from source to destination */
/** Wikipedia link -> https://en.wikipedia.org/wiki/Shortest_path_problem */
package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;


public class AllPathsFromSourceToTarget {

    /**
     * Common variables to "AllPathsFromSourceToTarget" class.
     *
     * 1) Number of vertices in graph.
     * 2) List to store the paths from source to destiny.
     * 3) Adjacency list.
     */
    private final int v;
    static List<List<Integer>> nm = new ArrayList<>();
    private ArrayList<Integer>[] adjList;

    /**
    * Constructor.
    * @param vertices Number of vertices.
    */
    
    public AllPathsFromSourceToTarget(int vertices) {

        // initialise vertex count
        this.v = vertices;

        // initialise adjacency list
        initAdjList();
    }

    /**
    * Creates an ArrayList for each position in adjList.
    */
    
    private void initAdjList() {
        adjList = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    /**
    * Add edge from u to v
    *
    * @param u Value to select list.
    * @param v Value to introduce.
    */
    
    public void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    /**
    *
    * @param s Value of source
    * @param d Value of destiny.
    */
    
    public void storeAllPaths(int s, int d) {
        // Array composed by boolean´s objects with v like lenght
        boolean[] isVisited = new boolean[v];
        // This ArrayList stores the track.
        ArrayList<Integer> pathList = new ArrayList<>();
        // add source to path[]
        pathList.add(s);
        // Call recursive function utility.
        storeAllPathsUtil(s, d, isVisited, pathList);
    }

    /**
    *
    * Function to StoreAllPaths
    * 
    * @param u Value of source
    * @param d Value of destiny
    * @param isVisited array to keeps track of vertices in current path.
    * @param localPathList stores actual vertices in the current path
    */
    
    private void storeAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) {

        //If node u is equals to d the path has benn completly found it.
        if (u.equals(d)) {
            nm.add(new ArrayList<>(localPathList));
            return;
        }

        // Mark the current node as visited.
        isVisited[u] = true;

        // Recursion for all the vertices adjacent to current vertex
        for (Integer i : adjList[u]) {
            if (!isVisited[i]) {
                // store current node in path[]
                localPathList.add(i);
                storeAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node as false to release it and be used by others.
        isVisited[u] = false;
    }

    /**
    *
    * Driver program
    *
    * @param vertices number of vertices of the graph
    * @param a represents the aristas of the graph.
    * @param source value of source.
    * @param destination value of destiny.
    * @return A List composed by diferent lists that stores all the possible paths to the destiny.
    */
    
    public static List<List<Integer>> allPathsFromSourceToTarget(int vertices, int[][] a, int source, int destination) {
        // Create a sample graph
        AllPathsFromSourceToTarget g = new AllPathsFromSourceToTarget(vertices);

        // edges are added
        for (int[] i : a) {
            g.addEdge(i[0], i[1]);
        }

        // method call to store all possible paths
        g.storeAllPaths(source, destination);
        
        return nm;
    }
}
