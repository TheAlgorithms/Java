package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test Cases of Preemptive Priority Scheduling Algorithm
 *
 * @author [Bama Charan Chhandogi](https://www.github.com/BamaCharanChhandogi)
 */
class PreemptivePrioritySchedulingTest {

    @Test
    void testPreemptivePriorityScheduling() {
        List<ProcessDetails> processes = new ArrayList<>();
        processes.add(new ProcessDetails("P1", 0, 5, 2));
        processes.add(new ProcessDetails("P2", 1, 4, 4));
        processes.add(new ProcessDetails("P3", 2, 2, 6));
        processes.add(new ProcessDetails("P4", 4, 1, 8));

        PreemptivePriorityScheduling scheduler = new PreemptivePriorityScheduling(processes);
        scheduler.scheduleProcesses();

        List<String> expectedSchedule = List.of("P1", "P2", "P3", "P3", "P4", "P2", "P2", "P2", "P1", "P1", "P1", "P1");

        assertEquals(expectedSchedule, scheduler.ganttChart);
    }

    @Test
    void testPreemptivePrioritySchedulingWithIdleTime() {
        List<ProcessDetails> processes = new ArrayList<>();
        processes.add(new ProcessDetails("P1", 2, 5, 3));
        processes.add(new ProcessDetails("P2", 5, 3, 5));
        processes.add(new ProcessDetails("P3", 7, 1, 9));

        PreemptivePriorityScheduling scheduler = new PreemptivePriorityScheduling(processes);
        scheduler.scheduleProcesses();

        List<String> expectedSchedule = List.of("Idle", "Idle", "P1", "P1", "P1", "P2", "P2", "P3", "P2", "P1", "P1");

        assertEquals(expectedSchedule, scheduler.ganttChart);
    }
}
