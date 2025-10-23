package sorting;

public class OptimizedBubbleSort {
    public static void optimizedBubbleSort(int[] arr) {
        int n = arr.length;
        int newn;
        do {
            newn = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    newn = i;
                }
            }
            n = newn;
        } while (n > 1);
    }

    public static void main(String[] args) {
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Sıralamadan önce:");
        for (int num : numbers) System.out.print(num + " ");
        System.out.println();
        optimizedBubbleSort(numbers);
        System.out.println("Sıralamadan sonra:");
        for (int num : numbers) System.out.print(num + " ");
    }
}
