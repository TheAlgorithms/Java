package com.thealgorithms.datastructures.trees;

public class LazySegmentTree {
    static class Node {
        private final int start, end;
        private int value, lazy;
        Node left, right;
        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
            this.lazy = 0;
            this.left = null;
            this.right = null;
        }

        public void applyUpdate(int diff) {
            this.lazy += diff;
            this.value += (this.end - this.start) * diff;
        }

        public void shift() {
            if (lazy == 0) return;
            if (this.left == null && this.right == null) return;
            this.value += this.lazy;
            if (this.left != null) this.left.applyUpdate(this.lazy);
            if (this.right != null) this.right.applyUpdate(this.lazy);
            this.lazy = 0;
        }

        static Node merge(Node left, Node right) {
            if (left == null) return right;
            if (right == null) return left;
            Node result = new Node(left.start, right.end, left.value + right.value);
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

    public LazySegmentTree(int[] arr) {
        this.root = buildTree(arr, 0, arr.length);
    }

    private Node buildTree(int[] arr, int start, int end) {
        if (end - start < 2) return new Node(start, end, arr[start]);
        int mid = (start + end) >> 1;
        Node left = buildTree(arr, start, mid);
        Node right = buildTree(arr, mid, end);
        return Node.merge(left, right);
    }

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

    private Node getRange(int left, int right, Node curr) {
        if (left <= curr.start && curr.end <= right) return curr;
        if (left >= curr.end || right <= curr.start) return null;
        curr.shift();
        return Node.merge(getRange(left, right, curr.left), getRange(left, right, curr.right));
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
