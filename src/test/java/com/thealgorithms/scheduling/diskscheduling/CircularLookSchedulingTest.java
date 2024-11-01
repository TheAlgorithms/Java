package com.thealgorithms.scheduling.diskscheduling;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CircularLookSchedulingTest {

    @Test
    public void testCircularLookSchedulingMovingUp() {
        CircularLookScheduling scheduling = new CircularLookScheduling(50, true, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);
        List<Integer> expected = Arrays.asList(55, 58, 90, 150, 160, 18, 39);

        List<Integer> result = scheduling.execute(requests);
        assertEquals(expected, result);
    }

    @Test
    public void testCircularLookSchedulingMovingDown() {
        CircularLookScheduling scheduling = new CircularLookScheduling(50, false, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);
        List<Integer> expected = Arrays.asList(39, 18, 160, 150, 90, 58, 55);

        List<Integer> result = scheduling.execute(requests);
        assertEquals(expected, result);
    }

    @Test
    public void testCircularLookSchedulingEmptyRequests() {
        CircularLookScheduling scheduling = new CircularLookScheduling(50, true, 200);
        List<Integer> requests = emptyList();
        List<Integer> expected = emptyList();

        List<Integer> result = scheduling.execute(requests);
        assertEquals(expected, result);
    }

    @Test
    public void testCircularLookSchedulingPrintStatus() {
        CircularLookScheduling scheduling = new CircularLookScheduling(50, true, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);
        List<Integer> result = scheduling.execute(requests);

        // Print the final status
        System.out.println("Final CircularLookScheduling Position: " + scheduling.getCurrentPosition());
        System.out.println("CircularLookScheduling Moving Up: " + scheduling.isMovingUp());

        // Print the order of request processing
        System.out.println("Request Order: " + result);
    }
}
