package com.thealgorithms.datastructures.heaps;

import com.thealgorithms.bitmanipulation.SingleBitOperations;
import com.thealgorithms.maths.LeonardoNumber;
import java.util.ArrayList;
import java.util.List;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Smoothsort
 */
public class LeonardoHeap<T extends Comparable<T>> {

    private int leonardoLevelTracker;
    private int leonardoHeapSize;
    private final List<T> leonardoHeap;

    public LeonardoHeap() {
        this.leonardoHeap = new ArrayList<T>();
        this.leonardoLevelTracker = 0;
        this.leonardoHeapSize = 0;
    }

    public int getHeapsize() {
        return this.leonardoHeapSize;
    }

    private void decreaseLeonardoLevelTracker() {
        int lastTreeLevel = getRightMostTree();
        leonardoLevelTracker = SingleBitOperations.clearBit(leonardoLevelTracker, lastTreeLevel);
        if (lastTreeLevel != 0 && lastTreeLevel != 1) {
            leonardoLevelTracker = SingleBitOperations.setBit(leonardoLevelTracker, lastTreeLevel - 1);
            leonardoLevelTracker = SingleBitOperations.setBit(leonardoLevelTracker, lastTreeLevel - 2);
        }
    }

    private void increaseLeonardoLevelTracker() {
        ArrayList<Integer> consecutiveTreeIndices = findConsecutiveLeonardoTreeIndices(leonardoLevelTracker);
        if (consecutiveTreeIndices.get(0) != -1) {
            // if 0th or 1st index is -1 that implies there are no concequtive trees
            leonardoLevelTracker = SingleBitOperations.clearBit(leonardoLevelTracker, consecutiveTreeIndices.get(0));
            leonardoLevelTracker = SingleBitOperations.clearBit(leonardoLevelTracker, consecutiveTreeIndices.get(1));
            leonardoLevelTracker = SingleBitOperations.setBit(leonardoLevelTracker, consecutiveTreeIndices.get(1) + 1);
        } else if ((leonardoLevelTracker & 2) == 0) {
            leonardoLevelTracker = SingleBitOperations.setBit(leonardoLevelTracker, 1);
        } else {
            leonardoLevelTracker = SingleBitOperations.setBit(leonardoLevelTracker, 0);
        }
    }

    private void decreaseHeapSize() {
        this.leonardoHeapSize--;
    }

    private void increaseHeapSize() {
        this.leonardoHeapSize++;
    }

    private void maxHeapifyLeonardoTree(int rootNodeIndex, int currentLeonardoLevel) {
        // A leonardo tree of level n is just 1 node(the root) plus the leonardo tree of n-1 level(left child) plus leonardo tree of n-2 level(right child)
        // To maxheapify a leonardo tree we need to compare the current root and roots of it's left and right subtree
        // We recursively hepify the left and right subtrees using the currentLeonardoLevel

        // BASE CASE
        if (currentLeonardoLevel == 0 || currentLeonardoLevel == 1) {
            return; // Trees with one node are in already max-heapified.
        }

        int currentRootNodeIndex = rootNodeIndex;
        int rightChildIndex = rootNodeIndex - 1;
        int leftChildIndex = rootNodeIndex - LeonardoNumber.leonardoNumber(currentLeonardoLevel - 2) - 1;
        int childIndexForSwap = -1;

        if (leonardoHeap.get(rightChildIndex).compareTo(leonardoHeap.get(leftChildIndex)) >= 0) {
            childIndexForSwap = rightChildIndex;
        } else {
            childIndexForSwap = leftChildIndex;
        }

        if (leonardoHeap.get(childIndexForSwap).compareTo(leonardoHeap.get(currentRootNodeIndex)) > 0) {
            swap(currentRootNodeIndex, childIndexForSwap);
            if (childIndexForSwap == rightChildIndex) {
                maxHeapifyLeonardoTree(rightChildIndex, currentLeonardoLevel - 2);
            } else { // swap happened with the left child
                maxHeapifyLeonardoTree(leftChildIndex, currentLeonardoLevel - 1);
            }
        }
    }

