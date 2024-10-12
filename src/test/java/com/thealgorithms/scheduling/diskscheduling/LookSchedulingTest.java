package com.thealgorithms.scheduling.diskscheduling;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LookSchedulingTest {

    @Test
    public void testLookScheduling_MovingUp() {
        LookScheduling lookScheduling = new LookScheduling(50, true, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);
        List<Integer> expected = Arrays.asList(55, 58, 90, 150, 160, 39, 18);

        List<Integer> result = lookScheduling.execute(requests);
        assertEquals(expected, result);
    }

    @Test
    public void testLookScheduling_MovingDown() {
        LookScheduling lookScheduling = new LookScheduling(50, false, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);
        List<Integer> expected = Arrays.asList(39, 18, 55, 58, 90, 150, 160);

        List<Integer> result = lookScheduling.execute(requests);
        assertEquals(expected, result);
    }

    @Test
    public void testLookScheduling_EmptyRequests() {
        LookScheduling lookScheduling = new LookScheduling(50, true, 200);
        List<Integer> requests = Arrays.asList();
        List<Integer> expected = Arrays.asList();

        List<Integer> result = lookScheduling.execute(requests);
        assertEquals(expected, result);
    }

    @Test
    public void testLookScheduling_CurrentPosition() {
        LookScheduling lookScheduling = new LookScheduling(50, true, 200);

        // Testing current position remains unchanged after scheduling.
        assertEquals(50, lookScheduling.getCurrentPosition());
    }

    @Test
    public void testLookScheduling_PrintStatus() {
        LookScheduling lookScheduling = new LookScheduling(50, true, 200);

        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);

        List<Integer> result = lookScheduling.execute(requests);

        List<Integer> expectedOrder = Arrays.asList(55, 58, 90, 150, 160, 39, 18);
        assertEquals(expectedOrder, result);

        System.out.println("Final LookScheduling Position: " + lookScheduling.getCurrentPosition());
        System.out.println("LookScheduling Moving Up: " + lookScheduling.isMovingUp());

        System.out.println("Farthest Position Reached: " + lookScheduling.getFarthestPosition());

        System.out.println("Request Order: " + result);
    }
}
