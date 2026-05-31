package com.thealgorithms.greedyalgorithms;

import java.util.Stack;

/**
 * The RobotsCollision class provides a method to find the earliest time
 * at which any two robots collide on a 1D line.
 *
 * Each robot has an initial position and a direction ('L' or 'R').
 * All robots move simultaneously at the same speed we can assume 1 unit.
 * A collision happens when an R-moving robot meets an L-moving robot.
 *
 */
public final class RobotsCollision {
    private RobotsCollision() {
    }
    /**
     * Finds the earliest time at which any two robots collide.
     *
     * @param n an integer representing the number of robots
     * @param positions an array of integers representing initial positions of robots
     * @param directions a string of 'L' and 'R' representing movement directions
     * @return the earliest collision time, or -1 if no collision occurs
     */

    /*
    *  R robot = moving right (increasing position)
    *  L robot = moving left (decreasing position)
     */
    public static int earliestCollisionTime(int n, int[] positions, String directions) {
        // stack for keeping the track of R moving robots waiting to collide
        Stack<Integer> stack = new Stack<>();

        int minTime = Integer.MAX_VALUE;
        boolean collisionFound = false;

        for(int i = 0; i < n; i++){
            char dir = directions.charAt(i);
            if (dir == 'R') {
                // moving right, save its position for later
                stack.push(positions[i]);
            } else {
                // moving left — if there's an R robot behind it, they'll collide
                if (!stack.isEmpty()) {
                    int rPosition = stack.pop();

                    // time = half the gap between them (both moving toward each other)
                    int time = (positions[i] - rPosition) / 2;
                    minTime = Math.min(minTime, time);
                    collisionFound = true;
                }
                // no R robot behind it, this one just moves left forever
            }
        }

        // if nothing collided at all, return -1
        return collisionFound ? minTime : -1;
    }
}
