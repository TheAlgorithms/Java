package searches;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */
class BinarySearch {

    /**
     * This method implements the Generic Binary Search
     *
     * @param array The array to make the binary search
     * @param key   The number you are looking for
     * @param lb    The lower bound
     * @param ub    The  upper bound
     * @return the location of the key
     **/
    public static <T extends Comparable<T>> int BS(T array[], T key, int lb, int ub) {
        if (lb > ub)
            return -1;
        int mid = (ub + lb) >>> 1;
        int comp = key.compareTo(array[mid]);
        if (comp < 0)
            return (BS(array, key, lb, mid - 1));
        if (comp > 0)
            return (BS(array, key, mid + 1, ub));
        return mid;
    }
}
