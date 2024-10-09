package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LotterySchedulingTest {

    private Random mockRandom;

    @BeforeEach
    public void setup() {
        mockRandom = mock(Random.class);
    }

    @Test
    public void testLotterySchedulingWithMockedRandom() {
        List<LotteryScheduling.Process> processes = createProcesses();
        LotteryScheduling lotteryScheduling = new LotteryScheduling(processes, mockRandom);

        // Mock the sequence of random numbers (winning tickets)
        // This sequence ensures that P1 (10 tickets), P3 (8 tickets), and P2 (5 tickets) are selected.
        when(mockRandom.nextInt(23)).thenReturn(5, 18, 11); // winning tickets for P1, P3, and P2

        List<LotteryScheduling.Process> executedProcesses = lotteryScheduling.scheduleProcesses();

        assertEquals(3, executedProcesses.size());

        // Assert the process execution order and properties
        LotteryScheduling.Process process1 = executedProcesses.get(0);
        assertEquals("P1", process1.getProcessId());
        assertEquals(0, process1.getWaitingTime());
        assertEquals(10, process1.getTurnAroundTime());

        LotteryScheduling.Process process2 = executedProcesses.get(1);
        assertEquals("P2", process2.getProcessId());
        assertEquals(10, process2.getWaitingTime());
        assertEquals(15, process2.getTurnAroundTime());

        LotteryScheduling.Process process3 = executedProcesses.get(2);
        assertEquals("P3", process3.getProcessId());
        assertEquals(15, process3.getWaitingTime());
        assertEquals(23, process3.getTurnAroundTime());
    }

    private List<LotteryScheduling.Process> createProcesses() {
        LotteryScheduling.Process process1 = new LotteryScheduling.Process("P1", 10, 10); // 10 tickets
        LotteryScheduling.Process process2 = new LotteryScheduling.Process("P2", 5, 5); // 5 tickets
        LotteryScheduling.Process process3 = new LotteryScheduling.Process("P3", 8, 8); // 8 tickets

        List<LotteryScheduling.Process> processes = new ArrayList<>();
        processes.add(process1);
        processes.add(process2);
        processes.add(process3);

        return processes;
    }
}
