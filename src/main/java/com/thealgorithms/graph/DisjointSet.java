package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Disjoint Set (Union-Find) data structure with path
 * compression
 * and union by rank optimizations.
 *
 * <p>
 * A disjoint-set data structure maintains a collection of disjoint dynamic
 * sets.
 * Each set is represented by a "representative" element. The data structure
 * supports
 * two main operations:
 * </p>
 * <ul>
 * <li>Find: Determine which set an element belongs to by finding the
 * representative</li>
 * <li>Union: Join two sets into a single set</li>
 * </ul>
 *
 * <p>
 * Time Complexity:
 * </p>
 * <ul>
 * <li>Find: O(α(n)) amortized</li>
 * <li>Union: O(α(n)) amortized</li>
 * </ul>
 * <p>
 * where α(n) is the inverse Ackermann function, which grows extremely slowly.
 * For all practical values of n, α(n) ≤ 4.
 * </p>
 *
 * <p>
 * Space Complexity: O(n) where n is the number of elements
 * </p>
 *
 * <p>
 * Applications:
 * </p>
 * <ul>
 * <li>Kruskal's algorithm for minimum spanning trees</li>
 * <li>Finding connected components in graphs</li>
 * <li>Online dynamic connectivity</li>
 * <li>Least common ancestor in trees</li>
 * </ul>
 */
public class DisjointSet {
    private final int[] parent;
    private final int[] rank;
    private final int size;
    private int numSets; // Tracks number of disjoint sets

    /**
     * Initializes a disjoint set data structure with elements from 0 to size-1.
     *
     * @param size number of elements
     * @throws IllegalArgumentException if size is negative
     */
    public DisjointSet(int size) {
        if (size < 0)
            throw new IllegalArgumentException("Size must be non-negative");
        this.size = size;
        this.numSets = size;
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Finds the representative of the set containing element x.
     * Uses path compression to optimize future queries.
     *
     * @param x element to find representative for
     * @return representative of x's set
     * @throws IllegalArgumentException if x is out of bounds
     */
    public int find(int x) {
        if (x < 0 || x >= size) {
            throw new IllegalArgumentException("Element out of bounds: " + x);
        }

        // Path compression: Make all nodes on path point directly to root
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * Merges the sets containing elements x and y.
     * Uses union by rank to keep the tree shallow.
     *
     * @param x first element
     * @param y second element
     * @return true if x and y were in different sets, false if they were already in
     *         same set
     * @throws IllegalArgumentException if either element is out of bounds
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false; // Already in same set
        }

        // Union by rank: Attach smaller rank tree under root of higher rank tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        numSets--;
        return true;
    }

    /**
     * Checks if two elements are in the same set.
     *
     * @param x first element
     * @param y second element
     * @return true if x and y are in the same set
     * @throws IllegalArgumentException if either element is out of bounds
     */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * Returns the current number of disjoint sets.
     *
     * @return number of disjoint sets
     */
    public int getNumSets() {
        return numSets;
    }

    /**
     * Returns the size of the set containing element x.
     *
     * @param x element to find set size for
     * @return size of x's set
     * @throws IllegalArgumentException if x is out of bounds
     */
    public int getSetSize(int x) {
        int root = find(x);
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (find(i) == root) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns all elements in the same set as x.
     *
     * @param x element to find set members for
     * @return list of all elements in x's set
     * @throws IllegalArgumentException if x is out of bounds
     */
    public List<Integer> getSetMembers(int x) {
        int root = find(x);
        List<Integer> members = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (find(i) == root) {
                members.add(i);
            }
        }
        return members;
    }
}