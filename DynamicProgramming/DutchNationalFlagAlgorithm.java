package DynamicProgramming;

//import java.util.*

/*

 Function to sort the input array where the array is assumed to have values in {0, 1, 2}  
 We have to take 3 distint or unique elements 
 
 @author SNEHAL311998

*/
public class DutchNationalFlagAlgorithm {

    /*
     * This function sorts an array of 0s, 1s, 2s
     * 
     * @param input: array of 0s, 1s, 2s
     * 
     * @return return sorted array
     * 
     *
     */

    static int[] sort0s1s2s(int[] arr) {

        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int temp;
        while (mid <= high) {
            switch (arr[mid]) {
            case 0: {
                temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
                break;
            }

            case 1: {
                mid++;
                break;
            }

            case 2: {
                temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
                break;
            }
            }
        }
        return arr;
    }

    // Driver program to test above function
    public static void main(String args[]) {

        int[] array = { 0, 1, 2, 0, 1, 0, 0, 2, 1, 2 };
        int[] result = sort0s1s2s(array);

        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
    }
}