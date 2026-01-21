package com.thealgorithms.recursion;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TowerOfHanoiTest {

    /**
     * Test Case 1: Base Case (n = 1)
     * Requirement: Explicitly requested.
     * Logic: With only 1 disk, it should move directly from Source (A) to Destination (C).
     */
    @Test
    public void testBaseCase() {
        List<String> result = TowerOfHanoi.solveTowerOfHanoi(1, 'A', 'C', 'B');
        
        // Assertion 1: Check size (2^1 - 1 = 1 move)
        assertEquals(1, result.size(), "Should have exactly 1 move for 1 disk");
        
        // Assertion 2: Verify the exact string
        assertEquals("Move disk 1 from rod A to rod C", result.get(0));
    }

    /**
     * Test Case 2: Small Recursion (n = 2)
     * Logic: Tests the basic recursive step (Move 1 to Aux, Move 2 to Dest, Move 1 to Dest).
     */
    @Test
    public void testSmallRecursion() {
        List<String> result = TowerOfHanoi.solveTowerOfHanoi(2, 'A', 'C', 'B');
        
        // Assertion 1: Check size (2^2 - 1 = 3 moves)
        assertEquals(3, result.size());
        
        // Assertion 2: Verify the exact sequence of moves
        List<String> expected = Arrays.asList("Move disk 1 from rod A to rod B", // Small disk to Aux
            "Move disk 2 from rod A to rod C", // Big disk to Dest
            "Move disk 1 from rod B to rod C" // Small disk to Dest
        );
        
        assertEquals(expected, result, "Sequence of moves for 2 disks is incorrect");
    }

    /**
     * Test Case 3: Standard Case (n = 3)
     * Logic: Verifies a slightly larger recursion to ensure the stack depth works correctly.
     */
    @Test
    public void testStandardCase() {
        List<String> result = TowerOfHanoi.solveTowerOfHanoi(3, 'A', 'C', 'B');
        
        // Assertion 1: Check size (2^3 - 1 = 7 moves)
        assertEquals(7, result.size());
        
        // Assertion 2: Verify start and end moves specifically
        assertEquals("Move disk 1 from rod A to rod C", result.get(0)); // First move
        assertEquals("Move disk 1 from rod A to rod C", result.get(6)); // Last move
    }

    /**
     * Extra Test Case: Negative Input
     * Logic: Ensures your "Defensive Programming" check works.
     */
    @Test
    public void testNegativeInput() {
         assertThrows(IllegalArgumentException.class, () -> { TowerOfHanoi.solveTowerOfHanoi(-5, 'A', 'C', 'B'); }, 
"Should throw exception for negative disks");
    }
}