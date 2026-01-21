package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TowerOfHanoiTest {

    @Test
    public void testBaseCase() {
        List<String> result = TowerOfHanoi.solveTowerOfHanoi(1, 'A', 'C', 'B');
        assertEquals(1, result.size(), "Should have exactly 1 move for 1 disk");
        assertEquals("Move disk 1 from rod A to rod C", result.get(0));
    }

    @Test
    public void testSmallRecursion() {
        List<String> result = TowerOfHanoi.solveTowerOfHanoi(2, 'A', 'C', 'B');
        assertEquals(3, result.size());
        List<String> expected = Arrays.asList(
            "Move disk 1 from rod A to rod B",
            "Move disk 2 from rod A to rod C",
            "Move disk 1 from rod B to rod C"
        );
        assertEquals(expected, result, "Sequence of moves for 2 disks is incorrect");
    }

    @Test
    public void testStandardCase() {
        List<String> result = TowerOfHanoi.solveTowerOfHanoi(3, 'A', 'C', 'B');
        assertEquals(7, result.size());
        assertEquals("Move disk 1 from rod A to rod C", result.get(0));
        assertEquals("Move disk 1 from rod A to rod C", result.get(6));
    }

    @Test
    public void testNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> TowerOfHanoi.solveTowerOfHanoi(-5, 'A', 'C', 'B'), "Should throw exception for negative disks");
    }
}