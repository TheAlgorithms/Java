package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

// ─────────────────────────────────────────────────────────────────────────────
// 1. Shared geometry utilities
//    (SRP: one class owns all pure geometric calculations used by both algorithms)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Pure geometric helper methods shared across convex-hull algorithms.
 *
 * <p>All methods are stateless and side-effect-free.
 * No algorithm control-flow lives here — only geometry.</p>
 */
final class HullGeometry {

    /** Sentinel used when no extreme-point distance has been recorded yet. */
    static final int NO_DISTANCE = Integer.MIN_VALUE;

    private HullGeometry() {}

    /**
     * Determines whether point {@code k} lies on the same side of line {@code ij}
     * as the reference direction established by the first non-collinear point.
     *
     * <p>For collinear points the method returns {@code true} when {@code k} falls
     * between {@code i} and {@code j} (inclusive), so that segment endpoints are
     * treated as hull candidates.</p>
     *
     * @return {@code true} if {@code k} is left-of or on the segment {@code ij};
     *         {@code false} if it is right-of the line
     */
    static boolean isOnExpectedSide(Point i, Point j, Point k) {
        int orientation = Point.orientation(i, j, k);
        if (orientation > 0) {
            return true;   // k is left of i→j
        } else if (orientation < 0) {
            return false;  // k is right of i→j
        } else {
            return k.compareTo(i) >= 0 && k.compareTo(j) <= 0;  // k is collinear, between i and j
        }
    }

    /**
     * Returns the squared Euclidean distance between {@code p1} and {@code p2}.
     *
     * <p>Using squared distance avoids floating-point arithmetic while still
     * providing a consistent total order on distances.</p>
     */
    static long squaredDistance(Point p1, Point p2) {
        long dx = (long) p1.x() - p2.x();
        long dy = (long) p1.y() - p2.y();
        return dx * dx + dy * dy;
    }

    /**
     * Sorts {@code hullPoints} in counter-clockwise order starting from the
     * bottom-most, left-most point (the angular pivot).
     *
     * <p>Intermediate collinear points are removed so that only the farthest
     * point along each ray from the pivot is retained.</p>
     *
     * @param hullPoints mutable list of hull points to sort in-place and return
     * @return the same list, now ordered counter-clockwise
     */
    static List<Point> sortCounterClockwise(List<Point> hullPoints) {
        if (hullPoints.size() <= 2) {
            Collections.sort(hullPoints);
            return hullPoints;
        }

        Point pivot = findBottomLeftPoint(hullPoints);
        List<Point> remaining = sortByPolarAngle(hullPoints, pivot);
        return buildOrderedHull(pivot, remaining);
    }

    // ── private helpers ───────────────────────────────────────────────────────

    /** Finds the bottom-most, left-most point in {@code points}. */
    private static Point findBottomLeftPoint(List<Point> points) {
        Point pivot = points.getFirst();
        for (Point p : points) {
            if (p.y() < pivot.y() || (p.y() == pivot.y() && p.x() < pivot.x())) {
                pivot = p;
            }
        }
        return pivot;
    }

    /**
     * Returns all points except {@code pivot}, sorted by polar angle relative
     * to {@code pivot}. Collinear points are ordered closer-first so that the
     * final hull-building step can simply keep the last one.
     */
    private static List<Point> sortByPolarAngle(List<Point> hullPoints, Point pivot) {
        List<Point> remaining = new ArrayList<>(hullPoints);
        remaining.remove(pivot);
        remaining.sort((p1, p2) -> {
            int crossProduct = Point.orientation(pivot, p1, p2);
            if (crossProduct == 0) {
                // Collinear: put the closer point first so we discard it later
                return Long.compare(squaredDistance(pivot, p1), squaredDistance(pivot, p2));
            }
            // Negative cross product means p1 is CCW from p2 → p1 should come first
            return -crossProduct;
        });
        return remaining;
    }

