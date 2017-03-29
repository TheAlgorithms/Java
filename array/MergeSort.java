package array;

import array.security.ProjectAlgorithmsScanner;

/**
 * Merge Sort
 */
public class MergeSort {
    private int[] array;
    private int[] tempMergeArr;

    /**
     * Sorts {@code inputArr} with merge sort algorithm.
     *
     * @param inputArr
     */
    public final void sort(int inputArr[]) {
        this.array = inputArr;
        int length = inputArr.length;
        this.tempMergeArr = new int[length];
        this.mergeSort(0, length - 1);
    }

    /**
     * Partitions Array into recursively smaller pieces.
     *
     * @param lowerIndex  lower bound to include in the first partition
     * @param higherIndex upper bound to include in the third partition
     */
    private void mergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            this.mergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            this.mergeSort(middle + 1, higherIndex);
            // Now merge both sides
            this.mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    /**
     * Merges partitions.
     *
     * @param lowerIndex
     * @param middle
     * @param higherIndex
     */
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            this.tempMergeArr[i] = this.array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (this.tempMergeArr[i] <= this.tempMergeArr[j]) {
                this.array[k] = this.tempMergeArr[i];
                i++;
            } else {
                this.array[k] = this.tempMergeArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            this.array[k] = this.tempMergeArr[i];
            k++;
            i++;
        }

    }

    /**
     * Gets input to sort.
     *
     * @return unsorted array of integers to sort
     */
    public static int[] getInput() {
        final int numElements = 6;
        int[] unsorted = new int[numElements];
        System.out.println("Enter any 6 Numbers for Unsorted Array : ");
        for (int i = 0; i < numElements; i++) {
            unsorted[i] = ProjectAlgorithmsScanner.getInteger();
        }
        return unsorted;
    }

    /**
     * Main Method.
     *
     * @param args
     */
    public static void main(String args[]) {
        int[] inputArr = getInput();
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(inputArr);
        for (int i : inputArr) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
