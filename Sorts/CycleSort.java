/**
 *
 * @author Kriztoper Urmeneta (https://github.com/Kriztoper)
 *
 */

class CycleSort
{
    /**
     * This method implements the Generic Cycle Sort
     *
     * @param array The array to be sorted
     * @param last The count of total number of elements in array
     * Sorts the array in increasing order
     **/

    public static <T extends Comparable<T>> void CS(T array[], int last) {
        //Sorting
        // count number of memory writes
        int writes = 0;
        
        // traverse array elements and put it to on
        // the right place
        for (int cycleStart = 0; cycleStart <= last - 2; cycleStart++) {
            // initialize item as starting point
            T item = array[cycleStart];

            // Find position where we put the item. We basically
            // count all smaller elements on right side of item.
            int pos = cycleStart;
            for (int i = cycleStart + 1; i < last; i++) {
                if (array[i].compareTo(item) < 0) {
                    pos++;
                }
            }

            // If item is already in correct position
            if (pos == cycleStart) {
                continue;
            }

            // ignore all duplicate elements
            while (item.compareTo(array[pos]) == 0) {
                pos++;
            }

            // put the item to it's right position
            if (pos != cycleStart) {
                T temp = item;
                item = array[pos];
                array[pos] = temp;
                writes++;
            }

            // Rotate rest of the cycle
            while (pos != cycleStart) {
                pos = cycleStart;

                // Find position where we put the element
                for (int i = cycleStart + 1; i < last; i++) {
                    if (array[i].compareTo(item) < 0) {
                        pos += 1;
                    }
                }

                // ignore all duplicate elements
                while (item.compareTo(array[pos]) == 0) {
                    pos++;
                }

                // put the item to it's right position
                if (item.compareTo(array[pos]) != 0) {
                    T temp = item;
                    item = array[pos];
                    array[pos] = temp;
                    writes++;
                }
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

        CS(array, last);

        // Output => 1	  4	 6	9	12	23	54	78	231
        for(int i=0; i<last; i++)
        {
            System.out.print(array[i]+"\t");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b","d"};
        last = array1.length;

        CS(array1, last);

        //Output => a	  b	 c	d	e
        for(int i=0; i<last; i++)
        {
            System.out.print(array1[i]+"\t");
        }
    }
}
