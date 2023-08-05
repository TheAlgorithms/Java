package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class RRSchedulingTest {
    @Test
    public void testingProcesses() {
        List<ProcessDetails> processes = addProcessesForRR();
        final RRScheduling rrScheduling = new RRScheduling(processes, 4); // for sending to RR with quantum value 4

        rrScheduling.scheduleProcesses();

        assertEquals(6, processes.size());

        assertEquals("P1", processes.get(0).getProcessId());
        assertEquals(12, processes.get(0).getWaitingTime());
        assertEquals(17, processes.get(0).getTurnAroundTimeTime());

        assertEquals("P2", processes.get(1).getProcessId());
        assertEquals(16, processes.get(1).getWaitingTime());
        assertEquals(22, processes.get(1).getTurnAroundTimeTime());

        assertEquals("P3", processes.get(2).getProcessId());
        assertEquals(6, processes.get(2).getWaitingTime());
        assertEquals(9, processes.get(2).getTurnAroundTimeTime());

        assertEquals("P4", processes.get(3).getProcessId());
        assertEquals(8, processes.get(3).getWaitingTime());
        assertEquals(9, processes.get(3).getTurnAroundTimeTime());

        assertEquals("P5", processes.get(4).getProcessId());
        assertEquals(15, processes.get(4).getWaitingTime());
        assertEquals(20, processes.get(4).getTurnAroundTimeTime());

        assertEquals("P6", processes.get(5).getProcessId());
        assertEquals(11, processes.get(5).getWaitingTime());
        assertEquals(15, processes.get(5).getTurnAroundTimeTime());
    }

    private List<ProcessDetails> addProcessesForRR() {
        final ProcessDetails process1 = new ProcessDetails("P1", 0, 5);
        final ProcessDetails process2 = new ProcessDetails("P2", 1, 6);
        final ProcessDetails process3 = new ProcessDetails("P3", 2, 3);
        final ProcessDetails process4 = new ProcessDetails("P4", 3, 1);
        final ProcessDetails process5 = new ProcessDetails("P5", 4, 5);
        final ProcessDetails process6 = new ProcessDetails("P6", 6, 4);

        final List<ProcessDetails> processDetails = new ArrayList<>();
        processDetails.add(process1);
        processDetails.add(process2);
        processDetails.add(process3);
        processDetails.add(process4);
        processDetails.add(process5);
        processDetails.add(process6);

        return processDetails;
    }
}