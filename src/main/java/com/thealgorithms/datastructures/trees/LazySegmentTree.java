package com.thealgorithms.datastructures.trees;

public class LazySegmentTree {

    /**
     * Lazy Segment Tree
     *
     * @see
     *	<a href="https://www.geeksforgeeks.org/lazy-propagation-in-segment-tree/">
     */
    static class Node {

        private final int start, end; // start and end of the segment represented by this node
        private int value; // value is the sum of all elements in the range [start, end)
        private int lazy; // lazied value that should be added to children nodes
        Node left, right; // left and right children

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
            this.lazy = 0;
            this.left = null;
            this.right = null;
        }

        /** Update the value of this node with the given value diff.
         *
         * @param diff The value to add to every index of this node range.
         */
        public void applyUpdate(int diff) {
            this.lazy += diff;
            this.value += (this.end - this.start) * diff;
        }

        /** Shift the lazy value of this node to its children.
         */
        public void shift() {
            if (lazy == 0) return;
            if (this.left == null && this.right == null) return;
            this.value += this.lazy;
            if (this.left != null) this.left.applyUpdate(this.lazy);
            if (this.right != null) this.right.applyUpdate(this.lazy);
            this.lazy = 0;
        }

        /** Create a new node that is the sum of this node and the given node.
         *
         * @param left The left Node of merging
         * @param right The right Node of merging
         * @return The new Node.
         */
        static Node merge(Node left, Node right) {
            if (left == null) return right;
            if (right == null) return left;
            Node result = new Node(
                left.start,
                right.end,
                left.value + right.value
            );
            result.left = left;
            result.right = right;
            return result;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    private final Node root;

    /** Create a new LazySegmentTree with the given array.
     *
     * @param array The array to create the LazySegmentTree from.
     */
    public LazySegmentTree(int[] array) {
        this.root = buildTree(array, 0, array.length);
    }

    /** Build a new LazySegmentTree from the given array in O(n) time.
     *
     * @param array The array to build the LazySegmentTree from.
     * @param start The start index of the current node.
     * @param end The end index of the current node.
     * @return The root of the new LazySegmentTree.
     */
    private Node buildTree(int[] array, int start, int end) {
        if (end - start < 2) return new Node(start, end, array[start]);
        int mid = (start + end) >> 1;
        Node left = buildTree(array, start, mid);
        Node right = buildTree(array, mid, end);
        return Node.merge(left, right);
    }

    /** Update the value of given range with the given value diff in O(log n) time.
     *
     * @param left The left index of the range to update.
     * @param right The right index of the range to update.
     * @param diff The value to add to every index of the range.
     * @param curr The current node.
     */
    private void updateRange(int left, int right, int diff, Node curr) {
        if (left <= curr.start && curr.end <= right) {
            curr.applyUpdate(diff);
            return;
        }
        if (left >= curr.end || right <= curr.start) return;
        curr.shift();
        updateRange(left, right, diff, curr.left);
        updateRange(left, right, diff, curr.right);
        Node merge = Node.merge(curr.left, curr.right);
        curr.value = merge.value;
    }

    /** Get Node of given range in O(log n) time.
     *
     * @param left The left index of the range to update.
     * @param right The right index of the range to update.
     * @return The Node representing the sum of the given range.
     */
    private Node getRange(int left, int right, Node curr) {
        if (left <= curr.start && curr.end <= right) return curr;
        if (left >= curr.end || right <= curr.start) return null;
        curr.shift();
        return Node.merge(
            getRange(left, right, curr.left),
            getRange(left, right, curr.right)
        );
    }

    public int getRange(int left, int right) {
        Node result = getRange(left, right, root);
        return result == null ? 0 : result.getValue();
    }

    public void updateRange(int left, int right, int diff) {
        updateRange(left, right, diff, root);
    }

    public Node getRoot() {
        return root;
    }
}
