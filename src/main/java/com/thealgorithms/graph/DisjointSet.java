package com.thealgorithms.graph;

public class DisjointSet {
    int[] parent;
    int[] rank;
    public DisjointSet(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    int findLeader(int x){
        if(parent[x]==x){
            return parent[x];
        }
        int leader = findLeader(parent[x]);
        parent[x] = leader;
        return leader;
    }

    boolean isSame(int x, int y){
        return findLeader(x)==findLeader(y);
    }

    void merge(int x, int y){
        int xLeader = findLeader(x);
        int yLeader = findLeader(y);
        if(xLeader != yLeader){
            if(rank[xLeader]<rank[yLeader]){
                parent[xLeader] = yLeader;
            }else{
                parent[yLeader] = xLeader;
                if(rank[xLeader] == rank[yLeader]){
                    rank[xLeader] += 1;
                }
            }
        }
    }
}
