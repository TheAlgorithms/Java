package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CentroidDecomposition {
    private List<Integer>[] tree;
    private List<Integer>[] centroidTree;
    private boolean[] centroidMarked;
    private int[] centroidParent;
    private int startingNode;
    private int N;

    public CentroidDecomposition(int n, int startingNode){
        tree = new ArrayList[n];
        centroidTree = new ArrayList[n];
        N = n;
        for (int i = 0; i < centroidTree.length; i++) centroidTree[i] = new ArrayList<>();
        for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>();
        centroidMarked = new boolean[n];
        centroidParent = new int[n];
        startingNode = (int)(Math.random() * n+1);
        for(int i = 0; i<n; i++){
            tree[i] = new ArrayList<>();
        }
    }
    public CentroidDecomposition(int n){
        tree = new ArrayList[n];
        centroidTree = new ArrayList[n];
        centroidMarked = new boolean[n];
        centroidParent = new int[n];
        startingNode = (this.startingNode == -1) ? (int)(Math.random() * n+1) : this.startingNode;
        N = n;
        for (int i = 0; i < centroidTree.length; i++) centroidTree[i] = new ArrayList<>();
        for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>();
        for(int i = 0; i<n; i++){
            tree[i] = new ArrayList<>();
        }
    }

    public List<Integer>[] getCentroidTree(){
        return centroidTree;
    }

    public void addEdge(int u, int v){
        tree[u].add(v);
        tree[v].add(u);
    }

    private void addEdgeCTree(int u, int v){
        centroidTree[u].add(v);
        centroidTree[v].add(u);
        centroidParent[v] = u;
    }


    public void findSubtreeSizes(int src, boolean[] visited, int[] subtreeSizes){
        // dfs traversal to find size of subtree rooted at src
        visited[src] = true;
        subtreeSizes[src] = 1;
        for (int node : tree[src]){
            if(!visited[node] && !centroidMarked[node]){
                visited[node] = true;
                findSubtreeSizes(node, visited, subtreeSizes); // recurse down to last child node
                subtreeSizes[src] += subtreeSizes[node]; // add size of full recursive path to subtree Size of src
            }
        }
    }
    
    public void findCentroid(int src, int previousCentroid){
        int[] subtreeSizes = new int[N];
        boolean[] visited = new boolean[N];
        Arrays.fill(visited, false);
        
        findSubtreeSizes(src, visited, subtreeSizes);
        int treeSize = Arrays.stream(subtreeSizes).max().getAsInt();

        centroidMarked[src] = true;

        for (int node : tree[src]){
            if (subtreeSizes[node] > (treeSize/2)){
                centroidMarked[src] = false;
                break;
            }
        }
        
        if (centroidMarked[src]){
            if (src != startingNode && src != previousCentroid) addEdgeCTree(previousCentroid, src); 
            // centroidTree[previousCentroid].add(src);
            for (int node : tree[src]){
                if (!centroidMarked[node])
                    findCentroid(node, src);                
            }
        }
        else{
            // pick one of the children of the root for next check
            int nextLargestSubtree = tree[src].getFirst();
            for (int node : tree[src]){
                if (subtreeSizes[node] >= subtreeSizes[nextLargestSubtree]) 
                    nextLargestSubtree = node;
            }
            findCentroid(nextLargestSubtree, previousCentroid);
        }
    }

    public static void main(String[] args) {
        CentroidDecomposition cd = new CentroidDecomposition(16, -1);
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

        // int start = cd.startingNode;
        int src = (int) ((Math.random() * (15)) + 0);
        System.out.println("src= " + src);

        cd.findCentroid(src, src);

        // System.out.println((int)(Math.random() * 16));

        for (int i = 0; i < cd.centroidTree.length; i++) {
            System.out.println(String.format("%s %s", i, cd.centroidTree[i]));
        }

        // cd.findSubtreeSizes(8, visited, subtreeSizes);
        // for (int i = 0; i < subtreeSizes.length; i++) {
        //     System.out.println(String.format("%s %s", i, subtreeSizes[i]));
        // }
    }

}
