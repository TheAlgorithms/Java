package datastructures.stacks;
import java.util.Arrays;
import java.util.Stack;

/**
 * NearestElement four classic stack based algorithms:
 *Implemented methods:
 * nearestGreaterToRight(int[] arr)  - for each index i, first element to the right  arr[i] or -1
 * nearestGreaterToLeft(int[] arr)   - for each index i, first element to the left arr[i] or -1
 * nearestSmallerToRight(int[] arr)  - for each index i, first element to the right arr[i] or -1
 * nearestSmallerToLeft(int[] arr)   - for each index i, first element to the left arr[i] or -1
 * All methods run in O(n) time and O(n) extra space (stack + output).
 * Usage: Each method returns an int[] of the same length as input. If no nearest element satisfies the condition, -1 is used at that index.
 */
public class NearestElement {
    /**
     * For each element, finds the nearest greater element to its right.
     * Example: [4, 5, 2, 25] -> [5, 25, 25, -1]
     * Time: O(n), Space: O(n)
     */
    public static int[] nearestGreaterToRight(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> st = new Stack<>(); // it holds indices

        // traverse left to right, but we pop while current > stack top
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }
        return res;
    }

    /**
     * For each element, finds the nearest greater element to its left.
     * Example: [4, 5, 2, 25] -> [-1, -1, 5, -1]
     * Time: O(n), Space: O(n)
     */
    public static int[] nearestGreaterToLeft(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> st = new Stack<>(); 
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            if (!st.isEmpty()) res[i] = arr[st.peek()];
            st.push(i);
        }
        return res;
    }

    /**
     * For each element, finds the nearest smaller element to its right.
     * Example: [4, 5, 2, 25] -> [2, 2, -1, -1]
     * Time: O(n), Space: O(n)
     */
    public static int[] nearestSmallerToRight(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> st = new Stack<>(); 
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }
        return res;
    }

    /**
     * For each element, finds the nearest smaller element to its left.
     * Example: [4, 5, 2, 25] -> [-1, 4, -1, 2]
     * Time: O(n), Space: O(n)
     */
    public static int[] nearestSmallerToLeft(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> st = new Stack<>(); // holds indices
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            if (!st.isEmpty()) res[i] = arr[st.peek()];
            st.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 25, 1, 7};
        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("Nearest Greater to Right: " + Arrays.toString(nearestGreaterToRight(arr)));
        System.out.println("Nearest Greater to Left:  " + Arrays.toString(nearestGreaterToLeft(arr)));
        System.out.println("Nearest Smaller to Right: " + Arrays.toString(nearestSmallerToRight(arr)));
        System.out.println("Nearest Smaller to Left:  " + Arrays.toString(nearestSmallerToLeft(arr)));
    }
}
