package com.thealgorithms.others;
/**
 * @author Alexandros Lemonaris
 */

import java.util.ArrayList;

public abstract class CPUalgorithms {
    public static void main (String [] args){
        CPUalgorithms algorithm = new FirstFitCPU();
        int [] sizeOfBlocks = {15, 40, 10, 20};
        int [] sizesOfProcesses = {10, 40, 25, 30};
        ArrayList <Integer> memoryAllocation = algorithm.fitProcess(sizeOfBlocks, sizesOfProcesses);
        algorithm.printMemoryAllocation(memoryAllocation);
    }

    /**
     * Method to allocate memory to blocks according to CPU algorithms.
     * Use of inheritance to avoid repeated code.
     * Abstract method since it is implemented different for each algorithm.
     * It should return an ArrayList of Integers, where the index is the process
     * ID (zero-indexed) and the value is the block number (also zero-indexed).
     * @param sizeOfBlocks an int array that contains the sizes of the memory
     * blocks available.
     * @param sizeOfProcesses: an int array that contains the sizes of the
     * processes we need memory blocks for.
     * @return the ArrayList filled with Integers repressenting the memory
     * allocation that took place.
     */
    public abstract ArrayList<Integer> fitProcess(int[] sizeOfBlocks, int[] sizeOfProcesses);

    /**
     * Method to print the memory allocated.
     *
     * @param memAllocation: an ArrayList of Integer representing the memory
     * allocation done by the worstFit method.
     */
    public abstract void printMemoryAllocation(ArrayList<Integer> memAllocation);
}
/**
 * @author Dekas Dimitrios
 */
class BestFitCPU extends CPUalgorithms{

    private static final int NO_ALLOCATION
            = -255; // if a process has been allocated in position -255,
    // it means that it has not been actually allocated.

    /**
     * Method to find the maximum valued element of an array filled with
     * positive integers.
     *
     * @param array: an array filled with positive integers.
     * @return the maximum valued element of the array.
     */
    private static int findMaxElement(int[] array) {
        int max = -1;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Method to find the index of the memory block that is going to fit the
     * given process based on the best fit algorithm.
     *
     * @param blocks: the array with the available memory blocks.
     * @param process: the size of the process.
     * @return the index of the block that fits, or -255 if no such block
     * exists.
     */
    private static int findBestFit(int[] blockSizes, int processSize) {
        // Initialize minDiff with an unreachable value by a difference between a blockSize and the
        // processSize.
        int minDiff = findMaxElement(blockSizes);
        int index
                = NO_ALLOCATION; // If there is no block that can fit the process, return NO_ALLOCATION as the
        // result.
        for (int i = 0;
             i < blockSizes.length;
             i++) { // Find the most fitting memory block for the given process.
            if (blockSizes[i] - processSize < minDiff && blockSizes[i] - processSize >= 0) {
                minDiff = blockSizes[i] - processSize;
                index = i;
            }
        }
        return index;
    }

    /**
     * Method to allocate memory to blocks according to the best fit algorithm.
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
        // The array list responsible for saving the memory allocations done by the best-fit algorithm
        ArrayList<Integer> memAlloc = new ArrayList<>();
        // Do this for every process
        for (int processSize : sizeOfProcesses) {
            int chosenBlockIdx
                    = findBestFit(
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
     * allocation done by the bestFit method.
     */
    public void printMemoryAllocation(ArrayList<Integer> memAllocation) {
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

/**
 * @author Dekas Dimitrios
 */
class WorstFitCPU extends CPUalgorithms{

    private static final int NO_ALLOCATION
            = -255; // if a process has been allocated in position -255,
    // it means that it has not been actually allocated.

    /**
     * Method to find the index of the memory block that is going to fit the
     * given process based on the worst fit algorithm.
     *
     * @param blocks: the array with the available memory blocks.
     * @param process: the size of the process.
     * @return the index of the block that fits, or -255 if no such block
     * exists.
     */
    private static int findWorstFit(int[] blockSizes, int processSize) {
        int max = -1;
        int index = -1;
        for (int i = 0;
             i < blockSizes.length;
             i++) { // Find the index of the biggest memory block available.
            if (blockSizes[i] > max) {
                max = blockSizes[i];
                index = i;
            }
        }
        // If the biggest memory block cannot fit the process, return -255 as the result
        if (processSize > blockSizes[index]) {
            return NO_ALLOCATION;
        }
        return index;
    }

    /**
     * Method to allocate memory to blocks according to the worst fit algorithm.
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
        // The array list responsible for saving the memory allocations done by the worst-fit algorithm
        ArrayList<Integer> memAlloc = new ArrayList<>();
        // Do this for every process
        for (int processSize : sizeOfProcesses) {
            int chosenBlockIdx
                    = findWorstFit(
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
     * allocation done by the worstFit method.
     */
    public void printMemoryAllocation(ArrayList<Integer> memAllocation) {
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

/**
 * @author Dekas Dimitrios
 */
class FirstFitCPU extends CPUalgorithms{

    private static final int NO_ALLOCATION
            = -255; // if a process has been allocated in position -255,
    // it means that it has not been actually allocated.

    /**
     * Method to find the index of the memory block that is going to fit the
     * given process based on the first fit algorithm.
     *
     * @param blocks: the array with the available memory blocks.
     * @param process: the size of the process.
     * @return the index of the block that fits, or -255 if no such block
     * exists.
     */
    private static int findFirstFit(int[] blockSizes, int processSize) {
        for (int i = 0; i < blockSizes.length; i++) {
            if (blockSizes[i] >= processSize) {
                return i;
            }
        }
        // If there is not a block that can fit the process, return -255 as the result
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
                    = findFirstFit(
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
    public void printMemoryAllocation(ArrayList<Integer> memAllocation) {
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


