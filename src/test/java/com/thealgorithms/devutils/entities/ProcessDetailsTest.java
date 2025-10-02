package com.thealgorithms.devutils.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for ProcessDetails
 * Tests the ProcessDetails entity used in scheduling algorithms
 *
 * @author Sourav Saha (yashsaha555)
 */
class ProcessDetailsTest {

    private ProcessDetails processWithPriority;
    private ProcessDetails processWithoutPriority;

    @BeforeEach
    void setUp() {
        // Initialize test objects before each test
        processWithPriority = new ProcessDetails("P1", 0, 10, 5);
        processWithoutPriority = new ProcessDetails("P2", 2, 8);
    }

    @Test
    void testConstructorWithPriority() {
        // Test constructor with priority parameter
        ProcessDetails process = new ProcessDetails("P3", 1, 15, 3);

        assertEquals("P3", process.getProcessId());
        assertEquals(1, process.getArrivalTime());
        assertEquals(15, process.getBurstTime());
        assertEquals(3, process.getPriority());
        assertEquals(0, process.getWaitingTime()); // Default value
        assertEquals(0, process.getTurnAroundTimeTime()); // Default value
    }

    @Test
    void testConstructorWithoutPriority() {
        // Test constructor without priority parameter
        ProcessDetails process = new ProcessDetails("P4", 3, 12);

        assertEquals("P4", process.getProcessId());
        assertEquals(3, process.getArrivalTime());
        assertEquals(12, process.getBurstTime());
        assertEquals(0, process.getPriority()); // Default value
        assertEquals(0, process.getWaitingTime()); // Default value
        assertEquals(0, process.getTurnAroundTimeTime()); // Default value
    }

    @Test
    void testGetProcessId() {
        assertEquals("P1", processWithPriority.getProcessId());
        assertEquals("P2", processWithoutPriority.getProcessId());
    }

    @Test
    void testGetArrivalTime() {
        assertEquals(0, processWithPriority.getArrivalTime());
        assertEquals(2, processWithoutPriority.getArrivalTime());
    }

    @Test
    void testGetBurstTime() {
        assertEquals(10, processWithPriority.getBurstTime());
        assertEquals(8, processWithoutPriority.getBurstTime());
    }

    @Test
    void testGetWaitingTime() {
        // Initial waiting time should be 0
        assertEquals(0, processWithPriority.getWaitingTime());
        assertEquals(0, processWithoutPriority.getWaitingTime());
    }

    @Test
    void testGetTurnAroundTimeTime() {
        // Initial turnaround time should be 0
        assertEquals(0, processWithPriority.getTurnAroundTimeTime());
        assertEquals(0, processWithoutPriority.getTurnAroundTimeTime());
    }

    @Test
    void testGetPriority() {
        assertEquals(5, processWithPriority.getPriority());
        assertEquals(0, processWithoutPriority.getPriority()); // Default for constructor without priority
    }

    @Test
    void testSetProcessId() {
        processWithPriority.setProcessId("NewP1");
        assertEquals("NewP1", processWithPriority.getProcessId());

        // Test setting null process ID
        processWithPriority.setProcessId(null);
        assertNull(processWithPriority.getProcessId());

        // Test setting empty process ID
        processWithPriority.setProcessId("");
        assertEquals("", processWithPriority.getProcessId());
    }

    @Test
    void testSetArrivalTime() {
        processWithPriority.setArrivalTime(5);
        assertEquals(5, processWithPriority.getArrivalTime());

        // Test setting negative arrival time
        processWithPriority.setArrivalTime(-1);
        assertEquals(-1, processWithPriority.getArrivalTime());

        // Test setting zero arrival time
        processWithPriority.setArrivalTime(0);
        assertEquals(0, processWithPriority.getArrivalTime());
    }

    @Test
    void testSetBurstTime() {
        processWithPriority.setBurstTime(20);
        assertEquals(20, processWithPriority.getBurstTime());

        // Test setting zero burst time
        processWithPriority.setBurstTime(0);
        assertEquals(0, processWithPriority.getBurstTime());

        // Test setting very large burst time
        processWithPriority.setBurstTime(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, processWithPriority.getBurstTime());
    }

    @Test
    void testSetWaitingTime() {
        processWithPriority.setWaitingTime(15);
        assertEquals(15, processWithPriority.getWaitingTime());

        // Test setting negative waiting time
        processWithPriority.setWaitingTime(-5);
        assertEquals(-5, processWithPriority.getWaitingTime());

        // Test setting zero waiting time
        processWithPriority.setWaitingTime(0);
        assertEquals(0, processWithPriority.getWaitingTime());
    }

    @Test
    void testSetTurnAroundTimeTime() {
        processWithPriority.setTurnAroundTimeTime(25);
        assertEquals(25, processWithPriority.getTurnAroundTimeTime());

        // Test setting negative turnaround time
        processWithPriority.setTurnAroundTimeTime(-10);
        assertEquals(-10, processWithPriority.getTurnAroundTimeTime());

        // Test setting zero turnaround time
        processWithPriority.setTurnAroundTimeTime(0);
        assertEquals(0, processWithPriority.getTurnAroundTimeTime());
    }

