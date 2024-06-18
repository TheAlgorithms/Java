package com.thealgorithms.sorts;

import java.util.ArrayList;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Smoothsort
 */
public final class SmoothSort {

    private SmoothSort() {
    }

    public static Integer[] getLeonardoNumbers() {
        Integer[] leonardoNumbers = {1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529, 21891, 35421, 57313, 92735, 150049, 242785, 392835, 635621, 1028457, 1664079, 2692537, 4356617, 7049155, 1405773, 18454929, 29860703, 48315633, 78176337, 126491971,
            204668309, 331160281, 535828591};

        return leonardoNumbers;
    }

    public static Integer[] smoothSort(Integer[] array) {
        int length = array.length;
        int leonardoHeapSize = 0; // start with size 0
        int leonardoLevels = 0; // No leonardo tree present initially

        while (leonardoHeapSize < length) {
            // if two trees with consequtive level
            // combine them to get new tree
            // else if there is no Level 1, add the node as level 1
            // else add the node as Level 0
            // perform shiftRoot to restore heap property

            Integer[] consecutiveTreeIndices = findConsecutiveLeonardoTrees(leonardoLevels);
            if (consecutiveTreeIndices[0] != -1) {
                // if 0th or 1st index is -1 that implies there are no concequtive trees
                leonardoLevels = leonardoLevels & ~(1 << consecutiveTreeIndices[0]);
                leonardoLevels = leonardoLevels & ~(1 << consecutiveTreeIndices[1]);
                leonardoLevels = leonardoLevels | (1 << consecutiveTreeIndices[1] + 1);
            } else if (((leonardoLevels & 2) == 0)) {
                leonardoLevels = leonardoLevels | (1 << 1);
            } else {
                leonardoLevels = leonardoLevels | (1 << 0);
            }

            leonardoHeapSize++;
            array = shiftRoot(leonardoLevels, leonardoHeapSize, array);
        }

        // Now our Leonardo heap is fully ready, start extracting the max
        while (leonardoHeapSize > 0) {
            // destroy the current level
            // if level is not L1 or L0
            // create two smaller sublevels
            // perform shiftRoot to restore heap property

            int lastTreeLevel = getRightMostTree(leonardoLevels); // getting the right most tree

            leonardoLevels = leonardoLevels & ~(1 << lastTreeLevel);
            if (lastTreeLevel != 0 && lastTreeLevel != 1) {
                leonardoLevels = leonardoLevels | (1 << lastTreeLevel - 1);
                leonardoLevels = leonardoLevels | (1 << lastTreeLevel - 2);
            }

            leonardoHeapSize--;
            array = shiftRoot(leonardoLevels, leonardoHeapSize, array);
        }

        return array;
    }

    public static int getRightMostTree(int leonardoLevels) {
        // Isolate the rightmost set bit
        int isolatedBit = leonardoLevels & -leonardoLevels;
        int position = 0;

        while (isolatedBit > 1) {
            isolatedBit >>= 1;
            position++;
        }

        return position;
    }

    public static Integer[] findConsecutiveLeonardoTrees(int num) {
        int prevOneIndex = -1;
        int currentBit;

        Integer[] answer = new Integer[] {-1, -1};
        for (int i = 0; num > 0; i++) {
            currentBit = num & 1;
            if (currentBit == 1) {
                if (prevOneIndex != -1) {
                    answer[0] = prevOneIndex;
                    answer[1] = i;
                }
                prevOneIndex = i;
            } else {
                prevOneIndex = -1;
            }
            num >>>= 1;
        }
        return answer;
    }

    public static Integer[] findAllLeonardoTrees(int num) {
        int setBitCount = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            if ((num & (1 << i)) != 0) {
                setBitCount++;
            }
        }

