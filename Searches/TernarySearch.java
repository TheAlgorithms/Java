import java.util.Scanner;

public class TernarySearch{

    /**
     * @param arr The **Sorted** array in which we will search the element.
     * @param value The value that we want to search for.
     * @return The index of the element if found.
     * Else returns -1.
     */
    public static int ternarySearch(int[] arr, int value){
        return ternarySearch(arr, value, 0, arr.length - 1);
    }

    /**
     * @param arr The **Sorted** array in which we will search the element.
     * @param key The value that we want to search for.
     * @param start The starting index from which we will start Searching.
     * @param end The ending index till which we will Search.
     * @return Returns the index of the Element if found.
     * Else returns -1.
     */
    public static int ternarySearch(int[] arr, int key, int start, int end) {
        if (start > end){
            return -1;
        }
        /* First boundary: add 1/3 of length to start */
        int mid1 = start + (end - start) / 3;
        /* Second boundary: add 2/3 of length to start */
        int mid2 = start + 2 * (end - start) / 3;
        if (arr[mid1] == key) {
            return mid1;
        }
        else if (arr[mid2] == key) {
            return mid2;
        }

        /* Search the first (1/3) rd part of the array.*/

        else if (key < arr[mid1]) {
            return ternarySearch(arr, key, start, mid1 - 1);
        }
        /* Search 3rd (1/3)rd part of the array */

        else if (key > arr[mid2]) {
            return ternarySearch(arr, key, mid2 + 1, end);
        }
        /* Search middle (1/3)rd part of the array */

        else {
            return ternarySearch(arr, key, mid1, mid2);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of elements in the array");
        int n = s.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter the elements of the Sorted array");
        for (int i= 0; i < n; i++){
            arr[i] = s.nextInt();
        }
        System.out.println("Enter element to search for : ");
        int k = s.nextInt();
        int ans = ternarySearch(arr, k);
        if (ans == -1) {
            System.out.println(" The element is not present in the array.");
        }
        else {
            System.out.println("The element is present at the position " + (ans+1));
        }
    }
}