package com.thealgorithms.scheduling.diskscheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Circular Scan Scheduling (C-SCAN) is a disk scheduling algorithm that
 * works by moving the disk arm in one direction to service requests until
 * it reaches the end of the disk. Once it reaches the end, instead of reversing
 * direction like in the SCAN algorithm, the arm moves back to the starting point
 * without servicing any requests. This ensures a more uniform wait time for all
 * requests, especially those near the disk edges. The algorithm then continues in
 * the same direction, making it effective for balancing service time across all disk sectors.
 */
public class CircularScanScheduling {
    private int currentPosition;
    private boolean movingUp;
    private final int diskSize;

    public CircularScanScheduling(int startPosition, boolean movingUp, int diskSize) {
        this.currentPosition = startPosition;
        this.movingUp = movingUp;
        this.diskSize = diskSize;
    }

    public List<Integer> execute(List<Integer> requests) {
        if (requests.isEmpty()) {
            return new ArrayList<>(); // Return empty list if there are no requests
        }

        List<Integer> sortedRequests = new ArrayList<>(requests);
        Collections.sort(sortedRequests);

        List<Integer> result = new ArrayList<>();

        if (movingUp) {
            // Moving up: process requests >= current position
            for (int request : sortedRequests) {
                if (request >= currentPosition && request < diskSize) {
                    result.add(request);
                }
            }

            // Jump to the smallest request and continue processing from the start
            for (int request : sortedRequests) {
                if (request < currentPosition) {
                    result.add(request);
                }
            }
        } else {
            // Moving down: process requests <= current position in reverse order
            for (int i = sortedRequests.size() - 1; i >= 0; i--) {
                int request = sortedRequests.get(i);
                if (request <= currentPosition) {
                    result.add(request);
                }
            }

            // Jump to the largest request and continue processing in reverse order
            for (int i = sortedRequests.size() - 1; i >= 0; i--) {
                int request = sortedRequests.get(i);
                if (request > currentPosition) {
                    result.add(request);
                }
            }
        }

        // Set final position to the last request processed
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
