package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Heavy-Light Decomposition (HLD) implementation in Java.
 * HLD is used to efficiently handle path queries on trees, such as maximum,
 * sum, or updates. It decomposes the tree into heavy and light chains,
 * enabling queries in O(log N) time.
 * Wikipedia Reference: https://en.wikipedia.org/wiki/Heavy-light_decomposition
 * Author: Nithin U.
 * Github: https://github.com/NithinU2802
 */

public class HeavyLightDecomposition {
    private List<List<Integer>> tree;
    private int[] parent;
    private int[] depth;
    private int[] subtreeSize;
    private int[] chainHead;
    private int[] position;
    private int[] nodeValue;
    private int[] segmentTree;
    private int positionIndex;

    public HeavyLightDecomposition(int n) {
        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        parent = new int[n + 1];
        depth = new int[n + 1];
        subtreeSize = new int[n + 1];
        chainHead = new int[n + 1];
        position = new int[n + 1];
        nodeValue = new int[n + 1];
        segmentTree = new int[4 * (n + 1)];
        for (int i = 0; i <= n; i++) {
            chainHead[i] = -1;
        }
        positionIndex = 0;
    }

    public int getPosition(int index) {
        return position[index];
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void addEdge(int u, int v) {
        tree.get(u).add(v);
        tree.get(v).add(u);
    }

    private void dfsSize(int node, int parentNode) {
        parent[node] = parentNode;
        subtreeSize[node] = 1;
        for (int child : tree.get(node)) {
            if (child != parentNode) {
                depth[child] = depth[node] + 1;
                dfsSize(child, node);
                subtreeSize[node] += subtreeSize[child];
            }
        }
    }

    private void decompose(int node, int head) {
        chainHead[node] = head;
        position[node] = positionIndex++;
        int heavyChild = -1;
        int maxSubtreeSize = -1;
        for (int child : tree.get(node)) {
            if (child != parent[node] && subtreeSize[child] > maxSubtreeSize) {
                heavyChild = child;
                maxSubtreeSize = subtreeSize[child];
            }
        }
        if (heavyChild != -1) {
            decompose(heavyChild, head);
        }
        for (int child : tree.get(node)) {
            if (child != parent[node] && child != heavyChild) {
                decompose(child, child);
            }
        }
    }

    private void buildSegmentTree(int node, int start, int end) {
        if (start == end) {
            segmentTree[node] = nodeValue[start];
            return;
        }
        int mid = (start + end) / 2;
        buildSegmentTree(2 * node, start, mid);
        buildSegmentTree(2 * node + 1, mid + 1, end);
        segmentTree[node] = Math.max(segmentTree[2 * node], segmentTree[2 * node + 1]);
    }

    public void updateSegmentTree(int node, int start, int end, int index, int value) {
        if (start == end) {
            segmentTree[node] = value;
            return;
        }
        int mid = (start + end) / 2;
        if (index <= mid) {
            updateSegmentTree(2 * node, start, mid, index, value);
        } else {
            updateSegmentTree(2 * node + 1, mid + 1, end, index, value);
        }
        segmentTree[node] = Math.max(segmentTree[2 * node], segmentTree[2 * node + 1]);
    }

    public int querySegmentTree(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return Integer.MIN_VALUE;
        }
        if (left <= start && end <= right) {
            return segmentTree[node];
        }
        int mid = (start + end) / 2;
        int leftQuery = querySegmentTree(2 * node, start, mid, left, right);
        int rightQuery = querySegmentTree(2 * node + 1, mid + 1, end, left, right);
        return Math.max(leftQuery, rightQuery);
    }

    public int queryMaxInPath(int u, int v) {
        int result = Integer.MIN_VALUE;
        while (chainHead[u] != chainHead[v]) {
            if (depth[chainHead[u]] < depth[chainHead[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            result = Math.max(result, querySegmentTree(1, 0, positionIndex - 1, position[chainHead[u]], position[u]));
            u = parent[chainHead[u]];
        }
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        result = Math.max(result, querySegmentTree(1, 0, positionIndex - 1, position[u], position[v]));
        return result;
    }

    public void initialize(int root, int[] values) {
        dfsSize(root, -1);
        decompose(root, root);
        for (int i = 0; i < values.length; i++) {
            nodeValue[position[i]] = values[i];
        }
        buildSegmentTree(1, 0, positionIndex - 1);
    }
}
