package com.thealgorithms.scheduling.diskscheduling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *https://en.wikipedia.org/wiki/Shortest_seek_first
 * Shortest Seek First (SFF) Scheduling algorithm implementation.
 * The SFF algorithm selects the next request to be serviced based on the shortest distance
 * from the current position of the disk arm. It continuously evaluates all pending requests
 * and chooses the one that requires the least amount of movement to service.
 *
 * This approach minimizes the average seek time, making it efficient in terms of response
 * time for individual requests. However, it may lead to starvation for requests located
 * further away from the current position of the disk arm.
 *
 * The SFF algorithm is particularly effective in systems where quick response time
 * is crucial, as it ensures that the most accessible requests are prioritized for servicing.
 */
public class SSFScheduling {
    private int currentPosition;

    public SSFScheduling(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public List<Integer> execute(Collection<Integer> requests) {
        List<Integer> result = new ArrayList<>(requests);
        List<Integer> orderedRequests = new ArrayList<>();

        while (!result.isEmpty()) {
            int closest = findClosest(result);
            orderedRequests.add(closest);
            result.remove(Integer.valueOf(closest));
            currentPosition = closest;
        }
        return orderedRequests;
    }

    private int findClosest(List<Integer> requests) {
        int minDistance = Integer.MAX_VALUE;
        int closest = -1;
        for (int request : requests) {
            int distance = Math.abs(currentPosition - request);
            if (distance < minDistance) {
                minDistance = distance;
                closest = request;
            }
        }
        return closest;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
