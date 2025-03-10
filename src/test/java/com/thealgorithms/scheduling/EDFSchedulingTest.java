package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EDFSchedulingTest {

    private List<EDFScheduling.Process> processes;

    @BeforeEach
    public void setup() {
        processes = createProcesses();
    }

    @Test
    public void testEDFScheduling() {
        EDFScheduling edfScheduling = new EDFScheduling(processes);
        List<EDFScheduling.Process> executedProcesses = edfScheduling.scheduleProcesses();

        // Assert the correct number of processes
        assertEquals(3, executedProcesses.size());

        // Assert that processes are executed in order of earliest deadline first
        EDFScheduling.Process process1 = executedProcesses.get(0);
        assertEquals("P2", process1.getProcessId());
        assertEquals(0, process1.getWaitingTime());
        assertEquals(3, process1.getTurnAroundTime());

        EDFScheduling.Process process2 = executedProcesses.get(1);
        assertEquals("P1", process2.getProcessId());
        assertEquals(3, process2.getWaitingTime());
        assertEquals(10, process2.getTurnAroundTime());

        EDFScheduling.Process process3 = executedProcesses.get(2);
        assertEquals("P3", process3.getProcessId());
        assertEquals(10, process3.getWaitingTime());
        assertEquals(18, process3.getTurnAroundTime());
    }

    @Test
    public void testProcessMissedDeadline() {
        // Modify the deadline of a process to ensure it will miss its deadline
        processes.get(1).setTurnAroundTime(5); // Set P1's deadline to 5 (which it will miss)

        EDFScheduling edfScheduling = new EDFScheduling(processes);
        edfScheduling.scheduleProcesses();

        // Check if the process with ID "P1" missed its deadline
        assertEquals("P1", processes.get(1).getProcessId());
    }

    private List<EDFScheduling.Process> createProcesses() {
        // Process ID, Burst Time, Deadline
        EDFScheduling.Process process1 = new EDFScheduling.Process("P1", 7, 10); // 7 burst time, 10 deadline
        EDFScheduling.Process process2 = new EDFScheduling.Process("P2", 3, 5); // 3 burst time, 5 deadline
        EDFScheduling.Process process3 = new EDFScheduling.Process("P3", 8, 18); // 8 burst time, 18 deadline

        List<EDFScheduling.Process> processes = new ArrayList<>();
        processes.add(process1);
        processes.add(process2);
        processes.add(process3);

        return processes;
    }
}
