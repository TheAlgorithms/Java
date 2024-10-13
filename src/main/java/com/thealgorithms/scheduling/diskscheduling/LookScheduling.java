package com.thealgorithms.scheduling.diskscheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://en.wikipedia.org/wiki/LOOK_algorithm
 * Look Scheduling algorithm implementation.
 * The Look algorithm moves the disk arm to the closest request in the current direction,
 * and once it processes all requests in that direction, it reverses the direction.
 */
public class LookScheduling {
    private final int maxTrack;
    private final int currentPosition;
    private boolean movingUp;
    private int farthestPosition;
    public LookScheduling(int startPosition, boolean initialDirection, int maxTrack) {
        this.currentPosition = startPosition;
        this.movingUp = initialDirection;
        this.maxTrack = maxTrack;
    }

    /**
     * Executes the Look Scheduling algorithm on the given list of requests.
     *
     * @param requests List of disk requests.
     * @return Order in which requests are processed.
     */
    public List<Integer> execute(List<Integer> requests) {
        List<Integer> result = new ArrayList<>();
        List<Integer> lower = new ArrayList<>();
        List<Integer> upper = new ArrayList<>();

        // Split requests into two lists based on their position relative to current position
        for (int request : requests) {
            if (request >= 0 && request < maxTrack) {
                if (request < currentPosition) {
                    lower.add(request);
                } else {
                    upper.add(request);
                }
            }
        }

        // Sort the requests
        Collections.sort(lower);
        Collections.sort(upper);

        // Process the requests depending on the initial moving direction
        if (movingUp) {
            // Process requests in the upward direction
            result.addAll(upper);
            if (!upper.isEmpty()) {
                farthestPosition = upper.get(upper.size() - 1);
            }

            // Reverse the direction and process downward
            movingUp = false;
            Collections.reverse(lower);
            result.addAll(lower);
            if (!lower.isEmpty()) {
                farthestPosition = Math.max(farthestPosition, lower.get(0));
            }
        } else {
            // Process requests in the downward direction
            Collections.reverse(lower);
            result.addAll(lower);
            if (!lower.isEmpty()) {
                farthestPosition = lower.get(0);
            }

            // Reverse the direction and process upward
            movingUp = true;
            result.addAll(upper);
            if (!upper.isEmpty()) {
                farthestPosition = Math.max(farthestPosition, upper.get(upper.size() - 1));
            }
        }

        return result;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public int getFarthestPosition() {
        return farthestPosition;
    }
}
