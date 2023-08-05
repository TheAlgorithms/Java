package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FCFSSchedulingTest {

    @Test
    public void testingProcesses() {
        List<ProcessDetails> processes = addProcessesForFCFS();
        final FCFSScheduling fcfsScheduling = new FCFSScheduling(processes); // for sending to FCFS

        fcfsScheduling.scheduleProcesses();

        assertEquals(3, processes.size());

        assertEquals("P1", processes.get(0).getProcessId());
        assertEquals(0, processes.get(0).getWaitingTime());
        assertEquals(10, processes.get(0).getTurnAroundTimeTime());

        assertEquals("P2", processes.get(1).getProcessId());
        assertEquals(10, processes.get(1).getWaitingTime());
        assertEquals(15, processes.get(1).getTurnAroundTimeTime());

        assertEquals("P3", processes.get(2).getProcessId());
        assertEquals(15, processes.get(2).getWaitingTime());
        assertEquals(23, processes.get(2).getTurnAroundTimeTime());
    }

    private List<ProcessDetails> addProcessesForFCFS() {
        final ProcessDetails process1 = new ProcessDetails("P1", 0, 10);
        final ProcessDetails process2 = new ProcessDetails("P2", 1, 5);
        final ProcessDetails process3 = new ProcessDetails("P3", 2, 8);

        final List<ProcessDetails> processDetails = new ArrayList<>();
        processDetails.add(process1);
        processDetails.add(process2);
        processDetails.add(process3);

        return processDetails;
    }
}