        Integer[] setBitIndexes = new Integer[setBitCount];
        int index = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            if ((num & (1 << i)) != 0) {
                setBitIndexes[index++] = i;
            }
        }
        return setBitIndexes;
    }

    public static Integer[] shiftRoot(int lenardoLevels, int leonardoHeapSize, Integer[] array) {
        if (leonardoHeapSize == 0) {
            return array;
        }
        Integer[] currentLeonardoTrees = findAllLeonardoTrees(lenardoLevels);
        Integer[] leonardoNumbers = getLeonardoNumbers();
        int prevTreeSizeCumulative = 0;
        ArrayList<Integer> treeSizeList = new ArrayList<Integer>();
        ArrayList<Integer> rootNodeIndex = new ArrayList<Integer>();
        for (int i = currentLeonardoTrees.length - 1; i >= 0; i--) {
            int currentTreeSize = leonardoNumbers[currentLeonardoTrees[i]];
            treeSizeList.add(currentTreeSize);
            rootNodeIndex.add(prevTreeSizeCumulative + currentTreeSize - 1);
            prevTreeSizeCumulative = prevTreeSizeCumulative + currentTreeSize;
        }

        int rootNodeIndexForHeapify = rootNodeIndex.getLast(); // default value for heapify
        int treeSizeForHeapify = treeSizeList.getLast();
        for (int i = 1; i < currentLeonardoTrees.length; i++) { // iterate form 1 because there is no left of the left-most tree
            int j = i;
            while (j > 0 && array[rootNodeIndex.get(j - 1)] > array[rootNodeIndex.get(j)]) {
                int currentTreeSize = treeSizeList.get(j);
                if (currentTreeSize >= 3) { // has children
                    // if greater than each of two children then swap
                    if (array[rootNodeIndex.get(j - 1)] > array[rootNodeIndex.get(j) - 1] && array[rootNodeIndex.get(j - 1)] > array[rootNodeIndex.get(j) - 2]) {
                        // swap
                        int temp = array[rootNodeIndex.get(j - 1)];
                        array[rootNodeIndex.get(j - 1)] = array[rootNodeIndex.get(j)];
                        array[rootNodeIndex.get(j)] = temp;
                        rootNodeIndexForHeapify = rootNodeIndex.get(j - 1);
                        treeSizeForHeapify = treeSizeList.get(j - 1);
                    }
                } else {
                    // swap
                    int temp = array[rootNodeIndex.get(j - 1)];
                    array[rootNodeIndex.get(j - 1)] = array[rootNodeIndex.get(j)];
                    array[rootNodeIndex.get(j)] = temp;
                    rootNodeIndexForHeapify = rootNodeIndex.get(j - 1);
                    treeSizeForHeapify = treeSizeList.get(j - 1);
                }

                j--;
            }
        }

        array = maxHeapify(rootNodeIndexForHeapify, treeSizeForHeapify, array);
        return array;
    }

    public static Integer[] maxHeapify(int rootNodeIndex, int treeSizeForHeapify, Integer[] array) {
        int startNodeIndex = rootNodeIndex;
        int endNodeIndex = rootNodeIndex - treeSizeForHeapify + 1;

        // This is a heap where the root node is the end index of the array
        // The left child node for an element i is 2i - n
        // The right child node for an element i is 2i - n - 1
        // The parent node is n - 1 - Floor( (n-i-2)/2 )

        if (startNodeIndex <= endNodeIndex) {
            return array;
        }

        for (int i = startNodeIndex; i >= endNodeIndex; i--) {
            int parentNodeIndex = treeSizeForHeapify + endNodeIndex - 1 - ((treeSizeForHeapify - i + endNodeIndex - 2) / 2);
            if ((parentNodeIndex <= rootNodeIndex) && (parentNodeIndex >= i)) {
                int currenNodeIndex = i;
                while (array[currenNodeIndex] > array[parentNodeIndex]) {
                    int temp = array[currenNodeIndex];
                    array[currenNodeIndex] = array[parentNodeIndex];
                    array[parentNodeIndex] = temp;

                    currenNodeIndex = parentNodeIndex;
                    parentNodeIndex = treeSizeForHeapify - 1 - ((treeSizeForHeapify - currenNodeIndex - 2) / 2);

                    if (currenNodeIndex == rootNodeIndex) break; // reached the root node
                }
            }
        }
        return array;
    }
}
