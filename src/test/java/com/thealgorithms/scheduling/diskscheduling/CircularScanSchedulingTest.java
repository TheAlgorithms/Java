package com.thealgorithms.scheduling.diskscheduling;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularScanSchedulingTest {

    @Test
    public void testCircularScanScheduling_MovingUp() {
        CircularScanScheduling circularScan = new CircularScanScheduling(50, true, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);
        List<Integer> expectedOrder = Arrays.asList(55, 58, 90, 150, 160, 18, 39);

        List<Integer> result = circularScan.execute(requests);
        assertEquals(expectedOrder, result);

        System.out.println("Final CircularScan Position: " + circularScan.getCurrentPosition());
        System.out.println("CircularScan Moving Up: " + circularScan.isMovingUp());
        System.out.println("Request Order: " + result);
    }

    @Test
    public void testCircularScanScheduling_MovingDown() {
        CircularScanScheduling circularScan = new CircularScanScheduling(50, false, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);
        List<Integer> expectedOrder = Arrays.asList(39, 18, 160, 150, 90, 58, 55);

        List<Integer> result = circularScan.execute(requests);
        assertEquals(expectedOrder, result);

        System.out.println("Final CircularScan Position: " + circularScan.getCurrentPosition());
        System.out.println("CircularScan Moving Down: " + circularScan.isMovingUp());
        System.out.println("Request Order: " + result);
    }

    @Test
    public void testCircularScanScheduling_EmptyRequests() {
        CircularScanScheduling circularScan = new CircularScanScheduling(50, true, 200);
        List<Integer> requests = Arrays.asList();
        List<Integer> expectedOrder = Arrays.asList();

        List<Integer> result = circularScan.execute(requests);
        assertEquals(expectedOrder, result);
    }
}
