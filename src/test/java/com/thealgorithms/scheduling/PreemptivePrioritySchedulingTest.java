package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test Cases of Preemptive Priority Scheduling Algorithm
 *
 * @author [Bama Charan Chhandogi](https://www.github.com/BamaCharanChhandogi)
 */
class PreemptivePrioritySchedulingTest {
    @ParameterizedTest
    @MethodSource("provideProcessesAndExpectedSchedules")
    void testPreemptivePriorityScheduling(Collection<ProcessDetails> processes, List<String> expectedSchedule) {
        PreemptivePriorityScheduling scheduler = new PreemptivePriorityScheduling(processes);
        scheduler.scheduleProcesses();
        assertEquals(expectedSchedule, scheduler.ganttChart);
    }

    static Stream<Arguments> provideProcessesAndExpectedSchedules() {
        return Stream.of(Arguments.of(List.of(new ProcessDetails("P1", 0, 5, 2), new ProcessDetails("P2", 1, 4, 4), new ProcessDetails("P3", 2, 2, 6), new ProcessDetails("P4", 4, 1, 8)), List.of("P1", "P2", "P3", "P3", "P4", "P2", "P2", "P2", "P1", "P1", "P1", "P1")),
            Arguments.of(List.of(new ProcessDetails("P1", 2, 5, 3), new ProcessDetails("P2", 5, 3, 5), new ProcessDetails("P3", 7, 1, 9)), List.of("Idle", "Idle", "P1", "P1", "P1", "P2", "P2", "P3", "P2", "P1", "P1")));
    }
}
