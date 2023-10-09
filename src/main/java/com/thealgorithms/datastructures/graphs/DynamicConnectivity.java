package com.thealgorithms.datastructures.graphs;
//Link for reference: https://www.scaler.com/topics/data-structures/disjoint-set/
class DynamicConnectivity {
    private int[] parent;
    private int[] size;

    public DynamicConnectivity(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ)
            return;

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    public static void main(String[] args) {
        int n = 10; // Number of elements

        // Create a DynamicConnectivity object with n elements
        DynamicConnectivity dynamicConnectivity = new DynamicConnectivity(n);

        // Perform union operations to connect elements
        dynamicConnectivity.union(0, 1);
        dynamicConnectivity.union(2, 3);
        dynamicConnectivity.union(4, 5);
        dynamicConnectivity.union(6, 7);

        // Check if elements are connected
        System.out.println("0 and 1 are connected: " + dynamicConnectivity.isConnected(0, 1)); // true
        System.out.println("2 and 4 are connected: " + dynamicConnectivity.isConnected(2, 4)); // false
        System.out.println("3 and 5 are connected: " + dynamicConnectivity.isConnected(3, 5)); // false

        // Perform more union operations
        dynamicConnectivity.union(1, 5);
        dynamicConnectivity.union(3, 7);

        // Check if elements are connected after more unions
        System.out.println("0 and 4 are connected: " + dynamicConnectivity.isConnected(0, 4)); // true
        System.out.println("2 and 6 are connected: " + dynamicConnectivity.isConnected(2, 6)); // true
    }
}
