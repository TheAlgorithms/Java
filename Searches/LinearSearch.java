import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

public class LinearSearch{
    /**
     * The main method
     * @param args Command line arguments
     */
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Test for Integer inputs
        Integer[] myArray;
        int size = 0;

        //Prompt user to create array and its elements
        System.out.print("Enter the array size: ");
        size = Integer.parseInt(br.readLine());
        myArray = new Integer[size];
        for (int i = 0; i < size; i++){
            System.out.print("For index " + i + ", enter an integer: ");
            myArray[i] = Integer.parseInt(br.readLine());
        }

        //Prompt user to search for particular element
        System.out.print("Enter integer to search for: ");
        Integer key = Integer.parseInt(br.readLine());

        //Output array and index of target element, if found
        System.out.printf("The integer %d is found in index %d\n", key, linearSearch(myArray, key));

        // Test for String inputs
        String[] myArray1;
        int size1 = 0;

        //Prompt user to create array and its elements
        System.out.print("Enter the array size: ");
        size1 = Integer.parseInt(br.readLine());
        myArray1 = new String[size];
        for (int i = 0; i < size1; i++){
            System.out.print("For index " + i + ", enter a String: ");
            myArray1[i] = br.readLine();
        }

        //Prompt user to search for particular element
        System.out.print("Enter String to search for: ");
        String key1 = br.readLine();

        //Output array and index of target element, if found
        System.out.printf("The string %s is found in index %d\n", key1, linearSearch(myArray1, key1));
    }

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
