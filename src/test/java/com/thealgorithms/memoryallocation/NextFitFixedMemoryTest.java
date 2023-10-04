package com.thealgorithms.memoryallocation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NextFitFixedMemoryTest {
    @Test
    public void testCaseNumber1() {
        int[] answer = {48, 59, 125};
        int[] memory_input = {25, 48, 59, 55, 47, 125, 236, 458, 659, 789};
        int[] processes_required_mem = {27, 28, 66};

        assertEquals(answer, NextFitFixedMemory.nextFitFixedMemory(3, 10, memory_input, processes_required_mem));
    }

    @Test
    public void testCaseNumber2() {
        int[] answer = {236};
        int[] memory_input = {25, 48, 59, 55, 47, 125, 236, 458, 659, 789};
        int[] processes_required_mem = {227};

        assertEquals(answer, NextFitFixedMemory.nextFitFixedMemory(1, 10, memory_input, processes_required_mem));
    }

    @Test
    public void testCaseNumber3() {
        int[] answer = {48, 59, 55, 47, 125};
        int[] memory_input = {25, 48, 59, 55, 47, 125, 236, 458, 659, 789};
        int[] processes_required_mem = {27, 28, 6, 16, 46};

        assertEquals(answer, NextFitFixedMemory.nextFitFixedMemory(5, 10, memory_input, processes_required_mem));
    }
}
