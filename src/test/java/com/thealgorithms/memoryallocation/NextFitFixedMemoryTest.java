package com.thealgorithms.memoryallocation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NextFitFixedMemoryTest {
    @Test
    public void testCaseNumber1() {
        int[] memory_input = {25, 48, 59, 55, 47, 125, 236, 458, 659, 789};
        int[] processes_required_mem = {27, 28, 66};

        assertEquals(3, NextFitFixedMemory.nextFitFixedMemory(3, 10, memory_input, processes_required_mem));
    }

    @Test
    public void testCaseNumber2() {
        int[] memory_input = {25, 48, 59, 55, 47, 125, 236, 458, 659, 789};
        int[] processes_required_mem = {227};

        assertEquals(1, NextFitFixedMemory.nextFitFixedMemory(1, 10, memory_input, processes_required_mem));
    }

    @Test
    public void testCaseNumber3() {
        int[] memory_input = {25, 48, 59, 55, 47, 125, 236, 458, 659, 789};
        int[] processes_required_mem = {27, 28, 6, 16, 46};

        assertEquals(5, NextFitFixedMemory.nextFitFixedMemory(5, 10, memory_input, processes_required_mem));
    }
}
