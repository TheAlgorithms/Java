package com.thealgorithms.geometry;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Implementation of the Bentley–Ottmann algorithm for finding all intersection
 * points among a set of line segments in O((n + k) log n) time.
 *
 * <p>Uses a sweep-line approach with an event queue and status structure to
 * efficiently detect intersections in 2D plane geometry.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Bentley%E2%80%93Ottmann_algorithm">
 *      Bentley–Ottmann algorithm</a>
 */
public final class BentleyOttmann {

    private BentleyOttmann() {
    }

    private static final double EPS = 1e-9;
    private static double currentSweepX;

    /**
     * Represents a line segment with two endpoints.
     */
    public static class Segment {
        final Point2D.Double p1;
        final Point2D.Double p2;
        final int id; // Unique identifier for each segment

        Segment(Point2D.Double p1, Point2D.Double p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.id = segmentCounter++;
        }

        private static int segmentCounter = 0;

        /**
         * Computes the y-coordinate of this segment at a given x value.
         */
        double getY(double x) {
            if (Math.abs(p2.x - p1.x) < EPS) {
                // Vertical segment: return midpoint y
                return (p1.y + p2.y) / 2.0;
            }
            double t = (x - p1.x) / (p2.x - p1.x);
            return p1.y + t * (p2.y - p1.y);
        }

        Point2D.Double leftPoint() {
            return p1.x < p2.x ? p1 : p1.x > p2.x ? p2 : p1.y < p2.y ? p1 : p2;
        }

        Point2D.Double rightPoint() {
            return p1.x > p2.x ? p1 : p1.x < p2.x ? p2 : p1.y > p2.y ? p1 : p2;
        }

        @Override
        public String toString() {
            return String.format("S%d[(%.2f, %.2f), (%.2f, %.2f)]", id, p1.x, p1.y, p2.x, p2.y);
        }
    }

    /**
     * Event types for the sweep line algorithm.
     */
    private enum EventType { START, END, INTERSECTION }

    /**
     * Represents an event in the event queue.
     */
    private static class Event implements Comparable<Event> {
        final Point2D.Double point;
        final EventType type;
        final Set<Segment> segments; // Segments involved in this event

        Event(Point2D.Double point, EventType type) {
            this.point = point;
            this.type = type;
            this.segments = new HashSet<>();
        }

        void addSegment(Segment s) {
            segments.add(s);
        }

