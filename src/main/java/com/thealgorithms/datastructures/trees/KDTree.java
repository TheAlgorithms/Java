package com.thealgorithms.datastructures.trees;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class KDTree {

    Node root;

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
        private final Point point;

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

    private Node insert(Node curr, Point point, int depth) {
        if (curr == null) {
            return new Node(point);
        }

        int axis = depth % k;
        int currDimension = curr.getPoint().getCoordinate(axis);
        int pointDimension = point.getCoordinate(axis);

        if (pointDimension < currDimension) {
            curr.left = insert(curr.left, point, depth + 1);
        } else {
            curr.right = insert(curr.right, point, depth + 1);
        }
        return curr;
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

    public Node findMin(int dimension) {
        return findMin(root, dimension, 0);
    }

    public Node findMin(Node curr, int dimension, int depth) {
        if (curr == null) return null;
        int axis = depth % k;
        if (axis == dimension) {
            if (curr.left == null) return curr;
            return findMin(curr.left, dimension, depth + 1);
        } else {
            Node left = findMin(curr.left, dimension, depth + 1);
            Node right = findMin(curr.right, dimension, depth + 1);
            Node[] candidates = {left, curr, right};
            return Arrays.stream(candidates)
                        .filter(Objects::nonNull)
                        .min(Comparator.comparingInt(a -> a.getPoint().getCoordinate(dimension))).orElse(null);
        }
    }

    public Node findMax(int dimension) {
        return findMax(root, dimension, 0);
    }

    public Node findMax(Node curr, int dimension, int depth) {
        if (curr == null) return null;
        int axis = depth % k;
        if (axis == dimension) {
            if (curr.right == null) return curr;
            return findMax(curr.right, dimension, depth + 1);
        } else {
            Node left = findMax(curr.left, dimension, depth + 1);
            Node right = findMax(curr.right, dimension, depth + 1);
            Node[] candidates = {left, curr, right};
            return Arrays.stream(candidates)
                        .filter(Objects::nonNull)
                        .max(Comparator.comparingInt(a -> a.getPoint().getCoordinate(dimension))).orElse(null);
        }
    }

}
