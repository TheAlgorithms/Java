package sorts;

import static sorts.RadixSort.print;
import static sorts.RadixSort.radixsort;

public class RadixSortTest {

    public static void main(String[] args) {
        int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;
        radixsort(arr, n);
        print(arr, n);
    }
}