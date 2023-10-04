package com.thealgorithms.datastructures.trees;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/*
 * K-D Tree Implementation
 * Wikipedia: https://en.wikipedia.org/wiki/K-d_tree
 *
 * Author: Amir Hosseini (https://github.com/itsamirhn)
 *
 * */

public class KDTree {

    private Node root;

    private final int k; // Dimensions of the points

    /**
     * Constructor for empty KDTree
     *
     * @param k Number of dimensions
     */
    KDTree(int k) {
        this.k = k;
    }

    /**
     * Builds the KDTree from the specified points
     *
     * @param points Array of initial points
     */
    KDTree(Point[] points) {
        if (points.length == 0) throw new IllegalArgumentException("Points array cannot be empty");
        this.k = points[0].getDimension();
        for (Point point : points)
            if (point.getDimension() != k) throw new IllegalArgumentException("Points must have the same dimension");
        this.root = build(points, 0);
    }

    /**
     * Builds the KDTree from the specified coordinates of the points
     *
     * @param pointsCoordinates Array of initial points coordinates
     *
     */
    KDTree(int[][] pointsCoordinates) {
        if (pointsCoordinates.length == 0) throw new IllegalArgumentException("Points array cannot be empty");
        this.k = pointsCoordinates[0].length;
        Point[] points = Arrays.stream(pointsCoordinates).map(Point::new).toArray(Point[] ::new);
        for (Point point : points)
            if (point.getDimension() != k) throw new IllegalArgumentException("Points must have the same dimension");
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

        @Override
        public String toString() {
            return Arrays.toString(coordinates);
        }

        /**
         * Find the comparable distance between two points (without SQRT)
         *
         * @param p1 First point
         * @param p2 Second point
         *
         * @return The comparable distance between the two points
         */
        public static int comparableDistance(Point p1, Point p2) {
            int distance = 0;
            for (int i = 0; i < p1.getDimension(); i++) {
                int t = p1.getCoordinate(i) - p2.getCoordinate(i);
                distance += t * t;
            }
            return distance;
        }

        /**
         * Find the comparable distance between two points with ignoring specified axis
         *
         * @param p1 First point
         * @param p2 Second point
         * @param axis The axis to ignore
         *
         * @return The distance between the two points
         */
        public static int comparableDistanceExceptAxis(Point p1, Point p2, int axis) {
            int distance = 0;
            for (int i = 0; i < p1.getDimension(); i++) {
                if (i == axis) continue;
                int t = p1.getCoordinate(i) - p2.getCoordinate(i);
                distance += t * t;
            }
            return distance;
        }
    }

    static class Node {

        private Point point;
        private int axis; // 0 for x, 1 for y, 2 for z, etc.

        private Node left = null; // Left child
        private Node right = null; // Right child