        @Override
        public int compareTo(Event other) {
            // Sort by x-coordinate, then by y-coordinate
            int cmp = Double.compare(this.point.x, other.point.x);
            if (cmp == 0) {
                cmp = Double.compare(this.point.y, other.point.y);
            }
            if (cmp == 0) {
                // Process END events before START events at same point
                cmp = this.type.compareTo(other.type);
            }
            return cmp;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Event e)) {
                return false;
            }
            return pointsEqual(this.point, e.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Math.round(point.x * 1e6), Math.round(point.y * 1e6));
        }
    }

    /**
     * Comparator for segments in the status structure (sweep line).
     * Orders segments by their y-coordinate at the current sweep line position.
     */
    private static final class StatusComparator implements Comparator<Segment> {
        @Override
        public int compare(Segment s1, Segment s2) {
            if (s1.id == s2.id) {
                return 0;
            }

            double y1 = s1.getY(currentSweepX);
            double y2 = s2.getY(currentSweepX);

            int cmp = Double.compare(y1, y2);
            if (Math.abs(y1 - y2) < EPS) {
                // If y-coordinates are equal, use segment id for consistency
                return Integer.compare(s1.id, s2.id);
            }
            return cmp;
        }
    }

    /**
     * Finds all intersection points among a set of line segments.
     *
     * <p>An intersection point is reported when two or more segments cross or touch.
     * For overlapping segments, only actual crossing/touching points are reported,
     * not all points along the overlap.</p>
     *
     * @param segments list of line segments represented as pairs of points
     * @return a set of intersection points where segments meet or cross
     * @throws IllegalArgumentException if the list is null or contains null points
     */
    public static Set<Point2D.Double> findIntersections(List<Segment> segments) {
        if (segments == null) {
            throw new IllegalArgumentException("Segment list must not be null");
        }

        Segment.segmentCounter = 0; // Reset counter
        Set<Point2D.Double> intersections = new HashSet<>();
        PriorityQueue<Event> eventQueue = new PriorityQueue<>();
        TreeSet<Segment> status = new TreeSet<>(new StatusComparator());
        Map<Point2D.Double, Event> eventMap = new HashMap<>();

        // Initialize event queue with segment start and end points
        for (Segment s : segments) {
            Point2D.Double left = s.leftPoint();
            Point2D.Double right = s.rightPoint();

            Event startEvent = getOrCreateEvent(eventMap, left, EventType.START);
            startEvent.addSegment(s);

            Event endEvent = getOrCreateEvent(eventMap, right, EventType.END);
            endEvent.addSegment(s);
        }

        // Add all unique events to the queue
        for (Event e : eventMap.values()) {
            if (!e.segments.isEmpty()) {
                eventQueue.add(e);
            }
        }

        // Process events
        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.poll();
            currentSweepX = event.point.x;

            handleEvent(event, status, eventQueue, eventMap, intersections);
        }

        return intersections;
    }

    private static Event getOrCreateEvent(Map<Point2D.Double, Event> eventMap, Point2D.Double point, EventType type) {
        // Find existing event at this point
        for (Map.Entry<Point2D.Double, Event> entry : eventMap.entrySet()) {
            if (pointsEqual(entry.getKey(), point)) {
                return entry.getValue();
            }
        }
        // Create new event
        Event event = new Event(point, type);
        eventMap.put(point, event);
        return event;
    }

    private static void handleEvent(Event event, TreeSet<Segment> status, PriorityQueue<Event> eventQueue, Map<Point2D.Double, Event> eventMap, Set<Point2D.Double> intersections) {
        Point2D.Double p = event.point;
        Set<Segment> segmentsAtPoint = new HashSet<>(event.segments);

        // Check segments in status structure (much smaller than allSegments)
        for (Segment s : status) {
            if (pointsEqual(s.p1, p) || pointsEqual(s.p2, p) || (onSegment(s, p) && !pointsEqual(s.p1, p) && !pointsEqual(s.p2, p))) {
                segmentsAtPoint.add(s);
            }
        }

        // If 2 or more segments meet at this point, it's an intersection
        if (segmentsAtPoint.size() >= 2) {
            intersections.add(p);
        }

        // Categorize segments
        Set<Segment> upperSegs = new HashSet<>(); // Segments starting at p
        Set<Segment> lowerSegs = new HashSet<>(); // Segments ending at p
        Set<Segment> containingSegs = new HashSet<>(); // Segments containing p in interior

        for (Segment s : segmentsAtPoint) {
            if (pointsEqual(s.leftPoint(), p)) {
                upperSegs.add(s);
            } else if (pointsEqual(s.rightPoint(), p)) {
                lowerSegs.add(s);
            } else {
                containingSegs.add(s);
            }
        }

        // Remove ending segments and segments containing p from status
        status.removeAll(lowerSegs);
        status.removeAll(containingSegs);

        // Update sweep line position slightly past the event
        currentSweepX = p.x + EPS;

        // Add starting segments and re-add containing segments
        status.addAll(upperSegs);
        status.addAll(containingSegs);

        if (upperSegs.isEmpty() && containingSegs.isEmpty()) {
            // Find neighbors and check for new intersections
            Segment sl = getNeighbor(status, lowerSegs, true);
            Segment sr = getNeighbor(status, lowerSegs, false);
            if (sl != null && sr != null) {
                findNewEvent(sl, sr, p, eventQueue, eventMap);
            }
        } else {
            Set<Segment> unionSegs = new HashSet<>(upperSegs);
            unionSegs.addAll(containingSegs);

            Segment leftmost = getLeftmost(unionSegs, status);
            Segment rightmost = getRightmost(unionSegs, status);

            if (leftmost != null) {
                Segment sl = status.lower(leftmost);
                if (sl != null) {
                    findNewEvent(sl, leftmost, p, eventQueue, eventMap);
                }
            }

            if (rightmost != null) {
                Segment sr = status.higher(rightmost);
                if (sr != null) {
                    findNewEvent(rightmost, sr, p, eventQueue, eventMap);
                }
            }
        }
    }

    private static Segment getNeighbor(NavigableSet<Segment> status, Set<Segment> removed, boolean lower) {
        if (removed.isEmpty()) {
            return null;
        }
        Segment ref = removed.iterator().next();
        return lower ? status.lower(ref) : status.higher(ref);
    }

    private static Segment getLeftmost(Set<Segment> segments, SortedSet<Segment> status) {
        Segment leftmost = null;
        for (Segment s : segments) {
            if (leftmost == null || Objects.requireNonNull(status.comparator()).compare(s, leftmost) < 0) {
                leftmost = s;
            }
        }
        return leftmost;
    }

    private static Segment getRightmost(Set<Segment> segments, SortedSet<Segment> status) {
        Segment rightmost = null;
        for (Segment s : segments) {
            if (status.comparator() != null && (rightmost == null || status.comparator().compare(s, rightmost) > 0)) {
                rightmost = s;
            }
        }
        return rightmost;
    }

    private static void findNewEvent(Segment s1, Segment s2, Point2D.Double currentPoint, PriorityQueue<Event> eventQueue, Map<Point2D.Double, Event> eventMap) {
        Point2D.Double intersection = getIntersection(s1, s2);

        if (intersection != null && intersection.x > currentPoint.x - EPS && !pointsEqual(intersection, currentPoint)) {

            // Check if event already exists
            boolean exists = false;
            for (Map.Entry<Point2D.Double, Event> entry : eventMap.entrySet()) {
                if (pointsEqual(entry.getKey(), intersection)) {
                    exists = true;
                    Event existingEvent = entry.getValue();
                    existingEvent.addSegment(s1);
                    existingEvent.addSegment(s2);
                    break;
                }
            }

            if (!exists) {
                Event newEvent = new Event(intersection, EventType.INTERSECTION);
                newEvent.addSegment(s1);
                newEvent.addSegment(s2);
                eventMap.put(intersection, newEvent);
                eventQueue.add(newEvent);
            }
        }
    }

    private static Point2D.Double getIntersection(Segment s1, Segment s2) {
        double x1 = s1.p1.x;
        double y1 = s1.p1.y;
        double x2 = s1.p2.x;
        double y2 = s1.p2.y;
        double x3 = s2.p1.x;
        double y3 = s2.p1.y;
        double x4 = s2.p2.x;
        double y4 = s2.p2.y;

        double denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (Math.abs(denom) < EPS) {
            // Parallel or collinear
            if (areCollinear(s1, s2)) {
                // For collinear segments, check if they overlap
                // Return any overlapping point
                List<Point2D.Double> overlapPoints = new ArrayList<>();

                if (onSegment(s1, s2.p1)) {
                    overlapPoints.add(s2.p1);
                }
                if (onSegment(s1, s2.p2)) {
                    overlapPoints.add(s2.p2);
                }
                if (onSegment(s2, s1.p1)) {
                    overlapPoints.add(s1.p1);
                }
                if (onSegment(s2, s1.p2)) {
                    overlapPoints.add(s1.p2);
                }

                // Remove duplicates and return the first point
                if (!overlapPoints.isEmpty()) {
                    // Find the point that's not an endpoint of both segments
                    for (Point2D.Double pt : overlapPoints) {
                        boolean isS1Endpoint = pointsEqual(pt, s1.p1) || pointsEqual(pt, s1.p2);
                        boolean isS2Endpoint = pointsEqual(pt, s2.p1) || pointsEqual(pt, s2.p2);

                        // If it's an endpoint of both, it's a touching point
                        if (isS1Endpoint && isS2Endpoint) {
                            return pt;
                        }
                    }
                    // Return the first overlap point
                    return overlapPoints.getFirst();
                }
            }
            return null;
        }

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denom;
        double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / denom;

        if (t >= -EPS && t <= 1 + EPS && u >= -EPS && u <= 1 + EPS) {
            double px = x1 + t * (x2 - x1);
            double py = y1 + t * (y2 - y1);
            return new Point2D.Double(px, py);
        }

        return null;
    }

    private static boolean areCollinear(Segment s1, Segment s2) {
        double cross1 = crossProduct(s1.p1, s1.p2, s2.p1);
        double cross2 = crossProduct(s1.p1, s1.p2, s2.p2);
        return Math.abs(cross1) < EPS && Math.abs(cross2) < EPS;
    }

    private static double crossProduct(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    private static boolean onSegment(Segment s, Point2D.Double p) {
        return p.x >= Math.min(s.p1.x, s.p2.x) - EPS && p.x <= Math.max(s.p1.x, s.p2.x) + EPS && p.y >= Math.min(s.p1.y, s.p2.y) - EPS && p.y <= Math.max(s.p1.y, s.p2.y) + EPS && Math.abs(crossProduct(s.p1, s.p2, p)) < EPS;
    }

    private static boolean pointsEqual(Point2D.Double p1, Point2D.Double p2) {
        return Math.abs(p1.x - p2.x) < EPS && Math.abs(p1.y - p2.y) < EPS;
    }
}
