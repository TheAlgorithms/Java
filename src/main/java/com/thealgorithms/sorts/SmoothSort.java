package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Smoothsort
 */
public final class SmoothSort implements SortAlgorithm {

    public SmoothSort() {
    }

    private static Integer[] getLeonardoNumbers() {
        Integer[] leonardoNumbers = {1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529, 21891, 35421, 57313, 92735, 150049, 242785, 392835, 635621, 1028457, 1664079, 2692537, 4356617, 7049155, 1405773, 18454929, 29860703, 48315633, 78176337, 126491971,
            204668309, 331160281, 535828591};

        return leonardoNumbers;
    }

    private static <T extends Comparable<T>> void smoothSort(T[] array) {
        int length = array.length;
        int leonardoHeapSize = 0; // start with size 0
        int leonardoLevelTracker = 0; // No leonardo tree present initially

        while (leonardoHeapSize < length) {
            // if two trees with consequtive level
            // combine them to get new tree
            // else if there is no Level 1, add the node as level 1
            // else add the node as Level 0
            // perform shiftRoot and restore heap property

            Integer[] consecutiveTreeIndices = findConsecutiveLeonardoTreeIndices(leonardoLevelTracker);
            if (consecutiveTreeIndices[0] != -1) {
                // if 0th or 1st index is -1 that implies there are no concequtive trees
                leonardoLevelTracker = leonardoLevelTracker & ~(1 << consecutiveTreeIndices[0]);
                leonardoLevelTracker = leonardoLevelTracker & ~(1 << consecutiveTreeIndices[1]);
                leonardoLevelTracker = leonardoLevelTracker | (1 << consecutiveTreeIndices[1] + 1);
            } else if ((leonardoLevelTracker & 2) == 0) {
                leonardoLevelTracker = leonardoLevelTracker | (1 << 1);
            } else {
                leonardoLevelTracker = leonardoLevelTracker | (1 << 0);
            }
            leonardoHeapSize++;
            shiftRootAndRestoreHeap(leonardoLevelTracker, leonardoHeapSize, array);
        }

        while (leonardoHeapSize > 0) {
            // destroy the current level
            // if level is not L1 or L0
            // create two smaller sublevels
            // perform shiftRoot and restore heap property

            int lastTreeLevel = getRightMostTree(leonardoLevelTracker); // getting the right most tree

            leonardoLevelTracker = leonardoLevelTracker & ~(1 << lastTreeLevel);
            if (lastTreeLevel != 0 && lastTreeLevel != 1) {
                leonardoLevelTracker = leonardoLevelTracker | (1 << lastTreeLevel - 1);
                leonardoLevelTracker = leonardoLevelTracker | (1 << lastTreeLevel - 2);
            }

            leonardoHeapSize--;
            shiftRootAndRestoreHeap(leonardoLevelTracker, leonardoHeapSize, array);
        }
    }

    private static int getRightMostTree(int leonardoLevelTracker) {
        // Isolate the rightmost set bit
        int isolatedBit = leonardoLevelTracker & -leonardoLevelTracker;
        int position = 0;

        while (isolatedBit > 1) {
            isolatedBit >>= 1;
            position++;
        }

        return position;
    }

