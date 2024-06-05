package com.thealgorithms.sorts;

/**
 * Introspective Sort Algorithm Implementation
 *
 * @see <a href="https://en.wikipedia.org/wiki/Introsort">IntroSort Algorithm</a>
 */
public class IntrospectiveSort implements SortAlgorithm {

    private static final int INSERTION_SORT_THRESHOLD = 16;

    @Override
    public <T extends Comparable<T>> T[] sort(T[] a) {
        int n = a.length;
        introSort(a, 0, n - 1, 2 * (int) (Math.log(n) / Math.log(2)));
        return a;
    }

    private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static <T extends Comparable<T>> void introSort(T[] a, int low, int high, int depth) {
        while (high - low > INSERTION_SORT_THRESHOLD) {
            if (depth == 0) {
                heapSort(a, low, high);
                return;
            }
            int pivotIndex = partition(a, low, high);
            introSort(a, pivotIndex + 1, high, depth - 1);
            high = pivotIndex - 1;
        }
        insertionSort(a, low, high);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int low, int high) {
        int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        swap(a, pivotIndex, high);
        T pivot = a[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (a[j].compareTo(pivot) <= 0) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    private static <T extends Comparable<T>> void insertionSort(T[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            T key = a[i];
            int j = i - 1;
            while (j >= low && a[j].compareTo(key) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    private static <T extends Comparable<T>> void heapSort(T[] a, int low, int high) {
        for (int i = (high + low - 1) / 2; i >= low; i--) {
            heapify(a, i, high - low + 1, low);
        }
        for (int i = high; i > low; i--) {
            swap(a, low, i);
            heapify(a, low, i - low, low);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] a, int i, int n, int low) {
        int left = 2 * i - low + 1;
        int right = 2 * i - low + 2;
        int largest = i;
        if (left < n && a[left].compareTo(a[largest]) > 0) {
            largest = left;
        }
        if (right < n && a[right].compareTo(a[largest]) > 0) {
            largest = right;
        }
        if (largest != i) {
            swap(a, i, largest);
            heapify(a, largest, n, low);
        }
    }
}
