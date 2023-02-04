package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Java program that implements Kosaraju Algorithm.
 * @author Shivanagouda S A (https://github.com/shivu2002a)
 * 
 */

/**
 * Kosaraju algorithm is a linear time algorithm to find the strongly connected components of a 
   directed graph, which, from here onwards will be referred by SCC. It leverages the fact that the transpose
   graph (same graph with all the edges reversed) has exactly the same SCCs as the original graph.
    
 * A graph is said to be strongly connected if every vertex is reachable from every other vertex. 
   The SCCs of a directed graph form a partition into subgraphs that are themselves strongly connected.
   Single node is always a SCC.

 * Example:

    0 <--- 2 -------> 3 -------- > 4 ---- > 7
    |     ^                      | ^       ^
    |    /                       |  \     /
    |   /                        |   \   /
    v  /                         v    \ /
    1                            5 --> 6 

    For the above graph, the SCC list goes as follows:
    0, 1, 2
    3
    4, 5, 6
    7
    
 {@summary}
 * Kosaraju Algorithm: 
    1. Perform DFS traversal of the graph. Push node to stack before returning.
    2. Find the transpose graph by reversing the edges.
    3. Pop nodes one by one from the stack and again to DFS on the modified graph.

    The transpose graph of the above graph:
     0 ---> 2 <------- 3 <------- 4 <------ 7
    ^     /                      ^ \       /
    |    /                       |  \     /
    |   /                        |   \   /
    |  v                         |    v v
    1                            5 <--- 6 

    We can observe that this graph has the same SCC as that of original graph.

 */

public class Kosaraju {

    //Store each component
    private List<Integer> scc = new ArrayList<>();

    //All the strongly connected components
    private List<List<Integer>> sccsList = new ArrayList<>();

    public List<List<Integer>> kosaraju(int v, List<List<Integer>> list){
        
        //Sort the edges according to lowest finish time
        var st = new Stack<Integer>();
        int vis[] = new int[v];
        for (int i = 0; i < v; i++) {
            if(vis[i] == 0){
                dfs(i, vis, list, st);
            }
        }

        //Create a transpose graph of the given graph
        var transposeGraph = new ArrayList<List<Integer>>(8);
        for (int i = 0; i < v; i++) {
            transposeGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < vis.length; i++) {
            vis[i] = 0;
            for (Integer neigh : list.get(i)) {
                transposeGraph.get(neigh).add(i);
            }
        }

        //Run DFS on the transpose graph and get the Strongly Connected Components
        while (!st.isEmpty()) {
            var node = st.pop();
            if(vis[node] == 0){
                dfs2(node, vis, transposeGraph);
                System.out.println();
                sccsList.add(scc);
                scc = new ArrayList<>();
            }
        }
        return sccsList;
    }

    //Dfs to store the nodes in order of lowest finish time
    private void dfs(int node, int vis[], List<List<Integer>> list, Stack<Integer> st){
        vis[node] = 1;
        for(Integer neighbour : list.get(node)){
            if(vis[neighbour] == 0)
                dfs(neighbour, vis, list, st);
        }
        st.push(node);
    }

    //Dfs to find all the nodes of each strongly connected component
    private void dfs2(int node, int vis[], List<List<Integer>> list){
        vis[node] = 1;
        for(Integer neighbour : list.get(node)){
            if(vis[neighbour] == 0)
                dfs2(neighbour, vis, list);
        }
        scc.add(node);
    }


    public static void main(String[] args) {
        var n = 8;
        var adjList = new ArrayList<List<Integer>>(n);

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        adjList.get(0).add(1);
        adjList.get(1).add(2);
        adjList.get(2).add(0);
        adjList.get(2).add(3);
        adjList.get(3).add(4);
        adjList.get(4).add(5);
        adjList.get(4).add(7);
        adjList.get(5).add(6);
        adjList.get(6).add(4);
        adjList.get(6).add(7);
        System.out.println(adjList);

        var scc = new Kosaraju();
        List<List<Integer>> sccsList = scc.kosaraju(n, adjList);

        System.out.println(sccsList);
    }
    
}
