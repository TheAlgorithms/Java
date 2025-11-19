package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

/**
 * Implements centroid decomposition on a tree.
 *
 * A centroid of a tree is a node whose removal splits the tree into
 * components of size at most n/2. Centroid decomposition recursively
 * finds centroids and builds a centroid tree of height O(log n).
 *
 * This class supports:
 * <ul>
 *   <li>Adding edges to the original tree</li>
 *   <li>Building the centroid decomposition</li>
 *   <li>Querying parents and children in the centroid tree</li>
 * </ul>
 *
 * <p>
 * <strong>Reference:</strong> https://medium.com/carpanese/an-illustrated-introduction-to-centroid-decomposition-8c1989d53308<br>
 * <strong>Author:</strong> Lennart S.<br>
 * <strong>GitHub:</strong> https://github.com/lens161<br>
 * </p>
 */

public class CentroidDecomposition {
    private final List<Integer>[] tree;
    private final List<Integer>[] centroidTree;
    private int[] subtreeSizes;
    private boolean[] visited;
    private boolean[] centroidMarked;
    private int[] centroidParent;
    private int startingNode;
    private final int N;

    @SuppressWarnings("unchecked")
    public CentroidDecomposition(int n, int startingNode) {
        this.tree = (List<Integer>[]) new ArrayList<?>[n];
        this.centroidTree = (List<Integer>[]) new ArrayList<?>[n];
        N = n;
        centroidMarked = new boolean[n];
        centroidParent = new int[n];
        subtreeSizes = new int[N];
        visited = new boolean[N];
        if (startingNode < 0 || startingNode > n - 1) {
            throw new IllegalArgumentException("Starting node must be in range 0.." + (n - 1) + " but got " + startingNode);
        }
        this.startingNode = startingNode;
        for (int i = 0; i < n; i++) {
            centroidParent[i] = -1;
            tree[i] = new ArrayList<>();
            centroidTree[i] = new ArrayList<>();
        }
    }

    public CentroidDecomposition(int n) {
        this(n, (int) (Math.random() * n));
    }

    public void build() {
        reset();
        findCentroid(startingNode, startingNode);
    }

    public void reset() {
        for (int i = 0; i < N; i++) {
            centroidTree[i].clear();
            centroidParent[i] = -1;
            subtreeSizes[i] = 0;
            centroidMarked[i] = false;
            visited[i] = false;
        }
    }

    public int getStartingNode() {
        return startingNode;
    }

    public int[] getSubtreeSizes() {
        return subtreeSizes;
    }

    public void addEdgeTree(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }

    private void addEdgeCTree(int u, int v) {
        centroidTree[u].add(v);
        centroidTree[v].add(u);
        centroidParent[v] = u;
    }

    public List<Integer>[] getCentroidTree() {
        return centroidTree;
    }

    public int getParent(int v) {
        return centroidParent[v];
    }

    public List<Integer> getCentroidChildren(int v) {
        return centroidTree[v].stream().filter(child -> centroidParent[child] == v && centroidParent[v] != child).collect(Collectors.toList());
    }

    public int getRoot() {
        for (int i = 0; i < N; i++) {
            if (centroidParent[i] == -1) {
                return i;
            }
        }
        throw new IllegalStateException("Centroid tree has no root. likely it was not built");
    }

    public void findSubtreeSizes(int src) {
        visited[src] = true;
        subtreeSizes[src] = 1;
        for (int node : tree[src]) {
            if (!visited[node] && !centroidMarked[node]) {
                visited[node] = true;
                findSubtreeSizes(node);
                subtreeSizes[src] += subtreeSizes[node];
            }
        }
    }

    public void findCentroid(int src, int previousCentroid) {
        Arrays.fill(visited, false);
        findSubtreeSizes(src);
        int treeSize = subtreeSizes[src];
        int heavyChild = -1;

        for (int node : tree[src]) {
            if (centroidMarked[node]) continue;
            if (subtreeSizes[node] > (treeSize / 2)) {
                heavyChild = node;
                break;
            }
        }

        if (heavyChild != -1) {
            findCentroid(heavyChild, previousCentroid);
            return;
        }

        centroidMarked[src] = true;

        if (src != startingNode && src != previousCentroid) {
            addEdgeCTree(previousCentroid, src);
        }

        for (int node : tree[src]) {
            if (!centroidMarked[node]) findCentroid(node, src);                
        }
    }

    /**
     * Applies the given action to all centroid ancestors of the given node,
     * including the node itself, walking up via centroidParent[] until the root.
     */
    public void forEachAncestor(int centroid, IntConsumer action) {
        int curr = centroid;
        while (curr != -1) {
            action.accept(curr);
            curr = getParent(curr);
        }
    }
}
