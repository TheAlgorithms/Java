package com.thealgorithms.sorts;

/**
 * Implementation of Flash Sort algorithm that implements the SortAlgorithm interface.
 *
 * Sorts an array using the Flash Sort algorithm.
 * <p>
 * Flash Sort is a distribution sorting algorithm that partitions the data into
 * different classes based on a classification array. It performs the sorting by
 * first distributing the data elements into different buckets (or classes) and
 * then permuting these buckets into the sorted order.
 * <p>
 * The method works as follows:
 * <ol>
 *     <li>Finds the minimum and maximum values in the array.</li>
 *     <li>Initializes a classification array `L` to keep track of the number of elements in each class.</li>
 *     <li>Computes a normalization constant `c1` to map elements into classes.</li>
 *     <li>Classifies each element of the array into the corresponding bucket in the classification array.</li>
 *     <li>Transforms the classification array to compute the starting indices of each bucket.</li>
 *     <li>Permutes the elements of the array into sorted order based on the classification.</li>
 *     <li>Uses insertion sort for the final arrangement to ensure complete sorting.</li>
 * </ol>
 */
public class FlashSort implements SortAlgorithm {
    private double classificationRatio = 0.45;

    public FlashSort() {
    }

    public FlashSort(double classificationRatio) {
        if (classificationRatio <= 0 || classificationRatio >= 1) {
            throw new IllegalArgumentException("Classification ratio must be between 0 and 1 (exclusive).");
        }
        this.classificationRatio = classificationRatio;
    }

    public double getClassificationRatio() {
        return classificationRatio;
    }

    public void setClassificationRatio(double classificationRatio) {
        if (classificationRatio <= 0 || classificationRatio >= 1) {
            throw new IllegalArgumentException("Classification ratio must be between 0 and 1 (exclusive).");
        }
        this.classificationRatio = classificationRatio;
    }

    /**
     * Sorts an array using the Flash Sort algorithm.
     *
     * @param array the array to be sorted.
     * @param <T> the type of elements to be sorted, must be comparable.
     * @return the sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        flashSort(array);
        return array;
    }

    /**
     * Sorts an array using the Flash Sort algorithm.
     *
     * @param arr the array to be sorted.
     * @param <T> the type of elements to be sorted, must be comparable.
     */
    private <T extends Comparable<? super T>> void flashSort(T[] arr) {
        if (arr.length == 0) {
            return;
        }

        final T min = findMin(arr);
        final int maxIndex = findMaxIndex(arr);

        if (arr[maxIndex].compareTo(min) == 0) {
            return; // All elements are the same
        }

        final int m = (int) (classificationRatio * arr.length);

        final int[] classificationArray = new int[m];

        final double c1 = (double) (m - 1) / arr[maxIndex].compareTo(min);

        classify(arr, classificationArray, c1, min);

        transform(classificationArray);

        permute(arr, classificationArray, c1, min, arr.length, m);

        insertionSort(arr);
    }

    /**
     * Finds the minimum value in the array.
     *
     * @param arr the array to find the minimum value in.
     * @param <T> the type of elements in the array, must be comparable.
     * @return the minimum value in the array.
     */
    private <T extends Comparable<? super T>> T findMin(final T[] arr) {
        T min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(min) < 0) {
                min = arr[i];
            }
        }
        return min;
    }

    /**
     * Finds the index of the maximum value in the array.
     *
     * @param arr the array to find the maximum value index in.
     * @param <T> the type of elements in the array, must be comparable.
     * @return the index of the maximum value in the array.
     */
    private <T extends Comparable<? super T>> int findMaxIndex(final T[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Classifies elements of the array into the classification array classificationArray.
     *
     * @param arr the array to be classified.
     * @param classificationArray the classification array holding the count of elements in each class.
     * @param c1 the normalization constant used to map the elements to the classification array.
     * @param min the minimum value in the array.
     * @param <T> the type of elements in the array, must be comparable.
     */
    private <T extends Comparable<? super T>> void classify(final T[] arr, final int[] classificationArray, final double c1, final T min) {
        for (int i = 0; i < arr.length; i++) {
            int k = (int) (c1 * arr[i].compareTo(min));
            classificationArray[k]++;
        }
    }

    /**
     * Transforms the classification array classificationArray into the starting index array.
     *
     * @param classificationArray the classification array holding the count of elements in each class.
     */
    private void transform(final int[] classificationArray) {
        for (int i = 1; i < classificationArray.length; i++) {
            classificationArray[i] += classificationArray[i - 1];
        }
    }

    /**
     * Permutes the array into sorted order based on the classification array classificationArray.
     *
     * @param arr the array to be permuted.
     * @param classificationArray the classification array holding the count of elements in each class.
     * @param c1 the normalization constant used to map the elements to the classification array.
     * @param min the minimum value in the array.
     * @param n the length of the array.
     * @param m the number of classes in the classification array.
     * @param <T> the type of elements in the array, must be comparable.
     */
    private <T extends Comparable<? super T>> void permute(final T[] arr, final int[] classificationArray, final double c1, T min, int n, int m) {
        int move = 0;
        int j = 0;
        int k = m - 1;
        T flash;
        while (move < n - 1) {
            while (j > classificationArray[k] - 1) {
                j++;
                k = (int) (c1 * arr[j].compareTo(min));
            }
            flash = arr[j];
            while (j != classificationArray[k]) {
                k = (int) (c1 * flash.compareTo(min));
                T temp = arr[classificationArray[k] - 1];
                arr[classificationArray[k] - 1] = flash;
                flash = temp;
                classificationArray[k]--;
                move++;
            }
        }
    }

    /**
     * Sorts an array using the insertion sort algorithm.
     *
     * @param arr the array to be sorted.
     * @param <T> the type of elements to be sorted, must be comparable.
     */
    private <T extends Comparable<? super T>> void insertionSort(final T[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
