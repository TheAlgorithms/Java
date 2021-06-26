package DataStructures.UnionFindDisjointSet;

/**
 * A Java program for the UFDS data structure, useful to detect cycles in graphs, and can be used in 
 * Kruskal's algorithm to find a MST (Minimum Spanning Tree).
 * Time: find O(n), union O(n)
 */
class UFDS {

    private int[] parent;

    public UFDS(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        else return find(parent[x]);
    }

    public void union(int p, int q) {
        if (find(p) == find(q)) return;
        parent[find(p)] = find(q);
    }
}