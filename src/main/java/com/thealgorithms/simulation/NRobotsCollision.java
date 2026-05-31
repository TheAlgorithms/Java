package com.thealgorithms.simulation;

/**
 * NRobotsCollision simulates the movement of N robots on a 1D line and determines
 * the earliest time at which any two robots collide.
 *
 * <p>Each robot has an initial integer position and a direction of movement
 * ('R' for right, 'L' for left). All robots move simultaneously at the same speed.
 * A collision occurs when a right-moving robot and a left-moving robot meet.
 * The time of collision between two adjacent (in sorted order) robots moving toward
 * each other is half the distance between them.
 *
 * <p>If no two robots ever collide, the method returns -1.
 */
public class NRobotsCollision {
    /**
     * Pair holds a robot's position and its direction of movement.
     */
    static class Pair {
        int first;
        char second;
        Pair(int first, char second) {
            this.first = first;
            this.second = second;
        }
    }
    /**
     * Finds the earliest time at which any two robots collide.
     *
     * <p>Robots are sorted by position. A collision can only happen between a robot
     * moving right ('R') and the next robot (in sorted order) moving left ('L').
     * The collision time for such a pair is {@code (posL - posR) / 2}.
     *
     * @param n          the number of robots
     * @param positions  an array of initial positions of the robots
     * @param directions a string where each character is 'L' or 'R', representing
     *                   the direction of the corresponding robot
     * @return the earliest collision time, or -1 if no collision occurs
     */
    public static int earliestCollisionTime(int n, int[] positions, String directions) {
        List<Pair> sortedDirections = new ArrayList<>();
        for(int i = 0; i < n; i++){
            sortedDirections.add(new Pair(positions[i], directions.charAt(i)));
        }
        sortedDirections.sort((a, b) -> Integer.compare(a.first, b.first));
        int minTime = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++){
            if(sortedDirections.get(i).second == 'L' && sortedDirections.get(i - 1).second == 'R'){
                minTime = Math.min(minTime, sortedDirections.get(i).first - sortedDirections.get(i - 1).first);
            }
        }
        return minTime == Integer.MAX_VALUE ? -1 : minTime / 2;
    }
}