    @Test
    void testCompleteProcessLifecycle() {
        // Test a complete process lifecycle with realistic scheduling values
        ProcessDetails process = new ProcessDetails("P5", 0, 10, 2);

        // Simulate process execution
        process.setWaitingTime(5); // Process waited 5 time units
        process.setTurnAroundTimeTime(15); // Total time from arrival to completion

        assertEquals("P5", process.getProcessId());
        assertEquals(0, process.getArrivalTime());
        assertEquals(10, process.getBurstTime());
        assertEquals(5, process.getWaitingTime());
        assertEquals(15, process.getTurnAroundTimeTime());
        assertEquals(2, process.getPriority());
    }

    @Test
    void testProcessWithMinimumValues() {
        // Test process with minimum possible values
        ProcessDetails process = new ProcessDetails("", 0, 1, 0);

        assertEquals("", process.getProcessId());
        assertEquals(0, process.getArrivalTime());
        assertEquals(1, process.getBurstTime());
        assertEquals(0, process.getPriority());
    }

    @Test
    void testProcessWithMaximumValues() {
        // Test process with large values
        ProcessDetails process = new ProcessDetails("LongProcessName", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

        assertEquals("LongProcessName", process.getProcessId());
        assertEquals(Integer.MAX_VALUE, process.getArrivalTime());
        assertEquals(Integer.MAX_VALUE, process.getBurstTime());
        assertEquals(Integer.MAX_VALUE, process.getPriority());
    }

    @Test
    void testProcessModificationAfterCreation() {
        // Test that all fields can be modified after object creation
        ProcessDetails process = new ProcessDetails("Original", 1, 5, 3);

        // Modify all fields
        process.setProcessId("Modified");
        process.setArrivalTime(10);
        process.setBurstTime(20);
        process.setWaitingTime(8);
        process.setTurnAroundTimeTime(28);

        // Verify all modifications
        assertEquals("Modified", process.getProcessId());
        assertEquals(10, process.getArrivalTime());
        assertEquals(20, process.getBurstTime());
        assertEquals(8, process.getWaitingTime());
        assertEquals(28, process.getTurnAroundTimeTime());
        assertEquals(3, process.getPriority()); // Priority has no setter, should remain unchanged
    }

    @Test
    void testMultipleProcessesIndependence() {
        // Test that multiple ProcessDetails objects are independent
        ProcessDetails process1 = new ProcessDetails("P1", 0, 5, 1);
        ProcessDetails process2 = new ProcessDetails("P2", 2, 8, 2);

        // Modify first process
        process1.setWaitingTime(10);
        process1.setTurnAroundTimeTime(15);

        // Verify first process was modified correctly
        assertEquals("P1", process1.getProcessId());
        assertEquals(0, process1.getArrivalTime());
        assertEquals(5, process1.getBurstTime());
        assertEquals(1, process1.getPriority());
        assertEquals(10, process1.getWaitingTime());
        assertEquals(15, process1.getTurnAroundTimeTime());

        // Verify second process is unchanged
        assertEquals("P2", process2.getProcessId());
        assertEquals(2, process2.getArrivalTime());
        assertEquals(8, process2.getBurstTime());
        assertEquals(2, process2.getPriority());
        assertEquals(0, process2.getWaitingTime());
        assertEquals(0, process2.getTurnAroundTimeTime());
    }

    @Test
    void testConstructorParameterOrder() {
        // Test that constructor parameters are assigned to correct fields
        ProcessDetails process = new ProcessDetails("TestProcess", 123, 456, 789);

        assertEquals("TestProcess", process.getProcessId());
        assertEquals(123, process.getArrivalTime());
        assertEquals(456, process.getBurstTime());
        assertEquals(789, process.getPriority());
    }

    @Test
    void testTypicalSchedulingScenario() {
        // Test a typical scheduling scenario with multiple processes
        ProcessDetails[] processes = {new ProcessDetails("P1", 0, 8, 3), new ProcessDetails("P2", 1, 4, 1), new ProcessDetails("P3", 2, 9, 4), new ProcessDetails("P4", 3, 5, 2)};

        // Simulate FCFS scheduling calculations
        int currentTime = 0;
        for (ProcessDetails process : processes) {
            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();
            }
            process.setWaitingTime(currentTime - process.getArrivalTime());
            currentTime += process.getBurstTime();
            process.setTurnAroundTimeTime(process.getWaitingTime() + process.getBurstTime());
        }

        // Verify calculations
        assertEquals(0, processes[0].getWaitingTime()); // P1: arrives at 0, starts immediately
        assertEquals(8, processes[0].getTurnAroundTimeTime()); // P1: 0 + 8

        assertEquals(7, processes[1].getWaitingTime()); // P2: arrives at 1, starts at 8
        assertEquals(11, processes[1].getTurnAroundTimeTime()); // P2: 7 + 4

        assertEquals(10, processes[2].getWaitingTime()); // P3: arrives at 2, starts at 12
        assertEquals(19, processes[2].getTurnAroundTimeTime()); // P3: 10 + 9

        assertEquals(18, processes[3].getWaitingTime()); // P4: arrives at 3, starts at 21
        assertEquals(23, processes[3].getTurnAroundTimeTime()); // P4: 18 + 5
    }
}
