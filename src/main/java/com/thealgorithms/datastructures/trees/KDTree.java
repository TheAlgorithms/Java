package com.thealgorithms.datastructures.trees;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class KDTree {

    private Node root;

    private final int k; // Dimensions of the points

    KDTree(int k) {
        this.k = k;
    }

    KDTree(Point[] points) {
        if (points.length == 0) throw new IllegalArgumentException("Points array cannot be empty");
        this.k = points[0].getDimension();
        for (Point point : points) if (point.getDimension() != k) throw new IllegalArgumentException("Points must have the same dimension");
        this.root = build(points, 0);
    }

    static class Point {
        int[] coordinates;

        public int getCoordinate(int i) {
            return coordinates[i];
        }

        public int getDimension() {
            return coordinates.length;
        }

        public Point(int[] coordinates) {
            this.coordinates = coordinates;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point other) {
                if (other.getDimension() != this.getDimension()) return false;
                return Arrays.equals(other.coordinates, this.coordinates);
            }
            return false;
        }
    }


    static class Node {
        private Point point;

        private Node left = null; // Left child
        private Node right = null; // Right child

        Node(Point point) {
            this.point = point;
        }

        public Point getPoint() {
            return point;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    public Node getRoot() {
        return root;
    }

    private Node build(Point[] points, int depth) {
        if (points.length == 0) return null;
        if (points.length == 1) return new Node(points[0]);
        Arrays.sort(points, Comparator.comparingInt(o -> o.getCoordinate(depth % k)));
        int median = points.length >> 1;
        Node node = new Node(points[median]);
        node.left = build(Arrays.copyOfRange(points, 0, median), depth + 1);
        node.right = build(Arrays.copyOfRange(points, median + 1, points.length), depth + 1);
        return node;
    }

    public void insert(Point point) {
        if (point.getDimension() != k) throw new IllegalArgumentException("Point has wrong dimension");
        root = insert(root, point, 0);
    }

    private Node insert(Node root, Point point, int depth) {
        if (root == null) return new Node(point);

        int axis = depth % k;

        if (root.point.getCoordinate(axis) < point.getCoordinate(axis)) root.left = insert(root.left, point, depth + 1);
        else root.right = insert(root.right, point, depth + 1);

        return root;
    }

    public Optional<Node> search(Point point) {
        if (point.getDimension() != k) throw new IllegalArgumentException("Point has wrong dimension");
        return search(root, point, 0);
    }

    public Optional<Node> search(Node root, Point point, int depth) {
        if (root == null) return Optional.empty();
        if (root.point.equals(point)) return Optional.of(root);
        int axis = depth % k;
        if (point.getCoordinate(axis) < root.point.getCoordinate(axis)) return search(root.left, point, depth + 1);
        else return search(root.right, point, depth + 1);

    }

    public Point findMin(int dimension) {
        return findMin(root, dimension, 0).point;
    }

    public Node findMin(Node root, int dimension, int depth) {
        if (root == null) return null;
        int axis = depth % k;
        if (axis == dimension) {
            if (root.left == null) return root;
            return findMin(root.left, dimension, depth + 1);
        } else {
            Node left = findMin(root.left, dimension, depth + 1);
            Node right = findMin(root.right, dimension, depth + 1);
            Node[] candidates = {left, root, right};
            return Arrays.stream(candidates)
                        .filter(Objects::nonNull)
                        .min(Comparator.comparingInt(a -> a.point.getCoordinate(dimension))).orElse(null);
        }
    }

    public Point findMax(int dimension) {
        return findMax(root, dimension, 0).point;
    }

    public Node findMax(Node root, int dimension, int depth) {
        if (root == null) return null;
        int axis = depth % k;
        if (axis == dimension) {
            if (root.right == null) return root;
            return findMax(root.right, dimension, depth + 1);
        } else {
            Node left = findMax(root.left, dimension, depth + 1);
            Node right = findMax(root.right, dimension, depth + 1);
            Node[] candidates = {left, root, right};
            return Arrays.stream(candidates)
                        .filter(Objects::nonNull)
                        .max(Comparator.comparingInt(a -> a.point.getCoordinate(dimension))).orElse(null);
        }
    }

    public void delete(Point point) {
        Node node = search(point).orElseThrow(() -> new IllegalArgumentException("Point not found"));
        root = delete(root, node, 0);
    }

    private Node delete(Node root, Node node, int depth) {
        if (root == null) return null;
        int axis = depth % k;
        if (root.equals(node)) {
            if (root.right != null) {
                Node min = findMin(root.right, axis, depth + 1);
                root.point = min.point;
                root.right = delete(root.right, min, depth + 1);
            } else if (root.left != null) {
                Node min = findMin(root.left, axis, depth + 1);
                root.point = min.point;
                root.left = delete(root.left, min, depth + 1);
            } else return null;
        }
        if (root.point.getCoordinate(axis) < node.point.getCoordinate(axis)) root.left = delete(root.left, node, depth + 1);
        else root.right = delete(root.right, node, depth + 1);
        return root;
    }
}