    /**
     * Builds the final ordered list by placing {@code pivot} first and
     * discarding intermediate collinear points (keeping only the farthest one
     * along each ray).
     */
    private static List<Point> buildOrderedHull(Point pivot, List<Point> sorted) {
        List<Point> result = new ArrayList<>();
        result.add(pivot);

        for (int i = 0; i < sorted.size() - 1; i++) {
            int orientation = Point.orientation(pivot, sorted.get(i), sorted.get(i + 1));
            if (orientation != 0) {
                // Current point is the farthest at its angle — keep it
                result.add(sorted.get(i));
            }
            // orientation == 0 means the next point is farther along the same ray — skip this one
        }
        // The last sorted point is always kept: it is either unique at its angle
        // or the farthest among a collinear group
        result.add(sorted.getLast());

        return result;
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 2. Strategy interface
//    (OCP + DIP: new algorithms can be added without touching existing code;
//     callers depend on this abstraction, not on concrete classes)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Strategy contract for convex-hull algorithms.
 *
 * <p>Any class implementing this interface accepts a list of {@link Point}s
 * and returns the hull vertices in counter-clockwise order.</p>
 */
interface ConvexHullAlgorithm {
    /**
     * Computes the convex hull of {@code points}.
     *
     * @param points the input point set (not modified)
     * @return the hull vertices in counter-clockwise order
     */
    List<Point> compute(List<Point> points);
}

// ─────────────────────────────────────────────────────────────────────────────
// 3. Brute-force algorithm
//    (SRP: this class is solely responsible for the O(n³) brute-force method)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Convex-hull computation using the O(n³) brute-force edge-enumeration method.
 *
 * <p>For every pair of points {@code (i, j)}, the algorithm checks whether all
 * remaining points lie on the same side of the directed line {@code i→j}.
 * If they do, both endpoints are hull vertices.</p>
 */
final class BruteForceHull implements ConvexHullAlgorithm {

    @Override
    public List<Point> compute(List<Point> points) {
        Set<Point> convexSet = new TreeSet<>(Comparator.naturalOrder());

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (isHullEdge(points, i, j)) {
                    convexSet.add(points.get(i));
                    convexSet.add(points.get(j));
                }
            }
        }

        return new ArrayList<>(convexSet);
    }

