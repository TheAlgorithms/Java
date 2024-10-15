package com.thealgorithms.scheduling.diskscheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SSFSchedulingTest {

    private SSFScheduling scheduler;

    @BeforeEach
    public void setUp() {
        scheduler = new SSFScheduling(50);
    }

    @Test
    public void testExecuteWithEmptyList() {
        List<Integer> requests = new ArrayList<>();
        List<Integer> result = scheduler.execute(requests);
        assertTrue(result.isEmpty(), "Result should be empty for an empty request list.");
    }

    @Test
    public void testExecuteWithSingleRequest() {
        List<Integer> requests = new ArrayList<>(List.of(100));
        List<Integer> result = scheduler.execute(requests);
        assertEquals(List.of(100), result, "The only request should be served first.");
    }

    @Test
    public void testExecuteWithMultipleRequests() {
        List<Integer> requests = new ArrayList<>(List.of(10, 90, 60, 40, 30, 70));
        List<Integer> result = scheduler.execute(requests);
        assertEquals(List.of(60, 70, 90, 40, 30, 10), result, "Requests should be served in the shortest seek first order.");
    }

    @Test
    public void testExecuteWithSameDistanceRequests() {
        List<Integer> requests = new ArrayList<>(List.of(45, 55));
        List<Integer> result = scheduler.execute(requests);
        assertEquals(List.of(45, 55), result, "When distances are equal, requests should be served in the order they appear in the list.");
    }

    @Test
    public void testGetCurrentPositionAfterExecution() {
        List<Integer> requests = new ArrayList<>(List.of(10, 90, 60, 40, 30, 70));
        scheduler.execute(requests);
        int currentPosition = scheduler.getCurrentPosition();
        assertEquals(10, currentPosition, "Current position should be the last request after execution.");
    }
}
