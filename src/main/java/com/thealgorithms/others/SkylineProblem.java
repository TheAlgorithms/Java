package com.thealgorithms.others;

import java.util.ArrayList;

/**
 * The {@code SkylineProblem} class is used to solve the skyline problem using a
 * divide-and-conquer approach.
 * It reads input for building data, processes it to find the skyline, and
 * prints the skyline.
 */
public class SkylineProblem {

    Building[] building;
    int count;

    /**
     * Adds a building with the given left, height, and right values to the
     * buildings list.
     *
     * @param left   The left x-coordinate of the building.
     * @param height The height of the building.
     * @param right  The right x-coordinate of the building.
     */
    public void add(int left, int height, int right) {
        building[count++] = new Building(left, height, right);
    }

    /**
     * Computes the skyline for a range of buildings using the divide-and-conquer
     * strategy.
     *
     * @param start The starting index of the buildings to process.
     * @param end   The ending index of the buildings to process.
     * @return A list of {@link Skyline} objects representing the computed skyline.
     */
    public ArrayList<Skyline> findSkyline(int start, int end) {
        // Base case: only one building, return its skyline.
        if (start == end) {
            ArrayList<Skyline> list = new ArrayList<>();
            list.add(new Skyline(building[start].left, building[start].height));
            list.add(new Skyline(building[end].right, 0)); // Add the end of the building
            return list;
        }

        int mid = (start + end) / 2;

        ArrayList<Skyline> sky1 = this.findSkyline(start, mid); // Find the skyline of the left half
        ArrayList<Skyline> sky2 = this.findSkyline(mid + 1, end); // Find the skyline of the right half
        return this.mergeSkyline(sky1, sky2); // Merge the two skylines
    }

    /**
     * Merges two skylines (sky1 and sky2) into one combined skyline.
     *
     * @param sky1 The first skyline list.
     * @param sky2 The second skyline list.
     * @return A list of {@link Skyline} objects representing the merged skyline.
     */
    public ArrayList<Skyline> mergeSkyline(ArrayList<Skyline> sky1, ArrayList<Skyline> sky2) {
        int currentH1 = 0;
        int currentH2 = 0;
        ArrayList<Skyline> skyline = new ArrayList<>();
        int maxH = 0;

        // Merge the two skylines
        while (!sky1.isEmpty() && !sky2.isEmpty()) {
            if (sky1.get(0).coordinates < sky2.get(0).coordinates) {
                int currentX = sky1.get(0).coordinates;
                currentH1 = sky1.get(0).height;

                if (currentH1 < currentH2) {
                    sky1.remove(0);
                    if (maxH != currentH2) {
                        skyline.add(new Skyline(currentX, currentH2));
                    }
                } else {
                    maxH = currentH1;
                    sky1.remove(0);
                    skyline.add(new Skyline(currentX, currentH1));
                }
            } else {
                int currentX = sky2.get(0).coordinates;
                currentH2 = sky2.get(0).height;

                if (currentH2 < currentH1) {
                    sky2.remove(0);
                    if (maxH != currentH1) {
                        skyline.add(new Skyline(currentX, currentH1));
                    }
                } else {
                    maxH = currentH2;
                    sky2.remove(0);
                    skyline.add(new Skyline(currentX, currentH2));
                }
            }
        }

        // Add any remaining points from sky1 or sky2
        while (!sky1.isEmpty()) {
            skyline.add(sky1.get(0));
            sky1.remove(0);
        }

        while (!sky2.isEmpty()) {
            skyline.add(sky2.get(0));
            sky2.remove(0);
        }

        return skyline;
    }

    /**
     * A class representing a point in the skyline with its x-coordinate and height.
     */
    public class Skyline {
        public int coordinates;
        public int height;

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
    }

    /**
     * A class representing a building with its left, height, and right
     * x-coordinates.
     */
    public class Building {
        public int left;
        public int height;
        public int right;

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
    }
}
