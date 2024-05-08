package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class JobSequencingTest {
    @Test
    public void testJobSequencingWithExampleCase() {
        ArrayList<JobSequencing.Job> jobs = new ArrayList<>();
        jobs.add(new JobSequencing.Job('a', 2, 100));
        jobs.add(new JobSequencing.Job('b', 1, 19));
        jobs.add(new JobSequencing.Job('c', 2, 27));
        jobs.add(new JobSequencing.Job('d', 1, 25));
        jobs.add(new JobSequencing.Job('e', 3, 15));
        Collections.sort(jobs);
        String jobSequence = JobSequencing.findJobSequence(jobs, jobs.size());

        assertEquals("Job Sequence: c -> a -> e", jobSequence);
    }
}
