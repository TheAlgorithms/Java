import java.util.*;
import java.lang.*;
import java.io.*;

class TugOfWar {
    public int min_diff;

    void TOWUtil(int arr[], int n, int curr_elements[], int no_of_selected_elements, int soln[], int sum, int curr_sum,
            int curr_position) {
        if (curr_position == n)
            return;

        if ((n / 2 - no_of_selected_elements) > (n - curr_position))
            return;

        TOWUtil(arr, n, curr_elements, no_of_selected_elements, soln, sum, curr_sum, curr_position + 1);

        no_of_selected_elements += 1;
        curr_sum = curr_sum + arr[curr_position];
        curr_elements[curr_position] = 1;

        if (no_of_selected_elements == n / 2) {
            if (Math.abs(sum / 2 - curr_sum) < min_diff) {
                min_diff = Math.abs(sum / 2 - curr_sum);
                for (int i = 0; i < n; i++)
                    soln[i] = curr_elements[i];
            }
        } else {
            TOWUtil(arr, n, curr_elements, no_of_selected_elements, soln, sum, curr_sum, curr_position + 1);
        }
        curr_elements[curr_position] = 0;
    }

    void tugOfWar(int arr[]) {
        int n = arr.length;

        int[] curr_elements = new int[n];

        int[] soln = new int[n];

        min_diff = Integer.MAX_VALUE;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            curr_elements[i] = soln[i] = 0;
        }
        TOWUtil(arr, n, curr_elements, 0, soln, sum, 0, 0);
        System.out.print("First subset:");
        for (int i = 0; i < n; i++) {
            if (soln[i] == 1)
                System.out.print(arr[i] + " ");
        }
        System.out.print("\nSecond subset:");
        for (int i = 0; i < n; i++) {
            if (soln[i] == 0)
                System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int arr[] = { 24, 35, -24, 15, 0, 89, -29, 6, 169, -7, 10 };
        TugOfWar tow = new TugOfWar();
        tow.tugOfWar(arr);
    }
}