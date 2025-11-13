package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CentroidDecomposition {
    private ArrayList<Integer>[] tree;
    private ArrayList<Integer>[] centroidTree;
    private int[] subtreeSizes;
    private boolean[] visited;
    private boolean[] centroidMarked;
    private int[] centroidParent;
    private int startingNode;
    private int N;

    @SuppressWarnings("unchecked")
    public CentroidDecomposition(int n, int startingNode){
        tree = new ArrayList[n];
        centroidTree = new ArrayList[n];
        N = n;
        centroidMarked = new boolean[n];
        centroidParent = new int[n];
        subtreeSizes = new int[N];
        visited = new boolean[N];
        if (startingNode < 0 || startingNode > n-1){
            throw new IllegalArgumentException("Starting node must be in range 0.." + (n - 1) + " but got " + startingNode);
        }
        for(int i = 0; i<n; i++){
            centroidParent[i] = -1;
            tree[i] = new ArrayList<>();
            centroidTree[i] = new ArrayList<>();
        }
    }

    public CentroidDecomposition(int n){
        this(n, (int)(Math.random() * n));
    }

    public void build(){
        findCentroid(startingNode, startingNode);
    }

    public void reset(){
        for(int i = 0; i<N; i++){
            centroidTree[i] = new ArrayList<>();
            centroidParent[i] = -1;
            subtreeSizes[i] = 0;
            centroidMarked[i] = false;
            visited[i] = false;
        }
    }

    public int getStartingNode(){
        return startingNode;
    }

    public int[] getSubtreeSizes(){
        return subtreeSizes;
    }

    public void addEdgeTree(int u, int v){
        tree[u].add(v);
        tree[v].add(u);
    }

    private void addEdgeCTree(int u, int v){
        centroidTree[u].add(v);
        centroidTree[v].add(u);
        centroidParent[v] = u;
    }

    public ArrayList<Integer>[] getCentroidTree(){
        return centroidTree;
    }

    public int getParent(int v){
        return centroidParent[v];
    }

    public List<Integer> getCentroidChildren(int v) {
        return centroidTree[v].stream()
                .filter(child -> centroidParent[child] == v && centroidParent[v] != child)
                .collect(Collectors.toList());
    }

    public void findSubtreeSizes(int src){
        visited[src] = true;
        subtreeSizes[src] = 1;
        for (int node : tree[src]){
            if(!visited[node] && !centroidMarked[node]){
                visited[node] = true;
                findSubtreeSizes(node);
                subtreeSizes[src] += subtreeSizes[node];
            }
        }
    }
    
    public void findCentroid(int src, int previousCentroid){

        Arrays.fill(visited, false);
        
        findSubtreeSizes(src);
        int treeSize = subtreeSizes[src];

        int heavyChild = -1;

        for (int node : tree[src]){
            if(centroidMarked[node]) continue;
            if(subtreeSizes[node] > (treeSize/2)){
                heavyChild = node;
                break;
            }
        }

        if (heavyChild != -1){
            findCentroid(heavyChild, previousCentroid);
            return;
        }
        
        centroidMarked[src] = true;

        if(src != startingNode && src != previousCentroid) addEdgeCTree(previousCentroid, src);

        for (int node : tree[src]){
            if (!centroidMarked[node])
                findCentroid(node, src);                
            }
        }

    public static void main(String[] args) {
        CentroidDecomposition cd = new CentroidDecomposition(16);
        cd.addEdgeTree(0, 1);
        cd.addEdgeTree(0, 2);
        cd.addEdgeTree(0, 3);
        cd.addEdgeTree(1, 4);
        cd.addEdgeTree(1, 5);
        cd.addEdgeTree(2, 6);
        cd.addEdgeTree(2, 7);
        cd.addEdgeTree(3, 8);
        cd.addEdgeTree(8, 9);
        cd.addEdgeTree(8, 10);
        cd.addEdgeTree(6, 11);
        cd.addEdgeTree(11, 12);
        cd.addEdgeTree(11, 13);
        cd.addEdgeTree(13, 14);
        cd.addEdgeTree(14, 15);

        // boolean[] visited = new boolean[16];
        // int[] subtreeSizes = new int[16];

        // int start = cd.startingNode;
        // int src = (int)(Math.random() * 15);
        // System.out.println("src= " + src);
        int start = cd.getStartingNode();
        cd.findCentroid(start, start);

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
