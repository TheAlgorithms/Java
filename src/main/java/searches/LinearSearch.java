package searches;

/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

public class LinearSearch{
    /**
     * Generic Linear search method
     *
     * @param array List to be searched
     * @param value Key being searched for
     * @return Location of the key
     */
    public static <T extends Comparable<T>> int linearSearch(T[] array, T value) {
        int lo = 0;
        int hi = array.length - 1;
        for (int i = lo; i <= hi; i++) {
            if (array[i].compareTo(value) == 0) {
                return i;
            }
        }
        return -1;
    }
}
