public class DivideAndConquerMax {

    public static int findMax(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int mid = (left + right) / 2;

        int leftMax = findMax(arr, left, mid);
        int rightMax = findMax(arr, mid + 1, right);

        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, 2, 8, 7, 5};
        int max = findMax(arr, 0, arr.length - 1);
        System.out.println("The maximum element in the array is: " + max);
    }
}
