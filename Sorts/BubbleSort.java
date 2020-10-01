package Sorts;

import static Sorts.SortUtils.*;
import java.util.Scanner;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SortAlgorithm
 */

class BubbleSort implements SortAlgorithm {
    /**
     * This method implements the Generic Bubble Sort
     *
     * @param array The array to be sorted
     *              Sorts the array in ascending order
     **/

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 0, size = array.length; i < size - 1; ++i) {
            boolean swapped = false;
            for (int j = 0; j < size - 1 - i; ++j) {
            	if (greater(array[j], array[j + 1])) {
            		swap(array, j, j + 1);
            		swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return array;
    }

    // Driver Program
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements : ");
        int n = scanner.nextInt();
        //n = 9
        Integer[] integers = new Integer[n];
        for(int i=0;i<n;i++)
        {
            integers[i] = scanner.nextInt();
        }
        // Integer Input
        //Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(integers);

        // Output => 1, 4, 6, 9, 12, 23, 54, 78, 231
        print(integers);

        // String Input
        String[] strings = {"c", "a", "e", "b", "d"};
        //Output => a, b, c, d, e
        print(bubbleSort.sort(strings));

    }
}
