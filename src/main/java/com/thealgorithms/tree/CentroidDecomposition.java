package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class CentroidDecomposition {
    private List<Integer>[] tree;
    private List<Integer>[] centroidTree;
    private boolean[] removedCentroids;

    public CentroidDecomposition(int n){
        tree = new ArrayList[n];
        centroidTree = new ArrayList[n];
        removedCentroids = new boolean[n];
        for(int i = 0; i<n; i++){
            tree[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v){
        tree[u].add(v);
        tree[v].add(u);
    }

    public void findSubtreeSizes(int src, boolean[] visited, int[] subtreeSizes){
        // dfs traversal to find size of subtree rooted at src
        visited[src] = true;
        subtreeSizes[src] = 1;
        for (int node : tree[src]){
            if(!visited[node]){
                visited[node] = true;
                findSubtreeSizes(node, visited, subtreeSizes); // recurse down to last child node
                subtreeSizes[src] += subtreeSizes[node]; // add size of full recursive path to subtree Size of src
            }
        }
    }
    
    private void findCentroid(int src){
        int treeSize = tree[src].size();
        int[] subtreeSizes = new int[treeSize];
        boolean[] visited = new boolean[treeSize];
        Arrays.fill(visited, false);

        findSubtreeSizes(src, visited, subtreeSizes);

        boolean isCentroid = true;
        for (int node : tree[src]){
            isCentroid = (subtreeSizes[node] <= (subtreeSizes[src]/2)) ? true : false;
        }

        if (isCentroid){
            for (int node : tree[src]){
                
            }
        }
        else{
            // pick one of the children of the node for next check
        }
    }

    public static void main(String[] args) {
        CentroidDecomposition cd = new CentroidDecomposition(16);
        cd.addEdge(0, 1);
        cd.addEdge(0, 2);
        cd.addEdge(0, 3);
        cd.addEdge(1, 4);
        cd.addEdge(1, 5);
        cd.addEdge(2, 6);
        cd.addEdge(2, 7);
        cd.addEdge(3, 8);
        cd.addEdge(8, 9);
        cd.addEdge(8, 10);
        cd.addEdge(6, 11);
        cd.addEdge(11, 12);
        cd.addEdge(11, 13);
        cd.addEdge(13, 14);
        cd.addEdge(14, 15);

        boolean[] visited = new boolean[16];
        int[] subtreeSizes = new int[16];

        cd.findSubtreeSizes(3, visited, subtreeSizes);
    }

}
