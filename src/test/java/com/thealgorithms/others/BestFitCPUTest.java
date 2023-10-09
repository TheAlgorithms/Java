package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * author Alexandros Lemonaris
 */
class BestFitCPUTest {

    int[] sizeOfBlocks;
    int[] sizeOfProcesses;
    ArrayList<Integer> memAllocation = new ArrayList<>();
    ArrayList<Integer> testMemAllocation;
    MemoryManagementAlgorithms bestFit = new BestFitCPU();

    @Test
    void testFitForUseOfOneBlock() {
        // test1 - 2 processes shall fit to one block instead of using a different block each
        sizeOfBlocks = new int[] {5, 12, 17, 10};
        sizeOfProcesses = new int[] {10, 5, 15, 2};
        memAllocation = bestFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(3, 0, 2, 2));
        assertEquals(testMemAllocation, memAllocation);
    }

    @Test
    void testFitForEqualProcecesses() {
        // test2
        sizeOfBlocks = new int[] {5, 12, 17, 10};
        sizeOfProcesses = new int[] {10, 10, 10, 10};
        memAllocation = bestFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(3, 1, 2, -255));
        assertEquals(testMemAllocation, memAllocation);
    }

    @Test
    void testFitForNoEmptyBlockCell() {
        // test3 for more processes than blocks - no empty space left to none of the blocks
        sizeOfBlocks = new int[] {5, 12, 17};
        sizeOfProcesses = new int[] {5, 12, 10, 7};
        memAllocation = bestFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(0, 1, 2, 2));
        assertEquals(testMemAllocation, memAllocation);
    }

    @Test
    void testFitForSameInputDifferentQuery() {
        // test4 for more processes than blocks - one element does not fit due to input series
        sizeOfBlocks = new int[] {5, 12, 17};
        sizeOfProcesses = new int[] {5, 7, 10, 12};
        memAllocation = bestFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(0, 1, 2, -255));
        assertEquals(testMemAllocation, memAllocation);
    }

    @Test
    void testFitForMoreBlocksNoFit() {
        // test5 for more blocks than processes
        sizeOfBlocks = new int[] {5, 4, -1, 3, 6};
        sizeOfProcesses = new int[] {10, 11};
        memAllocation = bestFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(-255, -255));
        assertEquals(testMemAllocation, memAllocation);
    }
}
