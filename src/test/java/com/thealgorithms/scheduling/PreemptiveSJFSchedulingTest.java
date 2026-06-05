package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PreemptiveSJFSchedulingTest {

    @Test
    public void testBasicPreemptiveSJF() {
        List<ProcessDetails> processes = new ArrayList<>();
        processes.add(new ProcessDetails("P1", 0, 6));
        processes.add(new ProcessDetails("P2", 1, 2));

        double avgWaitingTime = PreemptiveSJFScheduling.schedule(processes);

        assertEquals(0, processes.get(0).getWaitingTime());
        assertEquals(4, processes.get(1).getWaitingTime());
        assertEquals(6, processes.get(0).getTurnAroundTimeTime());
        assertEquals(6, processes.get(1).getTurnAroundTimeTime());
        assertEquals(2.0, avgWaitingTime, 0.001);
    }

    @Test
    public void testMultiplePreemptions() {
        List<ProcessDetails> processes = new ArrayList<>();
        processes.add(new ProcessDetails("P1", 0, 10));
        processes.add(new ProcessDetails("P2", 1, 4));
        processes.add(new ProcessDetails("P3", 2, 5));
        processes.add(new ProcessDetails("P4", 3, 3));

        double avgWaitingTime = PreemptiveSJFScheduling.schedule(processes);

        assertEquals(9, processes.get(0).getWaitingTime());
        assertEquals(0, processes.get(1).getWaitingTime());
        assertEquals(5, processes.get(2).getWaitingTime());
        assertEquals(0, processes.get(3).getWaitingTime());
        assertEquals(22, processes.get(0).getTurnAroundTimeTime());
        assertEquals(4, processes.get(1).getTurnAroundTimeTime());
        assertEquals(10, processes.get(2).getTurnAroundTimeTime());
        assertEquals(3, processes.get(3).getTurnAroundTimeTime());
        assertEquals(3.5, avgWaitingTime, 0.001);
    }

    @Test
    public void testAllProcessesAtTimeZero() {
        List<ProcessDetails> processes = new ArrayList<>();
        processes.add(new ProcessDetails("P3", 0, 8));
        processes.add(new ProcessDetails("P1", 0, 5));
        processes.add(new ProcessDetails("P2", 0, 3));

        double avgWaitingTime = PreemptiveSJFScheduling.schedule(processes);

        assertEquals(8, processes.get(0).getWaitingTime());
        assertEquals(3, processes.get(1).getWaitingTime());
        assertEquals(0, processes.get(2).getWaitingTime());
        assertEquals(16, processes.get(0).getTurnAroundTimeTime());
        assertEquals(8, processes.get(1).getTurnAroundTimeTime());
        assertEquals(3, processes.get(2).getTurnAroundTimeTime());
        assertEquals(11.0 / 3.0, avgWaitingTime, 0.001);
    }

    @Test
    public void testBurstTimeOne() {
        List<ProcessDetails> processes = new ArrayList<>();
        processes.add(new ProcessDetails("P1", 0, 1));
        processes.add(new ProcessDetails("P2", 0, 1));
        processes.add(new ProcessDetails("P3", 0, 1));

        double avgWaitingTime = PreemptiveSJFScheduling.schedule(processes);

        assertEquals(0, processes.get(0).getWaitingTime());
        assertEquals(1, processes.get(1).getWaitingTime());
        assertEquals(2, processes.get(2).getWaitingTime());
        assertEquals(1, processes.get(0).getTurnAroundTimeTime());
        assertEquals(2, processes.get(1).getTurnAroundTimeTime());
        assertEquals(3, processes.get(2).getTurnAroundTimeTime());
        assertEquals(1.0, avgWaitingTime, 0.001);
    }

    @Test
    public void testProcessesNotArrivingAtZero() {
        List<ProcessDetails> processes = new ArrayList<>();
        processes.add(new ProcessDetails("P1", 2, 5));
        processes.add(new ProcessDetails("P2", 4, 3));
        processes.add(new ProcessDetails("P3", 6, 2));

        double avgWaitingTime = PreemptiveSJFScheduling.schedule(processes);

        assertEquals(0, processes.get(0).getWaitingTime());
        assertEquals(1, processes.get(1).getWaitingTime());
        assertEquals(0, processes.get(2).getWaitingTime());
        assertEquals(5, processes.get(0).getTurnAroundTimeTime());
        assertEquals(4, processes.get(1).getTurnAroundTimeTime());
        assertEquals(2, processes.get(2).getTurnAroundTimeTime());
        assertEquals(1.0 / 3.0, avgWaitingTime, 0.001);
    }
}
