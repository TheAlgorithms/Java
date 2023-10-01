package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//Problem Link: https://en.wikipedia.org/wiki/Job-shop_scheduling

public class JobSequencing {

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

        public Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {
        JobSequencing jobSequencing = new JobSequencing();
        ArrayList<Job> jobs = new ArrayList<Job>();
        
        // Add jobs with their respective deadlines and profits
        jobs.add(new Job('a', 2, 100));
        jobs.add(new Job('b', 1, 19));
        jobs.add(new Job('c', 2, 27));
        jobs.add(new Job('d', 1, 25));
        jobs.add(new Job('e', 3, 15));

        // Sort jobs by profit in descending order
        Collections.sort(jobs);

        // Print sorted jobs with their profits
        for (Job job : jobs) {
            System.out.println(job.id + " - " + job.profit);
        }

        // Call the printJobSequence function to find the job sequence
        jobSequencing.printJobSequence(jobs, jobs.size());
    }

    // Function to print the job sequence
    private void printJobSequence(ArrayList<Job> jobs, int size) {
        Boolean[] slots = new Boolean[size];
        Arrays.fill(slots, false);

        int result[] = new int[size];

        // Iterate through jobs to find the optimal job sequence
        for (int i = 0; i < size; i++) {
            for (int j = jobs.get(i).deadline - 1; j >= 0; j--) {
                if (!slots[j]) {
                    result[j] = i;
                    slots[j] = true;
                    break;
                }
            }
        }

        // Print the job sequence
        System.out.print("Job Sequence: ");
        for (int i = 0; i < jobs.size(); i++) {
            if (slots[i])
                System.out.print(jobs.get(result[i]).id + " -> ");
        }
    }
}
