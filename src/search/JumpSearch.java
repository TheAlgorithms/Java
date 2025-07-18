package search;

public class JumpSearch {

    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (arr[Math.min(step, n)-1] < target) {
            prev = step;
            step += Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }

        for (int i = prev; i < Math.min(step, n); i++) {
            if (arr[i] == target)
                return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 12, 17, 21, 25};
        int target = 12;
        int index = jumpSearch(arr, target);
        System.out.println("Found at index: " + index);
    }
}
