package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelection {
    // Function to perform activity selection
    public static void activitySelection(int start[], int end[]) {
        int n = start.length;
        int activities[][] = new int[n][3]; 

        // Create a 2D array to store activities and their start/end times.
        // Each row: [activity index, start time, end time]

        for (int i = 0; i < n; i++) {
            activities[i][0] = i;      // Assign activity index
            activities[i][1] = start[i]; // Assign start time
            activities[i][2] = end[i];   // Assign end time
        }

        // Sort activities by their end times in ascending order.
        Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));

        int max = 1;         
        int lastEnd;         
        ArrayList<Integer> ans = new ArrayList<>(); 
        
        ans.add(activities[0][0]); 
        lastEnd = activities[0][2]; 

        // Iterate through sorted activities to select compatible ones.
        for (int i = 1; i < n; i++) {
            if (activities[i][1] >= lastEnd) {
                max++;                    
                ans.add(activities[i][0]); 
                lastEnd = activities[i][2]; 
            }
        }

        // Print the selected activities.
        System.out.print("Following activities are selected : \n");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print("A" + ans.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int start[] = {1, 3, 0, 5, 8, 5};
        int end[] = {2, 4, 6, 7, 9, 9};

        // Call the activitySelection function with the given start and end times.
        activitySelection(start, end);
    }
}