    private static Integer[] findConsecutiveLeonardoTreeIndices(int num) {
        int prevOneIndex = -1;
        int currentLevel;

        Integer[] answer = new Integer[] {-1, -1};
        for (int i = 0; num > 0; i++) {
            currentLevel = num & 1;
            if (currentLevel == 1) {
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

    private static Integer[] findAllLeonardoTreeIndices(int num) {
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

    private static <T extends Comparable<T>> void shiftRootAndRestoreHeap(int lenardoLevelTracker, int leonardoHeapSize, T[] array) {

        if (leonardoHeapSize == 0) {
            return;
        }

        Integer[] currentLeonardoTreeLevels = findAllLeonardoTreeIndices(lenardoLevelTracker);
        int previousTreeSizeCumulative = 0;
        ArrayList<Integer> rootNodeIndices = new ArrayList<Integer>();
        Collections.reverse(Arrays.asList(currentLeonardoTreeLevels)); // To get the Levels in decreasing order of levels

        // The number of roots are going to be same the the number of levels
        // iterate over the currentLeonardoTreeLevels and get roots

        for (int i = 0; i < currentLeonardoTreeLevels.length; i++) {
            rootNodeIndices.add(previousTreeSizeCumulative + getLeonardoNumbers()[currentLeonardoTreeLevels[i]] - 1);
            previousTreeSizeCumulative = previousTreeSizeCumulative + getLeonardoNumbers()[currentLeonardoTreeLevels[i]];
        }

        int rootNodeIndexForHeapify = rootNodeIndices.getLast();
        int leonardoTreeLevelforHeapify = currentLeonardoTreeLevels[currentLeonardoTreeLevels.length - 1];

        for (int i = 0; i < rootNodeIndices.size(); i++) {
            if (i == 0) {
                continue;
            }

            int currentRootNodeIndex = rootNodeIndices.get(i);
            int prevRootNodeIndex = rootNodeIndices.get(i - 1);
            int j = i;
            while (array[prevRootNodeIndex].compareTo(array[currentRootNodeIndex]) > 0) {
                int currentLeonardoLevel = currentLeonardoTreeLevels[j];
                if (currentLeonardoLevel > 1) {
                    // compare child and swap

                    int indexOfRightChild = rootNodeIndices.get(j) - 1; // right child is of level n-2
                    int indexOfLeftChild = rootNodeIndices.get(j) - 1 - getLeonardoNumbers()[currentLeonardoLevel - 2];
                    if (array[prevRootNodeIndex].compareTo(array[indexOfRightChild]) > 0 && array[prevRootNodeIndex].compareTo(array[indexOfLeftChild]) > 0) {
                        SortUtils.swap(array, prevRootNodeIndex, currentRootNodeIndex);
                        rootNodeIndexForHeapify = prevRootNodeIndex;
                        leonardoTreeLevelforHeapify = currentLeonardoTreeLevels[j - 1];
                    } else {
                        maxHeapifyLeonardoTree(currentRootNodeIndex, currentLeonardoLevel, array);
                    }
                } else {
                    SortUtils.swap(array, prevRootNodeIndex, currentRootNodeIndex);
                    rootNodeIndexForHeapify = prevRootNodeIndex;
                    leonardoTreeLevelforHeapify = currentLeonardoTreeLevels[j - 1];
                }
                j = j - 1;
                if (j == i - 1) {
                    maxHeapifyLeonardoTree(rootNodeIndexForHeapify, leonardoTreeLevelforHeapify, array);
                }
            }
        }

        maxHeapifyLeonardoTree(rootNodeIndexForHeapify, leonardoTreeLevelforHeapify, array);
    }

    private static <T extends Comparable<T>> void maxHeapifyLeonardoTree(int rootNodeIndex, int currentLeonardoLevel, T[] array) {
        // A leonardo tree of level n is just 1 node(the root) plus the leonardo tree of n-1 level(left child) plus leonardo tree of n-2 level(right child)
        // To maxheapify a leonardo tree we need to compare the current root and roots of it's left and right subtree
        // We recursively hepify the left and right subtrees using the currentLeonardoLevel

        // BASE CASE
        if (currentLeonardoLevel == 0 || currentLeonardoLevel == 1) {
            return; // Trees with one node are in already max-heapified.
        }

        int currentRootNodeIndex = rootNodeIndex;
        int rightChildIndex = rootNodeIndex - 1;
        int leftChildIndex = rootNodeIndex - getLeonardoNumbers()[currentLeonardoLevel - 2] - 1;
        int childIndexForSwap = -1;

        if (array[rightChildIndex].compareTo(array[leftChildIndex]) >= 0) {
            childIndexForSwap = rightChildIndex;
        } else {
            childIndexForSwap = leftChildIndex;
        }

        if (array[childIndexForSwap].compareTo(array[currentRootNodeIndex]) > 0) {
            SortUtils.swap(array, currentRootNodeIndex, childIndexForSwap);
            if (childIndexForSwap == rightChildIndex) {
                maxHeapifyLeonardoTree(rightChildIndex, currentLeonardoLevel - 2, array);
            } else { // swap happened with the left child
                maxHeapifyLeonardoTree(leftChildIndex, currentLeonardoLevel - 1, array);
            }
        }
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        smoothSort(unsorted);
        return unsorted;
    }
}
