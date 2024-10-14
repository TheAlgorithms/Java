package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Point is a simple class that represents a point in 2D space.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Point_(geometry)">Point</a>
 * @author <a href="https://github.com/sailok">Sailok Chinta</a>
 */
class Point {
    public double x;
    public double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

/**
 * BoundingBox is a simple class that represents a bounding box in 2D space.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Bounding_box">Bounding Box</a>
 * @author <a href="https://github.com/sailok">Sailok Chinta</a>
 */
class BoundingBox {
    public Point center;
    public double halfWidth;

    BoundingBox(Point center, double halfWidth) {
        this.center = center;
        this.halfWidth = halfWidth;
    }

    /**
     * Checks if the point is inside the bounding box
     *
     * @param point The point to check
     * @return true if the point is inside the bounding box, false otherwise
     */
    public boolean containsPoint(Point point) {
        return point.x >= center.x - halfWidth && point.x <= center.x + halfWidth && point.y >= center.y - halfWidth && point.y <= center.y + halfWidth;
    }

    /**
     * Checks if the bounding box intersects with the other bounding box
     *
     * @param otherBoundingBox The other bounding box
     * @return true if the bounding box intersects with the other bounding box, false otherwise
     */
    public boolean intersectsBoundingBox(BoundingBox otherBoundingBox) {
        return otherBoundingBox.center.x - otherBoundingBox.halfWidth <= center.x + halfWidth && otherBoundingBox.center.x + otherBoundingBox.halfWidth >= center.x - halfWidth && otherBoundingBox.center.y - otherBoundingBox.halfWidth <= center.y + halfWidth
            && otherBoundingBox.center.y + otherBoundingBox.halfWidth >= center.y - halfWidth;
    }
}

/**
 * QuadTree is a tree data structure that is used to store spatial information
 * in an efficient way.
 *
 * This implementation is specific to Point QuadTrees
 *
 * @see <a href="https://en.wikipedia.org/wiki/Quadtree">Quad Tree</a>
 * @author <a href="https://github.com/sailok">Sailok Chinta</a>
 */
public class QuadTree {
    private final BoundingBox boundary;
    private final int capacity;

    private List<Point> pointList;
    private boolean divided;
    private QuadTree northWest;
    private QuadTree northEast;
    private QuadTree southWest;
    private QuadTree southEast;

    public QuadTree(BoundingBox boundary, int capacity) {
        this.boundary = boundary;
        this.capacity = capacity;

        this.pointList = new ArrayList<>();
        this.divided = false;
        this.northWest = null;
        this.northEast = null;
        this.southWest = null;
        this.southEast = null;
    }

    /**
     * Inserts a point into the tree
     *
     * @param point The point to insert
     * @return true if the point is successfully inserted, false otherwise
     */
    public boolean insert(Point point) {
        if (point == null) {
            return false;
        }

        // Ignore points that don't belong to this quad tree
        if (!boundary.containsPoint(point)) {
            return false;
        }

        // if the space is not already occupied, add it to the list
        if (pointList.size() < capacity) {
            pointList.add(point);
            return true;
        }

        // if subdivision hasn't happened, divide the tree
        if (!divided) {
            subDivide();
        }

        // try to add the point in one of the four quadrants
        if (northWest.insert(point)) {
            return true;
        }

        if (northEast.insert(point)) {
            return true;
        }

        if (southWest.insert(point)) {
            return true;
        }

        if (southEast.insert(point)) {
            return true;
        }

        return false;
    }

    /**
     * Create four children that fully divide this quad into four quads of equal area
     */
    private void subDivide() {
        double quadrantHalfWidth = boundary.halfWidth / 2;

        northWest = new QuadTree(new BoundingBox(new Point(boundary.center.x - quadrantHalfWidth, boundary.center.y + quadrantHalfWidth), quadrantHalfWidth), this.capacity);
        northEast = new QuadTree(new BoundingBox(new Point(boundary.center.x + quadrantHalfWidth, boundary.center.y + quadrantHalfWidth), quadrantHalfWidth), this.capacity);
        southWest = new QuadTree(new BoundingBox(new Point(boundary.center.x - quadrantHalfWidth, boundary.center.y - quadrantHalfWidth), quadrantHalfWidth), this.capacity);
        southEast = new QuadTree(new BoundingBox(new Point(boundary.center.x + quadrantHalfWidth, boundary.center.y - quadrantHalfWidth), quadrantHalfWidth), this.capacity);
        divided = true;
    }

    /**
     * Queries all the points that intersect with the other bounding box
     *
     * @param otherBoundingBox The other bounding box
     * @return List of points that intersect with the other bounding box
     */
    public List<Point> query(BoundingBox otherBoundingBox) {
        List<Point> points = new ArrayList<>();

        if (!boundary.intersectsBoundingBox(otherBoundingBox)) {
            return points;
        }

        // filter the points that intersect with the other bounding box
        points.addAll(pointList.stream().filter(otherBoundingBox::containsPoint).toList());

        if (divided) {
            points.addAll(northWest.query(otherBoundingBox));
            points.addAll(northEast.query(otherBoundingBox));
            points.addAll(southWest.query(otherBoundingBox));
            points.addAll(southEast.query(otherBoundingBox));
        }

        return points;
    }
}