    private void shiftRootAndRestoreHeap() {

        if (getHeapsize() == 0) {
            return;
        }

        Integer[] currentLeonardoTreeLevels = findAllLeonardoTreeIndices();
        int previousTreeSizeCumulative = 0;
        ArrayList<Integer> rootNodeIndices = new ArrayList<Integer>();

        // The number of roots are going to be same the the number of levels
        // iterate over the currentLeonardoTreeLevels and get roots

        for (int i = 0; i < currentLeonardoTreeLevels.length; i++) {
            rootNodeIndices.add(previousTreeSizeCumulative + LeonardoNumber.leonardoNumber(currentLeonardoTreeLevels[i]) - 1);
            previousTreeSizeCumulative = previousTreeSizeCumulative + LeonardoNumber.leonardoNumber(currentLeonardoTreeLevels[i]);
        }

        int rootNodeIndexForHeapify = rootNodeIndices.getLast();
        int leonardoTreeLevelforHeapify = currentLeonardoTreeLevels[currentLeonardoTreeLevels.length - 1];
        boolean swaped = false;

        for (int i = 1; i < rootNodeIndices.size(); i++) {

            int currentRootNodeIndex = rootNodeIndices.get(i);
            int prevRootNodeIndex = rootNodeIndices.get(i - 1);
            int j = i;
            while (leonardoHeap.get(prevRootNodeIndex).compareTo(leonardoHeap.get(currentRootNodeIndex)) > 0) {
                int currentLeonardoLevel = currentLeonardoTreeLevels[j];
                if (currentLeonardoLevel > 1) {
                    // compare child and swap

                    int indexOfRightChild = rootNodeIndices.get(j) - 1; // right child is of level n-2
                    int indexOfLeftChild = rootNodeIndices.get(j) - 1 - LeonardoNumber.leonardoNumber(currentLeonardoLevel - 2);
                    if (leonardoHeap.get(prevRootNodeIndex).compareTo(leonardoHeap.get(indexOfRightChild)) > 0 && leonardoHeap.get(prevRootNodeIndex).compareTo(leonardoHeap.get(indexOfLeftChild)) > 0) {
                        swap(prevRootNodeIndex, currentRootNodeIndex);
                        rootNodeIndexForHeapify = prevRootNodeIndex;
                        leonardoTreeLevelforHeapify = currentLeonardoTreeLevels[j - 1];
                        swaped = true;
                    } else {
                        maxHeapifyLeonardoTree(currentRootNodeIndex, currentLeonardoLevel);
                        swaped = false;
                    }
                } else {
                    swap(prevRootNodeIndex, currentRootNodeIndex);
                    rootNodeIndexForHeapify = prevRootNodeIndex;
                    leonardoTreeLevelforHeapify = currentLeonardoTreeLevels[j - 1];
                    swaped = true;
                }
                j = j - 1;
                if (j > 0) {
                    currentRootNodeIndex = rootNodeIndices.get(j);
                    prevRootNodeIndex = rootNodeIndices.get(j - 1);
                } else {
                    // j = 0 reached the left most tree
                    break;
                }
            }

            if (swaped) {
                maxHeapifyLeonardoTree(rootNodeIndexForHeapify, leonardoTreeLevelforHeapify);
                swaped = false;
            }
        }

        maxHeapifyLeonardoTree(rootNodeIndexForHeapify, leonardoTreeLevelforHeapify); // In case of insert and no swap.
    }

    private int getRightMostTree() {
        // Isolate the rightmost set bit
        int isolatedBit = leonardoLevelTracker & -leonardoLevelTracker;
        int position = 0;

        while (isolatedBit > 1) {
            isolatedBit >>= 1;
            position++;
        }

        return position;
    }

    private static ArrayList<Integer> findConsecutiveLeonardoTreeIndices(int num) {
        int prevOneIndex = -1;
        int currentLevel;

        ArrayList<Integer> answer = new ArrayList<Integer>();
        answer.add(-1);
        answer.add(-1);

        for (int i = 0; num > 0; i++) {
            currentLevel = num & 1;
            if (currentLevel == 1) {
                if (prevOneIndex != -1) {
                    answer.set(0, prevOneIndex);
                    answer.set(1, i);
                }
                prevOneIndex = i;
            } else {
                prevOneIndex = -1;
            }
            num >>>= 1;
        }
        return answer;
    }

    private void swap(int i, int j) {
        T temp = leonardoHeap.get(i);
        leonardoHeap.set(i, leonardoHeap.get(j));
        leonardoHeap.set(j, temp);
    }

    private Integer[] findAllLeonardoTreeIndices() {
        List<Integer> setBitIndexes = new ArrayList<>();
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            if ((leonardoLevelTracker & (1 << i)) != 0) {
                setBitIndexes.add(i);
            }
        }
        return setBitIndexes.toArray(new Integer[0]);
    }

    public void addElement(T element) {
        increaseLeonardoLevelTracker();
        leonardoHeap.add(element);
        increaseHeapSize();
        shiftRootAndRestoreHeap();
    }

    public T removeElement() {
        decreaseLeonardoLevelTracker();
        decreaseHeapSize();
        T element = leonardoHeap.removeLast();
        shiftRootAndRestoreHeap();

        return element;
    }
}
