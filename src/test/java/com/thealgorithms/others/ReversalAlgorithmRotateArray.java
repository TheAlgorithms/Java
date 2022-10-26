public class ReversalAlgorithmRotateArray {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        int k = 3;
        RotateArray(arr, k);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void RotateArray(int[] arr, int k) {
        k=k%arr.length;
        ReverseArray(arr, arr.length - k, arr.length - 1);// last k
        ReverseArray(arr, 0, arr.length - k - 1);// first arr.length-k;
        ReverseArray(arr, 0, arr.length - 1); // whole reverse
    }

    static void ReverseArray(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

}
