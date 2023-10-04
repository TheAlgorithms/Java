package com.thealgorithms.memoryallocation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BestFitFixedMemoryTest {
    @Test
    public void testCaseNumber1() {
        int[] memory_input = {25, 48, 59, 55, 47, 125, 236, 458, 659, 789};
        int[] processes_required_mem = {27, 28, 66};
        assertEquals(3, BestFitFixedMemory.bestFitFixedMemory(3, 10, memory_input, processes_required_mem));
    }

    @Test
    public void testCaseNumber2() {
        int[] memory_input = {25, 48, 59, 55, 47, 125, 236, 458, 659, 789};
        int[] processes_required_mem = {227};

        assertEquals(1, BestFitFixedMemory.bestFitFixedMemory(1, 10, memory_input, processes_required_mem));
    }

    @Test
    public void testCaseNumber3() {
        int[] memory_input = {25, 48, 59, 55, 47, 125, 236, 458, 659, 789};
        int[] processes_required_mem = {27, 28, 966};

        assertEquals(3, BestFitFixedMemory.bestFitFixedMemory(3, 10, memory_input, processes_required_mem));
    }

    @Test
    public void testCaseNumber4() {
        int[] memory_input = {25, 25, 25, 25, 10, 10, 20, 10, 10, 10};
        int[] processes_required_mem = {27, 28, 6};

        assertEquals(3, BestFitFixedMemory.bestFitFixedMemory(3, 10, memory_input, processes_required_mem));
    }
}
