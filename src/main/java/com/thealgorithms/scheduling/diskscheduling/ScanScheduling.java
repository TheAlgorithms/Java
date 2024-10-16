package com.thealgorithms.scheduling.diskscheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Elevator_algorithm
 * SCAN Scheduling algorithm implementation.
 * The SCAN algorithm moves the disk arm towards one end of the disk, servicing all requests
 * along the way until it reaches the end. Once it reaches the end, it reverses direction
 * and services the requests on its way back.
 *
 * This algorithm ensures that all requests are serviced in a fair manner,
 * while minimizing the seek time for requests located close to the current position
 * of the disk arm.
 *
 * The SCAN algorithm is particularly useful in environments with a large number of
 * disk requests, as it reduces the overall movement of the disk arm compared to
 */
public class ScanScheduling {
    private int headPosition;
    private int diskSize;
    private boolean movingUp;

    public ScanScheduling(int headPosition, boolean movingUp, int diskSize) {
        this.headPosition = headPosition;
        this.movingUp = movingUp;
        this.diskSize = diskSize;
    }

    public List<Integer> execute(List<Integer> requests) {
        // If the request list is empty, return an empty result
        if (requests.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Separate requests into those smaller than the current head position and those larger
        for (int request : requests) {
            if (request < headPosition) {
                left.add(request);
            } else {
                right.add(request);
            }
        }

        // Sort the requests
        Collections.sort(left);
        Collections.sort(right);

        // Simulate the disk head movement
        if (movingUp) {
            // Head moving upward, process right-side requests first
            result.addAll(right);
            // After reaching the end of the disk, reverse direction and process left-side requests
            result.add(diskSize - 1); // Simulate the head reaching the end of the disk
            Collections.reverse(left);
            result.addAll(left);
        } else {
            // Head moving downward, process left-side requests first
            Collections.reverse(left);
            result.addAll(left);
            // After reaching the start of the disk, reverse direction and process right-side requests
            result.add(0); // Simulate the head reaching the start of the disk
            result.addAll(right);
        }

        return result;
    }

    public int getHeadPosition() {
        return headPosition;
    }

    public boolean isMovingUp() {
        return movingUp;
    }
}
