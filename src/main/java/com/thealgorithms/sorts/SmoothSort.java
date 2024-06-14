package com.thealgorithms.sorts;
// Java implementation
import java.util.ArrayList;
import java.util.Arrays;

public class SmoothSort {

    public static void buildHeap() {
        // For ith element
        // Left node is present at (2*i)+1
        // Right node is present at (2*i)+2
        // Parent node is at (i-1)/2
    }

    public static int[] getLeonardoNumbers() {
        int[] lenardoNumbers = {
            1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529, 21891,
            35421, 57313, 92735, 150049, 242785, 392835, 635621, 1028457, 1664079, 2692537, 4356617, 7049155, 
            1405773, 18454929, 29860703, 48315633, 78176337, 126491971, 204668309, 331160281, 535828591
        };
    
        return lenardoNumbers;
      }

    public static Integer[] buildMaxHeap(Integer[] array, int endIndex) {
        // In Max heap parent node is always greater than or equal to child node
        // For everey element at index i
            // Compatre if element at i is greater than element at (i-2)/2
            // if true
                // then swap and keep on swapping till false or till you reach root node(index 0)
            // if false
                // then increment i
        for(int i = 0; i <= endIndex; i++) {
            int parent_index = (i-2)/2;
            if (parent_index < 0) continue; // no parent for root node, I guess I can change the iteration from 1
            int current_index = i;
            while(array[current_index] > array[parent_index]) {
                int temp = array[current_index];
                array[current_index] = array[parent_index];
                array[parent_index] = temp;

                current_index = parent_index;
                parent_index = (current_index-2)/2;

                if(current_index == 0) break;
            }
        }

        return array;
    }

    public static Integer[] heapsort(Integer[] array) {
        // build heap from the input
        // start_index = 0
        // end_index = arr.length

        // do till exd_index = start_index
            // build max heap 
            // swap elements start_index and end_index (as largest element is present at start_index and needs to be moved to end_index)
            // end_index = end_index - 1
        int length = array.length;
        int startIndex = 0;
        int endIndex = length - 1;

        int lenardoHeapSize = 0; // start with size 0
        int leonardoLevels = 0;

        while(lenardoHeapSize < length) {
            // if two trees with consequtive level
                // combine them to get new tree
            // else if there is no Level 1, add the node as level 1
            // else add the node as Level 0

            // restore heap property

            
            // System.out.println("HEAPSIZE = " + lenardoHeapSize);
            // System.out.println("leonardoLevels = " + leonardoLevels);
            // System.out.println("===============================================");

            int[] consecutiveTreeIndices = findConsecutiveLeonardoTrees(leonardoLevels);
            if(consecutiveTreeIndices[0] != -1 && consecutiveTreeIndices[1] != -1) {
                // combine the trees
                // update leonardoLevels
                leonardoLevels = leonardoLevels & ~(1 << consecutiveTreeIndices[0]);
                leonardoLevels = leonardoLevels & ~(1 << consecutiveTreeIndices[1]);
                leonardoLevels = leonardoLevels | (1 << consecutiveTreeIndices[1] + 1);
            }
            else if (((leonardoLevels & 2) == 0)) {
                // add the element at level 1 tree
                // update lenardoLevels

                leonardoLevels = leonardoLevels | (1 << 1);
            }
            else {// (leonardoLevels & 1) == 0
                // add the element at level 0 tree if level 1 is present
                // update lenardoLevels

                leonardoLevels = leonardoLevels | (1 << 0);
            }

            // perform correction
            lenardoHeapSize++;
            array = shiftRoot(leonardoLevels, lenardoHeapSize, array);
            // maxHeapify();
        }

        // while(endIndex != startIndex) {
        //     array = buildMaxHeap(array , endIndex);

        //     int temp = array[startIndex];
        //     array[startIndex] = array[endIndex];
        //     array[endIndex] = temp;

        //     endIndex = endIndex -1;
        // } 

        // Now our Leonardo heap is fully ready, start extracting the max
        while (lenardoHeapSize > 0) {
            int[] currentLeonardoTrees = findAllLeonardoTrees(leonardoLevels); // maybe try find last tree level
            int lastTreeLevel = currentLeonardoTrees[0];

            // destroy the current level
            leonardoLevels = leonardoLevels & ~(1 << lastTreeLevel);
            if(lastTreeLevel != 0 && lastTreeLevel != 1) {
                // if level is not L1 or L0
                // create two smaller sublevels
                leonardoLevels = leonardoLevels | (1 << lastTreeLevel - 1);
                leonardoLevels = leonardoLevels | (1 << lastTreeLevel - 2);
            }

            lenardoHeapSize--;
            array = shiftRoot(leonardoLevels, lenardoHeapSize, array);

        }
        

        return array;
    }

