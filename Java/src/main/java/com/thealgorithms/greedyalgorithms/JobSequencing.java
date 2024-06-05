package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Arrays;

// Problem Link: https://en.wikipedia.org/wiki/Job-shop_scheduling

public final class JobSequencing {
    private JobSequencing() {
    }

    // Define a Job class that implements Comparable for sorting by profit in descending order
    static class Job implements Comparable<Job> {
        char id;
        int deadline;
        int profit;

        // Compare jobs by profit in descending order
        @Override
        public int compareTo(Job otherJob) {
            return otherJob.profit - this.profit;
        }

        Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    // Function to print the job sequence
    public static String findJobSequence(ArrayList<Job> jobs, int size) {
        Boolean[] slots = new Boolean[size];
        Arrays.fill(slots, Boolean.FALSE);

        int[] result = new int[size];

        // Iterate through jobs to find the optimal job sequence
        for (int i = 0; i < size; i++) {
            for (int j = jobs.get(i).deadline - 1; j >= 0; j--) {
                if (!slots[j]) {
                    result[j] = i;
                    slots[j] = Boolean.TRUE;
                    break;
                }
            }
        }

        // Create a StringBuilder to build the job sequence string
        StringBuilder jobSequenceBuilder = new StringBuilder();
        jobSequenceBuilder.append("Job Sequence: ");
        for (int i = 0; i < jobs.size(); i++) {
            if (slots[i]) {
                jobSequenceBuilder.append(jobs.get(result[i]).id).append(" -> ");
            }
        }

        // Remove the trailing " -> " from the job sequence
        if (jobSequenceBuilder.length() >= 4) {
            jobSequenceBuilder.setLength(jobSequenceBuilder.length() - 4);
        }

        // Return the job sequence as a string
        return jobSequenceBuilder.toString();
    }
}
