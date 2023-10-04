package com.thealgorithms.memoryallocation;

/*
 * This is a Java Implementation of Best Fit Memory Allocation Algorithm (Fixed Size Memory Blocks)
 * Best-Fit Allocation is a memory allocation technique used in operating systems to allocate
 * memory to a process. In Best-Fit, the operating system searches through the list of free
 * blocks of memory to find the block that is closest in size to the memory request from the
 * process.
 *
 *    Psuedo Code:
 *    Initialize a list of memory blocks with fixed sizes.
 *    Create a list of free memory blocks initially containing the entire memory.
 *    When a new process requests memory allocation:
 *        a. Iterate through the list of free memory blocks.
 *        b. Find the free memory block with the smallest size greater than or equal to the requested size.
 *        c. If a suitable free memory block is found:
 *            i. Allocate the memory block to the process.
 *            ii. Update the size of the memory block to reflect the allocated size.
 *            iii. Remove the allocated memory block from the list of free memory blocks.
 *            iv. Return the starting address of the allocated memory block to the process.
 *    If no suitable free memory block is found: indicate that memory allocation failed.
 * For more information:
 * https://www.geeksforgeeks.org/best-fit-allocation-in-operating-system/
 */

import java.util.Arrays;

public class BestFitFixedMemory {
    public static int[] bestFitFixedMemory(int no_of_processes, int no_of_mem_blocks, int[] memory_input, int[] processes_required_mem) {
        /*
         * no_of_processes : Number of processes.
         * no_of_mem_blocks : Number of empty memory blocks.
         * memory_input : Array of empty memory block size.
         * processes_required_mem : Array of required memory processes.
         */

        int[] memoryBestFit = new int[no_of_mem_blocks];

        for (int i = 0; i < no_of_mem_blocks; i++) {
            memoryBestFit[i] = memory_input[i];
        }

        int[] temp = Arrays.copyOf(memory_input, no_of_mem_blocks);
        Arrays.sort(temp);

        int[] outputBestFit = new int[no_of_processes];
        for (int i = 0; i < no_of_processes; i++) {
            outputBestFit[i] = -1; // Initialize to -1 indicating memory not allocated
            for (int j = 0; j < no_of_mem_blocks; j++) {
                if (temp[j] >= processes_required_mem[i]) {
                    outputBestFit[i] = temp[j];
                    temp[j] = -1;
                    break;
                }
            }
        }
        return outputBestFit;
    }
}
