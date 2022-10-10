import java.util.Arrays;
import java.util.Scanner;

/*
	explanation url: https://www.geeksforgeeks.org/recursive-selection-sort/
*/

class RecursiveSelectionSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] entry = sc.nextLine().split(" ");
        int[] sequency = srtToInt(entry);

        selectionSort(sequency);
    }

    public static void selectionSort(int[] sequency) {
        selectionSort(sequency, 0);
    }

    public static void selectionSort(int[] sequency, int currentIndex) {
        if (currentIndex < sequency.length - 1) {
            int lesser = currentIndex;
            for (int j = currentIndex + 1; j < sequency.length; j++) {
                if (sequency[j] < sequency[lesser]) {
                    lesser = j;
                }
            }
            swap(sequency, currentIndex, lesser);
            System.out.println(Arrays.toString(sequency));
            selectionSort(sequency, currentIndex + 1);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }

    public static int[] srtToInt(String[] array) {
        int[] numbers = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }
        return numbers;
    }
}
