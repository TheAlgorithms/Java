package com.thealgorithms.others;

import java.util.ArrayList;

/**
 * @author Alexandros Lemonaris
 */
public class NextFit {

    private static final int NO_ALLOCATION
            = -255; // if a process has been allocated in position -255,
    // it means that it has not been actually allocated.
    private int counter = 0; // variable that keeps the position of the last registration into the memory
    /**
     * Method to find the index of the memory block that is going to fit the
     * given process based on the next fit algorithm. In the case of next fit,
     * if the search is interrupted in between, the new search is carried out from the last location.
     *
     * @param blocks: the array with the available memory blocks.
     * @param process: the size of the process.
     * @return the index of the block that fits, or -255 if no such block
     * exists.
     */
    private int findNextFit(int[] blockSizes, int processSize) {

        for (int i = 0; i < blockSizes.length; i++) {
            if (counter + i >= blockSizes.length){
                counter = -i; // starts from the start of the array
            }
            if (blockSizes[i + counter] >= processSize) {
                counter += i;
                return counter;
            }
        }
        // If there is not a block that can fit the process, return -255 as the result
        counter += blockSizes.length; // counter keeps its last value
        return NO_ALLOCATION;
    }

    /**
     * Method to allocate memory to blocks according to the first fit algorithm.
     * It should return an ArrayList of Integers, where the index is the process
     * ID (zero-indexed) and the value is the block number (also zero-indexed).
     *
     * @param sizeOfBlocks: an int array that contains the sizes of the memory
     * blocks available.
     * @param sizeOfProcesses: an int array that contains the sizes of the
     * processes we need memory blocks for.
     * @return the ArrayList filled with Integers repressenting the memory
     * allocation that took place.
     */
    public ArrayList<Integer> fitProcess(int[] sizeOfBlocks, int[] sizeOfProcesses) {
        // The array list responsible for saving the memory allocations done by the first-fit algorithm
        ArrayList<Integer> memAlloc = new ArrayList<>();
        // Do this for every process
        for (int processSize : sizeOfProcesses) {
            int chosenBlockIdx
                    = findNextFit(
                    sizeOfBlocks, processSize); // Find the index of the memory block going to be used
            memAlloc.add(chosenBlockIdx); // Store the chosen block index in the memAlloc array list
            if (chosenBlockIdx
                    != NO_ALLOCATION) { // Only if a block was chosen to store the process in it,
                sizeOfBlocks[chosenBlockIdx] -= processSize; // resize the block based on the process size
            }
        }
        return memAlloc;
    }

    /**
     * Method to print the memory allocated.
     *
     * @param memAllocation: an ArrayList of Integer representing the memory
     * allocation done by the firstFit method.
     */
    public static void printMemoryAllocation(ArrayList<Integer> memAllocation) {
        System.out.println("Process No.\tBlock No.");
        System.out.println("===========\t=========");
        for (int i = 0; i < memAllocation.size(); i++) {
            System.out.print(" " + i + "\t\t\t");
            if (memAllocation.get(i) != NO_ALLOCATION) {
                System.out.print(memAllocation.get(i));
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println();
        }
    }
}
