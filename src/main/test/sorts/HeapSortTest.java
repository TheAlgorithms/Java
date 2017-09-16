package sorts;

import java.util.Scanner;

import static sorts.HeapSort.printData;

public class HeapSortTest {
    public static void main(String[] args) {
        int[] heap = getInput();
        HeapSort data = new HeapSort(heap);
        int[] sorted = data.sort();
        printData(sorted);
    }

    private static int[] getInput() {
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
}