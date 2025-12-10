package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class SRTFSchedulingTest {
    ArrayList<ProcessDetails> processes;

    public void initialization() {
        processes = new ArrayList<>();
        processes.add(new ProcessDetails("4", 0, 3));
        processes.add(new ProcessDetails("3", 1, 8));
        processes.add(new ProcessDetails("1", 2, 6));
        processes.add(new ProcessDetails("5", 4, 4));
        processes.add(new ProcessDetails("2", 5, 2));
    }

    @Test
    public void constructor() {
        initialization();
        SRTFScheduling s = new SRTFScheduling(processes);
        assertEquals(3, s.processes.get(0).getBurstTime());
        assertEquals(8, s.processes.get(1).getBurstTime());
        assertEquals(6, s.processes.get(2).getBurstTime());
        assertEquals(4, s.processes.get(3).getBurstTime());
        assertEquals(2, s.processes.get(4).getBurstTime());
    }

    @Test
    void evaluateScheduling() {
        initialization();
        SRTFScheduling s = new SRTFScheduling(processes);
        s.evaluateScheduling();
        assertEquals("4", s.ready.get(0));
        assertEquals("4", s.ready.get(1));
        assertEquals("4", s.ready.get(2));
        assertEquals("1", s.ready.get(3));
        assertEquals("5", s.ready.get(4));
        assertEquals("2", s.ready.get(5));
        assertEquals("2", s.ready.get(6));
        assertEquals("5", s.ready.get(7));
        assertEquals("5", s.ready.get(8));
        assertEquals("5", s.ready.get(9));
        assertEquals("1", s.ready.get(10));
        assertEquals("1", s.ready.get(11));
        assertEquals("1", s.ready.get(12));
        assertEquals("1", s.ready.get(13));
        assertEquals("1", s.ready.get(14));
        assertEquals("3", s.ready.get(15));
        assertEquals("3", s.ready.get(16));
        assertEquals("3", s.ready.get(17));
        assertEquals("3", s.ready.get(18));
        assertEquals("3", s.ready.get(19));
        assertEquals("3", s.ready.get(20));
        assertEquals("3", s.ready.get(21));
        assertEquals("3", s.ready.get(22));
    }
}
