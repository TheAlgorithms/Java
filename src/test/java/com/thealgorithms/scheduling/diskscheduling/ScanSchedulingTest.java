package com.thealgorithms.scheduling.diskscheduling;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ScanSchedulingTest {

    @Test
    public void testScanSchedulingMovingUp() {
        ScanScheduling scanScheduling = new ScanScheduling(50, true, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);
        List<Integer> expected = Arrays.asList(55, 58, 90, 150, 160, 199, 39, 18);

        List<Integer> result = scanScheduling.execute(requests);
        assertEquals(expected, result);
    }

    @Test
    public void testScanSchedulingMovingDown() {
        ScanScheduling scanScheduling = new ScanScheduling(50, false, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);
        List<Integer> expected = Arrays.asList(39, 18, 0, 55, 58, 90, 150, 160);

        List<Integer> result = scanScheduling.execute(requests);
        assertEquals(expected, result);
    }

    @Test
    public void testScanSchedulingEmptyRequests() {
        ScanScheduling scanScheduling = new ScanScheduling(50, true, 200);
        List<Integer> requests = emptyList();
        List<Integer> expected = emptyList();

        List<Integer> result = scanScheduling.execute(requests);
        assertEquals(expected, result);
    }

    @Test
    public void testScanScheduling() {
        ScanScheduling scanScheduling = new ScanScheduling(50, true, 200);
        List<Integer> requests = Arrays.asList(55, 58, 39, 18, 90, 160, 150);

        List<Integer> result = scanScheduling.execute(requests);
        List<Integer> expectedOrder = Arrays.asList(55, 58, 90, 150, 160, 199, 39, 18);
        assertEquals(expectedOrder, result);

        System.out.println("Final Head Position: " + scanScheduling.getHeadPosition());
        System.out.println("Head Moving Up: " + scanScheduling.isMovingUp());
        System.out.println("Request Order: " + result);
    }
}
