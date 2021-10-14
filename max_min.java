import java.util.Scanner;

public class max_min {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the length of array - ");
        int n = in.nextInt();
        System.out.println("Enter numbers in array - ");
        int array[] = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < n; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }
        System.out.println("Minimum - " + min);
        System.out.println("Maximum - " + max);
    }
}