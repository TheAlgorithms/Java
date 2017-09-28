/**
 *
 * @author Kriztoper Urmeneta (https://github.com/Kriztoper)
 *
 */

class ShellSort
{
    /**
     * This method implements the Generic Shell Sort
     *
     * @param array The array to be sorted
     * @param last The count of total number of elements in array
     * Sorts the array in increasing order
     **/

    public static <T extends Comparable<T>> void SS(T array[], int last) {
        for (int gap = last/2; gap > 0; gap /= 2)
        {
            for (int i = gap, j = 0; i < last; i++)
            {
                T temp = array[i];
                for (j = i; (j >= gap) && (array[j - gap].compareTo(temp) > 0); j -= gap)
                {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
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

        SS(array, last);

        // Output => 1    4  6  9   12  23  54  78  231
        for(int i=0; i<last; i++)
        {
            System.out.print(array[i]+"\t");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b","d"};
        last = array1.length;

        SS(array1, last);

        //Output => a     b  c  d   e
        for(int i=0; i<last; i++)
        {
            System.out.print(array1[i]+"\t");
        }
        System.out.println();
    }
}