package Sorts;

import java.lang.Math;
import java.util.Random;

/**
 * @author Hemanth Kotagiri (https://github.com/hemanth-kotagiri)
 * @see SortAlgorithm
 */

// Tim Sort: https://en.wikipedia.org/wiki/Tim_sort
class TimSort {
    int array[];
    int array_length;
    int RUN = 32;

    /**
     * @param array the array given by the user
    */

    public TimSort(int[] array) {
        this.array = array;
        this.array_length = array.length;
    }

    /**
     * @param array_length the array_length given by the user to generate random array of that length
    */
    public TimSort(int array_length) {
        Random rand = new Random();

        this.array_length = array_length;
        this.array = new int[this.array_length];

        for (int i = 0; i < this.array_length; i++) {
            int random_number = rand.nextInt(1000);
            this.array[i] = random_number;
        }
    }

    /**
     * @param run change size of the run
    */

    public void change_run(int run) {
        this.RUN = run;
    }

    // Constructor when no argument is given.
    public TimSort() {
        this.array_length = 100;
        this.array = new int[this.array_length];

        Random rand = new Random();
        for (int i = 0; i < this.array_length; i++) {
            int random_number = rand.nextInt(1000);
            this.array[i] = random_number;
        }
    }

    // Insertion sort algorithm
    public void insertion_sort(int[] array, int start_idx, int end_idx) {
        for (int i = 0; i < array.length; i++) {
            int current_element = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > current_element) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current_element;
        }
    }

    public void merge_runs(int array[], int start, int mid, int end) {

        int first_array_size = mid - start + 1, second_array_size = end - mid;
        int array1[] = new int[first_array_size], array2[] = new int[second_array_size];
        int i = 0, j = 0, k = 0;

        // Building the two sub arrays from the array to merge later
        for (i = 0; i < first_array_size; i++)
            array1[i] = array[start + i];
        for (i = 0; i < second_array_size; i++)
            array2[i] = array[mid + 1 + i];

        i = 0;
        j = 0;
        k = start;

        while (i < first_array_size && j < second_array_size) {
            if (array1[i] <= array2[j]) {
                array[k] = array1[i];
                i++;
            } else {
                array[k] = array2[j];
                j++;
            }
            k++;
        }

        while (i < first_array_size) {
            array[k] = array1[i];
            k++;
            i++;
        }

        while (j < second_array_size) {
            array[k] = array2[j];
            k++;
            j++;
        }
    }

    // Tim Sort Algorithm
    public void algorithm() {
        // Before Sorting
        System.out.println("Before sorting the array: ");
        this.showArrayElements();
        System.out.println();

        // Applying insertion sort on RUNS.
        for (int i = 0; i < this.array_length; i += this.RUN)
            this.insertion_sort(this.array, i, Math.min(i + this.RUN, (this.array_length - 1)));

        for (int split = this.RUN; split < this.array_length; split = 2 * split) {
            for (int start_idx = 0; start_idx < this.array_length; start_idx += 2 * split) {
                int mid = start_idx + split - 1;
                int end_idx = Math.min((start_idx + 2 * split - 1), (this.array_length - 1));

                this.merge_runs(this.array, start_idx, mid, end_idx);
            }
        }
        // After sorting
        System.out.println("After sorting the array: ");
        this.showArrayElements();
        System.out.println();
    }

    public void showArrayElements() {
        for (int i = 0; i < this.array.length; i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
    }
}
