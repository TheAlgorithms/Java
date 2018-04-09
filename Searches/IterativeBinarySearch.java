
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Gabriele La Greca : https://github.com/thegabriele97
 * 
 */

public final class IterativeBinarySearch {

    /**
     * This method implements an iterative version of binary search algorithm
     * 
     * @param array a sorted array
     * @param key the key to search in array
     * 
     * @return the index of key in the array or -1 if not found
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T key) {
        int l, r, k, cmp;

        l = 0;
        r = array.length - 1;

        while (l <= r) {
            k = (l + r) / 2;
            cmp = key.compareTo(array[k]);

            if (cmp == 0) {
                return k;
            } else if (cmp < 0) {
                r = --k;
            } else if (cmp > 0) {
                l = ++k;
            }
        }

        return -1;
    }

    //Only a main method for test purpose
    public static void main(String[] args) {
        Random rand = new Random();
        int base = rand.nextInt(1000);

        Integer[] array = new Integer[65535];
        for (int i = 0; i < array.length; i++) {
            array[i] = base + (i + 1);
        }

        //Arrays.sort(array); //if needed
        Integer key = base + rand.nextInt(array.length * 2); //can generate keys that aren't in array

        System.out.println(binarySearch(array, key));
        System.out.println(Arrays.binarySearch(array, key));
    }
}
