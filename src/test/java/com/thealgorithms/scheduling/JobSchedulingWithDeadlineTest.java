package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class JobSchedulingWithDeadlineTest {

    @Test
    void testJobSequencingWithDeadlines1() {
        JobSchedulingWithDeadline.Job[] jobs = {new JobSchedulingWithDeadline.Job(1, 1, 4, 20), new JobSchedulingWithDeadline.Job(2, 1, 1, 10), new JobSchedulingWithDeadline.Job(3, 1, 1, 40), new JobSchedulingWithDeadline.Job(4, 1, 1, 30)};
        int[] result = JobSchedulingWithDeadline.jobSequencingWithDeadlines(jobs);
        assertArrayEquals(new int[] {2, 60}, result); // Expected output: 2 jobs, 60 profit
    }

    @Test
    void testJobSequencingWithDeadlines2() {
        JobSchedulingWithDeadline.Job[] jobs = {new JobSchedulingWithDeadline.Job(1, 1, 2, 100), new JobSchedulingWithDeadline.Job(2, 1, 1, 19), new JobSchedulingWithDeadline.Job(3, 1, 2, 27), new JobSchedulingWithDeadline.Job(4, 1, 1, 25), new JobSchedulingWithDeadline.Job(5, 1, 1, 15)};
        int[] result = JobSchedulingWithDeadline.jobSequencingWithDeadlines(jobs);
        assertArrayEquals(new int[] {2, 127}, result); // Expected output: 2 jobs, 127 profit
    }

    @Test
    void testJobSequencingWithDeadlinesWithArrivalTimes() {
        JobSchedulingWithDeadline.Job[] jobs = {new JobSchedulingWithDeadline.Job(1, 2, 5, 50), new JobSchedulingWithDeadline.Job(2, 3, 4, 60), new JobSchedulingWithDeadline.Job(3, 1, 3, 20)};
        int[] result = JobSchedulingWithDeadline.jobSequencingWithDeadlines(jobs);
        assertArrayEquals(new int[] {3, 130}, result); // All 3 jobs fit within their deadlines
    }

    @Test
    void testJobSequencingWithDeadlinesNoJobs() {
        JobSchedulingWithDeadline.Job[] jobs = {};
        int[] result = JobSchedulingWithDeadline.jobSequencingWithDeadlines(jobs);
        assertArrayEquals(new int[] {0, 0}, result); // No jobs, 0 profit
    }

    @Test
    void testJobSequencingWithDeadlinesSingleJob() {
        JobSchedulingWithDeadline.Job[] jobs = {new JobSchedulingWithDeadline.Job(1, 1, 1, 50)};
        int[] result = JobSchedulingWithDeadline.jobSequencingWithDeadlines(jobs);
        assertArrayEquals(new int[] {1, 50}, result); // 1 job scheduled, 50 profit
    }
}
