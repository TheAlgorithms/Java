package com.thealgorithms.greedyalgorithms;

import java.util.Arrays;

public final class MinimizingLateness {
    private MinimizingLateness() {
    }

    public static class Job {
        String jobName;
        int startTime = 0;
        int lateness = 0;
        int processingTime;
        int deadline;

        public Job(String jobName, int processingTime, int deadline) {
            this.jobName = jobName;
            this.processingTime = processingTime;
            this.deadline = deadline;
        }

        public static Job of(String jobName, int processingTime, int deadline) {
            return new Job(jobName, processingTime, deadline);
        }

        @Override
        public String toString() {
            return String.format("%s, startTime: %d, endTime: %d, lateness: %d", jobName, startTime, processingTime + startTime, lateness);
        }
    }

    static void calculateLateness(Job... jobs) {

        // sort the jobs based on their deadline
        Arrays.sort(jobs, (a, b) -> a.deadline - b.deadline);

        int startTime = 0;

        for (Job job : jobs) {
            job.startTime = startTime;
            startTime += job.processingTime;
            job.lateness = Math.max(0, startTime - job.deadline); // if the job finishes before deadline the lateness is 0
        }
    }
}
