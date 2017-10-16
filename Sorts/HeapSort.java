import java.util.Scanner;

/**
 * Heap Sort Algorithm
 * Implements MinHeap
 * 
 * @author Unknown
 *
 */
public class HeapSort {
    /** Array to store heap */
    private int[] heap;
    /** The size of the heap */
    private int size;

    /**
     * Constructor
     * 
     * @param heap array of unordered integers
     */
    public HeapSort(int[] heap) {
        this.setHeap(heap);
        this.setSize(heap.length);
    }

    /**
     * Setter for variable size
     *  
     * @param length new size
     */
    private void setSize(int length) {
        this.size = length;
    }

    /**
     * Setter for variable heap
     * 
     * @param heap array of unordered elements
     */
    private void setHeap(int[] heap) {
        this.heap = heap;
    }

    /**
     * Swaps index of first with second
     * 
     * @param first First index to switch
     * @param second Second index to switch
     */
    private void swap(int first, int second) {
        int temp = this.heap[first];
        this.heap[first] = this.heap[second];
        this.heap[second] = temp;
    }

    /**
     * Heapifies subtree from top as root to last as last child
     * 
     * @param rootIndex index of root
     * @param lastChild index of last child
     */
    private void heapSubtree(int rootIndex, int lastChild) {
        int leftIndex = rootIndex * 2 + 1; 
        int rightIndex = rootIndex * 2 + 2;
        int root = this.heap[rootIndex];
        if (rightIndex <= lastChild) { // if has right and left children
            int left = this.heap[leftIndex]; 
            int right = this.heap[rightIndex]; 
            if (left < right && left < root) {
                this.swap(leftIndex, rootIndex); 
                this.heapSubtree(leftIndex, lastChild);
            } else if (right < root) {
                this.swap(rightIndex, rootIndex);
                this.heapSubtree(rightIndex, lastChild);
            }
        } else if (leftIndex <= lastChild) { // if no right child, but has left child
            int left = this.heap[leftIndex];
            if (left < root) {
                this.swap(leftIndex, rootIndex);
                this.heapSubtree(leftIndex, lastChild);
            }
        }
    }

    /**
     * Makes heap with root as root
     * 
     * @param root index of root of heap
     */
    private void makeMinHeap(int root) {
        int leftIndex = root * 2 + 1; 
        int rightIndex = root * 2 + 2; 
        boolean hasLeftChild = leftIndex < this.heap.length;
        boolean hasRightChild = rightIndex < this.heap.length;
        if (hasRightChild) { //if has left and right
            this.makeMinHeap(leftIndex);
            this.makeMinHeap(rightIndex);
            this.heapSubtree(root, this.heap.length - 1);
        } else if (hasLeftChild) {
            this.heapSubtree(root, this.heap.length - 1);
        }
    }

    /**
     * Gets the root of heap
     *
     * @return root of heap
     */
    private int getRoot() {
        this.swap(0, this.size - 1);
        this.size--;
        this.heapSubtree(0, this.size - 1);
        return this.heap[this.size]; // return old root
    }

    /**
     * Sorts heap with heap sort; displays ordered elements to console.
     * 
     * @return sorted array of sorted elements
     */
    public final int[] sort() {
        this.makeMinHeap(0); // make min heap using index 0 as root.
        int[] sorted = new int[this.size];
        int index = 0;
        while (this.size > 0) {
            int min = this.getRoot();
            sorted[index] = min;
            index++;
        }
        return sorted;
    }

    /**
     * Gets input to sort
     *
     * @return unsorted array of integers to sort
     */
    public static int[] getInput() {
        final int numElements = 6;
        int[] unsorted = new int[numElements];
        Scanner input = new Scanner(System.in);
        System.out.println("Enter any 6 Numbers for Unsorted Array : ");
        for (int i = 0; i < numElements; i++) {
            unsorted[i] = input.nextInt();
        }
        input.close();
        return unsorted;
    }

    /**
     * Prints elements in heap
     *
     * @param heap array representing heap
     */
    public static void printData(int[] heap) {
        System.out.println("Sorted Elements:");
        for (int i = 0; i < heap.length; i++) {
            System.out.print(" " + heap[i] + " ");
        }
    }

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] heap = getInput();
        HeapSort data = new HeapSort(heap);
        int[] sorted = data.sort();
        printData(sorted);
    }

}
