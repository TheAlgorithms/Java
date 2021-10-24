package Others;

import java.util.Scanner;

/**
 * Calculate the Dot Product of two vectors length 3
 *
 * @author John Li
 * @date 2021.10.24
 */
public class DotProduct {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = new int[6];
        int index = 0;

        System.out.print("Input 3 integers for Vector 1: ");
        while (index < arr.length) {
            int nextVal = input.nextInt();
            if (index == 2) {
                System.out.print("Input 3 integers for Vector 2: ");
            }
            arr[index] = nextVal;
            index++;
        }

        int secondVectorIndex = 3;
        int total = 0;
        for (int i = 0; i < 3; i++) {
            total += arr[i] * arr[secondVectorIndex];
            secondVectorIndex++;
        }
        System.out.println("ANSWER: " + total);
    }
}
