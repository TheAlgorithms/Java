package com.thealgorithms.scheduling.diskscheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Circular Look Scheduling (C-LOOK) is a disk scheduling algorithm similar to
 * the C-SCAN algorithm but with a key difference. In C-LOOK, the disk arm also
 * moves in one direction to service requests, but instead of going all the way
 * to the end of the disk, it only goes as far as the furthest request in the
 * current direction. After servicing the last request in the current direction,
 * the arm immediately jumps back to the closest request on the other side without
 * moving to the disk's extreme ends. This reduces the unnecessary movement of the
 * disk arm, resulting in better performance compared to C-SCAN, while still
 * maintaining fair wait times for requests.
 */
public class CircularLookScheduling {
    private int currentPosition;
    private boolean movingUp;
    private final int maxCylinder;

    public CircularLookScheduling(int startPosition, boolean movingUp, int maxCylinder) {
        this.currentPosition = startPosition;
        this.movingUp = movingUp;
        this.maxCylinder = maxCylinder;
    }

    public List<Integer> execute(List<Integer> requests) {
        List<Integer> result = new ArrayList<>();

        // Filter and sort valid requests in both directions
        List<Integer> upRequests = new ArrayList<>();
        List<Integer> downRequests = new ArrayList<>();

        for (int request : requests) {
            if (request >= 0 && request < maxCylinder) {
                if (request > currentPosition) {
                    upRequests.add(request);
                } else if (request < currentPosition) {
                    downRequests.add(request);
                }
            }
        }

        Collections.sort(upRequests);
        Collections.sort(downRequests);

        if (movingUp) {
            // Process all requests in the upward direction
            result.addAll(upRequests);

            // Jump to the lowest request and process all requests in the downward direction
            result.addAll(downRequests);
        } else {
            // Process all requests in the downward direction (in reverse order)
            Collections.reverse(downRequests);
            result.addAll(downRequests);

            // Jump to the highest request and process all requests in the upward direction (in reverse order)
            Collections.reverse(upRequests);
            result.addAll(upRequests);
        }

        // Update current position to the last processed request
        if (!result.isEmpty()) {
            currentPosition = result.get(result.size() - 1);
        }

        return result;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public boolean isMovingUp() {
        return movingUp;
    }
}
