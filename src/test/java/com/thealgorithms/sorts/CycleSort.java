// import java.util.Scanner;
import java.util.Arrays;
/*
 * @author Satyam Shrivastav (https://github.com/D-extremity)
 */
public class CycleSort {
    static void cycle(int arr[]) {
        int i = 0;
        while (i < arr.length) {
            int correct_index = arr[i];
            if (arr[i] != arr[correct_index]) {
                int temp = arr[i];
                arr[i] = arr[correct_index];
                arr[correct_index] = temp;
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = { 0, 3, 1, 2, 5, 4, 6 };
        cycle(arr);
        System.out.println(Arrays.toString(arr));
        in.close();
    }
}