        Node(Point point, int axis) {
            this.point = point;
            this.axis = axis;
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

        public int getAxis() {
            return axis;
        }

        /**
         * Get the nearest child according to the specified point
         *
         * @param point The point to find the nearest child to
         *
         * @return The nearest child Node
         */
        public Node getNearChild(Point point) {
            if (point.getCoordinate(axis) < this.point.getCoordinate(axis))
                return left;
            else
                return right;
        }

        /**
         * Get the farthest child according to the specified point
         *
         * @param point The point to find the farthest child to
         *
         * @return The farthest child Node
         */
        public Node getFarChild(Point point) {
            if (point.getCoordinate(axis) < this.point.getCoordinate(axis))
                return right;
            else
                return left;
        }

        /**
         * Get the node axis coordinate of point
         *
         * @return The axis coordinate of the point
         */
        public int getAxisCoordinate() {
            return point.getCoordinate(axis);
        }
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Builds the KDTree from the specified points
     *
     * @param points Array of initial points
     * @param depth The current depth of the tree
     *
     * @return The root of the KDTree
     */
    private Node build(Point[] points, int depth) {
        if (points.length == 0) return null;
        int axis = depth % k;
        if (points.length == 1) return new Node(points[0], axis);
        Arrays.sort(points, Comparator.comparingInt(o -> o.getCoordinate(axis)));
        int median = points.length >> 1;
        Node node = new Node(points[median], axis);
        node.left = build(Arrays.copyOfRange(points, 0, median), depth + 1);
        node.right = build(Arrays.copyOfRange(points, median + 1, points.length), depth + 1);
        return node;
    }

    /**
     * Insert a point into the KDTree
     *
     * @param point The point to insert
     *
     */
    public void insert(Point point) {
        if (point.getDimension() != k) throw new IllegalArgumentException("Point has wrong dimension");
        root = insert(root, point, 0);
    }

    /**
     * Insert a point into a subtree
     *
     * @param root The root of the subtree
     * @param point The point to insert
     * @param depth The current depth of the tree
     *
     * @return The root of the KDTree
     */
    private Node insert(Node root, Point point, int depth) {
        int axis = depth % k;
        if (root == null) return new Node(point, axis);
        if (point.getCoordinate(axis) < root.getAxisCoordinate())
            root.left = insert(root.left, point, depth + 1);
        else
            root.right = insert(root.right, point, depth + 1);

        return root;
    }

    /**
     * Search for Node corresponding to the specified point in the KDTree
     *
     * @param point The point to search for
     *
     * @return The Node corresponding to the specified point
     */
    public Optional<Node> search(Point point) {
        if (point.getDimension() != k) throw new IllegalArgumentException("Point has wrong dimension");
        return search(root, point);
    }

    /**
     * Search for Node corresponding to the specified point in a subtree
     *
     * @param root The root of the subtree to search in
     * @param point The point to search for
     *
     * @return The Node corresponding to the specified point
     */
    public Optional<Node> search(Node root, Point point) {
        if (root == null) return Optional.empty();
        if (root.point.equals(point)) return Optional.of(root);
        return search(root.getNearChild(point), point);
    }

    /**
     * Find a point with minimum value in specified axis in the KDTree
     *
     * @param axis The axis to find the minimum value in
     *
     * @return The point with minimum value in the specified axis
     */
    public Point findMin(int axis) {
        return findMin(root, axis).point;
    }

    /**
     * Find a point with minimum value in specified axis in a subtree
     *
     * @param root The root of the subtree to search in
     * @param axis The axis to find the minimum value in
     *
     * @return The Node with minimum value in the specified axis of the point
     */
    public Node findMin(Node root, int axis) {
        if (root == null) return null;
        if (root.getAxis() == axis) {
            if (root.left == null) return root;
            return findMin(root.left, axis);
        } else {
            Node left = findMin(root.left, axis);
            Node right = findMin(root.right, axis);
            Node[] candidates = {left, root, right};
            return Arrays.stream(candidates).filter(Objects::nonNull).min(Comparator.comparingInt(a -> a.point.getCoordinate(axis))).orElse(null);
        }
    }

    /**
     * Find a point with maximum value in specified axis in the KDTree
     *
     * @param axis The axis to find the maximum value in
     *
     * @return The point with maximum value in the specified axis
     */
    public Point findMax(int axis) {
        return findMax(root, axis).point;
    }

    /**
     * Find a point with maximum value in specified axis in a subtree
     *
     * @param root The root of the subtree to search in
     * @param axis The axis to find the maximum value in
     *
     * @return The Node with maximum value in the specified axis of the point
     */
    public Node findMax(Node root, int axis) {
        if (root == null) return null;
        if (root.getAxis() == axis) {
            if (root.right == null) return root;
            return findMax(root.right, axis);
        } else {
            Node left = findMax(root.left, axis);
            Node right = findMax(root.right, axis);
            Node[] candidates = {left, root, right};
            return Arrays.stream(candidates).filter(Objects::nonNull).max(Comparator.comparingInt(a -> a.point.getCoordinate(axis))).orElse(null);
        }
    }

    /**
     * Delete the node with the given point.
     *
     * @param point the point to delete
     * */
    public void delete(Point point) {
        Node node = search(point).orElseThrow(() -> new IllegalArgumentException("Point not found"));
        root = delete(root, node);
    }

    /**
     * Delete the specified node from a subtree.
     *
     * @param root The root of the subtree to delete from
     * @param node The node to delete
     *
     * @return The new root of the subtree
     */
    private Node delete(Node root, Node node) {
        if (root == null) return null;
        if (root.equals(node)) {
            if (root.right != null) {
                Node min = findMin(root.right, root.getAxis());
                root.point = min.point;
                root.right = delete(root.right, min);
            } else if (root.left != null) {
                Node min = findMin(root.left, root.getAxis());
                root.point = min.point;
                root.left = delete(root.left, min);
            } else
                return null;
        }
        if (root.getAxisCoordinate() < node.point.getCoordinate(root.getAxis()))
            root.left = delete(root.left, node);
        else
            root.right = delete(root.right, node);
        return root;
    }

    /**
     * Finds the nearest point in the tree to the given point.
     *
     * @param point The point to find the nearest neighbor to.
     * */
    public Point findNearest(Point point) {
        return findNearest(root, point, root).point;
    }

    /**
     * Finds the nearest point in a subtree to the given point.
     *
     * @param root The root of the subtree to search in.
     * @param point The point to find the nearest neighbor to.
     * @param nearest The nearest neighbor found so far.
     * */
    private Node findNearest(Node root, Point point, Node nearest) {
        if (root == null) return nearest;
        if (root.point.equals(point)) return root;
        int distance = Point.comparableDistance(root.point, point);
        int distanceExceptAxis = Point.comparableDistanceExceptAxis(root.point, point, root.getAxis());
        if (distance < Point.comparableDistance(nearest.point, point)) nearest = root;
        nearest = findNearest(root.getNearChild(point), point, nearest);
        if (distanceExceptAxis < Point.comparableDistance(nearest.point, point)) nearest = findNearest(root.getFarChild(point), point, nearest);
        return nearest;
    }
}
