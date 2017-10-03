import java.util.Scanner;

/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

class BinarySearch
{
    /**
     * This method implements the Generic Binary Search
     *
     * @param array The array to make the binary search
     * @param key The number you are looking for
     * @param lb The lower bound
     * @param ub The  upper bound
     * @return the location of the key
     **/

    public static <T extends Comparable<T>> int BS(T array[], T key, int lb, int ub)
    {
        if ( lb > ub)
            return -1;

        int mid = (ub+lb) >>> 1;
        int comp = key.compareTo(array[mid]);

        if (comp < 0)
            return (BS(array, key, lb, mid-1));

        if (comp > 0)
            return (BS(array, key, mid + 1, ub));

        return mid;
    }

    // Driver Program
    public static void main(String[] args)
    {
        Scanner input=new Scanner(System.in);

        // For INTEGER Input
        Integer[] array = new Integer[10];
        int key = 5;

        for (int i = 0; i < 10 ; i++ )
            array[i] = i+1;

        int index = BS(array, key, 0, 9);

        if (index != -1)
            System.out.println("Number " +  key + " found at index number : " + index);
        else
            System.out.println("Not found");


        // For STRING Input
        String[] array1 = {"a", "b", "c", "d", "e"};
        String key1 = "d";

        int index1 = BS(array1, key1, 0, array1.length-1);

        if (index1 != -1)
            System.out.println("String " + key1 + " found at index number : " + index1);
        else
            System.out.println("Not found");

        input.close();
    }
}
