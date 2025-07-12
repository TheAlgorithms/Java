package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SJFSchedulingTest {

    private static Stream<Arguments> schedulingTestData() {
        return Stream.of(Arguments.of(List.of(new ProcessDetails("1", 0, 6), new ProcessDetails("2", 1, 2)), List.of("1", "2")),
            Arguments.of(List.of(new ProcessDetails("1", 0, 6), new ProcessDetails("2", 1, 2), new ProcessDetails("3", 4, 3), new ProcessDetails("4", 3, 1), new ProcessDetails("5", 6, 4), new ProcessDetails("6", 5, 5)), List.of("1", "4", "2", "3", "5", "6")),
            Arguments.of(List.of(new ProcessDetails("1", 0, 3), new ProcessDetails("2", 1, 2), new ProcessDetails("3", 2, 1)), List.of("1", "3", "2")), Arguments.of(List.of(new ProcessDetails("1", 0, 3), new ProcessDetails("2", 5, 2), new ProcessDetails("3", 9, 1)), List.of("1", "2", "3")),
            Arguments.of(Collections.emptyList(), List.of()));
    }

    @ParameterizedTest(name = "Test SJF schedule: {index}")
    @MethodSource("schedulingTestData")
    void testSJFScheduling(List<ProcessDetails> inputProcesses, List<String> expectedSchedule) {
        SJFScheduling scheduler = new SJFScheduling(inputProcesses);
        scheduler.scheduleProcesses();
        assertEquals(expectedSchedule, scheduler.getSchedule());
    }

    @Test
    @DisplayName("Test sorting by arrival order")
    void testProcessArrivalOrderIsSorted() {
        List<ProcessDetails> processes = List.of(new ProcessDetails("1", 0, 6), new ProcessDetails("2", 1, 2), new ProcessDetails("4", 3, 1), new ProcessDetails("3", 4, 3), new ProcessDetails("6", 5, 5), new ProcessDetails("5", 6, 4));
        SJFScheduling scheduler = new SJFScheduling(processes);
        List<String> actualOrder = scheduler.getProcesses().stream().map(ProcessDetails::getProcessId).toList();

        assertEquals(List.of("1", "2", "4", "3", "6", "5"), actualOrder);
    }

    @Test
    void testSchedulingEmptyList() {
        SJFScheduling scheduler = new SJFScheduling(Collections.emptyList());
        scheduler.scheduleProcesses();
        assertTrue(scheduler.getSchedule().isEmpty());
    }
}
