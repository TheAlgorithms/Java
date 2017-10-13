/**
 *
 * @author RenCloud (https://github.com/rencloud)
 *
 */

class CycleSort {

    /**
     * This method implements the CycleSort Sort
     *
     * @param array The array to be sorted 
     * @param last The count of total number of elements in array
     * Sorts the array in increasing order
     **/

    public static <T extends Comparable<T>> void CS(T array[], int last) {
             for (int cycleStart = 0; cycleStart <= last - 2; cycleStart++) {
                T item = array[cycleStart];
                int pos = cycleStart;
                for (int i = cycleStart + 1; i <= last - 1; i++) {
                    if (item.compareTo(array[i]) >0) {
                        pos++;
                    }
                }
                if (pos == cycleStart) {
                    continue;
                }
                while (array[pos].compareTo(item)==0) {
                    pos++;
                }
                T temp = array[pos];
                array[pos] = item;
                item = temp;
                while (pos != cycleStart) {
                    pos = cycleStart;
                    for (int i = cycleStart + 1; i <= last - 1; i++) {
                        if (item.compareTo(array[i]) >0) {
                            pos++;
                        }
                    }
                    while (array[pos].compareTo(item)==0) {
                        pos++;
                    }
                    temp = array[pos];
                    array[pos] = item;
                    item = temp;
                }
            }
    }

    // Driver Program
    public static void main(String[] args) {
        // Integer Input
        int[] arr1 = {4,23,6,78,1,54,231,9,12};
        int last = arr1.length;
        Integer[] array = new Integer[arr1.length];
        for (int i=0;i<arr1.length;i++) {
            array[i] = arr1[i];
        }

        CS(array, last);

        // Output => 1 4 6 9 12 23 54 78 231
        for (int i=0;i<array.length;i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b","d"};
        last = array1.length;

        CS(array1, last);

        //Output => a	b	c	d	e
        for(int i=0; i<last; i++)
        {
            System.out.print(array1[i]+"\t");
        }

    }
}
