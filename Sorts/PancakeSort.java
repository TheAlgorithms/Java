/**
 *
 * @author Kriztoper Urmeneta (https://github.com/Kriztoper)
 *
 */

class PancakeSort
{
    /**
     * This method implements the Generic Pancake Sort
     *
     * @param array The array to be sorted
     * @param last The count of total number of elements in array
     * Sorts the array in increasing order
     **/
    
     /* Reverses array[0..i] */
    static <T extends Comparable<T>> void flip(T array[], int i)
    {
        T temp;
        int start = 0;
        while (start < i)
        {
            temp = array[start];
            array[start] = array[i];
            array[i] = temp;
            start++;
            i--;
        }
    }
 
    /* Returns index of the maximum element in arr[0..n-1] */
    static <T extends Comparable<T>> int findMax(T array[], int n)
    {
        int maxElementIndex, i;
        for (maxElementIndex = 0, i = 0; i < n; ++i) {
            if (array[i].compareTo(array[maxElementIndex]) > 0) {
                maxElementIndex = i;
            }
        }

        return maxElementIndex;
    }

    
    public static <T extends Comparable<T>> void PS(T array[], int last) {
        //Sorting
        for (int currSize = last; currSize > 1; --currSize)
        {
            // Find index of the maximum element in
            // arr[0..curr_size-1]
            int maxElementIndex = findMax(array, currSize);
 
            // Move the maximum element to end of current
            // array if it's not already at the end
            if (maxElementIndex != currSize - 1)
            {
                // To move at the end, first move maximum
                // number to beginning
                flip(array, maxElementIndex);
 
                // Now move the maximum number to end by
                // reversing current array
                flip(array, currSize - 1);
            }
        }
    }

    // Driver Program
    public static void main(String[] args)
    {
        // Integer Input
        int[] arr1 = {4,23,6,78,1,54,231,9,12};
        int last = arr1.length;
        Integer[] array = new Integer[last];
        for (int i=0;i<last;i++) {
            array[i] = arr1[i];
        }

        PS(array, last);

        // Output => 1	  4	 6	9	12	23	54	78	231
        for(int i=0; i<last; i++)
        {
            System.out.print(array[i]+"\t");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b","d"};
        last = array1.length;

        PS(array1, last);

        //Output => a	  b	 c	d	e
        for(int i=0; i<last; i++)
        {
            System.out.print(array1[i]+"\t");
        }
    }
}
