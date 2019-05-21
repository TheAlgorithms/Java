package src.main.java.com.search;

/**
 * Linear search is an algorithm which finds the position of a target value within an array (Usually unsorted)
 *
 * Worst-case performance	O(n)
 * Best-case performance	O(1)
 * Average performance	O(n)
 * Worst-case space complexity	O(1)
 */
public final class LinearSearch {
    /**
     * @param array is an array where the element should be found
     * @param key is an element which should be found
     * @param <T> is any comparable type
     * @return index of the element
     */
    public static <T extends Comparable<T>> int findIndex(T[] array, T key) {
        return search(array, key);
    }

    /**
     * @param array The array to search
     * @param key The element you are looking for
     * @return the location of the key or -1 if the element is not found
     **/
    private static <T extends Comparable<T>> int search(T[] array, T key){
        for(int i = 0; i < array.length;i++) {
            if (array[i].compareTo(key) == 0){
                return i;
            }
        }
        return -1;
    }
}
