package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class SJFSchedulingTest {
    private ArrayList<ProcessDetails> process;
    void initialisation0() {

        process = new ArrayList<>();
        process.add(new ProcessDetails("1", 0, 6));
        process.add(new ProcessDetails("2", 1, 2));
    }
    void initialisation1() {

        process = new ArrayList<>();
        process.add(new ProcessDetails("1", 0, 6));
        process.add(new ProcessDetails("2", 1, 2));
        process.add(new ProcessDetails("3", 4, 3));
        process.add(new ProcessDetails("4", 3, 1));
        process.add(new ProcessDetails("5", 6, 4));
        process.add(new ProcessDetails("6", 5, 5));
    }

    void initialisation2() {

        process = new ArrayList<>();
        process.add(new ProcessDetails("1", 0, 3));
        process.add(new ProcessDetails("2", 1, 2));
        process.add(new ProcessDetails("3", 2, 1));
    }
    void initialisation3() {
        process = new ArrayList<>();
        process.add(new ProcessDetails("1", 0, 3));
        process.add(new ProcessDetails("2", 5, 2));
        process.add(new ProcessDetails("3", 9, 1));
    }
    @Test
    void constructor() {
        initialisation0();
        SJFScheduling a = new SJFScheduling(process);
        assertEquals(6, a.processes.get(0).getBurstTime());
        assertEquals(2, a.processes.get(1).getBurstTime());
    }

    @Test
    void sort() {
        initialisation1();
        SJFScheduling a = new SJFScheduling(process);
        a.sortByArrivalTime();
        assertEquals("1", a.processes.get(0).getProcessId());
        assertEquals("2", a.processes.get(1).getProcessId());
        assertEquals("3", a.processes.get(3).getProcessId());
        assertEquals("4", a.processes.get(2).getProcessId());
        assertEquals("5", a.processes.get(5).getProcessId());
        assertEquals("6", a.processes.get(4).getProcessId());
    }

    @Test
    void scheduling() {
        initialisation1();
        SJFScheduling a = new SJFScheduling(process);
        a.scheduleProcesses();
        assertEquals("1", a.schedule.get(0));
        assertEquals("4", a.schedule.get(1));
        assertEquals("2", a.schedule.get(2));
        assertEquals("3", a.schedule.get(3));
        assertEquals("5", a.schedule.get(4));
        assertEquals("6", a.schedule.get(5));
    }

    @Test
    void schedulingOf_TwoProcesses() {
        initialisation0();
        SJFScheduling a = new SJFScheduling(process);
        a.scheduleProcesses();
        assertEquals("1", a.schedule.get(0));
        assertEquals("2", a.schedule.get(1));
    }

    @Test
    void schedulingOfA_ShortestJobArrivingLast() {
        initialisation2();
        SJFScheduling a = new SJFScheduling(process);
        a.scheduleProcesses();
        assertEquals("1", a.schedule.get(0));
        assertEquals("3", a.schedule.get(1));
        assertEquals("2", a.schedule.get(2));
    }
    @Test
    void scheduling_WithProcessesNotComingBackToBack() {
        initialisation3();
        SJFScheduling a = new SJFScheduling(process);
        a.scheduleProcesses();
        assertEquals("1", a.schedule.get(0));
        assertEquals("2", a.schedule.get(1));
        assertEquals("3", a.schedule.get(2));
    }
    @Test
    void schedulingOf_nothing() {
        process = new ArrayList<>();
        SJFScheduling a = new SJFScheduling(process);
        a.scheduleProcesses();
        assertTrue(a.schedule.isEmpty());
    }
}