    /**
     * Returns {@code true} when the directed edge from {@code points[i]} to
     * {@code points[j]} has all other points on the same side.
     */
    private boolean isHullEdge(List<Point> points, int i, int j) {
        // Use the point just after i as the reference side
        boolean referenceSide = HullGeometry.isOnExpectedSide(
                points.get(i), points.get(j), points.get((i + 1) % points.size())
        );

        for (int k = 0; k < points.size(); k++) {
            if (k == i || k == j) {
                continue;
            }
            if (HullGeometry.isOnExpectedSide(points.get(i), points.get(j), points.get(k)) != referenceSide) {
                return false;
            }
        }
        return true;
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 4. Recursive (divide-and-conquer) algorithm
//    (SRP: this class is solely responsible for the recursive hull method)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Convex-hull computation using a recursive divide-and-conquer approach.
 *
 * <p>The algorithm splits the point set into an upper and a lower hull relative
 * to the line connecting the leftmost and rightmost points, then recursively
 * finds the extreme point of each sub-hull.</p>
 *
 * <p>Returns points in counter-clockwise order starting from the bottom-most,
 * left-most point.</p>
 */
final class RecursiveHull implements ConvexHullAlgorithm {

    @Override
    public List<Point> compute(List<Point> points) {
        if (points.size() < 3) {
            List<Point> result = new ArrayList<>(points);
            Collections.sort(result);
            return result;
        }

        List<Point> sorted = new ArrayList<>(points);
        Collections.sort(sorted);

        Point leftMost = sorted.getFirst();
        Point rightMost = sorted.getLast();

        Set<Point> convexSet = new HashSet<>();
        convexSet.add(leftMost);
        convexSet.add(rightMost);

        List<Point> upperHull = new ArrayList<>();
        List<Point> lowerHull = new ArrayList<>();

        partitionPoints(sorted, leftMost, rightMost, upperHull, lowerHull);

        constructHull(upperHull, leftMost, rightMost, convexSet);
        constructHull(lowerHull, rightMost, leftMost, convexSet);

        return HullGeometry.sortCounterClockwise(new ArrayList<>(convexSet));
    }

    /**
     * Splits interior points into upper and lower hulls relative to the
     * baseline {@code leftMost → rightMost}.
     */
    private void partitionPoints(List<Point> sorted, Point leftMost, Point rightMost,
                                 List<Point> upperHull, List<Point> lowerHull) {
        for (int i = 1; i < sorted.size() - 1; i++) {
            int orientation = Point.orientation(leftMost, rightMost, sorted.get(i));
            if (orientation > 0) {
                upperHull.add(sorted.get(i));
            } else if (orientation < 0) {
                lowerHull.add(sorted.get(i));
            }
        }
    }

    /**
     * Recursively identifies the extreme point on the left side of the directed
     * line {@code left → right} and adds confirmed hull vertices to
     * {@code convexSet}.
     */
    private void constructHull(Collection<Point> points, Point left, Point right, Set<Point> convexSet) {
        if (points.isEmpty()) {
            return;
        }

        Point extremePoint = findExtremePoint(points, left, right);
        if (extremePoint == null) {
            return;
        }

        List<Point> leftCandidates = collectLeftOf(points, left, right);

        constructHull(leftCandidates, left, extremePoint, convexSet);
        convexSet.add(extremePoint);
        constructHull(leftCandidates, extremePoint, right, convexSet);
    }

    /**
     * Returns the point in {@code points} that lies furthest to the left of the
     * directed line {@code left → right}, or {@code null} if no such point exists.
     */
    private Point findExtremePoint(Collection<Point> points, Point left, Point right) {
        Point extremePoint = null;
        int maxDistance = HullGeometry.NO_DISTANCE;

        for (Point p : points) {
            int distance = Point.orientation(left, right, p);
            if (distance > 0 && distance > maxDistance) {
                maxDistance = distance;
                extremePoint = p;
            }
        }
        return extremePoint;
    }

    /**
     * Returns all points in {@code points} that lie strictly to the left of the
     * directed line {@code left → right}.
     */
    private List<Point> collectLeftOf(Collection<Point> points, Point left, Point right) {
        List<Point> result = new ArrayList<>();
        for (Point p : points) {
            if (Point.orientation(left, right, p) > 0) {
                result.add(p);
            }
        }
        return result;
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 5. Public façade  (preserves the original public API surface exactly)
//    (DIP: static helpers now delegate to the strategy implementations)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Public entry point for convex-hull computation.
 *
 * <p>Provides two named factory methods that preserve the original API while
 * delegating to independent, interchangeable {@link ConvexHullAlgorithm}
 * implementations.</p>
 *
 * <p>Algorithms provided:</p>
 * <ol>
 *   <li>Brute-Force Method — O(n³), via {@link BruteForceHull}</li>
 *   <li>Recursive Divide-and-Conquer Method — O(n log n), via {@link RecursiveHull}</li>
 * </ol>
 *
 * @author Hardvan
 */
public final class ConvexHull {

    private ConvexHull() {}

    /**
     * Computes the convex hull using the O(n³) brute-force method.
     *
     * @param points the input points
     * @return the convex hull vertices
     */
    public static List<Point> convexHullBruteForce(List<Point> points) {
        return new BruteForceHull().compute(points);
    }

    /**
     * Computes the convex hull using a recursive divide-and-conquer approach.
     * Returns points in counter-clockwise order starting from the bottom-most,
     * left-most point.
     *
     * @param points the input points
     * @return the convex hull points in counter-clockwise order
     */
    public static List<Point> convexHullRecursive(List<Point> points) {
        return new RecursiveHull().compute(points);
    }
}