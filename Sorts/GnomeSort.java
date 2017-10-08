/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

class GnomeSort
{
    /**
     * This method implements the Generic Gnome Sort
     *
     * @param array The array to be sorted
     * @param last The count of total number of elements in array
     * Sorts the array in increasing order
     **/

    public static <T extends Comparable<T>> void GS(T array[], int last) {
        //Sorting
        int index = 0;
        
        while (index < last)
        {
            if (index == 0) {
                index++;
            }
            
            if (array[index].compareTo(array[index-1]) >= 0) {
                index++;
            } else {
                T temp = array[index];
                array[index] = array[index - 1];
                array[index - 1] = temp;
                index--;
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

        GS(array, last);

        // Output => 1	  4	 6	9	12	23	54	78	231
        for(int i=0; i<last; i++)
        {
            System.out.print(array[i]+"\t");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b","d"};
        last = array1.length;

        GS(array1, last);

        //Output => a	  b	 c	d	e
        for(int i=0; i<last; i++)
        {
            System.out.print(array1[i]+"\t");
        }
    }
}
