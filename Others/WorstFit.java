package Others;

import java.util.ArrayList;

/** @author Dekas Dimitrios */
public class WorstFit {
  private static final int NO_ALLOCATION =
      -255; // if a process has been allocated in position -255,
  // it means that it has not been actually allocated.

  /**
   * Method to find the index of the memory block that is going to fit the given process based on
   * the worst fit algorithm.
   *
   * @param blocks: the array with the available memory blocks.
   * @param process: the size of the process.
   * @return the index of the block that fits, or -255 if no such block exists.
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
   * Method to allocate memory to blocks according to the worst fit algorithm. It should return an
   * ArrayList of Integers, where the index is the process ID (zero-indexed) and the value is the
   * block number (also zero-indexed).
   *
   * @param sizeOfBlocks: an int array that contains the sizes of the memory blocks available.
   * @param sizeOfProcesses: an int array that contains the sizes of the processes we need memory
   *     blocks for.
   * @return the ArrayList filled with Integers repressenting the memory allocation that took place.
   */
  static ArrayList<Integer> worstFit(int[] sizeOfBlocks, int[] sizeOfProcesses) {
    // The array list responsible for saving the memory allocations done by the worst-fit algorithm
    ArrayList<Integer> memAlloc = new ArrayList<>();
    // Do this for every process
    for (int processSize : sizeOfProcesses) {
      int chosenBlockIdx =
          findWorstFit(
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
   * @param memAllocation: an ArrayList of Integer representing the memory allocation done by the
   *     worstFit method.
   */
  public static void printMemoryAllocation(ArrayList<Integer> memAllocation) {
    System.out.println("Process No.\tBlock No.");
    System.out.println("===========\t=========");
    for (int i = 0; i < memAllocation.size(); i++) {
      System.out.print(" " + i + "\t\t");
      if (memAllocation.get(i) != NO_ALLOCATION) System.out.print(memAllocation.get(i));
      else System.out.print("Not Allocated");
      System.out.println();
    }
  }
}
