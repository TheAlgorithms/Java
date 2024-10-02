package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// Problem Link: https://en.wikipedia.org/wiki/Activity_selection_problem

public final class ActivitySelection {

    // Private constructor to prevent instantiation of the utility class
    private ActivitySelection() {
    }

    /**
     * Function to perform activity selection using a greedy approach.
     *
     * The goal is to select the maximum number of activities that don't overlap
     * with each other, based on their start and end times. Activities are chosen
     * such that no two selected activities overlap.
     *
     * @param startTimes Array containing the start times of the activities.
     * @param endTimes   Array containing the end times of the activities.
     * @return A list of indices representing the selected activities that can be
     *         performed without overlap.
     */
    public static ArrayList<Integer> activitySelection(int[] startTimes, int[] endTimes) {
        int n = startTimes.length;

        // Create a 2D array to store activity indices along with their start and end
        // times.
        // Each row represents an activity in the format: [activity index, start time,
        // end time].
        int[][] activities = new int[n][3];

        // Populate the 2D array with the activity index, start time, and end time.
        for (int i = 0; i < n; i++) {
            activities[i][0] = i; // Assign the activity index
            activities[i][1] = startTimes[i]; // Assign the start time of the activity
            activities[i][2] = endTimes[i]; // Assign the end time of the activity
        }

        // Sort activities based on their end times in ascending order.
        // This ensures that we always try to finish earlier activities first.
        Arrays.sort(activities, Comparator.comparingDouble(activity -> activity[2]));
        int lastEndTime; // Variable to store the end time of the last selected activity
        // List to store the indices of selected activities
        ArrayList<Integer> selectedActivities = new ArrayList<>();

        // Select the first activity (as it has the earliest end time after sorting)
        selectedActivities.add(activities[0][0]); // Add the first activity index to the result
        lastEndTime = activities[0][2]; // Keep track of the end time of the last selected activity

        // Iterate over the sorted activities to select the maximum number of compatible
        // activities.
        for (int i = 1; i < n; i++) {
            // If the start time of the current activity is greater than or equal to the
            // end time of the last selected activity, it means there's no overlap.
            if (activities[i][1] >= lastEndTime) {
                selectedActivities.add(activities[i][0]); // Select this activity
                lastEndTime = activities[i][2]; // Update the end time of the last selected activity
            }
        }

        // Return the list of selected activity indices.
        return selectedActivities;
    }
}