    public static int[] findConsecutiveLeonardoTrees(int num) {
        int prevOneIndex = -1; // Initialize to -1 to handle leading 1s
        int currentBit;

        int[] answer = new int[] {-1, -1}; 
        for (int i = 0; num > 0; i++) {
          currentBit = num & 1;
          if (currentBit == 1) {
            if (prevOneIndex != -1) {
                answer[0] = prevOneIndex;
                answer[1] = i;
                
                // Found consecutive ones
            }
            prevOneIndex = i;
          } else {
            prevOneIndex = -1; // Reset if encounter 0
          }
          num >>>= 1; // Right shift to process next bit
        }
        return answer; // No consecutive ones found
      }

    public static int[] findAllLeonardoTrees(int num) {
        int setBitCount = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
          if ((num & (1 << i)) != 0) {
            setBitCount++;
          }
        }
      
        int[] setBitIndexes = new int[setBitCount]; // Create array with appropriate size
        int index = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
          if ((num & (1 << i)) != 0) {
            setBitIndexes[index++] = i;
          }
        }
        return setBitIndexes;
    }
    
    public static Integer[] shiftRoot(int lenardoLevels, int lenardoHeapSize, Integer[] array) {
        if (lenardoHeapSize == 0) {
            return array;
        }
        int[] currentLeonardoTrees = findAllLeonardoTrees(lenardoLevels);
        int[] leonardoNumbers = getLeonardoNumbers();
        // System.out.println("Current number of leonardo trees in leonardo heap = " + currentLeonardoTrees.length);
        // System.out.println("Current lenardoHeapSize " + lenardoHeapSize);
        // System.out.println("Size of the leonardo trees are : ");
        int prevTreeSizeCumulative = 0;
        ArrayList<Integer> treeSizeList = new ArrayList<Integer>();
        ArrayList<Integer> rootNodeIndex = new ArrayList<Integer>();
        for(int i = currentLeonardoTrees.length - 1; i >=0 ; i--) {
            int currentTreeSize = leonardoNumbers[currentLeonardoTrees[i]];
            // System.out.println("Size of current tree = " + currentTreeSize + "   ");
            // System.out.println("Root node of current tree = " + array[prevTreeSizeCumulative + currentTreeSize - 1]);
            // System.out.println("Root node index of current tree = " + (prevTreeSizeCumulative + currentTreeSize - 1));
            treeSizeList.add(currentTreeSize);
            rootNodeIndex.add(prevTreeSizeCumulative + currentTreeSize - 1);
            prevTreeSizeCumulative = prevTreeSizeCumulative + currentTreeSize;
        }

        int rootNodeIndexSize = rootNodeIndex.size(); // should be same as currentLeonardoTrees.length
        int rootNodeIndexForHeapify = rootNodeIndex.getLast(); //default value for heapify
        int treeSizeForHeapify = treeSizeList.getLast();
        for(int i = 1; i < rootNodeIndexSize; i++) { // iterate form 1 because there is no left of the left-most tree
            int j = i;
            while (j > 0 && array[rootNodeIndex.get(j-1)] > array[rootNodeIndex.get(j)]) {
                int currentTreeSize = treeSizeList.get(j);
                if(currentTreeSize >= 3) { //has children
                    //if greater than two children then swap
                    if(array[rootNodeIndex.get(j-1)] > array[rootNodeIndex.get(j) - 1] && array[rootNodeIndex.get(j-1)] > array[rootNodeIndex.get(j) - 2]) {
                        //swap
                        int temp = array[rootNodeIndex.get(j-1)];
                        array[rootNodeIndex.get(j-1)] = array[rootNodeIndex.get(j)];
                        array[rootNodeIndex.get(j)] = temp;
                        rootNodeIndexForHeapify = rootNodeIndex.get(j-1);
                        treeSizeForHeapify = treeSizeList.get(j-1);
                    }
                }
                else{
                    // swap
                    int temp = array[rootNodeIndex.get(j-1)];
                    array[rootNodeIndex.get(j-1)] = array[rootNodeIndex.get(j)];
                    array[rootNodeIndex.get(j)] = temp;
                    rootNodeIndexForHeapify = rootNodeIndex.get(j-1);
                    treeSizeForHeapify = treeSizeList.get(j-1);
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

        for(int i = startNodeIndex ; i >= endNodeIndex ; i--) {
            int parentNodeIndex = treeSizeForHeapify + endNodeIndex - 1 - ((treeSizeForHeapify - i + endNodeIndex - 2)/2);
            if (parentNodeIndex > rootNodeIndex || parentNodeIndex < endNodeIndex || parentNodeIndex < i) {
                continue;
            }

            int currenNodeIndex = i;
            while(array[currenNodeIndex] > array[parentNodeIndex]) {
                int temp = array[currenNodeIndex];
                array[currenNodeIndex] = array[parentNodeIndex];
                array[parentNodeIndex] = temp;

                currenNodeIndex = parentNodeIndex;
                parentNodeIndex = treeSizeForHeapify - 1 - ((treeSizeForHeapify-currenNodeIndex-2)/2);

                if(currenNodeIndex == rootNodeIndex) break; // reached the root node
            }
            // if the current node is greater than it's parent, keep on swaping it top


        }
        return array;
    }
    public static void main(String args[]) {
        // This is an existing test case, remove this after implementation is verified
        Integer[] array = new Integer[] {50,23,75,18,34,11,67,29,3,45,88,62,1,91,40};
        Integer[] expected = new Integer[] {1,3,11,18,23,29,34,40,45,50,62,67,75,88,91};
        
        Integer[] sorted = heapsort(array);
        System.out.println(myAssertArrayEquals(expected, sorted));

        // int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
        // for (int i = 0; i < 11; i++) {
        //     int ele = arr[i];
        //     int[] answer = findConsecutiveLeonardoTrees(ele);
        //     System.out.println(answer[0] + "                " + answer[1]);
        // }

        // int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
        // for (int i = 0; i < 11; i++) {
        //     int ele = arr[i];
        //     int[] answer = findAllLeonardoTrees(ele);
        //     for (int j = 0; j < answer.length ; j++) {
        //         System.out.print(answer[j] + "          ");
        //     }
        //     if (answer.length == 0) {
        //         System.out.print("NONE");
        //     }
        //     System.out.println("\n===================================");
        // }
    }

    //Because Why not? Need to remove this
    public static boolean myAssertArrayEquals(Integer[] expected, Integer[] actual) {
        // Check if both arrays are null
        if (expected == null && actual == null) {
            return true;
        }

        // Check if one of the arrays is null
        if (expected == null || actual == null) {
            return false;
        }

        // Check if the lengths of the arrays are different
        if (expected.length != actual.length) {
            return false;
        }

        // Compare each element of the arrays
        for (int i = 0; i < expected.length; i++) {
            if (!expected[i].equals(actual[i])) {
                return false;
            }
        }

        // If all elements are equal
        return true;
    }
}
