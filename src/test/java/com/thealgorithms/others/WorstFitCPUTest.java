package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * author Alexandros Lemonaris
 */
class WorstFitCPUTest {

    int[] sizeOfBlocks;
    int[] sizeOfProcesses;
    ArrayList<Integer> memAllocation = new ArrayList<>();
    ArrayList<Integer> testMemAllocation;
    MemoryManagementAlgorithms worstFit = new WorstFitCPU();

    @Test
    void testFitForUseOfOneBlock() {
        //test1
        sizeOfBlocks = new int[] { 5, 12, 17, 10 };
        sizeOfProcesses = new int[] { 10, 5, 15, 2 };
        memAllocation = worstFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(2, 1, -255, 3));
        assertEquals(testMemAllocation, memAllocation);
    }

    @Test
    void testFitForEqualProcecesses() {
        //test2
        sizeOfBlocks = new int[] { 5, 12, 17, 10 };
        sizeOfProcesses = new int[] { 10, 10, 10, 10 };
        memAllocation = worstFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(2, 1, 3, -255));
        assertEquals(testMemAllocation, memAllocation);
    }

    @Test
    void testFitForNoEmptyBlockCell() {
        //test3 - could suits best, bad use of memory allocation due to worstFit algorithm
        sizeOfBlocks = new int[] { 5, 12, 17 };
        sizeOfProcesses = new int[] { 5, 12, 10, 7 };
        memAllocation = worstFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(2, 1, 2, -255));
        assertEquals(testMemAllocation, memAllocation);
    }

    @Test
    void testFitForSameInputDifferentQuery() {
        //test4 same example different series - same results
        sizeOfBlocks = new int[] { 5, 12, 17 };
        sizeOfProcesses = new int[] { 5, 7, 10, 12 };
        memAllocation = worstFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(2, 1, 2, -255));
        assertEquals(testMemAllocation, memAllocation);
    }

    @Test
    void testFitForMoreBlocksNoFit() {
        //test5 for more blocks than processes
        sizeOfBlocks = new int[] { 5, 4, -1, 3, 6 };
        sizeOfProcesses = new int[] { 10, 11 };
        memAllocation = worstFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation = new ArrayList<>(Arrays.asList(-255, -255));
        assertEquals(testMemAllocation, memAllocation);
    }

    @Test
    void testFitBadCase() {
        //test6 for only two process fit
        sizeOfBlocks = new int[] { 7, 17, 7, 5, 6 };
        sizeOfProcesses = new int[] { 8, 10, 10, 8, 8, 8 };
        memAllocation = worstFit.fitProcess(sizeOfBlocks, sizeOfProcesses);
        testMemAllocation =
            new ArrayList<>(Arrays.asList(1, -255, -255, 1, -255, -255));
        assertEquals(testMemAllocation, memAllocation);
    }
}
