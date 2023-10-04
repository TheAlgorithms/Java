package com.thealgorithms.memoryallocation;

/*
 * This is an implementation of Next Fit Memory Allocation Algorithm (Fixed Size
 * Memory Blocks) in Java
 *
 * Algorithm flow :
 *
 * 1. Input the number of memory blocks and their sizes and initializes all the
 * blocks as free.
 * 2. Input the number of processes and their sizes.
 * 3. Start by picking each process and check if it can be assigned to the
 * current
 * block, if yes, allocate the required memory and check for next process but
 * from the block where we left not from starting.
 * 4. If the current block size is smaller then keep checking the further
 * blocks.
 * For more information::
 * https://www.geeksforgeeks.org/program-for-next-fit-algorithm-in-memory-management/
 */
public class NextFitFixedMemory {
    public static int[] nextFitFixedMemory(int no_of_processes, int no_of_mem_blocks, int[] memory_input, int[] processes_required_mem) {
        /*
         * no_of_processes : Number of processes.
         * no_of_mem_blocks : Number of empty memory blocks.
         * memory_input : Array of empty memory block size.
         * processes_required_mem : Array of required memory processes.
         */
        int[] memoryNextFit = new int[no_of_mem_blocks];

        System.arraycopy(memory_input, 0, memoryNextFit, 0, no_of_mem_blocks);

        int[] outputNextFit = new int[no_of_processes];
        for (int i = 0; i < no_of_processes; i++) {
            outputNextFit[i] = -1; // Initialize to -1 indicating memory not allocated
        }
        int j = 0; // Start with the first memory block

        for (int i = 0; i < no_of_processes; i++) {
            while (true) {
                if (processes_required_mem[i] <= memoryNextFit[j]) {
                    outputNextFit[i] = memoryNextFit[j];
                    memoryNextFit[j] = -1;
                    j = (j + 1) % no_of_mem_blocks; // Move to the next memory block
                    break;
                }
                j = (j + 1) % no_of_mem_blocks;
            }
        }
        return outputNextFit;
    }
}
