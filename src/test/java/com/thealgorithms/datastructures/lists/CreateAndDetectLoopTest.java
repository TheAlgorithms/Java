package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateAndDetectLoopTest {

    private CreateAndDetectLoop.Node head;

    @BeforeEach
    void setUp() {
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        head = new CreateAndDetectLoop.Node(1);
        CreateAndDetectLoop.Node second = new CreateAndDetectLoop.Node(2);
        CreateAndDetectLoop.Node third = new CreateAndDetectLoop.Node(3);
        CreateAndDetectLoop.Node fourth = new CreateAndDetectLoop.Node(4);
        CreateAndDetectLoop.Node fifth = new CreateAndDetectLoop.Node(5);
        CreateAndDetectLoop.Node sixth = new CreateAndDetectLoop.Node(6);

        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
    }

    @Test
    void testDetectLoop_NoLoop() {
        // Test when no loop exists
        assertFalse(CreateAndDetectLoop.detectLoop(head), "There should be no loop.");
    }

    @Test
    void testCreateAndDetectLoop_LoopExists() {
        // Create a loop between position 2 (node with value 2) and position 5 (node with value 5)
        CreateAndDetectLoop.createLoop(head, 2, 5);

        // Now test if the loop is detected
        assertTrue(CreateAndDetectLoop.detectLoop(head), "A loop should be detected.");
    }

    @Test
    void testCreateLoop_InvalidPosition() {
        // Create loop with invalid positions
        CreateAndDetectLoop.createLoop(head, 0, 0);

        // Ensure no loop was created
        assertFalse(CreateAndDetectLoop.detectLoop(head), "There should be no loop with invalid positions.");
    }

    @Test
    void testCreateLoop_SelfLoop() {
        // Create a self-loop at position 3 (node with value 3)
        CreateAndDetectLoop.createLoop(head, 3, 3);

        // Test if the self-loop is detected
        assertTrue(CreateAndDetectLoop.detectLoop(head), "A self-loop should be detected.");
    }

    @Test
    void testCreateLoop_NoChangeForNonExistentPositions() {
        // Create a loop with positions that don't exist in the linked list
        CreateAndDetectLoop.createLoop(head, 10, 20);

        // Ensure no loop was created
        assertFalse(CreateAndDetectLoop.detectLoop(head), "No loop should be created if positions are out of bounds.");
    }
}

