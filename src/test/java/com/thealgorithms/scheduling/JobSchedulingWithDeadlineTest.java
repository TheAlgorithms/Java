package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class JobSchedulingWithDeadlineTest {

    @Test
    void testJobSequencingWithDeadlines1() {
        JobSchedulingWithDeadline.Job[] jobs = {
                new JobSchedulingWithDeadline.Job(1, 4, 20),
                new JobSchedulingWithDeadline.Job(2, 1, 10),
                new JobSchedulingWithDeadline.Job(3, 1, 40),
                new JobSchedulingWithDeadline.Job(4, 1, 30)
        };
        int[] result = JobSchedulingWithDeadline.jobSequencingWithDeadlines(jobs);
        assertArrayEquals(new int[]{2, 60}, result);
    }

    @Test
    void testJobSequencingWithDeadlines2() {
        JobSchedulingWithDeadline.Job[] jobs = {
                new JobSchedulingWithDeadline.Job(1, 2, 100),
                new JobSchedulingWithDeadline.Job(2, 1, 19),
                new JobSchedulingWithDeadline.Job(3, 2, 27),
                new JobSchedulingWithDeadline.Job(4, 1, 25),
                new JobSchedulingWithDeadline.Job(5, 1, 15)
        };
        int[] result = JobSchedulingWithDeadline.jobSequencingWithDeadlines(jobs);
        assertArrayEquals(new int[]{2, 127}, result);
    }

    @Test
    void testJobSequencingWithDeadlinesNoJobs() {
        JobSchedulingWithDeadline.Job[] jobs = {};
        int[] result = JobSchedulingWithDeadline.jobSequencingWithDeadlines(jobs);
        assertArrayEquals(new int[]{0, 0}, result);
    }

    @Test
    void testJobSequencingWithDeadlinesSingleJob() {
        JobSchedulingWithDeadline.Job[] jobs = {
                new JobSchedulingWithDeadline.Job(1, 1, 50)
        };
        int[] result = JobSchedulingWithDeadline.jobSequencingWithDeadlines(jobs);
        assertArrayEquals(new int[]{1, 50}, result);
    }
}
