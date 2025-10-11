
package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Skyline Problem</h2>
 * <p>
 * Solves the classic skyline problem using a divide-and-conquer approach. Given
 * a list of buildings (each defined by left, height, right),
 * computes the silhouette (skyline) formed by these buildings when viewed from
 * a distance.
 * </p>
 * <p>
 * Usage example:
 *
 * <pre>
 * SkylineProblem sp = new SkylineProblem();
 * sp.building = new SkylineProblem.Building[3];
 * sp.add(1, 10, 5);
 * sp.add(2, 15, 7);
 * sp.add(3, 12, 9);
 * List<SkylineProblem.Skyline> skyline = sp.findSkyline(0, 2);
 * </pre>
 * </p>
 * <p>
 * This class is not thread-safe.
 * </p>
 */
public class SkylineProblem {

    /**
     * Array of buildings to process. Must be initialized before use.
     */
    private Building[] building;

    /**
     * Number of buildings added so far.
     */
    public int count;

    /**
     * Sets the building array to the specified size.
     *
     * @param size The size of the building array.
     * @throws IllegalArgumentException if size is negative
     */
    public void setBuilding(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be non-negative");
        }
        this.building = new Building[size];
        this.count = 0;
    }

    /**
     * Adds a building with the given left, height, and right values to the
     * buildings list.
     *
     * @param left   The left x-coordinate of the building.
     * @param height The height of the building.
     * @param right  The right x-coordinate of the building.
     * @throws IllegalArgumentException if left >= right or height < 0
     * @throws IllegalStateException    if building array is not initialized or is
     *                                  full
     */
    public void add(int left, int height, int right) {
        if (building == null) {
            throw new IllegalStateException("Building array not initialized");
        }
        if (count >= building.length) {
            throw new IllegalStateException("Building array is full");
        }
        if (left >= right) {
            throw new IllegalArgumentException("Left coordinate must be less than right coordinate");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Height must be non-negative");
        }
        building[count++] = new Building(left, height, right);
    }

    /**
     * Computes the skyline for a range of buildings using the divide-and-conquer
     * strategy.
     *
     * @param start The starting index of the buildings to process (inclusive).
     * @param end   The ending index of the buildings to process (inclusive).
     * @return A list of {@link Skyline} objects representing the computed skyline.
     * @throws IllegalArgumentException if indices are out of bounds or building
     *                                  array is null
     */
    public List<Skyline> findSkyline(int start, int end) {
        if (building == null) {
            throw new IllegalArgumentException("Building array is not initialized");
        }
        if (start < 0 || end >= count || start > end) {
            throw new IllegalArgumentException("Invalid start or end index");
        }
        // Base case: only one building, return its skyline.
        if (start == end) {
            List<Skyline> list = new ArrayList<>();
            list.add(new Skyline(building[start].left, building[start].height));
            list.add(new Skyline(building[start].right, 0)); // Add the end of the building
            return list;
        }

        int mid = (start + end) / 2;
        List<Skyline> sky1 = this.findSkyline(start, mid); // Find the skyline of the left half
        List<Skyline> sky2 = this.findSkyline(mid + 1, end); // Find the skyline of the right half
        return this.mergeSkyline(sky1, sky2); // Merge the two skylines
    }

    /**
     * Merges two skylines (sky1 and sky2) into one combined skyline.
     *
     * @param sky1 The first skyline list. Not modified.
     * @param sky2 The second skyline list. Not modified.
     * @return A list of {@link Skyline} objects representing the merged skyline.
     * @throws NullPointerException if either argument is null
     */
    public List<Skyline> mergeSkyline(List<Skyline> sky1, List<Skyline> sky2) {
        Objects.requireNonNull(sky1, "sky1 must not be null");
        Objects.requireNonNull(sky2, "sky2 must not be null");
        int i = 0;
        int j = 0;
        int h1 = 0;
        int h2 = 0;
        int prevHeight = 0;
        List<Skyline> result = new ArrayList<>();
        while (i < sky1.size() && j < sky2.size()) {
            Skyline p1 = sky1.get(i);
            Skyline p2 = sky2.get(j);
            int x;
            if (p1.coordinates < p2.coordinates) {
                x = p1.coordinates;
                h1 = p1.height;
                i++;
            } else if (p2.coordinates < p1.coordinates) {
                x = p2.coordinates;
                h2 = p2.height;
                j++;
            } else { // same x
                x = p1.coordinates;
                h1 = p1.height;
                h2 = p2.height;
                i++;
                j++;
            }
            int maxH = Math.max(h1, h2);
            if (result.isEmpty() || prevHeight != maxH) {
                result.add(new Skyline(x, maxH));
                prevHeight = maxH;
            }
        }
        // Append remaining points
        while (i < sky1.size()) {
            Skyline p = sky1.get(i++);
            if (result.isEmpty() || result.get(result.size() - 1).height != p.height || result.get(result.size() - 1).coordinates != p.coordinates) {
                result.add(new Skyline(p.coordinates, p.height));
            }
        }
        while (j < sky2.size()) {
            Skyline p = sky2.get(j++);
            if (result.isEmpty() || result.get(result.size() - 1).height != p.height || result.get(result.size() - 1).coordinates != p.coordinates) {
                result.add(new Skyline(p.coordinates, p.height));
            }
        }
        return result;
    }

    /**
     * Represents a point in the skyline with its x-coordinate and height.
     */
    public static class Skyline {
        /** The x-coordinate of the skyline point. */
        public final int coordinates;
        /** The height of the skyline at the given coordinate. */
        public final int height;

        /**
         * Constructor for the {@code Skyline} class.
         *
         * @param coordinates The x-coordinate of the skyline point.
         * @param height      The height of the skyline at the given coordinate.
         */
        public Skyline(int coordinates, int height) {
            this.coordinates = coordinates;
            this.height = height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Skyline skyline = (Skyline) o;
            return coordinates == skyline.coordinates && height == skyline.height;
        }

        @Override
        public int hashCode() {
            return Objects.hash(coordinates, height);
        }

        @Override
        public String toString() {
            return "(" + coordinates + ", " + height + ")";
        }
    }

    /**
     * Represents a building with its left, height, and right x-coordinates.
     */
    public static class Building {
        /** The left x-coordinate of the building. */
        public final int left;
        /** The height of the building. */
        public final int height;
        /** The right x-coordinate of the building. */
        public final int right;

        /**
         * Constructor for the {@code Building} class.
         *
         * @param left   The left x-coordinate of the building.
         * @param height The height of the building.
         * @param right  The right x-coordinate of the building.
         */
        public Building(int left, int height, int right) {
            this.left = left;
            this.height = height;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Building{"
                + "left=" + left + ", height=" + height + ", right=" + right + '}';
        }
    }
}
