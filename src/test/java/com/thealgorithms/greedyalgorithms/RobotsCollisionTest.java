package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RobotsCollisionTest {

    // basic example — two pairs colliding, find the earliest
    @Test
    void testBasicCollision() {
        int[] positions = {3, 5, 8, 10};
        String directions = "RLRL";
        assertEquals(1, RobotsCollision.earliestCollisionTime(4, positions, directions));
    }

    // no collision at all — all moving in same direction
    @Test
    void testNoCollision() {
        int[] positions = {1, 3, 5, 7};
        String directions = "RRRR";
        assertEquals(-1, RobotsCollision.earliestCollisionTime(4, positions, directions));
    }

    // all moving left — no one to crash into
    @Test
    void testAllMovingLeft() {
        int[] positions = {2, 5, 8};
        String directions = "LLL";
        assertEquals(-1, RobotsCollision.earliestCollisionTime(3, positions, directions));
    }

    // just two robots heading toward each other
    @Test
    void testTwoRobotsCollide() {
        int[] positions = {1, 5};
        String directions = "RL";
        // gap is 4, time = 4/2 = 2
        assertEquals(2, RobotsCollision.earliestCollisionTime(2, positions, directions));
    }

    // only one robot — can't collide with anyone
    @Test
    void testSingleRobot() {
        int[] positions = {4};
        String directions = "R";
        assertEquals(-1, RobotsCollision.earliestCollisionTime(1, positions, directions));
    }

    // multiple cases in one shot using parameterized test
    @ParameterizedTest
    @CsvSource({
        "2, '1,3', 'RL', 1",   // gap of 2 → time 1
        "2, '0,10', 'RL', 5",  // gap of 10 → time 5
        "2, '5,5', 'RL', 0"    // same spot → already colliding
    })
    void testVariousCollisionTimes(int n, String posStr, String directions, int expected) {
        // parse the position string into an int array
        String[] parts = posStr.split(",");
        int[] positions = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            positions[i] = Integer.parseInt(parts[i].trim());
        }
        assertEquals(expected, RobotsCollision.earliestCollisionTime(n, positions, directions));
    }
}