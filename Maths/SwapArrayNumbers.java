package Maths;

public class SwapArrayNumbers {

    public static void main(String[] args) {
        int[] numbers = new int[] {3, 0, 5, 7, 1, 9, 4};
        swap(numbers, 0, 1);
        assert numbers[0] == 0;
        assert numbers[1] == 3;
    }

    /**
     * Swaps the values of a and b in array arr without extra space
     *
     * @param arr the arr contains numbers
     * @param a candidate for replacement
     * @param b candidate for replacement
     */
    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

}
