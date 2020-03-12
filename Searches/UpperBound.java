public class UpperBound {
    public static void UpperBound(String[] args) {

    }

    private static int upper_bound(int arr[], int value) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (value >= arr[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
