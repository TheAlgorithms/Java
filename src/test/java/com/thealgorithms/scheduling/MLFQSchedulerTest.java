package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MLFQSchedulerTest {

    @Test
    void testMLFQScheduling() {
        // Create MLFQ Scheduler with 3 levels and time quantum for each level
        int[] timeQuantums = {4, 8, 12}; // Example of different quantum for each queue
        MLFQScheduler scheduler = new MLFQScheduler(3, timeQuantums);

        // Add processes to the scheduler
        scheduler.addProcess(new Process(1, 10, 0)); // pid=1, burstTime=10, arrivalTime=0
        scheduler.addProcess(new Process(2, 15, 0)); // pid=2, burstTime=15, arrivalTime=0
        scheduler.addProcess(new Process(3, 25, 0)); // pid=3, burstTime=25, arrivalTime=0

        // Run the scheduler
        scheduler.run();

        // Check current time after all processes are finished
        assertEquals(50, scheduler.getCurrentTime());
    }

    @Test
    void testProcessCompletionOrder() {
        int[] timeQuantums = {3, 6, 9};
        MLFQScheduler scheduler = new MLFQScheduler(3, timeQuantums);

        Process p1 = new Process(1, 10, 0);
        Process p2 = new Process(2, 5, 0);
        Process p3 = new Process(3, 20, 0);

        scheduler.addProcess(p1);
        scheduler.addProcess(p2);
        scheduler.addProcess(p3);

        scheduler.run();

        // After running, current time should match the total burst time for all
        // processes
        assertEquals(35, scheduler.getCurrentTime());
    }
}
