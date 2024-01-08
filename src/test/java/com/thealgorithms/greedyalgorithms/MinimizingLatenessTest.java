package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.greedyalgorithms.MinimizingLateness.Job;
import org.junit.jupiter.api.Test;

public class MinimizingLatenessTest {

    @Test
    void testCalculateLateness() {
        // Test case with three jobs
        Job job1 = new Job("Job1", 4, 6);
        Job job2 = new Job("Job2", 2, 8);
        Job job3 = new Job("Job3", 1, 9);
        Job job4 = new Job("Job4", 5, 9);
        Job job5 = new Job("Job5", 4, 10);
        Job job6 = new Job("Job6", 3, 5);

        MinimizingLateness.calculateLateness(job1, job2, job3, job4, job5, job6);

        // Check lateness for each job
        assertEquals(6, job4.lateness);
        assertEquals(0, job6.lateness);
        assertEquals(1, job2.lateness);
    }

    @Test
    void testCheckStartTime() {

        Job job1 = new Job("Job1", 2, 5);
        Job job2 = new Job("Job2", 1, 7);
        Job job3 = new Job("Job3", 3, 8);
        Job job4 = new Job("Job4", 2, 4);
        Job job5 = new Job("Job5", 4, 10);

        MinimizingLateness.calculateLateness(job1, job2, job3, job4, job5);

        assertEquals(2, job1.startTime);
        assertEquals(5, job3.startTime);
        assertEquals(8, job5.startTime);
    }
}
